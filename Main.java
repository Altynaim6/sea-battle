import java.util.*;
import java.util.Random;
import java.util.Scanner;

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

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                field[i][j] = 0;
            }
        }

        placeShip(field, 3);
        placeShip(field, 2);
        placeShip(field, 4);
        placeShip(field, 1);
        placeShip(field, 1);
        placeShip(field, 1);
        placeShip(field, 1);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                field[i][j] = 0;
            }
        }

        placeShip(field, 3);
        placeShip(field, 2);
        placeShip(field, 4);
        placeShip(field, 1);
        placeShip(field, 1);
        placeShip(field, 1);
        placeShip(field, 1);


        int allShipsCells = 11;
        playerGuess(field,printField,allShipsCells,shotsMade);
    }

    public static void displayField(String[][] printField, Integer[][] field) {
        System.out.println("   1  2  3  4  5  6  7");
        for (int i = 0; i < 7; i++) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < 7; j++) {
                printField[i][j] = " ≋ ";
                System.out.print(printField[i][j]);
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    public static void nextField(String[][] printField) {
        System.out.println("   1  2  3  4  5  6  7");
        for (int i = 0; i < 7; i++) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < 7; j++) {
                System.out.print(printField[i][j]);
            }
            System.out.println();
        }
    }
    public static void placeShip(Integer[][] field, int shipSize) {
        Random random = new Random();
        boolean placed = false;

        while (!placed) {
            int row = random.nextInt(7);
            int col = random.nextInt(7);
            boolean isVertical = random.nextBoolean();

            if (isValidPosition(field, row, col, shipSize, isVertical)) {
                if(shipSize == 1) {
                    for (int i = 0; i < shipSize; i++) {
                        if (isVertical) {
                            field[row + i][col] = 1;
                        } else {
                            field[row][col + i] = 1;
                        }
                    }
                } else if (shipSize == 2) {
                    for (int i = 0; i < shipSize; i++) {
                        if (isVertical) {
                            field[row + i][col] = 2;
                        } else {
                            field[row][col + i] = 2;
                        }
                    }
                } else if (shipSize == 4) {
                    for (int i = 0; i < shipSize - 2; i++) {
                        if (isVertical) {
                            field[row + i][col] = 4;
                        } else {
                            field[row][col + i] = 4;
                        }
                    }
                } else if (shipSize == 3) {
                    for (int i = 0; i < shipSize; i++) {
                        if (isVertical) {
                            field[row + i][col] = 3;
                        } else {
                            field[row][col + i] = 3;
                        }
                    }
                }
                placed = true;
            }
        }
    }

    public static boolean isValidPosition(Integer[][] field, int row, int col, int shipSize, boolean isVertical) {
        if (isVertical && row + shipSize > 7) {
            return false;
        }
        if (!isVertical && col + shipSize > 7) {
            return false;
        }

        for (int i = 0; i < shipSize; i++) {
            if (isVertical) {
                if (field[row + i][col] != 0 || hasAdjacentShip(field, row + i, col)) {
                    return false;
                }
            } else {
                if (field[row][col + i] != 0 || hasAdjacentShip(field, row, col + i)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean hasAdjacentShip(Integer[][] field, int row, int col) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;

                if (newRow >= 0 && newRow < 7 && newCol >= 0 && newCol < 7) {
                    if (field[newRow][newCol] != 0 && field[newRow][newCol] != 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void playerGuess(Integer[][] field, String[][] printField, int allShipsCells, int shotsMade) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String playerName = scanner.nextLine();
        displayField(printField, field);

        ArrayList<Integer> threeSquareShipsRow = new ArrayList<>();
        ArrayList<Integer> threeSquareShipsColumn = new ArrayList<>();
        ArrayList<Integer> twoSquareShipsRow = new ArrayList<>();
        ArrayList<Integer> twoSquareShipsColumn = new ArrayList<>();
        ArrayList<Integer> secondTwoSquareShipsRow = new ArrayList<>();
        ArrayList<Integer> secondTwoSquareShipsColumn = new ArrayList<>();

        while (allShipsCells > 0) {

            System.out.print("Enter Row and Column(1 to 7): ");
            int rowGuess = scanner.nextInt() - 1;
            int colGuess = scanner.nextInt() - 1;

            if (rowGuess < 0 || rowGuess > 6 || colGuess < 0 || colGuess > 6) {
                System.out.print("Invalid input. Enter Row and Column (1 to 7): ");
                continue;
            }

            if (printField[rowGuess][colGuess].equals(" H ") || printField[rowGuess][colGuess].equals(" M ") || printField[rowGuess][colGuess].equals(" S ")) {
                System.out.print("You have already entered this coordinates. Try again! ");
                continue;
            }

            if (field[rowGuess][colGuess] != 0) {
                if (field[rowGuess][colGuess] == 3) {
                    threeSquareShipsColumn.add(colGuess);
                    threeSquareShipsRow.add(rowGuess);
                } else if (field[rowGuess][colGuess] == 2) {
                    twoSquareShipsRow.add(rowGuess);
                    twoSquareShipsColumn.add(colGuess);
                } else if (field[rowGuess][colGuess] == 4) {
                    secondTwoSquareShipsRow.add(rowGuess);
                    secondTwoSquareShipsColumn.add(colGuess);
                }

                int currentIndexValue;
                System.out.print("Hit!\n");
                currentIndexValue = field[rowGuess][colGuess];
                field[rowGuess][colGuess] = 0;
                printField[rowGuess][colGuess] = " H ";
                allShipsCells--;

                if (isShipSunk(field, rowGuess, colGuess)) {
                    if(currentIndexValue == 3) {
                        for(int i = 0; i < 2; i++) {
                            printField[threeSquareShipsRow.get(i)][threeSquareShipsColumn.get(i)] = " S ";
                        }
                    }
                    else if (currentIndexValue == 2) {
                        for(int i = 0; i < 1; i++) {
                            printField[twoSquareShipsRow.get(i)][twoSquareShipsColumn.get(i)] = " S ";
                        }
                    }
                    else if (currentIndexValue == 4) {
                        for(int i = 0; i < 1; i++) {
                            printField[secondTwoSquareShipsRow.get(i)][secondTwoSquareShipsColumn.get(i)] = " S ";
                        }
                    }

                    System.out.println("Ship is Sunk!");
                    markSunkShip(field, printField, rowGuess, colGuess);
                }
            } else {
                System.out.print("Miss!\n");
                printField[rowGuess][colGuess] = " M ";
            }

            shotsMade++;
            clearScreen();
            nextField(printField);
        }

        System.out.println("Congratulations, " + playerName + "! You sank all the ships!");
        playerScores.put(playerName, playerScores.getOrDefault(playerName, 0) + shotsMade);
        System.out.print("Do you want to start another game? (yes/no): ");
        scanner.nextLine();
        String playAgain = scanner.nextLine();

        if (playAgain.equalsIgnoreCase("yes")) {
            playerGuess(field,printField,allShipsCells,shotsMade);
        } else {
            displayTopPlayers();
        }
        scanner.close();
    }
}