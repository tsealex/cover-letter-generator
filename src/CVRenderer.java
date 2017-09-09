import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Config {
	// placeholder = {? ?}
	static String placeholder = "\\{\\? \\?\\}";
	// where the text segments are located (input files)
	static String segmentDir = "texts";
	
}

class CVRenderer {
	// Syntax: {? filename ?}
	private Pattern pattern;
	private String content;
	
	public CVRenderer(String content) {
		pattern = Pattern.compile(Config.placeholder.replace(" ", 
				"(?<filename>[\\w. \\-]+)"));
		this.content = content;
	}
	
	public String parse() {
		LinkedList<Token> tokens = new LinkedList<Token>();
		String[] strings = pattern.split(content);
		
		int i = 0;
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			if (i < strings.length)
				tokens.add(Token.createStringToken(strings[i++]));
			tokens.add(Token.createFileToken(matcher.group("filename").trim()));
		}
		if (i < strings.length) 
			tokens.add(Token.createStringToken(strings[i++]));
		
		StringBuilder output = new StringBuilder();
		for (Token token : tokens)
			output.append(token.getValue());
		return output.toString();
	}
	
}

class Token {
	private String value;
	private boolean isFile = false;
	
	private Token(boolean isFile, String value) {
		this.value = value;
		this.isFile = isFile;
	}
	
	public String getValue() {
		if (isFile) {
			String filename = value + ".txt";
			Path path = Paths.get(Config.segmentDir, value + ".txt");
			if (!Files.exists(path)) {
				System.err.printf("File %s does not exist in %s.\n", 
						filename, Config.segmentDir);
				System.exit(1);
			}
			String temp = "";
			try {
				temp = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
			} catch (IOException e) {
				System.err.printf("Unexpected error when reading file %s.\n", 
						filename);
				System.exit(1);
			}
			value = temp;
			isFile = false;
		}
		// System.out.println(value);
		return value;
	}
	
	static public Token createFileToken(String filename) {
		return new Token(true, filename);
	}
	
	static public Token createStringToken(String value) {
		return new Token(false, value);
	}
	
}