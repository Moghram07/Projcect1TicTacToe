import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] board = {
                {'1', '2', '3'},
                {'4', '5', '6'},
                {'7', '8', '9'}
        };
        int squarePlayed = 0; //track the number of played squares
        char userTurn = 'X';  //the user will be X
        char computerTurn = 'O';
        String endMessage = "Game Over";

        while (squarePlayed < 9) {
            gameBoard(board);
            //User's turn
            if (squarePlayed % 2 == 0) {
                handleMove(board, scanner, userTurn);
            } else {
                //Computer's turn
                handleMove(board, null, computerTurn); // Passing null for scanner as computer doesn't use it
            }
            squarePlayed++;
            //Check for win after each move
            if (checkWin(board, userTurn)) {
                gameBoard(board);
                System.out.println("Congratulations! You win!");
                break;
            } else if (checkWin(board, computerTurn)) {
                gameBoard(board);
                System.out.println("Computer wins!");
                break;
            }
        }
        if (squarePlayed == 9 && !checkWin(board, userTurn) && !checkWin(board, computerTurn)) {
            gameBoard(board);
            System.out.println("The game is a tie!");
        }
        System.out.println(endMessage);
        scanner.close();
    }
    //Method to handle user and computer move
    public static void handleMove(char[][] board, Scanner scanner, char turn) {
        if (turn == 'X') {
            System.out.println("Your turn. Choose a square number (1-9): ");
            int input = getUserInput(scanner);
            updateBoard(board, input, turn);
        } else {
            //Computer's turn
            int computerMove = getComputerMove(board);
            updateBoard(board, computerMove, turn);
            System.out.println("Computer chooses square number " + computerMove);
        }
    }

    public static void updateBoard(char[][] board, int position, char marker) {
        int row = (position - 1) / 3; //row index
        int col = (position - 1) % 3; //column index
        board[row][col] = marker; // update board
    }

    public static void gameBoard(char[][] board) {
        System.out.println(board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("- + - + -");
        System.out.println(board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("- + - + -");
        System.out.println(board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
        System.out.println();
    }

    public static int getUserInput(Scanner scanner) {
        while (true) {
            int input = scanner.nextInt();
            if (input >= 1 && input <= 9) {
                return input;
            } else {
                System.out.println("Invalid input. Please choose a number between 1 and 9.");
            }
        }
    }
    //generate a random number for the computer input
    public static int getComputerMove(char[][] board) {
        Random rand = new Random();
        int move;
        do {
            move = rand.nextInt(9) + 1;
        } while (!isValidMove(board, move));
        return move;
    }
    //method to check if the chosen number is valid
    public static boolean isValidMove(char[][] board, int position) {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        return board[row][col] != 'X' && board[row][col] != 'O';
    }
    //method to check who won the game
    public static boolean checkWin(char[][] board, char marker) {
        //Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == marker && board[i][1] == marker && board[i][2] == marker) {
                return true;
            }
        }
        //Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == marker && board[1][j] == marker && board[2][j] == marker) {
                return true;
            }
        }
        //Check diagonals
        if ((board[0][0] == marker && board[1][1] == marker && board[2][2] == marker) ||
                (board[0][2] == marker && board[1][1] == marker && board[2][0] == marker)) {
            return true;
        }
        return false;
    }
}
