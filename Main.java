import java.util.Scanner;

class Main {
    // static String gamemode = "kills";//TODO: Gamemode 3D slices
    static short _game = 2;
    // static String gamemode = "reveals";
    // static String gamemode = "minecraftExample";
    // static String gamemode = "dimentionPhase";

    public static void main(String[] args) {
        // System.out.print("Please enter your gamemod { (K)ills,(R)eveals }:");
        // gamemode = UI.getTypedChr()+"";
        // gamemode = gamemode.charAt(0)+"";
        System.out.println("Main method started!");
        UI.clear();

        char cTemp = '\0';
        int currentMapNum = 0;


        Shops shops1 = new Shops();
        GameVariables passClass = new GameVariables();
        
        System.out.println("Please enter the number of the game you would like to play: ");
        // _game = (short) Character.getNumericValue(UI.getTypedChr());

        WorldBoard gameBoard = new WorldBoard(_game, passClass, shops1, 4, 4);

        Creation.initializeBoard(gameBoard,_game,(short)0,passClass);
        gameBoard.setMaps(0);
        if(_game==2){
            Creation.initializeBoard(gameBoard,_game,(short)1,passClass);
            gameBoard.setMaps(1);
            Creation.initializeBoard(gameBoard,_game,(short)2,passClass);
            gameBoard.setMaps(2);
            Creation.initializeBoard(gameBoard,_game,(short)3,passClass);
            gameBoard.setMaps(3);
            Creation.initializeBoard(gameBoard,_game,(short)4,passClass);
            gameBoard.setMaps(4);
            Creation.initializeBoard(gameBoard,_game,(short)5,passClass);
            gameBoard.setMaps(5);
            Creation.initializeBoard(gameBoard,_game,(short)6,passClass);
            gameBoard.setMaps(6);
            // gameBoard.getMaps(0);
        }
        gameBoard.getMaps(0);
// UI.getTypedChr();

        UI.clear();
        System.out.println("                        █▓▒░   ██▓▓▒▒░░");

        System.out.println("                          {▒"+Rules._BLACK+"▒"+Rules._RED+"▒"+Rules._GREEN+"▒"+Rules._YELLOW+"▒"+Rules._BLUE+"▒"+Rules._PURPLE+"▒"+Rules._CYAN+"▒"+Rules._WHITE+"▒"+Rules._CLEAR+"▒}");
        System.out.println("                          {C"+Rules._BLACK+"B"+Rules._RED+"r"+Rules._GREEN+"g"+Rules._YELLOW+"y"+Rules._BLUE+"b"+Rules._PURPLE+"p"+Rules._CYAN+"c"+Rules._WHITE+"W"+Rules._CLEAR+"C}");
        System.out.println("                          {█"+Rules._BLACK+"█"+Rules._RED+"█"+Rules._GREEN+"█"+Rules._YELLOW+"█"+Rules._BLUE+"█"+Rules._PURPLE+"█"+Rules._CYAN+"█"+Rules._WHITE+"█"+Rules._CLEAR+"█}");


        gameBoard.debugDataBoard("/");


        System.out.println(
            "\t\tKEY MAP, more currently active for debugging\n"+
            "\nWASD: Movements"+
            "\nQ:    Place"+
            "\nE:    Mine, Purchase"+
            "\nKL:   Move between planes\n");

        boolean flagHelper = true;
        while(true){
            System.out.println("You are on plane #"+currentMapNum);

            UI.printWorld(gameBoard);

            System.out.println();
            gameBoard.printHotbar();
            if(flagHelper){gameBoard.printHelper();flagHelper=false;}

            cTemp = gameBoard.move().charAt(0);
            //For hotbar selection
            if(Character.isDigit(cTemp)){
                gameBoard.debugDataBoard(cTemp+"");
                // gameBoard.selectHotbar(Short.valueOf(cTemp+"")== 0 ? (short) 0 : Short.valueOf(cTemp+"")-(short) 1);
                int temp = Integer.parseInt(cTemp+"");
                if(temp == 0){temp = 9;}
                else{temp--;}
                passClass.setHotbarSelected((short) temp);
            }else if(cTemp=='q'){
                gameBoard.debugDataBoard("q");
                gameBoard.placeFromHotbar();
            }else if(cTemp=='e'){
                // gameBoard.debugDataBoard("e");
                gameBoard.mineToHotbar();

            }else if(cTemp=='/'){
                gameBoard.debugDataBoard("/");

                UI.printWorld(gameBoard);
                try{Runtime.getRuntime().exec(UI.cmd2).waitFor();}catch(Exception e){};

                System.out.print("Input: ");
                cTemp = UI.getTypedChr();
                gameBoard.setPointCurrentS(cTemp);
                UI.clear();
            }else if(cTemp=='\''){
                gameBoard.writeWorld("saved.txt");
                System.out.println("Your game state has been saved");
            }else if(cTemp=='c'){
                gameBoard.clearHotbar();
            }else if(cTemp=='o'){
                UI.printWorldO(gameBoard);
            }else if(cTemp=='k'){
                if(currentMapNum != 0){
                    gameBoard.setMaps(currentMapNum);
                    gameBoard.getMaps(--currentMapNum);
                }
            }else if(cTemp=='l'){
                if(currentMapNum != 1){
                    gameBoard.setMaps(currentMapNum);
                    gameBoard.getMaps(++currentMapNum);
                }
            }
        }
        
    } 
}