public class SudokuGame {
    private SudokuBoard board;
    private SudokuGUI gui;
    private int lives;

    public SudokuGame(){
         lives = 3;
         board = new SudokuBoard();
         gui = new SudokuGUI(board, this);
    }

    public SudokuGame(SudokuSave save){
        lives = save.getLives();
        board = new SudokuBoard(save.getBoard(), save.getSolvedBoard());
        gui = new SudokuGUI(board, this);
    }
    
    public int getLives(){
        return lives;
    }

    public void removeLife(){
        lives--;
    }
}
