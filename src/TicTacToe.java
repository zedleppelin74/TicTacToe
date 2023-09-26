import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {

        char[][] board = new char[3][3];
        String currentChar = "X";
        String nextChar = "O";
        System.out.println("Welcome to the game! Player X is starting.");

        while(!checkEmptyBoard(board) && !checkWinner(board)) {

            printBoard(board);
            System.out.println("Enter row number, column number and either X or letter O, separated by a comma, or press Q to quit: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("Q")) {
                System.out.println("Quitting the game");
                break;
            }

            String[] inputArr = input.split(",");

            if (inputArr.length != 3) {
                System.out.println("Incorrect input!");
                continue;
            }

            if (!"123".contains(inputArr[0]) || !"123".contains(inputArr[1]) || !"XxOo".contains(inputArr[2])) {
                System.out.println("Incorrect input!");
                continue;
            }

            if (!currentChar.equalsIgnoreCase(inputArr[2])) {
                System.out.println("It's " + currentChar + "'s turn!");
                continue;
            }

            int row = Integer.parseInt(inputArr[0]) - 1;
            int col = Integer.parseInt(inputArr[1]) - 1;
            char value = inputArr[2].toUpperCase().charAt(0);

            if (board[row][col] == '\u0000') {
                board[row][col] = value;
            } else {
                System.out.println("Can't override value");
                continue;
            }

            String temp = currentChar;
            currentChar = nextChar;
            nextChar = temp;
        }

        if (checkWinner(board)) {
            printBoard(board);
            System.out.println(nextChar + " is the winner!");
        }

        if(checkEmptyBoard(board) && !checkWinner(board)) {
            printBoard(board);
            System.out.println("It's a draw!");
        }
    }

    public static boolean checkEmptyBoard(char[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '\u0000') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printBoard(char[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '\u0000') {
                    System.out.print("_ ");
                }
                else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static boolean checkWinner(char[][] board) {

        if (checkDiagonals(board)) return true;
        for (int i = 0; i < board.length; i++) {
            if(checkRow(board, i) || checkColumn(board, i)) return true;
        }
        return false;
    }

    public static boolean checkRow(char[][] board, int rowIdx) {

        for (int i = 1; i < board[rowIdx].length; i++) {
            if (board[rowIdx][i] == '\u0000') {
                return false;
            }
            if (board[rowIdx][i] != board[rowIdx][i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkColumn(char[][] board, int colIdx) {

        for (int i = 1; i < board[colIdx].length; i++) {
            if (board[i][colIdx] == '\u0000') {
                return false;
            }
            if (board[i][colIdx] != board[i - 1][colIdx]) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDiagonals(char[][] board) {

        boolean first = true;
        boolean second = true;

        int length = board.length;

        int firstCorner = board[0][0];
        int secondCorner = board[0][length-1];

        if (firstCorner == '\u0000') {
            first = false;
        }
        if (secondCorner == '\u0000') {
            second = false;
        }

        for(int i = 0; i < length; i++){
            if (board[i][i] != firstCorner) {
                first = false;
                break;
            }
        }

        for(int i = 0, j = length - 1; i < length; i++, j--){
            if (board[i][j] != secondCorner) {
                second = false;
                break;
            }
        }

        return first || second;
    }
}