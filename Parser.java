//The Parser class reads the input .asm file,
//removes comments/whitespace, and provides access to individual instructions.

import java.io.*;
import java.util.regex.*; //package that represents a compiled regular expression. It provides methods for matching and manipulating text based on patterns.

public class Parser {
    public static final int A_Command = 0;
    public static final int C_Command = 1;
    public static final int L_Command = 2;

    //private List<String> lines; //...
    
    private String currentCommand; //current command being proccessed 
    private static final Pattern commentPattern = Pattern.compile("//.*$");//code defines a pattern that will match any text starting with "//" and continuing to the end of the line, effectively capturing single-line comments.
    private BufferedReader reader; //reads the file line by line
    private SymbolTable symbolTable; //will check my command and put it in the symbol table if neccessary
    

    public Parser(String inputFile) throws IOException {
        //lines = Files.readAllLines(new File(inputFile).toPath());//code snippet opens the file specified by inputFile, reads all the lines from it, and stores each line as a separate string in the lines list.
        reader = new BufferedReader(new FileReader(inputFile));
        currentCommand = null;
    }

    public boolean hasMoreCommands() throws IOException {
        return reader.ready(); // Returns true if there are more commands to process
    }

    //assuming i have more to read, otherwise i wouldn't have called the function in the first place
    public void advance() throws IOException{
        currentCommand=null; //reset current command to null, to be prepared to read the next one if availbe
        String line = reader.readLine();//reads the next line directly         
        //removes comments and whitespaces 
        line = commentPattern.matcher(line).replaceAll(" ").trim();
        if (line.isEmpty()){
            advance(); //skip empty lines in a recursive call
        } else {
            currentCommand = line;
        }
    }

    public int commandType(){ //this is prefct
        if (currentCommand.startsWith("@")) {
            return A_Command;
        } else if (currentCommand.startsWith("(") && currentCommand.endsWith(")")) {
            return L_Command;
        } else {
            return C_Command;
        }
    }

    // Returns the symbol of the current A or L command
    public String symbol(){ 
        return currentCommand.replaceAll("^[@\\(]|\\)$", "");  // Remove '@', '(' and ')';
       }

    // Returns the dest mnemonic in the current C-command
    public String dest() {
        String dest=currentCommand.contains("=") ? currentCommand.split("=")[0] : ""; //condition ? value_if_true : value_if_false;
        return dest;
    }

    // Returns the comp mnemonic in the current C-command
    public String comp(){
        String comp=currentCommand;
        if (comp.contains("=")) comp = comp.split("=")[1]; //[1] - refers to the secound part after the = sign
        if (comp.contains(";")) comp = comp.split(";")[0]; //[0] - refers to the first part, before the ; sign
        return comp;
    }

    // Returns the jump mnemonic in the current C-command
    public String jump(){
       String jump = currentCommand.contains(";") ? currentCommand.split(";")[1]: "";
        System.out.println("jump mnemonic: "+ jump);//debugging line
        return jump;
    }
}

