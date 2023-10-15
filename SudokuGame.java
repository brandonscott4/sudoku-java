public class SudokuGame {
    private SudokuBoard board;
    private SudokuGUI gui;
    private InputHandler ih;
    private Validator validator;
    private int lives;

    public SudokuGame(){
         board = new SudokuBoard();
         gui = new SudokuGUI(board);
         ih = new InputHandler();
         validator = new Validator();
         lives = 3;
    }

    public void startGame(){
        boolean gameOver = false;
        while(!gameOver){
            board.displayBoard();
            String input = ih.getPlayerInput();

            if(input.equals("move")){
                SudokuMove move = ih.getPlayerMove();
                boolean validMove = validator.checkMove(board, move);
                
                if(validMove){
                    board.makeMove(move);
                } else{
                    lives--;

                    if(lives == 0){
                        gameOver = true;
                        System.out.println("Game over. You ran out of lives.");
                    } else{
                        System.out.println("Invalid move. You have " + lives + " lives.");
                    }

                }
            } else{
                boolean validBoard = validator.validBoard(board);
                if(validBoard){
                    gameOver = true;
                    System.out.println("You win!");
                } else {
                    System.out.println("Board is not valid");
                }
            }
        }
    }

    public static void main(String[] args) {
        SudokuGame game = new SudokuGame();
        game.startGame();
    }
}
