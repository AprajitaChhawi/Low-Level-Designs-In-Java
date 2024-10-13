package TIC_TAC_TOE;

import java.util.HashMap;
import java.util.Scanner;

class Player{
    private String name;
    private String marker;
    public Player(String name, String marker){
        this.name = name;
        this.marker = marker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }
}
class Board{
    private int n;
    private String[][] board;
    private HashMap<Integer,HashMap<String,Integer>> rowCount;
    private HashMap<Integer,HashMap<String,Integer>> colCount;
    private HashMap<String,Integer> diagCountForward;
    private HashMap<String,Integer> diagCountBackward;
    private int count;
    public Board(int n){
        this.n = n;
        this.board = new String[n][n];
        this.rowCount = new HashMap<>();
        this.colCount = new HashMap<>();
        this.diagCountForward = new HashMap<>();
        this.diagCountBackward = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = "";
            }
            rowCount.put(i, new HashMap<>());
            colCount.put(i, new HashMap<>());
        }
        this.count =0;
    }
    public boolean play(Player p, int x, int y){
        String marker = p.getMarker();
        if (x < 0 || x >= n || y < 0 || y >= n || !board[y][x].equals("")) {
            System.out.println("Invalid move. Try again.");
            return false;
        }
        board[y][x] = marker;
        count++;
        rowCount.get(y).put(marker, rowCount.get(y).getOrDefault(marker, 0) + 1);
        if(rowCount.get(y).get(marker)==n){
            System.out.println("Player "+p.getName()+" wins!!");
            return true;
        }
        colCount.get(x).put(marker, colCount.get(x).getOrDefault(marker, 0) + 1);
        if(colCount.get(x).get(marker)==n){
            System.out.println("Player "+p.getName()+" wins!!");
            return true;
        }
        if(x==y){
            diagCountForward.put(marker,diagCountForward.getOrDefault(marker,0)+1);
            if(diagCountForward.get(marker)==n){
                System.out.println("Player "+p.getName()+" wins!!");
                return true;
            }
        }
        if(x+y==n-1){
            diagCountBackward.put(marker,diagCountBackward.getOrDefault(marker,0)+1);
            if(diagCountBackward.get(marker)==n){
                System.out.println("Player "+p.getName()+" wins!!");
                return true;
            }
        }
        if(count>=n*n){
            System.out.println("Game Tied");
            return true;
        }
        return false;
    }

}
class Game{
    private Player p1;
    private Player p2;
    private Board board;
    public Game(Player p1,Player p2 ,Board board){
        this.p1 = p1;
        this.p2 = p2;
        this.board = board;
    }
    public void playGame() {
        int curr_turn = 1;
        boolean done = false;
        Scanner sc = new Scanner(System.in);
        while(!done){
            Player curr_player = (curr_turn%2==1)? p1:p2;
            System.out.println("Current turn: " + curr_player.getName());
            System.out.println("Enter coordinates alternatively");
            int x = sc.nextInt();
            int y = sc.nextInt();
            if(board.play(curr_player,x,y)){
                done = true;
            } else {
                curr_turn ++;
            }
        }
    }
}
public class TICTACTOE {
    public static void main(String[] args) {
        Player p1 = new Player("Aprajita","x");
        Player p2 = new Player("Chhawi","o");
        Board board = new Board(3);
        Game game = new Game(p1,p2,board);
        game.playGame();
    }
}
