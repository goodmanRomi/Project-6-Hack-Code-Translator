import java.util.*;

public class SymbolTable {

    private Map<String, Integer> symbols;

    //constructor
    //A label can be defined only once and can be used anywhere in the assembly program, even before the line in which it is defined.
    public SymbolTable(){
        symbols = new HashMap<>(Map.ofEntries(
            Map.entry("SP", 0),
            Map.entry("LCL", 1),
            Map.entry("ARG", 2),
            Map.entry("R0", 0), 
            Map.entry("R1", 1), 
            Map.entry("R2", 2), 
            Map.entry("R3", 3), 
            Map.entry("R4", 4), 
            Map.entry("R5", 5), 
            Map.entry("R6", 6), 
            Map.entry("R7", 7), 
            Map.entry("R8", 8), 
            Map.entry("R9", 9), 
            Map.entry("R10", 10), 
            Map.entry("R11", 11), 
            Map.entry("R12", 12), 
            Map.entry("R13", 13), 
            Map.entry("R14", 14), 
            Map.entry("R15", 15), 
            Map.entry("THIS", 3), 
            Map.entry("THAT", 4), 
            Map.entry("KBD", 24576), 
            Map.entry("SCREEN", 16384)
        ));
    }
    

    //adds the pair (symbol, adress - to the table)
    public void addEntry(String symbol, int Adress){
        symbols.put(symbol, Adress);
    }

    //does the symbol table cotain the given vaule? 
    public boolean contains(String symbol) {
        return symbols.containsKey(symbol);
    }

    //returns the adress associated with the symbol 
    public int getAdress(String symbol){
        return symbols.get(symbol);
    }

}
