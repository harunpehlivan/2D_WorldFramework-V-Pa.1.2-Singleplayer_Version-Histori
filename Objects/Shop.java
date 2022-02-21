import java.util.ArrayList;

class Shop{
    private String _shopName = "Shop name has not been declared yet";
    ArrayList<Item> avaibleItems = new ArrayList<Item>();
    ArrayList<Double> avaiblePrices = new ArrayList<Double>();
    ArrayList<Integer> avaibleQuantities = new ArrayList<Integer>();

    public Shop(String shopName_){
        if(!shopName_.equals(""))
            _shopName = shopName_;
    }

//Setters
    public void removeAvaible(int location_){
        if(avaibleQuantities.get(location_) > 1){
            avaibleQuantities.set(location_,avaibleQuantities.get(location_)-1);
        }else{
            avaiblePrices.remove(location_);
            avaibleItems.remove(location_);
            avaibleQuantities.remove(location_);
        }
    }

    public void addAvaible(Item item_, double price_, int quantity_){    
        avaibleItems.add(item_);
        avaiblePrices.add(price_);
        avaibleQuantities.add(quantity_);
        // avaibleItems.add(new Item(look_,name_,color_,price_,description_,quantity_));
    }

//Getters
    public String getName(){
        return _shopName;
    }

    public ArrayList<Item> getItems(){return avaibleItems;}
    public ArrayList<Double> getPrices(){return avaiblePrices;}
    public ArrayList<Integer> getQuantities(){return avaibleQuantities;}
    
}