import java.io.*;

public class Parser {
    public static final int A_Command = 0;
    public static final int C_Command = 1;
    public static final int L_Command = 2;

    private BufferedReader reader;
    private String currentLine;

    public Parser(String inputFile) throws IOException {
        reader = new BufferedReader(new FileReader(inputFile));
        currentLine = " ";
    }

    public boolean hasMoreCommands() throws IOException {
        return reader.ready();;
    }

    public void advance() throws IOException{
        String line;
        line = reader.readLine();
        while ((line = reader.readLine()) != null) {
            // Trim leading and trailing whitespace
            line = line.trim(); 

            // Skip empty lines and comment lines
            if (line.isEmpty() || line.startsWith("#")) { 
                continue; 
            }
        }
    }
    
    public int commandType(){
        if (currentLine.startsWith("@")) {
            return A_Command;
        } else if (currentLine.startsWith("(") && currentLine.endsWith(")")) {
            return L_Command;
        } else {
            return C_Command;
        }
    }

    public String symbol(){
        if (commandType() == A_Command) {
            return currentLine.substring(1);
        } else if (commandType() == L_Command) {
            return currentLine.substring(1, currentLine.length() - 1);
        }
        return null;
    }

    public String dest(){
        if (commandType() == C_Command) {
            int eqIndex = currentLine.indexOf('=');
            return (eqIndex != -1) ? currentLine.substring(0, eqIndex).trim() : "";
        }
        return null;
    }

    public String comp(){
        if (commandType() == C_Command) {
            int eqIndex = currentLine.indexOf('=');
            int scIndex = currentLine.indexOf(';');
            if (scIndex != -1) {
                return currentLine.substring(eqIndex + 1, scIndex).trim();
            }
            return currentLine.substring(eqIndex + 1).trim();
        }
        return null;
    }

    public String jump(){
        if (commandType() == C_Command) {
            int scIndex = currentLine.indexOf(';');
            if (scIndex != -1) {
                return currentLine.substring(scIndex + 1).trim();
            }
        }
        return "";
    }
}

