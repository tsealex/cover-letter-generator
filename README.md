# cv-generator
generates a CV from a template file.
### Usage
```
javac CVGenerator.java CVRenderer.java
java CVGenerator [input file] [output file] [[segements] ...]
```
Input file is the CV template. Both the input and output files should be a .txt file. Each segment is the name of a .txt file whose content will be used to fill the placeholder spots. All segment files should be stored in the same folder (by default, a folder named "texts"). \
By default, the placeholder syntax is {? \<segment name\> ?}
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
