import java.util.HashMap;

public class Validator {

    private int[] getRowValues(SudokuBoard gameBoard, int row){
        int[][] board = gameBoard.getSolvedBoard();
        int[] rowValues = new int[9];
        
        for(int i=0; i < 9; i++){
            rowValues[i] = board[row][i];
        }

        return rowValues;
    }

    private int[] getColumnValues(SudokuBoard gameBoard, int col){
        int[][] board = gameBoard.getSolvedBoard();
        int[] columnValues = new int[9];

        for(int i=0; i < 9; i++){
            columnValues[i] = board[i][col];
        }

        return columnValues;
    }

    private int[] getSquareValues(SudokuBoard gameBoard, int row, int col){
        int[][] board = gameBoard.getSolvedBoard();
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
        int[] rowValues = getRowValues(gameBoard, move.getRow());
        int[] colValues = getColumnValues(gameBoard, move.getCol());
        int[] squareValues = getSquareValues(gameBoard, move.getRow(), move.getCol());

        for(int i=0; i<9; i++){
            if(rowValues[i] == move.getValue()){
                return false;
            }
        }

        for(int i=0; i<9; i++){
            if(colValues[i] == move.getValue()){
                return false;
            }
        }

         for(int i=0; i<9; i++){
            if(squareValues[i] == move.getValue()){
                return false;
            }
        }

        return true;
    }

    public boolean validBoard(SudokuBoard gameBoard){
        int[][] playerBoard = gameBoard.getBoard();
        int[][] solvedBoard = gameBoard.getSolvedBoard();
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(playerBoard[i][j] != solvedBoard[i][j]){
                    return false;
                }
            }
       }
       
       return true;
    }

    public boolean solveWithBacktracking(SudokuBoard gameBoard){
        int[][] solvedBoard = gameBoard.getSolvedBoard();

        int[] emptyCell = findEmptyCell(solvedBoard);

        if(emptyCell == null){
            //board solved
            return true;
        }

        int row = emptyCell[0];
        int col = emptyCell[1];

        for(int value=1; value<=9; value++){
            SudokuMove move = new SudokuMove(row, col, value);
            if(checkMove(gameBoard, move)){
                solvedBoard[row][col] = value;

                if(solveWithBacktracking(gameBoard)){
                    return true;
                }

                solvedBoard[row][col] = 0;
            }
        }

        //will trigger backtracking as no value works for this cell in current board
        return false;
    }

    private int[] findEmptyCell(int[][] board){
        int[] emptyCell = new int[2];
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(board[i][j] == 0){
                    emptyCell[0] = i;
                    emptyCell[1] = j;
                    return emptyCell;
                }
            }
        }
        return null;
    }

    public boolean checkUserMove(SudokuBoard board, SudokuMove move){
        int[][] solvedBoard = board.getSolvedBoard();

        if(solvedBoard[move.getRow()][move.getCol()] == move.getValue()){
            return true;
        }

        return false;
    }

}
