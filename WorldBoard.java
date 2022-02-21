import java.util.ArrayList;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;   // Import the FileWriter class

class WorldBoard{
    public short _game;
    GameVariables _passClass;
    Shops _shops1;

//TEMPS
    String _lastAction = "NOT YET ASIGHNED";

    public final int worldMapHeight = 20+2;
    public final int worldMapWidth = 60+2;

    private int[] playerLocation = {3,3};
    private int[] lastPlayerLocation = {3,3};
    private int[] playerLocationNew=playerLocation.clone();//No longer local

//TODO: Make arraylists
    private char[][][][] _sharedMaps = new char[10][worldMapHeight][worldMapWidth][_passClass.THIRD_DIMENTION_SIZE];
    private char[][][][] _sharedMapOrignals = new char[10][worldMapHeight][worldMapWidth][_passClass.THIRD_DIMENTION_SIZE];


    private char playersLookChar = 'P';


//           0      1         2               3
// [X] [Y] [item, color, background, locked(U or L), specials(underlining), ]
    public char[][][] sharedMap = new char[worldMapHeight][worldMapWidth][_passClass.THIRD_DIMENTION_SIZE];
    public char[][][] sharedMapOrignal = new char[worldMapHeight][worldMapWidth][_passClass.THIRD_DIMENTION_SIZE];//TODO: Find a way to make final after asighment
    public String global = "_";

    //TODO: Make in a file so they can be saved and are in all one place
    // private char[] hotbar = {'1','2','3','4','5','6','7','8','9','0'};//new char[10]


    public WorldBoard(short game_, GameVariables passClass_, Shops shops1_, int startingX_, int startingY_){
        _game = game_;
        _passClass = passClass_;
        _shops1 = shops1_;

        playerLocationNew = new int[] {startingX_,startingY_};playerLocation=playerLocationNew.clone(); lastPlayerLocation = playerLocationNew.clone();
        // clearHotbar();//TODO: Change to keep it
        // Creation.initializeBoard(game_, sharedMap, sharedMapOrignal);
    }

    public int getHeight(){return worldMapHeight;}
    public int getWidth(){return worldMapWidth;}
    public short getGame(){return _game;}
    public char[][][] getBoard(){return sharedMap;}//TODO: KEEP?
    public char[][][] getSharedMap(){return sharedMap;}
    public char[][][] getSharedMapOringal(){return sharedMapOrignal;}
    public void setSharedMap(char[][][] sharedMap_){sharedMap = sharedMap_;}
    public void setSharedMapOringal(char[][][] sharedMapOrignal_){sharedMapOrignal = sharedMapOrignal_;}
    public char getPlayersLookChar(){return playersLookChar;}

    public char getPoint(int x_, int y_){return sharedMap[x_][y_][0];}
    public char getPointC(int x_, int y_){return sharedMap[x_][y_][1];}
    public char getPointCO(int x_, int y_){return sharedMapOrignal[x_][y_][1];}
    
    public char getPointO(int x_, int y_){return sharedMapOrignal[x_][y_][0];}
    public char getPointNewS(){return sharedMap[playerLocationNew[0]][playerLocationNew[1]][0];}
    public char getPointNewO(){return sharedMapOrignal[playerLocationNew[0]][playerLocationNew[1]][0];}

    public char getPointCurrentS(){return sharedMap[playerLocation[0]][playerLocation[1]][0];}
    public char getPointCurrentO(){return sharedMapOrignal[playerLocation[0]][playerLocation[1]][0];}

    public void setPoint(int x_,int y_,char c_){sharedMap[x_][y_][0]=c_;}
    public void setPointCurrentS(char c_){sharedMap[playerLocation[0]][playerLocation[1]][0]=c_;}
    public void setPointNewS(char c_){sharedMap[playerLocationNew[0]][playerLocationNew[1]][0] = c_;}
    public void setPointNewO(char c_){sharedMapOrignal[playerLocationNew[0]][playerLocationNew[1]][0] = c_;}


//COLORSETTING
    public void setColorNewS(char c_){sharedMap[playerLocationNew[0]][playerLocationNew[1]][1] = c_;}
    public void setColorNewO(char c_){sharedMapOrignal[playerLocationNew[0]][playerLocationNew[1]][1] = c_;}

    
    public void setColorCurrentS(char c_){sharedMap[playerLocation[0]][playerLocation[1]][1] = c_;}
    public void setColorCurrentO(char c_){sharedMapOrignal[playerLocation[0]][playerLocation[1]][1] = c_;}

    public char getColorCurrentS(){return sharedMap[playerLocation[0]][playerLocation[1]][1];}
    public char getColorCurrentO(){return sharedMapOrignal[playerLocation[0]][playerLocation[1]][1];}

    public char getColorNewS(){return sharedMap[playerLocationNew[0]][playerLocationNew[1]][1];}
    public char getColorNewO(){return sharedMapOrignal[playerLocationNew[0]][playerLocationNew[1]][1];}

//POINTLOCKING
    public void setLockChars(char c_, boolean state_){//1 for locked, 0 for unlocked
        if(state_)
            sharedMap[playerLocationNew[0]][playerLocationNew[1]][3]='L';//1;
        else
            sharedMap[playerLocationNew[0]][playerLocationNew[1]][3]='U';//0;
    }


    public void setLockNewS(boolean state_){//1 for locked, 0 for unlocked
        if(state_)
            sharedMap[playerLocationNew[0]][playerLocationNew[1]][3]='L';//1;
        else
            sharedMap[playerLocationNew[0]][playerLocationNew[1]][3]='U';//0;
    }

    public boolean getLockNewS(){
        char c = sharedMap[playerLocationNew[0]][playerLocationNew[1]][3];
        if(c == '\0'){
            // sharedMap[playerLocationNew[0]][playerLocationNew[1]][3]='T';
            return true;
        }else if(c == 'L')return true;//'1'
        else return false;
    }


    public int getPlayerX(){return playerLocation[0];}
    public int getPlayerY(){return playerLocation[1];}
    public void setPlayerX(int x_){playerLocation[0]=x_;}
    public void setPlayerY(int y_){playerLocation[0]=y_;}

    public String move(){
        // System.out.println("Moving...");
        String action;
        char c_ = UI.getTypedChr();//TODO: Move outside of meothod?
        UI.clear(); 
        // int moveing = -1;
        playerLocationNew=playerLocation.clone();//Local
        lastPlayerLocation = playerLocation.clone();//Not Local

        switch (c_) {
            case 'w': // up
            // moveing = 1;
                playerLocationNew[0] = playerLocation[0]-1;
                break;
            case 'a': // left
                playerLocationNew[1] = playerLocation[1]-1;
                break;
            case 'd': // right
                playerLocationNew[1] = playerLocation[1]+1;
                break;
            case 's': // down
                playerLocationNew[0] = playerLocation[0]+1;
                break;
            case 'q': // quit
            // default: System.out.println("!!!! ERROR IN MOVE !!!!!");//return String.valueOf(c_);
        }

        action = Rules.charToDo(_game,getPointNewS(),getPointNewO(),getLockNewS());
        _lastAction = action;

        if(action.equals("moves")){

        }else if(action.equals("dies")){
            UI.deathScreen();
            System.out.println("!!!YOU DIED!!!");
            System.out.println(" Points: "+_passClass.getPoints());
            playerLocation = new int[] {3,3};//TODO: DeathUpdate
            return "died";
        }else if(action.indexOf("points")==0){
            setPointNewS(getPointNewO());
            setColorNewS(getColorNewO());
            _passClass.addPoints(Double.parseDouble(action.replaceFirst("points","")));
        }else if(action.indexOf("barrier")==0){
            playerLocationNew = lastPlayerLocation.clone();

        }else if(action.indexOf("shop")==0){
            _shops1.goShopping(Character.getNumericValue(action.charAt(4)), _passClass);

        }else if(action.indexOf("portal")==0){
            

        // }else if(action.indexOf("reveals")==0){
        }else if(action.equals("reveals")){
            setPointNewS(getPointNewO());
            setColorNewS(getColorNewO());
        }else if(action.equals("pickup")){
            if(pickupItem(charToItem(getPointNewS()))){
                setPointNewS(getPointNewO());
            }
        }

        playerLocation = playerLocationNew.clone();//Set new location
        // setPoint(playerLocation[0],playerLocation[1],playersLookChar);

        // return new int[] {playerLocation[0],playerLocation[1]};

        debugDataBoard(action);
    //After move doings
        Creation.setLocksColor(sharedMap,true,'y');

        return c_+"";
    }

    public void debugDataBoard(String action_){
        // Debug data bar
        System.out.print("\nLocation: ("+playerLocation[0]+","+playerLocation[1]+")");
        System.out.print("  Action: '"+_lastAction+"'");
        System.out.print("  Currently on: '"+getPointCurrentS()+"'");
        System.out.print("  Points: '"+_passClass.getPoints()+_passClass.getCurrencey()+"\n");
        System.out.println("  Locked?: "+getLockNewS());
        System.out.println();
    }

//ITEMS
    public Item charToItem(char c_){
        return new Item(c_, "picked up item", 'W',"This item was picked up");
    }


    public boolean pickupItem(Item item_){//TODO: Move into Rules.java
        System.out.println("getPointNewS() = "+getPointNewS());
        System.out.println("item_ = "+item_);

        boolean flagPicked = false;

        if(item_.getLook() == '░'){
        }else if(item_.getLook()== '+'){
        }else{
            for(short i = 0; i < 10; i++){
                if(_passClass.getSlot(i).getLook() == ' ' && !getLockNewS()){
                    _passClass.setSlot(i,item_);
                    i = 15;
                    flagPicked = true;
                }
            }
            
        }

        return flagPicked;//Returns if the item was able to be picked up
    }

//#HOTBAR
    // public void selectHotbar(short slot_){//Must be from 0-10, 0 counts as 10, is the posistion on the bar- not starting at 0
    //     _passClass.setHotbarSelected(slot);
    // }

    // public char hotbarSelectedL(){return _passClass.getSlot(_passClass.getHotbarSelected()).getLook();}
    // public Item hotbarSelected(){return _passClass.getHotbarSelected();}//TODO: Remove


    public char placeFromHotbar(){
        char returnMe_ = _passClass.getSlot(_passClass.getHotbarSelected()).getLook();
        // if(returnMe_ != '\0'){//TODO: Keep?
        if(returnMe_ != ' '){//TODO: Keep?
            if(!getLockNewS()){
                setPointNewS(returnMe_);
                _passClass.setSlot(_passClass.getHotbarSelected(),new Item(' '));
                setColorNewS('W');
                setLockNewS(true);
            }else{
                returnMe_ = 'L';
            }
            //@@@
        }else{
            returnMe_ = getPointNewS();
            returnMe_ = 'S';
        }

        return returnMe_;// L for locked, S for sucuess.
    }
    
    public boolean mineToHotbar(){

        if(getLockNewS()){
            Item hotbarC = _passClass.getSlot(_passClass.getHotbarSelected());
            char brokenPair = Rules.canBreakPairs(_passClass.getSlot( _passClass.getHotbarSelected()).getLook(),getPointCurrentS());

            if(brokenPair != '!'){//Processing done in Rules.java
                setLockNewS(false);
                setPointNewS(brokenPair);
            }

            if(pickupItem(charToItem(getPointCurrentS()))){
                setPointCurrentS(getPointCurrentO());
                setColorCurrentS(getColorCurrentO());
                return true;
            }
            return true;
        }
        return false;
    }

    public void printHotbar(){
        System.out.println("              Hotbar: |1|2|3|4|5|6|7|8|9|0|");//TODO: Center the bar
        String tempForSpeed = ""/*new StringBuilder()*/;//TODO: Declare with length existing?
        String tempForSpeed2 = "";
        for(short i = 0; i < 10; i++){
            tempForSpeed+=(_passClass.getSlot(i).getLook()+"|");//TODO: Need to add color
            tempForSpeed2+=(" |");
            // tempForSpeed.append('|');
        }
        System.out.println("                      |"+tempForSpeed);
        tempForSpeed2 = tempForSpeed2.substring(0,(_passClass.getHotbarSelected())*2)+"▒"+tempForSpeed2.substring((_passClass.getHotbarSelected())*2+1);
        System.out.println("                      |"+tempForSpeed2);//▓▒░hotbarSelected
        System.out.println("\tCurrently Selected: "+_passClass.getSlot(_passClass.getHotbarSelected()).getName());//CurrentItem
    }

    public void clearHotbar(){
        for(short i = 0; i < 10; i++){
            _passClass.setSlot(i, new Item(' '));//' ';
        }
    }
//END OF HOTBAR



    public void printHelper(){
        System.out.println("\n "+Rules._YELLOW+"Orange"+Rules._CLEAR+" can be picked up, if a "+Rules._YELLOW+"▒"+Rules._CLEAR+" it will lock behind you.");

        System.out.println("\n Press Z or X to switch between diffrent planes,");
        System.out.println("\n ◊ are worth currencey, walk over them to gain them.");
        System.out.println("\n 'T' is a pickaxe, use it to mine diffrent things.");
        System.out.println("\n "+Rules._GREEN+"="+Rules._CLEAR+" Are for the shop, step on to enter.");

        // System.out.println(" "+Rules._GREEN+"Green"+Rules._CLEAR+" can be mined.");
    }

//MAPS
    public void switchMap(int placeMap_, int getMap_){
        for(int i=0; i < sharedMap.length; i++){
            for(int j=0; j < sharedMap[0].length; j++){
                for(int k=0; k < _passClass.THIRD_DIMENTION_SIZE; k++){
                    _sharedMaps[placeMap_][i][j][k] = sharedMap[i][j][k];
        }   }   }
        for(int i=0; i < sharedMap.length; i++){
            for(int j=0; j < sharedMap[0].length; j++){
                for(int k=0; k < _passClass.THIRD_DIMENTION_SIZE; k++){
                    _sharedMapOrignals[placeMap_][i][j][k] = sharedMapOrignal[i][j][k];
        }   }   }
        if(placeMap_==getMap_){return;}
        for(int i=0; i < sharedMap.length; i++){
            for(int j=0; j < sharedMap[0].length; j++){
                for(int k=0; k < _passClass.THIRD_DIMENTION_SIZE; k++){
                    sharedMap[i][j][k] = _sharedMaps[getMap_][i][j][k];
        }   }   }
        for(int i=0; i < sharedMap.length; i++){
            for(int j=0; j < sharedMap[0].length; j++){
                for(int k=0; k < _passClass.THIRD_DIMENTION_SIZE; k++){
                    sharedMapOrignal[i][j][k] = _sharedMapOrignals[getMap_][i][j][k];
        }   }   }
    }


    public void getMaps(int getMap_){
        for(int i=0; i < sharedMap.length; i++){
            for(int j=0; j < sharedMap[0].length; j++){
                for(int k=0; k < _passClass.THIRD_DIMENTION_SIZE; k++){
                    sharedMap[i][j][k] = _sharedMaps[getMap_][i][j][k];
        }   }   }
        for(int i=0; i < sharedMap.length; i++){
            for(int j=0; j < sharedMap[0].length; j++){
                for(int k=0; k < _passClass.THIRD_DIMENTION_SIZE; k++){
                    sharedMapOrignal[i][j][k] = _sharedMapOrignals[getMap_][i][j][k];
        }   }   }
    }

    public void setMaps(int setMap_){
        for(int i=0; i < sharedMap.length; i++){
            for(int j=0; j < sharedMap[0].length; j++){
                for(int k=0; k < _passClass.THIRD_DIMENTION_SIZE; k++){
                    _sharedMaps[setMap_][i][j][k] = sharedMap[i][j][k];
        }   }   }
        for(int i=0; i < sharedMap.length; i++){
            for(int j=0; j < sharedMap[0].length; j++){
                for(int k=0; k < _passClass.THIRD_DIMENTION_SIZE; k++){
                    _sharedMapOrignals[setMap_][i][j][k] = sharedMapOrignal[i][j][k];
        }   }   }
    }

//MAPS

    // @depercated
    public String getWorldState(){
        String returnMe_ = "";
        for(int i=0;i<worldMapHeight;i++){//ByRow
            for(int j=0;j<worldMapWidth;j++){//ByCollom
                returnMe_+=sharedMap[i][j][0];
            }
            returnMe_+="Ñ";
        }
        return returnMe_.substring(0,returnMe_.length()-1);
    }

    private char[][][] advance(char[][][] array_){
        for(int i=1;i<array_.length-1;i++){//ByRow
            for(int j=1;j<array_[0].length-1;j++){//ByCollom
                if(array_[i][j][0] == '▓'){
                    array_[i][j][0] = '▒';

                }else if(array_[i][j][0] == '▒'){
                    array_[i][j][0] = '░';

                }else if(sharedMap[i][j][0] == '░'){
                    array_[i][j][0] = sharedMapOrignal[i][j][0];
                }
            }
        }
        return array_;
    }

    public void advance(){//TODO: Have it pass a integer to change a certan map and another to do a spisfic item or for items make another metheod.
        sharedMap = advance(sharedMap);
    }

    public void writeWorld(String filename_){//TODO: Move to UI
        try {
            // File myObj = new File("filename.txt");
            // if (myObj.createNewFile()) {
            //     System.out.println("File created: " + myObj.getName());
            // } else {
            //     System.out.println("File already exists.");
            //     System.out.println("File Deleated");
            // }
            FileWriter myWriter = new FileWriter(filename_);
                
            String returnMe_ = "";
            for(int i=0;i<worldMapHeight;i++){//ByRow
                for(int j=0;j<worldMapWidth;j++){//ByCollom
                    returnMe_+=sharedMap[i][j][0];
                }
                myWriter.write(returnMe_+"\n");
                returnMe_="";
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // public void printWorld(){//For server debugging
    //     String returnMe_ = "";
    //     for(int i=0;i<worldMapHeight;i++){//ByRow
    //         for(int j=0;j<worldMapWidth;j++){//ByCollom
    //             if(playerLocation[0]==3 && playerLocation[1]==3){
    //                 returnMe_+=(playersLookChar+"");
    //                 System.out.println("LLLLLLLLLLLLLLLLLLLL");}
    //             else
    //                 returnMe_+=sharedMapOrignal[i][j];
    //         }
    //         System.out.println(returnMe_+"!");
    //         returnMe_="";
    //     }
    // }

}