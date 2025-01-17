# **Project 6: Assembler**

This project involves building an assembler that translates Hack assembly language (`.asm` files) into Hack binary code (`.hack` files). The assembler is a critical component of the Nand2Tetris course, bridging the gap between low-level symbolic language and executable binary code.

---

## **Features**
### **Two-Pass Assembly Process**
1. **First Pass:** Builds a symbol table for labels.
2. **Second Pass:** Generates binary code for each instruction.

### **Input and Output**
- **Input:** `Prog.asm` (Hack assembly file)  
- **Output:** `Prog.hack` (Hack binary file)  

Existing output files are automatically overridden.

---

## **How to Use**
1. **Clone the Repository**  
   Clone the repository from the provided URL.

2. **Run the Assembler**  
   Translate an `.asm` file into a `.hack` file by running the assembler.

3. **Test the Output**  
   Use the [CPU Emulator](https://nand2tetris.github.io/web-ide/cpu) to load the generated `.hack` file and verify its execution.

