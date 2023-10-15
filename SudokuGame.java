public class SudokuGame {
    private SudokuBoard board;
    private SudokuGUI gui;
    private int lives;

    public SudokuGame(){
         board = new SudokuBoard();
         gui = new SudokuGUI(board, this);
         lives = 3;
    }
    
    public int getLives(){
        return lives;
    }

    public void removeLife(){
        lives--;
    }
    
    public static void main(String[] args) {
        SudokuGame game = new SudokuGame();
    }
}
