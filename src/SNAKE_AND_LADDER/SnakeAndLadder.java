package SNAKE_AND_LADDER;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Player{
    String name;
    int position;
    public Player(String name , int position){
        this.name = name;
        this.position = 0;
    }
}
class Position{
    int start;
    int end;
    public Position(int start, int end){
        this.start = start;
        this.end = end;
    }
}
class Board{
    int n;
    ArrayList<Position> snakes;
    ArrayList<Position> ladders;
    public Board(int n){
        this.n = n;
        snakes = new ArrayList<>();
        ladders = new ArrayList<>();
    }
    public void addSnakes(Position p){
        snakes.add(p);
    }
    public void addLadders(Position p){
        ladders.add(p);
    }
}
class Game{
    ArrayList<Player> players;
    Board b;
    Random dice;
    public Game(ArrayList<Player> players, Board b) {
        this.players = players;
        this.b = b;
        this.dice = new Random();
    }
    public int rollDice(){
        return dice.nextInt(6) + 1;
    }
    public int checkForSnakesOrLadders(String name,int position){
        for(Position p: b.snakes){
            if(position==p.start){
                System.out.println(name+" got biiten by a snake");
                return p.end;
            }
        }
        for(Position p: b.ladders){
            if(position==p.start){
                System.out.println(name+" got a ladder");
                return p.end;
            }
        }
        return position;
    }
    public boolean currPlayerPlay(Player p){
        int diceRoll = rollDice();
        System.out.println("Dice has given "+diceRoll+ " for player "+p.name);
        if (p.position + diceRoll > 100) {
            System.out.println(p.name + " cannot move, must roll exactly to reach 100.");
            return false;
        }
        p.position = p.position + diceRoll;
        System.out.println("current position for player "+p.name+" is "+p.position);
        p.position = checkForSnakesOrLadders(p.name,p.position);
        return p.position==100;
    }
    public void playGame(){
        Scanner sc = new Scanner(System.in);
        int curr_turn = 0;
        boolean hasWon = false;
        while(!hasWon){
            Player curr_player = players.get(curr_turn%players.size());
            if(currPlayerPlay(curr_player)){
                System.out.println("Player "+curr_player.name+" wins!!");
                hasWon = true;
            }
            curr_turn++;
        }
    }
}
public class SnakeAndLadder {
    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Aprajita", 0));
        players.add(new Player("Chhawi", 0));
        players.add(new Player("Mummy", 0));
        Board board = new Board(100);
        board.addSnakes(new Position(99, 2));
        board.addSnakes(new Position(80, 45));
        board.addLadders(new Position(10, 90));
        board.addLadders(new Position(25, 55));
        Game game = new Game(players, board);
        game.playGame();
    }
}
