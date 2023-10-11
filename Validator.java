import java.util.HashMap;

public class Validator {

    private int[] getRowValues(SudokuBoard gameBoard, int row){
        int[][] board = gameBoard.getBoard();
        int[] rowValues = new int[9];
        
        for(int i=0; i < 9; i++){
            rowValues[i] = board[row][i];
        }

        return rowValues;
    }

    private int[] getColumnValues(SudokuBoard gameBoard, int col){
        int[][] board = gameBoard.getBoard();
        int[] columnValues = new int[9];

        for(int i=0; i < 9; i++){
            columnValues[i] = board[i][col];
        }

        return columnValues;
    }

    private int[] getSquareValues(SudokuBoard gameBoard, int row, int col){
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
        //each number should have 27 occurences as as should appear 9 times in each row/column/square
        HashMap<Integer, Integer> numberOccurrences = new HashMap<Integer, Integer>();

        for(int i=0; i < 9; i++){
            numberOccurrences.put(i+1, 0);
        }
    
        //for rows
        for(int i=0; i < 9; i++){
            int[] rowValues = getRowValues(gameBoard, i);
            for(int j=0; j < 9; j++){
                int value = rowValues[j];
                if(value == 0){
                    return false;
                }
                numberOccurrences.put(value, numberOccurrences.get(value) + 1);
            }
        }

        //for columns
        for(int i=0; i < 9; i++){
            int[] colValues = getColumnValues(gameBoard, i);
            for(int j=0; j < 9; j++){
                int value = colValues[j];
                if(value == 0){
                    return false;
                }
                numberOccurrences.put(value, numberOccurrences.get(value) + 1);
            }
        }

        //for squares
        for(int i=0; i < 9; i+=3){
            for(int j=0; j < 9; j+=3){
                int[] squareValues = getSquareValues(gameBoard, i, j);
                for(int k=0; k < 9; k++){
                    int value = squareValues[k];
                    if(value == 0){
                        return false;
                    }
                    numberOccurrences.put(value, numberOccurrences.get(value) + 1);
                }
            }
        }

        //check occurences are 27 for each number 1-9
        for(int i=0; i < 9; i++){
            int occurences = numberOccurrences.get(i+1);
            if(occurences != 27){
                return false;
            }
        }

        return true;
    }

}
