class Item{

    private String _name, _description;
    private char _look, _color;
    private double _price;
    private int _quantity;

    public Item(char look_){
        _look = look_;
        _name = "Empty";
        _color = 'C';
        _description = "Posistion in empty";        
    }

    public Item(char look_, String name_, char color_, String description_){
        _look = look_;
        _name = name_;
        _color = color_;
        _description = description_;
        // _quantity = quantity_;
    }


    public char getLook(){return _look;}
    public char getColor(){return _color;}
    public String getName(){return _name;}
    public String getDescription(){return _description;}
    // public int getQuantity(){return _quantity;}

    public void setName(String name_){_name = name_;}

}