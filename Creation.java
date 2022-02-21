import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
// import java.io.FileWriter;   // Import the FileWriter class
import java.util.Scanner; // Import the Scanner class to read text files

class Creation{
    private static final long _seed = 7182781;/*UI.getUsersInputLong("Please enter a seed for your game: ");//-1 to use the generators default seed*/
    
    public static void initializeBoard(WorldBoard worldBoard_, short game_, short level_, GameVariables passClass_){
        char[][][] sharedMapOrignal_ = worldBoard_.getSharedMapOringal();
        char[][][] sharedMap_ = worldBoard_.getSharedMap();

        if(game_ == 1){
            
        }else if(game_ == 2){

            sharedMap_ = fromFile("GAMES2","miner"+level_,passClass_, false);//Identical .clone();//Identical
            // sharedMapOrignal_ = fromFile("GAMES2","miner"+level_);
            sharedMapOrignal_ = fromFile("GAMES2","miner"+level_,passClass_,true);//Identical .clone();//Identical
            // sharedMapOrignal_ = sharedMap_.clone();

            sharedMap_ = Generator.generateOn(sharedMap_, level_+_seed, ' ','█', 5.0, 2, false);
            sharedMap_ = Generator.generateOn(sharedMap_, level_+_seed+1, ' ','◊', 75.0, 0, false);
            sharedMap_ = Generator.generateOn(sharedMap_, level_+_seed+2, ' ','⌁', 15.0, 0, false);
            sharedMap_ = replaceChars(sharedMap_, ' ', '▒');
            sharedMapOrignal_ = replaceChars(sharedMapOrignal_, ' ', '█');
            sharedMap_ = setCharsColor(sharedMap_,'◊','g');
            sharedMap_ = setCharsColor(sharedMap_,'⌁','y');


        //Underlayers ▓▒░
            // sharedMapOrignal_ = replaceChars(sharedMapOrignal_,'▒','█');
            // sharedMapOrignal_ = replaceChars(sharedMapOrignal_,'◊',' ');
            
            sharedMap_ = replaceChars(sharedMap_,'X','█');

            sharedMapOrignal_ = replaceChars(sharedMapOrignal_,'T',' ');

            //Colors
            // sharedMap_[3][3][1] = 'y';
            // sharedMapOrignal_[0][7][1] = 'y';

            // sharedMap_[0][7][1] = 'c';
            // sharedMapOrignal_[0][7][1] = 'c';
            
            // sharedMap_[2][7][0] = 'S';
            // sharedMapOrignal_[2][7][0] = 'O';

            // sharedMap_ = setStringsColor(sharedMap_,"SHOP#\\",'r');

            // sharedMap_ = setCharsColor(sharedMap_,'_','r');
            // sharedMap_ = setCharsColor(sharedMap_,'/','r');
            // sharedMap_ = setCharsColor(sharedMap_,'\\','r');
            // sharedMap_ = setCharsColor(sharedMap_,'|','r');
            // sharedMap_ = setCharsColor(sharedMap_,'[','r');
            // sharedMap_ = setCharsColor(sharedMap_,']','r');

            // sharedMap_ = setCharsColor(sharedMap_,'=','g');


            // sharedMapOrignal_ = replaceChars(sharedMapOrignal_,'⌂','▓');
            sharedMapOrignal_ = setCharsColor(sharedMapOrignal_,'▓','B');
            // sharedMap_ = setCharsLocks(sharedMap_, '⌂', true);
            sharedMap_ = setCharsColor(sharedMap_,'⌂','y');



            //Background locks
            sharedMap_[3][3][3] = 'L';


            sharedMap_[3][4][1] = 'r';//Temp to showing off,othersise the v's get collored
            sharedMap_[4][4][1] = 'b';//Temp to showing off,othersise the S's get collored

            //Points
            // sharedMapOrignal_[7][7][0]='o';
            // sharedMapOrignal_[7][7][1]='W';

            //Locks
            sharedMap_ = setCharsLocks(sharedMap_, '▒', false);
            sharedMap_ = setCharsLocks(sharedMap_, '▓', false);
            // sharedMap_ = setCharsLocks(sharedMap_, 'T', false);
            sharedMap_ = setCharsLocks(sharedMap_, ' ', false);

            // Colored Locks
            sharedMap_ = setLocksColor(sharedMap_,false,'y');


            sharedMapOrignal_ = replaceChars(sharedMapOrignal_,' ','▓');
            sharedMap_ = replaceChars(sharedMap_,' ','▓');


            sharedMap_ = setCharsColor(sharedMap_,'█','b');
            sharedMap_ = setCharsColor(sharedMap_,'▓','B');
            sharedMapOrignal_ = setCharsColor(sharedMapOrignal_,'▓','B');
            sharedMap_ = setCharsColor(sharedMap_,'▒','W');


            worldBoard_.setSharedMap(sharedMap_);
            worldBoard_.setSharedMapOringal(sharedMapOrignal_);
        }
    }


    public static char[][][] replaceChars(char[][][] worldBoard_, char cOld_, char cNew_){
        for(int i=0;i<worldBoard_.length;i++){//ByRow
            for(int j=0; j < worldBoard_[0].length; j++){
                if(worldBoard_[i][j][0]==cOld_) worldBoard_[i][j][0] = cNew_;
            }
        }
        return worldBoard_;
    }

    public static char[][][] setCharsColor(char[][][] worldBoard_, char cOld_, char color_){
        for(int i=0;i<worldBoard_.length;i++){//ByRow
            for(int j=0; j < worldBoard_[0].length; j++){
                if(worldBoard_[i][j][0]==cOld_) worldBoard_[i][j][1] = color_;
            }
        }
        return worldBoard_;
    }

    public static char[][][] setStringsColor(char[][][] worldBoard_, String compare_, char c_){
        // String tempStr = '\0';
        // for(int i=0;i<worldBoard_.length;i++){//ByRow
        //     for(int j=0; j < worldBoard_[0].length; j++){
        //         tempStr += worldBoard_[i][j][0];
        //         if(tempStr.charAt(0) != compare_.charAt(0)){
        //             tempStr="";
        //         }else if(worldBoard_.equals()){
                    
        //         }
        //     }
        // }
        return worldBoard_;
    }


    public static char[][][] setCharsLocks(char[][][] worldBoard_, char c_, boolean state_){
        char stateC = '\0';
        if(state_){stateC = 'T';}else{stateC = 'U';}
        for(int i=0;i<worldBoard_.length;i++){//ByRow
            for(int j=0; j < worldBoard_[0].length; j++){
                if(worldBoard_[i][j][0]==c_) worldBoard_[i][j][3] = stateC;
            }
        }
        return worldBoard_;
    }

    public static char[][][] setLocksColor(char[][][] worldBoard_, boolean state_, char color_){
        char locked_;
        if(state_){locked_ = 'L';}else{locked_ = 'U';}//TODO: Include more
        for(int i=0;i<worldBoard_.length;i++){//ByRow
            for(int j=0; j < worldBoard_[0].length; j++){
                if(worldBoard_[i][j][3]==locked_ && worldBoard_[i][j][0]!=' ') worldBoard_[i][j][1] = color_;
            }
        }
        return worldBoard_;
    }



//====================================GAMES====================================\\


//Later will need it as a bigger method with a input varible

public static char[][][] fromFile(String directory_, String fileName_, GameVariables passClass_, boolean isOringal_){
        char[][][] returnArray_ = new char[22][62][passClass_.THIRD_DIMENTION_SIZE];

        try{
            File textFile = new File("./"+directory_+"/"+fileName_+".txt");
            Scanner myReader = new Scanner(textFile);

            String deconstruct = "";

            for(int i=0;i <returnArray_.length; i++){//ByRow
                deconstruct = myReader.nextLine();
                for(int j=0; j < deconstruct.length(); j++){
                    returnArray_[i][j][0] = deconstruct.charAt(j);
                }
            }
            // if(!nextLine.isEmpty()){
            try{
                // CHARACTER COLORS
                if(myReader.nextLine().equals(".")){
                    deconstruct = myReader.nextLine().replace(" ","");
                    for(int i = 0; i < deconstruct.length(); i+=2){
                        returnArray_ = setCharsColor(returnArray_, deconstruct.charAt(i),deconstruct.charAt(i+1));
                    }
                }
                // CHARACTER REPLACEMENTS
                if(myReader.nextLine().equals(".") && isOringal_){
                    deconstruct = myReader.nextLine().replace(" ","");
                    for(int i = 0; i < deconstruct.length(); i+=2){
                        returnArray_ = replaceChars(returnArray_, deconstruct.charAt(i),deconstruct.charAt(i+1));
                    }
                }else{
                    myReader.nextLine();
                }
            }catch(Exception e){
                System.out.println("ERROR WITH CREATION FILE = "+e);
            }

            myReader.close();
        }catch(Exception e){System.out.println("Error with Creation file :"+e);}
        return returnArray_;
    }


}