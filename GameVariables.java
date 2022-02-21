class GameVariables{
// FINALS
    public final static int[] BOARD_DIMENTIONS_XY = {};
    public final static int THIRD_DIMENTION_SIZE = 5;
    public final int HELD_LEVELS = 10;
    
// CHANGEABLES
    private double points = 0.0;
    // private double points = 10_000;
    private int numberOfDeaths = 0;


    private Item[] inventory = new Item[50];
    private short hotbarSelected = 0;




    public GameVariables(){
        for(short i=0; i < inventory.length; i++){
            inventory[i] = new Item(' ');//,"\0",'\0',"\0");
            // look_, String name_, char color_, String description_, int quantity_
        }
    }
//     ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
// //   40  41  42  43  44  45  46  47  48  49
//     ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
// //   30  31  32  33  34  35  36  37  38  39
//     ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
// //   20  21  22  23  24  25  26  27  28  29
//     ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
// //   10  11  12  13  14  15  16  17  18  19
//     ' ',' ',' ',' ',' ',' ',' ',' ',' ',' }
// //   0   1   2   3   4   5   6   7   8   9
// // };

//INVENTORY
    public boolean addToInventory(Item item_){
        for(short i=0;i<inventory.length;i++){
            if(inventory[i].getLook() == ' '){
                inventory[i]=item_;
                return true;
            }}return false;}

    //Hotbar
    public void setHotbarSelected(short position_){hotbarSelected = position_;}
    public short getHotbarSelected(){return hotbarSelected;}
    //Hotbar

    public Item getSlot(short position_){return inventory[position_];}
    public void setSlot(short position_, Item item_){inventory[position_] = item_;}

//POINTS
    public void setPoints(double points_){points = points_;}
    public void addPoints(double points_){points += points_;}
    public double getPoints(){return points;}

    String currencey = Rules.getCurrencey();
    public String getCurrencey(){return currencey;}

//DEATHS
    public int getDeaths(){return numberOfDeaths;}
    public void addDeath(){numberOfDeaths++;}
    
}