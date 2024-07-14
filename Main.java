import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] gameBoard = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
        char user = 'X';
        char computer = 'O';
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic Tac Toe");
        printBoard(gameBoard);

        System.out.println("Choose the game mode:");
        System.out.println("1 - One round to win");
        System.out.println("2 - Three rounds to win");
        int gameMode = scanner.nextInt();
        System.out.println("Choose who start's the game:");
        System.out.println("1 - user \n2 - computer");
        int firstMove = scanner.nextInt();
        int roundsToWin = (gameMode == 1) ? 1 : 3; // Number of rounds to win
        int userWins = 0;
        int computerWins = 0;

        for (int round = 1; round <= roundsToWin; round++) {
            int squarePlayed = 0;
            boolean userTurn = (firstMove==1)? true : false; // to choose who start the game

            while (squarePlayed < 9) {
                if (userTurn) {
                    int userInput = getUserInput(scanner, gameBoard, user);
                    gamePlayAndValidate(gameBoard, userInput, user);
                    printBoard(gameBoard);
                    squarePlayed++;
                    if (checkWin(gameBoard, user)) {
                        System.out.println("Congratulations! You won this round!");
                        userWins++;
                        break;
                    }
                } else {
                    int computerInput;
                    Random random = new Random();
                    do {
                        computerInput = random.nextInt(9) + 1;
                    } while (!gamePlayAndValidate(gameBoard, computerInput, computer));
                    System.out.println("Computer chooses square number " + computerInput);
                    printBoard(gameBoard);
                    squarePlayed++;
                    if (checkWin(gameBoard, computer)) {
                        System.out.println("Computer wins this round! Better luck next time.");
                        computerWins++;
                        break;
                    }
                }
                // Switch turns
                userTurn = !userTurn;
                // Check for tie
                if (squarePlayed == 9 && !checkWin(gameBoard, user) && !checkWin(gameBoard, computer)) {
                    System.out.println("This round is a tie!");
                }
            }
            // Reset the board for the next round
            resetBoard(gameBoard);
        }

        // Determine overall game result
        if (userWins > computerWins) {
            System.out.println("Congratulations! You won the game " + userWins + " to " + computerWins + "!");
        } else if (computerWins > userWins) {
            System.out.println("Computer wins the game " + computerWins + " to " + userWins + ". Better luck next time!");
        } else {
            System.out.println("The game ends in a tie! Scores: User " + userWins + " - " + computerWins + " Computer");
        }

        scanner.close();
    }

    public static void printBoard(char[][] board) {
        System.out.println(" |===|==|===|==|===|");
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == 'X') {
                    System.out.print(" | \u001B[31mX\u001B[0m | "); // Red color for 'X'
                } else if (cell == 'O') {
                    System.out.print(" | \u001B[34mO\u001B[0m | "); // Blue color for 'O'
                } else {
                    System.out.print(" | " + cell + " | ");
                }
            }
            System.out.println("\n |===|==|===|==|===|");
        }
    }

    public static boolean gamePlayAndValidate(char[][] board, int input, char player) {
        int row = (input - 1) / 3;
        int col = (input - 1) % 3;
        if (Character.isDigit(board[row][col])) {
            board[row][col] = player;
            return true; // Valid move
        } else {
            return false; // Invalid move
        }
    }

    public static int getUserInput(Scanner scanner, char[][] board, char player) {
        int input;
        while (true) {
            System.out.println("Enter a number from (1-9): ");
            input = scanner.nextInt();
            if (input < 1 || input > 9) {
                System.out.println("Invalid input. Please choose a number between 1 and 9.");
                continue;
            }
            int row = (input - 1) / 3;
            int col = (input - 1) % 3;
            if (Character.isDigit(board[row][col])) {
                return input;
            } else {
                System.out.println("Invalid input. The square is already taken.");
            }
        }
    }

    public static boolean checkWin(char[][] board, char marker) {
        // Check rows, columns, and diagonals for a win
        return (board[0][0] == marker && board[0][1] == marker && board[0][2] == marker) ||
                (board[1][0] == marker && board[1][1] == marker && board[1][2] == marker) ||
                (board[2][0] == marker && board[2][1] == marker && board[2][2] == marker) ||
                (board[0][0] == marker && board[1][0] == marker && board[2][0] == marker) ||
                (board[0][1] == marker && board[1][1] == marker && board[2][1] == marker) ||
                (board[0][2] == marker && board[1][2] == marker && board[2][2] == marker) ||
                (board[0][0] == marker && board[1][1] == marker && board[2][2] == marker) ||
                (board[0][2] == marker && board[1][1] == marker && board[2][0] == marker);
    }

    public static void resetBoard(char[][] board) {
        char[][] initialBoard = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = initialBoard[i][j];
            }
        }
    }
}
