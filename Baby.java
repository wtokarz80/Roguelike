
public class Baby extends Player {

    private String symbol;
    private Coordinates coord ;
    private boolean isInHouse = false;
    private boolean isTouchingHer = false;

    public Baby(String name, Coordinates coord, Statistics stats, String symbol) {
        super(name, coord, stats, symbol);
        this.coord = coord;
        this.symbol = symbol;

    }


    public void move(Coordinates direction, Player player, Baby baby) {
        touchHer(player, baby);
        if (isTouchingHer){
        int x = this.coord.getX() + direction.getX();
        int y = this.coord.getY() + direction.getY();
        this.coord = new Coordinates(x, y);}
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setCoords(Coordinates newCoordinates){
        this.coord = newCoordinates;
    }
    public Coordinates getCoord(){
        return coord;
    }

    private void touchHer(Player player, Baby baby){
        if (player.getCoord().getX()==baby.getCoord().getX() && player.getCoord().getY()==baby.getCoord().getY()){
            this.isTouchingHer = true;
    }
}
    public void setIsInHouse (Baby baby){
        if (baby.getCoord().getX()==3 && baby.getCoord().getY()==2){
            this.isInHouse = true;
        }
    }
    public boolean getIsInHouse(Baby baby){
        setIsInHouse(baby);
        return this.isInHouse;
    }

}

