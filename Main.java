import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java Main <file.asm>");
            return;
        }

        String inputFile = args[0];
        Assembler assembler = new Assembler(inputFile);
        assembler.assemble();
    }
}
