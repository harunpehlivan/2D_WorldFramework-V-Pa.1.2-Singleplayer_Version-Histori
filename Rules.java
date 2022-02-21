import java.util.Arrays;
import java.util.ArrayList;

public class Rules{

private static final int _game = Main._game;//TODO: make changeable
private static final String _currencey = "◊";

static public int getGame(){return _game;}
static public String getCurrencey(){return _currencey;}

// private static final int _trail = 0;//TODO: make changeable & THIS NEEDS TO BE MADE TO A LENGTH OF TRIAL
// private static final char[] _TRAILS = {'',''};
    private static final int[] _CANBEPLACEDON = {' ','▒'};//█▓▒░
    private int[] _CanWalkOn = {};


    //TODO: Make this an arraylist
    private static char[][] breakPairs = {
        // {' ','T','t','Y','y','X','T'},
        {' ','T','T','Y','y','X',' '},
        {'T','▓','▒','⌂','⌂','X','x'},
        {'⌂','x','x'}//,'_','_','_','_'}
        };
    private static char[][] setPairs = {
        {' ','⌂'},
        {'▒','⌂','▓'}
        };
    // ArrayList<ArrayList<Character>> placePairs = new ArrayList<ArrayList<Character>>();
    public static final String _CLEAR = "\u001B[0m";
    public static final String _BLACK = "\u001B[30m";
    public static final String _RED = "\u001B[31m";
    public static final String _GREEN = "\u001B[32m";
    public static final String _YELLOW = "\u001B[33m";
    public static final String _BLUE = "\u001B[34m";
    public static final String _PURPLE = "\u001B[35m";
    public static final String _CYAN = "\u001B[36m";
    public static final String _WHITE = "\u001B[37m";

    public static final String _BOLD = "\033[1m";
    public static final String _UNDERLINE = "\u0332";

    public static final char UNUSED_CHAR = '™';

    static public String charToDo(int gameNum_, char c_, char o_, boolean lockedS_){
        if(gameNum_==1){
            //Change to a array? Or case?
            if(c_==' ') return "moves";
        
            else if(c_=='⌁') return "dies";
            // else if(c_=='0') return "points5";//Give5 points
            else if(c_=='▓') return "barrier1";

            else if(Character.isDigit(c_)) return ("portal"+c_);


            else if(c_=='◊') return "points1.5";
            
            else if(c_=='▒') return "reveals";
            else if(c_!=o_ && !lockedS_) return "pickup";
            
            else return "other";
            //TODO: Replace with orignal
        }else if(gameNum_==2){
            if(c_==' ') return "moves";
            // else if(c_=='▒') return "moves";
            else if(c_=='▒') return "reveals";
            else if(c_=='▓') return "reveals";
            else if(c_=='█') return "barrier1";

            else if(c_=='_') return "barrier2";
            else if(c_=='[') return "barrier2";
            else if(c_==']') return "barrier2";
            else if(c_=='=') return "shop1";
            else if(c_=='.') return "barrier2";
            else if(c_=='/') return "barrier2";
            else if(c_=='|') return "barrier2";
            else if(c_=='\\') return "barrier2";

            else if(c_=='⌁') return "dies";
            else if(c_=='◊') return "points25.5";
        }

        return "";
    }

    static public char canBreakPairs(char hand_, char check_){
        System.out.println("Test");
        for(int i = breakPairs.length-1; i >= 0; i--){
            if(breakPairs[i][0] == hand_ || breakPairs[i][0] ==' '){
                for(int j = 1; j < breakPairs[i].length; j+=2){
                    System.out.println("breakPairs[i][j] = " + breakPairs[i][j]);
                    if(breakPairs[i][j] == '\0'){
                        j = breakPairs[0].length;
                    }else{
                        if(breakPairs[i][j] == check_){
                            return breakPairs[i][j+1];
                        }
                    }
                }
            }
        }
        return '!';
    }

    // static public char canPlacePairs(char hand_, char check_){
    //     for(int i = breakPairs.length-1; i > 0; i++){
    //         if(breakPairs[i][0] == hand_ || breakPairs[i][0] ==' '){
    //             for(int j = 1; j < breakPairs[0].length;j+=2){
    //                 if(breakPairs[i][j] == '\0'){
    //                     j = breakPairs[0].length;
    //                 }else{
    //                     if(breakPairs[i][j] == check_){
    //                         return breakPairs[i][j+1];
    //                     }
    //                 }
    //             }
    //         }
    //     }
    //     return '!';
    // }

/* CODE FROM https://stackoverflow.com/questions/4401850/how-to-create-a-multidimensional-arraylist-in-java
    public void addToInnerArray(int index, T element) {
        while (index >= this.size()) {
            // Create enough Arrays to get to position = index
            this.add(new ArrayList<T>()); // (as if going along Vertical axis)
        }
        // this.get(index) returns the Arraylist instance at the "index" position
        this.get(index).add(element); // (as if going along Horizontal axis)
    }

    public void addToInnerArray(int index, int index2, T element) {
        while (index >= this.size()) {
            this.add(new ArrayList<T>());// (as if going along Vertical
        }
        //access the inner ArrayList at the "index" position.
        ArrayList<T> inner = this.get(index);
        while (index2 >= inner.size()) {
            //add enough positions containing "null" to get to the position index 2 ..
            //.. within the inner array. (if the requested position is too far)
            inner.add(null); // (as if going along Horizontal axis)
        }
        //Overwrite "null" or "old_element" with the new "element" at the "index 2" ..
        //.. position of the chosen(index) inner ArrayList
        inner.set(index2, element); // (as if going along Horizontal axis)
    }*/

    static public boolean isPlaceable(char c_){
        // return Arrays.charStream(worldBoard_.getBoard()).anyMatch(c_::equals);
        for(int i=0; i < _CANBEPLACEDON.length; i++){
            if(_CANBEPLACEDON[i] == c_){return true;}
        }
        return false;
    }
}