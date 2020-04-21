import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

class Game extends KeyAdapter {
    
    private Player player;
    // private final int width = 20;
    // private final int height = 20;
    private GameObject apple;
    private final int width = 30;
    private final int height = 30;
    private ArrayList<Obstacle> obstacles;



    public Game() {
        player = new Player();
        obstacles = new ArrayList<>();
        createObstacles();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        Common.clearScreen();
        char ch = event.getKeyChar();
        System.out.println((int)ch);

        switch(ch) {
            case 'w':
                if (canPlayerMove(Coordinates.W)){
                    player.move(Coordinates.W);
                }
                break;
            case 's':
                if (canPlayerMove(Coordinates.S)){
                    player.move(Coordinates.S);
                }
                break;
            case 'a':
                if (canPlayerMove(Coordinates.A)){
                    player.move(Coordinates.A);
                }
                break;
            case 'd':
                if (canPlayerMove(Coordinates.D)){
                    player.move(Coordinates.D);
            }
                break;   
        }
        System.out.println(player.getCoord().toString());
        System.out.println(player.getInventory().toString());
        printBoard();
    }

    public ArrayList<Obstacle> getObstacles(){
        return obstacles;
    }

    
    private void createObstacles(){
        Obstacle wall1 = new Obstacle("wall", "\ud83e\uddf1", new Coordinates(0, 0), width, 1);
        Obstacle wall2 = new Obstacle("wall", "\ud83e\uddf1", new Coordinates(0, 0), 1, height);
        Obstacle wall3 = new Obstacle("wall", "\ud83e\uddf1", new Coordinates(width-1, 0), width, 1);
        Obstacle wall4 = new Obstacle("wall", "\ud83e\uddf1", new Coordinates(0, height-1), 1, height-1);
        Obstacle tree = new Tree ("tree", "\ud83c\udf32", new Coordinates(7, 8), 1, 1);
        Obstacle apple = new Apple("apple", "\ud83c\udf4e", new Coordinates(10, 10), 1, 1);

        this.obstacles.add(wall1);
        this.obstacles.add(wall2);
        this.obstacles.add(wall3);
        this.obstacles.add(wall4);
        this.obstacles.add(tree);
        this.obstacles.add(apple);
    }

    private void printBoard() {
        String[][] board  = new String[width][height];
        board[this.player.getCoord().getX()][this.player.getCoord().getY()] = player.getSymbol();

        printObstacles(board);

        for(int i = 0; i< width;  i++) {
            for(int j = 0; j< height; j++) {
                if(board[i][j] != null) {
                    System.out.print(board[i][j]);
                    continue;
                }
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    private void printObstacles(String[][] board){
        for (Obstacle obstacle : obstacles){
            int width = obstacle.getWidth();
            int height = obstacle.getHeight();
            Coordinates pivot = obstacle.getPivot();

            for(int i = pivot.getX(); i < pivot.getX()+height; i++){
                for(int j = pivot.getY(); j < pivot.getY()+width; j++){
                    board[i][j] = obstacle.getSymbol();
                }
            }
        }
    }

    public boolean canPlayerMove(Coordinates coord){
        for (Obstacle obstacle : obstacles){
            if (isPlayerInRange(obstacle, coord)){
                obstacle.use(player);
                return false;
            }
        }
        return true;
    }

    private boolean isPlayerInRange(Obstacle obstacle, Coordinates coord){
        int width = obstacle.getWidth();
        int height = obstacle.getHeight();
        Coordinates pivot = obstacle.getPivot();
        int x = this.player.getCoord().getX() + coord.getX();
        int y = this.player.getCoord().getY() + coord.getY();

        return x >= pivot.getX() && x < pivot.getX() + height
            && y >= pivot.getY() && y < pivot.getY() + width;
    }
}