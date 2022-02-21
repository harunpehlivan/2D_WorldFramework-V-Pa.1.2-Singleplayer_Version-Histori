import java.util.Scanner;

public class UI{
    public final static String[] cmd1 = {"/bin/sh", "-c", "stty raw </dev/tty"};
    public final static String[] cmd2 = {"/bin/sh", "-c", "stty cooked </dev/tty"};

// @@UF = unfinished or needs to be implemented

// DISPLAYS
    static public void clear(){System.out.print("\033\143");}
    
    static public String deathScreen(){//@@UF
        return "";
    }

// TIME
    public static void tSleep(double seconds_){
        try{Thread.sleep(Double.valueOf(seconds_*1000).longValue());}
        catch(InterruptedException ex){
        Thread.currentThread().interrupt();}
    }

    public static void tSleepMil(double miliseconds_){
        try{Thread.sleep(Double.valueOf(miliseconds_).longValue());}
        catch(InterruptedException ex){
        Thread.currentThread().interrupt();}
    }




    static public String getFC(char c_){
        switch (c_) {
            case '\0': //Null
            return Rules._CLEAR;
            case 'C': //Clear
            return Rules._CLEAR;
            case 'B': //Black
            return Rules._BLACK;
            case 'r': //Red
            return Rules._RED;
            case 'g': //Green
            return Rules._GREEN;                           
            case 'y': //Yellow
            return Rules._YELLOW;                           
            case 'b': //Blue
            return Rules._BLUE;                           
            case 'p': //Purple
            return Rules._PURPLE;                           
            case 'c': //Cyan
            return Rules._CYAN;
            case 'W': //White
            return Rules._WHITE;
            default: return Rules._CLEAR;
        }
    }

    static public char getTypedChr(){
        try{
            Runtime.getRuntime().exec(cmd1).waitFor();
            char c = (char) System.in.read();
            Runtime.getRuntime().exec(cmd2).waitFor();

            System.out.print("\n");
            return c;
        }catch(Exception e){}
        return '\0';
    }

//More Inputs
    public static String getUsersInputStr(){
        try{
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            in.close();
            return input;
        }catch(Exception e){

        }
        return "failed";//Need to make it cappable of re-asking
    }

    public static String getUsersInputStr(String prompt_){
        try{
            System.out.print(prompt_);
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            in.close();
            return input;
        }catch(Exception e){

        }
        return "failed";//Need to make it cappable of re-asking
    }

    public static long getUsersInputLong(){
        try{
            // Runtime.getRuntime().exec(cmd1).waitFor();
            Scanner in = new Scanner(System.in);
            long input = in.nextLong();
            in.close();
            Runtime.getRuntime().exec(cmd2).waitFor();
            return input;
        }catch(Exception e){

        }
        return -1;//Need to make it cappable of re-asking
    }

    public static long getUsersInputLong(String prompt_){
        try{
            System.out.print(prompt_);
            Scanner in = new Scanner(System.in);
            long input = in.nextLong();
            in.close();
            return input;
        }catch(Exception e){

        }
        return -1;//Need to make it cappable of re-asking
    }

    static public String getTypedStr(){
        try{
            Runtime.getRuntime().exec(cmd1).waitFor();

            String str_ = "";
            char c;
            while ((c=(char)System.in.read ()) !=
            '\n') {
            str_ += c;
            }
            // String str_ = System.in.read().toString();
            Runtime.getRuntime().exec(cmd2).waitFor();
            System.out.print("\n");
            return str_;
        }catch(Exception e){}
        return "null";
    }
//End of more imports



//Prints
    static public void printWorldTrue(WorldBoard worldBoard_){
        // String returnMe_;
        StringBuilder returnMe_ = new StringBuilder();
        int playerX = worldBoard_.getPlayerX();
        int playerY = worldBoard_.getPlayerY();

//TODO: OPTIMISE TO USE .length
        for(int i=0;i<worldBoard_.getHeight();i++){//ByRow
            // returnMe_="";
            returnMe_.setLength(0);
            for(int j=0;j<worldBoard_.getWidth();j++){//ByCollom
                if(playerX==i && playerY==j){
                    returnMe_.append(worldBoard_.getPlayersLookChar());
                }else{
                    returnMe_.append(worldBoard_.getPoint(i,j));
                }
            }
            System.out.println(returnMe_);
        }
    }

    static public void printWorldO(WorldBoard worldBoard_){
        String returnMe_="";
        returnMe_+="\033[4;32m";//"\033[4;33m";//"\u001B[43m";
        int playerX = worldBoard_.getPlayerX();
        int playerY = worldBoard_.getPlayerY();
        String lastColor = Rules._CLEAR;
        char c_ = worldBoard_.getPointCO(0,0);
        char lastC_ = '/';//Unused character //worldBoard_.getPointC(0,0);

        for(int i=0;i<worldBoard_.getHeight();i++){//ByRow
            // returnMe_="";
            for(int j=0;j<worldBoard_.getWidth();j++){//ByCollom
                c_ = worldBoard_.getPointC(i,j);
                if(c_ != lastC_){
                    lastC_ = c_;
                    switch (c_) {
                        case '\0': //Null
                        returnMe_+=Rules._CLEAR;
                        break;
                        case 'C': //Clear
                        returnMe_+=Rules._CLEAR;
                        break;
                        case 'B': //Black
                        returnMe_+=Rules._BLACK;
                        break;
                        case 'r': //Red
                        returnMe_+=Rules._RED;
                        break;
                        case 'g': //Green
                        returnMe_+=Rules._GREEN;
                        break;                            
                        case 'y': //Yellow
                        returnMe_+=Rules._YELLOW;
                        break;                            
                        case 'b': //Blue
                        returnMe_+=Rules._BLUE;
                        break;                            
                        case 'p': //Purple
                        returnMe_+=Rules._PURPLE;
                        break;                            
                        case 'c': //Cyan
                        returnMe_+=Rules._CYAN;
                        break;
                        case 'W': //White
                        returnMe_+=Rules._WHITE;
                        break;
                        
                        default: ;
                    }
                }
                returnMe_+=worldBoard_.getPointO(i,j);
            }
            returnMe_+='\n';
        }
        System.out.println(returnMe_+Rules._CLEAR);
    }

    static public void printWorld/*InColor*/(WorldBoard worldBoard_){
        try{Runtime.getRuntime().exec(cmd2).waitFor();}catch(Exception e){};
        String returnMe_="";
        // returnMe_+="\033[4;32m";//"\033[4;33m";//"\u001B[43m";
        int playerX = worldBoard_.getPlayerX();
        int playerY = worldBoard_.getPlayerY();
        String lastColor = Rules._CLEAR;
        char c_ = worldBoard_.getPointC(0,0);
        char lastC_ = '/';//Unused character //worldBoard_.getPointC(0,0);

        for(int i=0;i<worldBoard_.getHeight();i++){//ByRow
            // returnMe_="";
            for(int j=0;j<worldBoard_.getWidth();j++){//ByCollom
                if(playerX==i && playerY==j){
                    returnMe_+=Rules._CYAN;
                    returnMe_+=worldBoard_.getPlayersLookChar();
                    returnMe_+=Rules._CLEAR;
                    lastC_='c';
                }else{
                    c_ = worldBoard_.getPointC(i,j);
                    if(c_ != lastC_){
                        lastC_ = c_;
                        switch (c_) {
                            case '\0': //Null
                            returnMe_+=Rules._CLEAR;
                            break;
                            case 'C': //Clear
                            returnMe_+=Rules._CLEAR;
                            break;
                            case 'B': //Black
                            returnMe_+=Rules._BLACK;
                            break;
                            case 'r': //Red
                            returnMe_+=Rules._RED;
                            break;
                            case 'g': //Green
                            returnMe_+=Rules._GREEN;
                            break;                            
                            case 'y': //Yellow
                            returnMe_+=Rules._YELLOW;
                            break;                            
                            case 'b': //Blue
                            returnMe_+=Rules._BLUE;
                            break;                            
                            case 'p': //Purple
                            returnMe_+=Rules._PURPLE;
                            break;                            
                            case 'c': //Cyan
                            returnMe_+=Rules._CYAN;
                            break;
                            case 'W': //White
                            returnMe_+=Rules._WHITE;
                            break;
                            
                            default: ;
                        }
                    }
                    returnMe_+=worldBoard_.getPoint(i,j);
                }
            }
            returnMe_+='\n';
        }
        System.out.println(returnMe_+Rules._CLEAR);
    }

// END OF PRINTS
}