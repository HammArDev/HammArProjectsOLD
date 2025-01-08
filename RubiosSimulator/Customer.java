import java.util.HashMap;
public class Customer {
    private int redID;
    private String name;
    private int guestNumber;
    private Order cOrder;
    //Stores their previous order in case their order was reset
    private Order prevOrder;
    private int timesVisitedThisWeek;
    protected static HashMap<Integer, Customer> allCustomers = new HashMap<>();
    public Customer(int redID) throws InstantiationException{
        this.redID = redID;
        guestNumber = -1;
        cOrder = null;
        prevOrder = null;
        timesVisitedThisWeek = 0;
        name = null;
        if(allCustomers.containsKey(redID)){
            throw new InstantiationException("Adding a repetitive customer");
        }
        allCustomers.put(redID, this);
    }
    //Setters and getters
    public void setRedID(int redID){
        if(!allCustomers.containsKey(redID)){
            this.redID = redID;
        }else{
            System.out.println("Adding a repetitive customer, please try again");
        }
    }
    public int getRedID(){
        return redID;
    }
    public void setGuestNumber(int GN){
        guestNumber = GN;
    }
    public int getGuestNumber(){
        return guestNumber;
    }
    public Order getOrder(){
        return cOrder;
    }
    public void setcOrder(Order o){
        cOrder = o;
    }
    public Order getPrevOrder(){
        return prevOrder;
    }
    public void setPrevOrder(Order o){
        prevOrder = o;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setTimesVisitedThisWeek(int times){
        timesVisitedThisWeek = times;
    }
    public int getTimesVisitedThisWeek(){
        return timesVisitedThisWeek;
    }
    @Override
    public String toString(){
        return String.format("RED ID: %10d\n"
                + "GN: %14d\n" +
                "Order: ", redID, guestNumber)+ cOrder;
    }
    //TODO Add checkpoints to setters and getters to ensure a unique REDID
}
