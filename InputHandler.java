import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler(){
        scanner = new Scanner(System.in);
    }

    public SudokuMove getPlayerInput(){
        int row;
        int col;
        int value;

        while(true){

            System.out.println("Enter move 'row column value' each in range 1-9");
            String move = scanner.nextLine();

            String[] split = move.split(" ");

            if(split.length != 3){
                System.out.println("Expected 3 inputs and receieved " + split.length);
                continue;
            }

            try {
                //-1 to use 0 based indexing for board
                row = Integer.parseInt(split[0]) - 1;
                col = Integer.parseInt(split[1]) - 1;
                value = Integer.parseInt(split[2]);

            } catch (NumberFormatException e) {
                System.out.println("Please enter valid input (number number number)");
                continue;
            }

            if( (row < 0 || row > 8) || (col < 0 || col > 8) || (value < 1 || value > 9) ){
                System.out.println("Inputs must be in the range 1-9");
                continue;
            }

            break;
        }

        return new SudokuMove(row, col, value);
    }
}
