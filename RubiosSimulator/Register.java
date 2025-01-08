import java.util.ArrayList;
import java.util.Random;

//The register class manages customers
//It gives them a guest Number, adds things to their order
//And has a toString() method that acts as their receipt.
//Idea: run stats with the system.
public class Register {
    //The register has a bayNumber for itself
    private short bayNumber;
    //It'll also have a cashier that'll change from shift-to-shift
    //(future)--make a password system for logging in
    private String cashier;
    private ArrayList<Customer> customers;
    private Customer servingCustomer;
    private Item currItem;

    //This list stores all the Guest Numbers so that we can properly generate new ones
    private static Integer[] GNList = new Integer[100];
    private int totalGuests;
    private int guestsServed;

    public Register() {
        //When we create a new register object, we assign it to a random bay number from 100 to 999
        //This is so we can hold people accountable
        Random rndm = new Random();
        bayNumber = (short) (rndm.nextInt(900) + 100);
        customers = new ArrayList<>();
    }

    //This method changes the servingCustomer
    //If the customer is already in the ArrayList, we'll use them
    //Otherwise, we'll create a new Customer, add them to the list,
    //And assign servingCustomer to it.
    public void accessCustomer(int ID) {
        //search for Customer in customers. If found, set servingCustomer to that Customer
        //Else, create a new customer, add them to customers, and assign servingCustomer to it
        Customer temp = Customer.allCustomers.get(ID);
        if (temp != null) {
            servingCustomer = temp;
            customers.add(servingCustomer);
            generateGN();
            return;
        }
        try {
            Customer c = new Customer(ID);
            customers.add(c);
            servingCustomer = c;
            generateGN();
        }catch(InstantiationException e){
            System.out.println(e.getMessage());
            }
    }

    //This method is for logging an employee in
    public String logIn(String employee) {
        cashier = employee;
        return "Welcome " + employee + " :)";
    }

    //TODO build this function
    //(future) Can overload to have String param, or Order param
    //This method is for adding things to the customer's order
    public void addToOrder(int itemNum, int quantity) {
        Item temp = Order.getFromAllItems(itemNum);
        if(temp==null){
            System.out.println("Invalid item, please try again");
        }else{
            currItem = temp.clone();
            currItem.setQuantity(quantity);
            servingCustomer.getOrder().addItem(currItem);
        }
    }
    public void serveCustomer(){
        servingCustomer.setGuestNumber(-1);
        servingCustomer.setPrevOrder(servingCustomer.getOrder());
        servingCustomer.setcOrder(null);
        int times = servingCustomer.getTimesVisitedThisWeek();
        servingCustomer.setTimesVisitedThisWeek(++times);
        customers.remove(servingCustomer);
        servingCustomer = null;
    }
    public void newWeek(){
        for(int i=0; i<Customer.allCustomers.size(); ++i){
            Customer.allCustomers.get(i).setTimesVisitedThisWeek(0);
        }
    }
    //TODO(future) Handle case where there's already 100 guests in GNList
    //TODO(future) Handle case where servingCustomer is null
    private void generateGN() {
        Random rndm = new Random();
        int potentialGN = rndm.nextInt(100) + 1;
        boolean unique = false;
        while (contains(GNList, potentialGN)) {
            potentialGN = rndm.nextInt(100) + 1;
        }
        servingCustomer.setGuestNumber(potentialGN);
    }
    //Helper method for generateGN
    //Like .contains() for an ArrayList
    private<E extends Comparable<E>> boolean contains(E[] arr, E target){
        for(E element: arr){
            if(element.compareTo(target)==0)
                return true;
        }
        return false;
    }
}
