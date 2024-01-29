import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Integer[][] field = new Integer[7][7];
        String[][] printField = new String[7][7];

        int shotsMade = 0;

        System.out.println("Welcome to the game \"Sea Battle\"!");
        System.out.println("Instructions:");
        System.out.println("1. The game is played on a 7x7 grid.");
        System.out.println("2. Ships are hidden on the grid, and you need to guess their locations.");
        System.out.println("3. Enter coordinates to make a guess.");
        System.out.println("4. If you hit a ship, it will be marked with 'H'.");
        System.out.println("5. If you miss, the cell will be marked with 'M'.");
        System.out.println("6. Sink an entire ship by hitting all of its parts.");
        System.out.println("7. The game ends when all enemy ships are sunk.");
        System.out.println("Good luck! Let the battle begin!");

    }

}