import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.HashMap;
public class Order {
    private double subtotal;
    private final double SALESTAX = 0.0725;
    private double total;
    //This stores the items for an order object
    //Store Items in an ArrayList, and have multiples right next to each other
    //Make a method to determine the quantity of each item
    //Or try using a map
    private ArrayList<Item> items = new ArrayList<>();
    //This stores all the possible items

    //public static ArrayList<Item> allItems = new ArrayList<>();
    private static HashMap<Integer, Item> allItems = new HashMap<>();
    //Created this method with an Item object
    //as the parameter, because, I can create a new
    //Item object based on the cashier's inputs
    //Also, it'll be easier to be specific with the items
    //Cashier will enter the intial quantity, and can modify the item's
    //Quantity, add-ons,
    public static void setItems(HashMap<Integer, Item> itemList){
        allItems = itemList;
    }
    public static Item getFromAllItems(int key){
        return allItems.get(key);
    }
    //TODO Make a method that checkpoints
    public void addItem(int itemNum){
        addItem(itemNum, 1);
    }
    public void addItem(int itemNum, int quantity){
        if(quantity<=0) {
            System.out.println("INVALID QUANTITY, PLEASE RETRY");
            return;
        }else{
            Item original = allItems.get(itemNum);
            if(original==null) {
                System.out.println("INVAlID ITEM NUMBER, PLEASE TRY AGAIN");
                return;
            }
            Item copy = original.clone();
            copy.setQuantity(quantity);
            items.add(copy);
            subtotal+=copy.getPrice()*copy.getQuantity();
            total = subtotal*(1+SALESTAX);
            /*for(int i=0; i<allItems.size(); ++i){
                if(allItems.get(i).getItemNum()==itemNum){
                    //Make a copy and modify that
                    Item original = allItems.get(i);
                    Item copy = original.clone();
                    copy.setQuantity(quantity);
                    items.add(copy);
                    subtotal+=copy.getPrice()*copy.getQuantity();
                    total = subtotal*(1+SALESTAX);
                    return;
                }
            }*/
        }
    }
    public void addItem(Item item){
        items.add(item);
        subtotal+=item.getPrice()*item.getQuantity();
        total = subtotal*(1+SALESTAX);
    }
    //Created this method so that other classes can modify the item
    public Item getItem(int index){
        return items.get(index);
    }
    public void removeItem(int index){
        Item toBeRemoved = items.get(index);
        subtotal-=toBeRemoved.getQuantity()*toBeRemoved.getPrice();
        total = subtotal*(1+SALESTAX);
        items.remove(index);
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        for(Item i: items) {
            output.append(i.toString());
            output.append("\n");
        }
        for(int i=0; i<43; ++i){
            output.append('-');
        }
        output.append("\n");
        output.append(String.format("\nSubtotal:%34.2f\nTotal:%37.2f", subtotal, total));
        return output.toString();
    }

}
