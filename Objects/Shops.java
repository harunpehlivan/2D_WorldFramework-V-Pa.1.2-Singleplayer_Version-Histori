import java.util.ArrayList;

class Shops{// extends WorldBoard{
    ArrayList<Shop> _shopsArray = new ArrayList<Shop>();
    // GameVariables passClass;

    public Shops(){
        _shopsArray.add(new Shop("Shop #0"));
        _shopsArray.add(new Shop("Shop Alpha"));
            _shopsArray.get(1).addAvaible(new Item('T',"Pickaxe",'y',"A pickaxe"),1.2,1);
            _shopsArray.get(1).addAvaible(new Item('_',"Line",'y',"An underscore"),1.5,10);
            _shopsArray.get(1).addAvaible(new Item('a',"A thing",'y',"I'm not realy shure what that thing is, but I want that thing!"),100,5);
    }

    public void goShopping(int shopNumber_, GameVariables passClass_){
        UI.clear();
        printShop(shopNumber_, passClass_);//Move to UI
        UI.clear();
    }

    private void printShop(int shopNumber_, GameVariables passClass_){
        Shop shop = _shopsArray.get(shopNumber_);
        ArrayList<Item> itemsA = shop.getItems();
        ArrayList<Double> avaiblePrices = shop.getPrices();
        ArrayList<Integer> avaibleQuantities = shop.getQuantities();
        
        int location = 0;

        System.out.println(
        " ========================="+shop.getName()+"========================= \n"+
        "\nTo quit press q, to select an item use the WASD keys.");//TODO: Change to a do

        char input = '\0';
        do{
            if(input=='w' && location != 0){location--;}
            if(input=='s' && (location <= 10 && location != itemsA.size()-1)){location++;}
            UI.clear();
            System.out.println(
        " ========================="+shop.getName()+"========================= \n"+
            "\nTo quit press q or a, to select and purchase an item, use the WSD keys.");
            System.out.println("\nYour current balance is "+passClass_.getPoints()+passClass_.getCurrencey()+"\n");
            for(short i = 0; i < itemsA.size() && i < 10; i++){
                System.out.print("\t["+(i==location ? "*":" ")+"] '"+
                UI.getFC(itemsA.get(i).getColor())+itemsA.get(i).getLook()
                +Rules._CLEAR+"' (x"+avaibleQuantities.get(i)+") : "+
                itemsA.get(i).getName()+" - \"");
                if(itemsA.get(i).getDescription().length() > 27){
                    System.out.println(itemsA.get(i).getDescription().substring(0,27)+"...\"");
                }else{
                    System.out.println(itemsA.get(i).getDescription()+"\"");
                }
            }
            if(location < 0){location = 0;}
            if(itemsA.size() < 1){
                System.out.println("[Something here] nothing left :(");
            }else{
                System.out.println("\nItem: "+itemsA.get(location).getName());
                System.out.println("Cost: "+avaiblePrices.get(location)+passClass_.getCurrencey());
                System.out.println("Object Deskcription: "+itemsA.get(location).getDescription()+"\n\n\t\t[Press "+Rules._UNDERLINE+"E] to purchase for "+avaiblePrices.get(location)+passClass_.getCurrencey());
                if(input == 'e'){
                    if(avaiblePrices.get(location) <= passClass_.getPoints()){
                        if(passClass_.addToInventory(itemsA.get(location))){
                            passClass_.addPoints( - avaiblePrices.get(location));
                            avaibleQuantities.set(location, avaibleQuantities.get(location) - 1);
                            shop.removeAvaible(location);
                            if(location >= itemsA.size()){location = itemsA.size()-1;}
                            System.out.println("\t\t[S] Purchased! Your new balance is "+passClass_.getPoints()+passClass_.getCurrencey());
                            System.out.println("[S] Item added to inventory");
                        }else{
                            System.out.println("[S] !!! Item not added to inventory- out of space! Did not purchase");
                        }
                    }else{
                        System.out.println("\n[!] Unable to purchase (insufficient funds). You need "+(avaiblePrices.get(location) - passClass_.getPoints())+passClass_.getCurrencey()+" more to purchase.");
                    }
                }
            }
        }while((input = UI.getTypedChr()) != 'q' && input != 'a');//!Character.isDigit(
    }

}