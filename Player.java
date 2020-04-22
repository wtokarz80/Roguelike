import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    Coordinates coord;
    String symbol = "\ud83d\udd7a";
    static Map<String, Integer> inventory;

    public Player() {
        this.coord = new Coordinates(5, 5);
        Player.inventory = new HashMap<>();
    }

    public void move(Coordinates direction) {
        System.out.println(direction.toString());
        int x = this.coord.getX() + direction.getX();
        int y = this.coord.getY() + direction.getY();
        this.coord = new Coordinates(x, y);
    }

    public Coordinates getCoord() {
        return this.coord;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void addItem(String item) {
        Integer count = inventory.get(item);
        if (count == null) {
            inventory.put(item, 1);
        } else {
            inventory.put(item, count + 1);
        }
    }

    public static Map<String, Integer> getInventory() {
        return inventory;
    }
}