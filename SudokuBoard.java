public class SudokuBoard{

    private int[][] board;

    public SudokuBoard(){
        board = new int[9][9];
        //calling here as only testing with one puzzle for now
        initialize();
    }

    public void initialize(){
        int[][] initialValues = {
            {6, 8, 0, 0, 0, 7, 0, 0, 0},
            {0, 1, 5, 0, 4, 2, 8, 0, 0},
            {4, 0, 0, 0, 6, 0, 1, 3, 0},
            {1, 3, 2, 6, 8, 0, 7, 0, 0},
            {5, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 6, 0, 2, 0, 0, 3, 0, 5},
            {8, 7, 1, 0, 2, 0, 0, 5, 0},
            {0, 0, 9, 0, 5, 1, 2, 6, 7},
            {2, 0, 0, 0, 0, 3, 9, 0, 0}
        };

        int[][] completedSudokuBoard = {
            {6, 3, 9, 5, 7, 4, 1, 8, 2},
            {5, 4, 1, 8, 2, 9, 3, 7, 6},
            {7, 8, 2, 6, 1, 3, 9, 5, 4},
            {1, 9, 8, 4, 6, 7, 5, 2, 3},
            {3, 6, 5, 9, 8, 2, 4, 1, 7},
            {4, 2, 7, 1, 3, 5, 8, 6, 9},
            {9, 5, 6, 7, 4, 8, 2, 3, 1},
            {8, 1, 3, 2, 9, 6, 7, 4, 5},
            {2, 7, 4, 3, 5, 1, 6, 9, 8}
        };

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                board[i][j] = initialValues[i][j];
            }
        }
    }

    public int[][] getBoard(){
        return board;
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
        System.out.println("");
    }

    public void makeMove(SudokuMove move){
        board[move.getRow()][move.getCol()] = move.getValue();
    }
}