import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CVGenerator {
	
	// Usage: CVGenerator <input file> <output file> <segements ...>
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length < 2) {
			System.err.println("Usage: java CVGenerator [input file] " + 
					"[output file] [[segements] ...]");
			System.exit(1);
		}
		
		StringBuilder input = new StringBuilder();
		File inputFile = new File(args[0]);
		if (inputFile.exists()) {
			Scanner scanner = new Scanner(inputFile);
			while (scanner.hasNextLine()) input.append(scanner.nextLine());
			scanner.close();
		} else {
			System.err.printf("Input file %s does not exist.\n", inputFile);
			System.exit(1);
		}
		
		for (int i = 2; i < args.length; i++) {
			input.append(" {? " + args[i] + " ?}");
		}
		PrintWriter writer = new PrintWriter(args[1]);
		writer.write((new CVRenderer(input.toString())).parse());
		writer.close();
		System.out.println("Complete.");
	}
	
}

