public class SudokuSave {
    private int[][] board;
    private int lives;
    private int[][] solvedBoard;
    private boolean noSave;

    public SudokuSave(int[][] board, int lives, int[][] solvedBoard,boolean noSave){
        this.board = board;
        this.lives = lives;
        this.solvedBoard = solvedBoard;
        this.noSave = noSave;
    }

    public int[][] getBoard(){
        return board;
    }

    public int getLives(){
        return lives;
    }

    public int[][] getSolvedBoard(){
        return solvedBoard;
    }

    public boolean getNoSave(){
        return noSave;
    }
}
