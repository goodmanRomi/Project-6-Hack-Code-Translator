import java.io.*;

public class Assembler {
    public static void main(String[] args) throws IOException {
        String inputFilename = args[0];
        String outputFilename = inputFilename.replace(".asm", ".hack");
        
        Parser parser = new Parser(inputFilename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
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
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        }
    }





