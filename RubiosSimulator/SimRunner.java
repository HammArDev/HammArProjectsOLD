import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;
//TODO--add watchers to notify Register of changes
public class SimRunner {
    public static void main(String[] args) throws IOException, InstantiationException{
        Customer c1 = new Customer(131233177);
        System.out.println("Welcome to Rubio's Simulator! Please enter the name of your file specifying items." +
                "\nYour file must be in the format: 'itemNum,full name,price,*description*,*vegetarian status*,*shortened form*'"+
                "\neach parameter is seperated by '--', and none of the Strings can have '--' in them. The parameters " +
                "\nmarked with '*' are optional");
        Order.setItems(readItems("itemsList.txt"));
        System.out.println("The first items in our list of all items is:");
        System.out.println(Order.getFromAllItems(0));
        customerTest("orderTestSpace.txt");
        //Can use keySet() to get the keys of a HashMap
        registerTest("orderTestSpace.txt");
    }
    //TODONE Create a function to read and generate Items from a file
    //TODONE Ensure that all item numbers are unique
    //Try storing them in a hashmap
    //This method supports the creation of items using 3, 4, 5, or 6 parameters
    static HashMap<Integer, Item> readItems(String fileName) throws IOException{
        HashMap<Integer,Item> items = new HashMap<>();

        FileInputStream fileInput;
        Scanner fileStream;
        Scanner iss;
        //The surveyor is used to count how many things are specified
        //Bas
        Scanner surveyor;
        String line;
        //Use line count to help user pinpoint mistake
        //Exit code afterwards, don't want to recover completely from Scanner error
        int lineCount = 1;

        int paramCount = 0;
        try {
            fileInput = new FileInputStream(fileName);
            fileStream = new Scanner(fileInput);
            //
            while(fileStream.hasNextLine()){
                line = fileStream.nextLine();
                iss = new Scanner(line);
                iss.useDelimiter("--");
                surveyor = new Scanner(line);
                surveyor.useDelimiter("--");
                paramCount = 0;
                //Counts the number of parameters
                while(surveyor.hasNext()){
                    //Tests the tokens
                    //System.out.println(surveyor.next());
                    surveyor.next();
                    ++paramCount;
                }
                int itemNum = iss.nextInt();
                //check for unique item number
                if(items.containsKey(itemNum)){
                    throw new InputMismatchException();
                }
                String full = iss.next();
                double price = iss.nextDouble();
                if(paramCount == 3){
                    items.put(itemNum, new Item(itemNum, full, price));
                    ++lineCount;
                    continue;
                }
                String desc = iss.next();
                if(paramCount == 4){
                    items.put(itemNum, new Item(itemNum, full, price, desc));
                    ++lineCount;
                    continue;
                }
                boolean veggie =iss.nextBoolean();
                if(paramCount == 5){
                    items.put(itemNum, new Item(itemNum, full, price, desc, veggie));
                    ++lineCount;
                    continue;
                }
                String shrnForm = iss.next();
                items.put(itemNum, new Item(itemNum, full, price, desc, veggie, shrnForm));
                ++lineCount;
            }
            System.out.println("Sucessfully read all items :)");
            return items;
        }catch(InputMismatchException e){
            System.out.println("There was an error at " + lineCount);
            throw e;
        }
    }

    //TODONE replace all instances of writer  with writer
    static void orderTest(String fileName) throws IOException {
        //Output to a file
        PrintStream outputStream = new PrintStream(fileName);
        PrintWriter writer = new PrintWriter(outputStream);
        Order order1 = new Order();
        //Testing the order object's methods
        order1.addItem(198);
        order1.addItem(203);
        //Should output an error message
        order1.addItem(304);
        writer.println(order1);
        order1.removeItem(0);
        writer.println("After removing the first item:");
        writer.println(order1);
        writer.println("Adding 3 chopped salads:");
        order1.addItem(199, 3);
        writer.println(order1);
        //Testing out the last addItem() method
        Item secretItem = new Item(101, "Quesadilla Especial", 10.28);
        secretItem.setQuantity(1);
        order1.addItem(secretItem);
        writer.println("Testing negative quantities:");
        order1.addItem(501, -1);
        writer.println(order1);
        order1.addItem(501, 1);
        writer.println(order1);
        writer.println(order1);
        writer.println("Testing the getItem() method--expected to return Quesadilla Especial");
        writer.println(order1.getItem(2));
        writer.close();
        outputStream.close();
    }
    private static void customerTest(String fileName) throws IOException, InstantiationException{
        Customer c1 = new Customer(103129102);
        Order o1 = new Order();
        o1.addItem(501, 2);
        o1.addItem(197);
        o1.addItem(204);
        Order o2 = new Order();
        o2.addItem(201);
        o2.addItem(202);
        PrintStream outputStream = new PrintStream(fileName);
        PrintWriter writ = new PrintWriter(outputStream);
        //Testing the Customer methods
        c1.setGuestNumber(1);
        System.out.println(c1);
        c1.setRedID(103288710);
        c1.setName("Bob the Builder");
        System.out.printf("%s-%d has a guest number of %d with a current order of %s and a previous order of %s\n", c1.getName(), c1.getRedID(), c1.getGuestNumber(), c1.getOrder(), c1.getPrevOrder());
        c1.setcOrder(o1);
        c1.setPrevOrder(o2);
        System.out.println(c1);
        c1.setcOrder(c1.getPrevOrder());
        System.out.println("With updated ");
        System.out.println(c1);
        //TODO Test out Register accessCustomer() method
        //TODO See if servingCustomer is updated
    }
    private static void registerTest(String fileName) throws IOException{
        PrintStream outputStream = new PrintStream(fileName);
        PrintWriter writ = new PrintWriter(outputStream);
        Register Gladys = new Register();
        writ.println(Gladys.logIn("Gladys"));
        writ.flush();
        Customer c1 = new Customer(13012020);
        Customer c2 = new Customer(39029201);
        Customer c3 = new Customer(50219201);
        Gladys.accessCustomer(13012020);
        Gladys.
    }
    //TODO Create a function to read and generate Customers(or a list of RED ID's) from a file
}
