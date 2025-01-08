import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;
public class Test {
    public static void main(String[] args) throws IOException{
        String tester = "197-Chipotle Taco Salad-Epic";
        Scanner scnr = new Scanner(tester);
        scnr.useDelimiter("-");
        while(scnr.hasNext()){
            System.out.println(scnr.next());
        }
        PrintStream outputStream = new PrintStream("orderTestSpace.txt");
        PrintWriter writer = new PrintWriter(outputStream);
        writer.print("Mustafa Rocks :)");
        writer.close();

    }
}
