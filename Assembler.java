import java.io.*;

public class Assembler {

    private String inputFile;
    private String outputFile;
    private SymbolTable symbolTable;
    private int symbolAddress = 16; // Starting address for variables


    public Assembler(String inputFile){
        this.inputFile=inputFile;
        this.outputFile=inputFile.replace(".asm", ".hack");
        this.symbolTable = new SymbolTable();
    }

    public void assemble() throws IOException {
        firstPass();  // Build the symbol table
        secondPass(); // Translate instructions to binary
    }
    
    // First pass: Add labels (L_COMMAND) to the symbol table
    private void firstPass() throws IOException {
        Parser parser = new Parser(inputFile);
        int currentAddress=0;
            
        while (parser.hasMoreCommands()){
            parser.advance();
            int commandType = parser.commandType();

            if (commandType==Parser.A_Command || commandType==Parser.C_Command) {
                currentAddress++;
            }
            else if (commandType==Parser.L_Command) {
                String label = parser.symbol();
                if (!symbolTable.contains(label)) {
                    symbolTable.addEntry(label, currentAddress);
                }
            }
        }
    }

        private int getAddress(String symbol) {
            if (symbol.matches("\\d+")) { // If the symbol is a numeric constant
                return Integer.parseInt(symbol);
            } else {
                if (!symbolTable.contains(symbol)) {
                    symbolTable.addEntry(symbol, symbolAddress);
                    symbolAddress++;
                }
                return symbolTable.getAdress(symbol);
            }
        }

        // Second pass: Translate A_COMMAND and C_COMMAND to binary
       private void secondPass() throws IOException{
        Parser parser = new Parser(inputFile);
        Code code = new Code(); //dont use it
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        
            while (parser.hasMoreCommands()){
                parser.advance();
                String binaryCode=" ";

                switch (parser.commandType()) {
                    case Parser.A_Command://and tehcnialy also L instructions that i already put in the table before in the first pass
                    int address = getAddress(parser.symbol());
                    binaryCode = "0" + String.format("%15s", Integer.toBinaryString(address)).replace(' ', '0');
                    break;

                    case Parser.C_Command:
                    String comp = Code.comp(parser.comp());
                    String dest = Code.dest(parser.dest());
                    String jump = Code.jump(parser.jump());
                    binaryCode = "111" + comp + dest + jump;
                    break;
                
                    case Parser.L_Command:
                    continue;//handled in the first pass
                }
            
                    writer.write(binaryCode);
                    writer.newLine(); // Add a newline after each command
                
            }       writer.close();            
        
        }
    }






