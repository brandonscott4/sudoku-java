public class SudokuBoard{

    private int[][] board;

    public SudokuBoard(){
        board = new int[9][9];
        //calling here as only testing with one puzzle for now
        initialize();
    }

    public void initialize(){
        int[][] initialValues = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                board[i][j] = initialValues[i][j];
            }
        }
    }

    public void displayBoard(){
        for(int i = 0; i < 9; i++){
            if(i != 0 || i != 8){
                System.out.println("");
            }
            for(int j = 0; j < 9; j++){
                if(j != 8){
                    System.out.print(board[i][j] + "-");
                } else{
                    System.out.print(board[i][j]);
                }
            }
        }
    }

}