import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import junit.framework.*;


// dont forget to add VM argument "-ea" in configuration
public class TestCVGenerator extends TestCase {

	
	// for Eclipse, right click on this class on package explorer, then select
	// "Debug As" > "JUnit Test"
	public void setUp() throws FileNotFoundException {
		Config.segmentDir = "__tests";
		new File(Config.segmentDir).mkdirs();
		
		PrintWriter writer = new PrintWriter("__test_input.txt");
		writer.print("Tests are tests. {? good ?} {? bad ?}");
		writer.close();
		
		writer = new PrintWriter("__tests/good.txt");
		writer.print("I am good.");
		writer.close();
		
		writer = new PrintWriter("__tests/bad.txt");
		writer.print("I am bad.");
		writer.close();
		
	}
	
	public void test() throws FileNotFoundException {
		
		CVGenerator.main(new String[] {
			"__test_input.txt",
			"__test_output.txt",
			"good",
			"bad"
		});
		Scanner sc = new Scanner(new File("__test_output.txt"));
		String expStr = "Tests are tests. I am good. I am bad. I am good. I am bad.";
		String genStr = sc.nextLine();
		sc.close();
		assertTrue(genStr.equals(expStr));
		
	}
	
	public void tearDown() {
		new File("__tests/bad.txt").delete();
		new File("__tests/good.txt").delete();
		new File("__test_input.txt").delete();
		new File("__test_output.txt").delete();
		
		new File("__tests/bad.txt").delete();
		new File("__tests").delete();
	}

}
