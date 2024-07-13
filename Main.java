import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] gameBoard = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
        char user = 'X';
        char computer = 'O';
        int squarePlayed = 0;
        System.out.println("Welcome to Tic Tac Toe");
        printBoard(gameBoard);
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        while (squarePlayed < 9) {
            int userInput=getUserInput(scanner, gameBoard);//user turn
            gamePlay(gameBoard, userInput, user);
            printBoard(gameBoard);
            squarePlayed++;
            if(checkWin(gameBoard, user)){
                System.out.println("Congratulations! you won!");
                break;
            }
            int computerInput; // now the computer turn
            do{
                computerInput = random.nextInt(9) + 1;
            } while(!isValid(gameBoard,computerInput));
            gamePlay(gameBoard, computerInput, computer);
            System.out.println("Computer chooses square number" + computerInput);
            printBoard(gameBoard);
            squarePlayed++;
            if (checkWin(gameBoard, computer)){
                System.out.println("Computer wins! buy a new computer.");
                break;
            }
        }
        if(squarePlayed == 9 && !checkWin(gameBoard, user) && !checkWin(gameBoard, computer)){
            System.out.println("The game is a tie!");
        }
    }
    public static void printBoard(char[][] board) {
        System.out.println(" |===|==|===|==|===|");
        for (char[] a : board) {
            for (char b : a) {
                System.out.print(" | " + b + " | ");
            }
            System.out.println("\n |===|==|===|==|===|");
        }
    }
    public static boolean isValid(char[][] board, int input) {
        int row = (input - 1) / 3;
        int col = (input - 1) % 3;
        return Character.isDigit(board[row][col]);
    }
    public static void gamePlay(char[][] board, int input, char player) {
        int row = (input - 1) / 3;
        int col = (input - 1) % 3;
        board[row][col] = player;
    }
    public static int getUserInput(Scanner scanner, char[][]board){
        int input;
        while(true){
            System.out.println("Enter a number from (1-9): ");
            input = scanner.nextInt();
            if(isValid(board, input)){
                break;
            }else{
                System.out.println("Invalid input. Please choose an available square number.");
            }
        }
        return input;
    }
    public static boolean checkWin(char[][] board, char marker){
        for(int i =0; i<3; i++) {
            if (board[i][0] == marker && board[i][1] == marker && board[i][2] == marker) {
                return true;
            }
        }
        for(int j =0; j<3; j++) {
            if (board[0][j] == marker && board[1][j] == marker && board[2][j] == marker) {
                return true;
            }
        }
            if((board[0][0]==marker&&board[1][1]==marker&&board[2][2]==marker)||
                    (board[0][2]==marker&&board[1][1]==marker&&board[2][0]==marker)){
                return true;
            }

        return false;
    }
}
