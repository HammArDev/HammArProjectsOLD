public class Item {
    private int itemNum;
    private String shrnForm;
    private String fullName;
    private String description;
    private double price;
    private boolean Veggie;
    private int quantity;
    //Constructors
    public Item(){
        itemNum = -1;
        shrnForm = "";
        fullName = "";
        price = -1;
        quantity = 0;
        Veggie = false;
    }
    //Everything is specified
    public Item(int itemNum, String full, double price, String desc, boolean V, String shrn){
        this.itemNum = itemNum;
        this.fullName = full;
        this.description = desc;
        this.price = price;
        this.Veggie = V;
        shrnForm = shrn;
        quantity = 0;
    }
    //No shortened form specified--we generate it
    public Item(int itemNum, String full, double price, String desc, boolean V){
        this(itemNum, full, price, desc, V, "");
        // TODO Run algorithm to generate shortened form
    }
    public Item(int itemNum, String full, double price, String desc){
        this(itemNum, full, price);
        description = desc;
        // TODO Run algorithm to generate shortened form
    }
    //Bare minimum specified
    public Item(int itemNum, String full, double price){
        this.itemNum = itemNum;
        fullName = full;
        this.price = price;
    }
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int j){
        quantity = j;
    }
    public int getItemNum(){
        return itemNum;
    }
    public void setItemNum(int itemNum){
        this.itemNum = itemNum;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String d){
        description = d;
    }
    public double getPrice(){
        return price;
    }
    @Override
    public Item clone(){
        Item newOne = new Item(itemNum, fullName, price, description, Veggie, shrnForm);
        return newOne;
    }
    //TODO(future) Make algorithm to generate a shortened form
    @Override
    public String toString(){
        if(shrnForm==null){
            return String.format("%-25sx%-7d%10.2f", fullName, quantity, price*quantity);
        }else{
        return String.format("%-25sx%-7d%10.2f", shrnForm, quantity, price*quantity);
        }
    }
}
