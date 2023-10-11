public class SudokuGame {
    private SudokuBoard board;
    private InputHandler ih;
    private Validator validator;

    public SudokuGame(){
         board = new SudokuBoard();
         ih = new InputHandler();
         validator = new Validator();
    }

    public void startGame(){
        boolean gameOver = false;
        while(!gameOver){
            board.displayBoard();
            SudokuMove move = ih.getPlayerInput();
            boolean validMove = validator.checkMove(board, move);
            
            if(validMove){
                board.makeMove(move);
            }
        }
    }

    public static void main(String[] args) {
        SudokuGame game = new SudokuGame();
        game.startGame();
    }
}
