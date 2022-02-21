import java.util.Random;

class Generator{
    private static final long _seed = 42;

    // createWorld(char[][][] map_, long seed_, short game_){
    //     if(seed_ == 0){seed_ = _seed}

    //     for(int i =0; i < char.length; i++){

    //     }
    // }

    static public char[][][] generateOn(char[][][] mapO_, long seed_, char find_, char replace_, double percentage_, int collate_, boolean add_){//Can I switch percentage or collate to something smaller?

        char[][] map_ = new char[mapO_.length][mapO_[0].length];//[mapO_[0][0].length];
        Random randomGen = new Random(seed_ == -1 ? _seed : seed_);
        char unUsedChar_ = Rules.UNUSED_CHAR;

        //Generate
        for(int i = 0; i < map_.length; i++){
            for(int j = 0; j < map_[0].length; j++){
                if(mapO_[i][j][0] == find_){
                    if(randomGen.nextInt(100000)/1000.0 <= percentage_){
                        map_[i][j] = unUsedChar_;
                    }
                }
            }
        }

        short nearby = 0;
        //collate
        if(add_){//Add more that fit
            char[][] map2_ = map_.clone();
            for(int i = 1; i < map_.length-1; i++){
                for(int j = 1; j < map_[0].length-1; j++){
                    if(mapO_[i][j][0] == find_ && map_[i][j] != unUsedChar_){
                        if(map_[i][j+1]==unUsedChar_){nearby++;}
                        if(map_[i-1][j]==unUsedChar_){nearby++;}
                        if(map_[i+1][j]==unUsedChar_){nearby++;}
                        if(map_[i][j-1]==unUsedChar_){nearby++;}
                        if(nearby >= collate_)
                            mapO_[i][j][0] = replace_;
                        nearby = 0;
                    }
                }
            }
        }else{//only add the ones that fit
            for(int i = 1; i < map_.length-1; i++){
                for(int j = 1; j < map_[0].length-1; j++){
                    if(map_[i][j] == unUsedChar_){
                        if(map_[i][j+1]==unUsedChar_){nearby++;}
                        if(map_[i-1][j]==unUsedChar_){nearby++;}
                        if(map_[i+1][j]==unUsedChar_){nearby++;}
                        if(map_[i][j-1]==unUsedChar_){nearby++;}
                        if(nearby >= collate_)
                            mapO_[i][j][0] = replace_;
                        nearby = 0;
                    }
                }
            }
        }

        return mapO_;
    }

}