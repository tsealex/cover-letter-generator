# cover-letter-generator
generates a cover letter from a template file. (previously called CV generator, by mistakes)
### Usage
```
javac CVGenerator.java CVRenderer.java
java CVGenerator [input file] [output file] [[segements] ...]
```
Input file is the cover letter template. Both the input and output files should be a .txt file. Each segment is the name of a .txt file whose content will be used to fill the placeholder spots. All segment files should be stored in the same folder (by default, a folder named "texts"). The default placeholder syntax is __{? \<segment name\> ?}__. The segements included in the arguments when calling the program will be appended to the end of the output content.
### Examples
Let say, if your folder structure looks like this:
```
--- <your directory name>
 |- CVGenerator.java CVRenderer.java *.class
 |- input.txt (with content "Hello World! {? good ?} OK!")
 |- texts
    |- good.txt (with content "Nice.")
    |- bad.txt  (with content "Meh.")
```
Then, running
```
java CVGenerator input.txt output.txt bad
```
would produce a file called output.txt with content "Hello World! Nice. OK! Meh."
