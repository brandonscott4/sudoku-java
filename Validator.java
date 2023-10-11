public class Validator {

        private int[] checkRowValues(SudokuBoard gameBoard, int row){
        int[][] board = gameBoard.getBoard();
        int[] rowValues = new int[9];
        
        for(int i=0; i < 8; i++){
            rowValues[i] = board[row][i];
        }

        return rowValues;
    }

    private int[] checkColumnValues(SudokuBoard gameBoard, int col){
        int[][] board = gameBoard.getBoard();
        int[] columnValues = new int[9];

        for(int i=0; i < 8; i++){
            columnValues[i] = board[i][col];
        }

        return columnValues;
    }

    private int[] checkSquareValues(SudokuBoard gameBoard, int row, int col){
        int[][] board = gameBoard.getBoard();
        int i, j;
        
        int[] squareValues = new int[9];

        if(row <= 2){i = 0;}
        else if(row <= 5){i = 3;}
        else{i = 6;}

        if(col <= 2){j = 0;}
        else if(col <= 5){j = 3;}
        else{j = 6;}

        int iEdge = i + 3;
        int jEdge = j + 3;

        int arrayIndex = 0;
        for(; i < iEdge; i++){
            //have to initalize a variable so it 'resets' between iterations of the outerloop
            for(int jIndex = j; jIndex < jEdge; jIndex++){
                squareValues[arrayIndex] = board[i][jIndex];
                arrayIndex++;
            }
        }

        return squareValues;
    }
    
    public boolean checkMove(SudokuBoard gameBoard, SudokuMove move){
        //setting a cell value to empty (0) is always valid
        if(move.getValue() == 0){
            return true;
        }

        int[] rowValues = checkRowValues(gameBoard, move.getRow());
        int[] colValues = checkColumnValues(gameBoard, move.getCol());
        int[] squareValues = checkSquareValues(gameBoard, move.getRow(), move.getCol());

        for(int i=0; i<8; i++){
            if(rowValues[i] == move.getValue()){
                System.out.println("Invalid move");
                return false;
            }
        }

        for(int i=0; i<8; i++){
            if(colValues[i] == move.getValue()){
                System.out.println("Invalid move");
                return false;
            }
        }

         for(int i=0; i<8; i++){
            if(squareValues[i] == move.getValue()){
                System.out.println("Invalid move");
                return false;
            }
        }

        return true;
    }

}
