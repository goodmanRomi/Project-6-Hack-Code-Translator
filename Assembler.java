import java.io.*;

public class Assembler {

    private String inputFile;
    private String outputFile;

    public Assembler(String inputFile){
        this.inputFile=inputFile;
        this.outputFile=inputFile.replace(".asm", ".hack");
        this.symbolTable = new SymbolTable();
    }

    public void assemble() throws IOException {
        firstPass();  // Build the symbol table
        secondPass(); // Translate instructions to binary
    }

        public void firstPass() throws IOException {
        Parser parser = new Parser(inputFile);
        int currentAdress=0;
            
        while (parser.hasMoreCommands()){
            parser.advance();
            int commandType = parser.commandType();

            if (commandType==Parser.A_Command || commandType==Parser.C_Command) {
                currentAdress++;
            }
            else if (commandType==Parser.L_Command) {
                symbolTable.addEntry(parser.symbol(), currentAdress);
            }
       
       
        Code code = new Code(); //dont use it
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        
            while (parser.hasMoreCommands()){
                parser.advance();
                String binaryCode=" ";

                switch (parser.commandType()) {
                    case Parser.A_Command:
                    int address=Integer.parseInt(parser.symbol());
                    binaryCode = "0" + String.format("%15s", Integer.toBinaryString(address)).replace(' ', '0');
                    break;

                    case Parser.C_Command:
                    String comp = Code.comp(parser.comp());
                    String dest = Code.dest(parser.dest());
                    String jump = Code.jump(parser.jump());
                    binaryCode = "111" + comp + dest + jump;
                    break;

                    case Parser.L_Command: 
                    // Ignore L_COMMAND for now
                        break;    }
                
                    writer.write(binaryCode);
                    writer.newLine(); // Add a newline after each command
                }

                writer.close();
        }
    }





