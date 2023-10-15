import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuGUI{
    
    private JFrame frame;
    private JPanel gridPanel;
    private JButton[][] sudokuGrid;
    private JPanel inputPanel;
    private JButton[][] inputGrid;
    private SudokuBoard board;
    private JPanel infoPanel;
    private JTextField gameMessage;
    private JButton checkWin;

    private int currentCellRow;
    private int currentCellCol;
    private JButton selectedBtn;

    private Validator validator;

    private SudokuGame game;

    SudokuGUI(SudokuBoard board, SudokuGame game){
        this.game = game;
        this.board = board;
        int[][] boardValues = board.getBoard();

        currentCellRow = -1;
        currentCellCol = -1;

        validator = new Validator();

        frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        initializeSudokuGrid(boardValues);
        frame.add(gridPanel, BorderLayout.CENTER);

        initalizeInputGrid();
        frame.add(inputPanel, BorderLayout.EAST);

        initalizeInfoPanel();
        frame.add(infoPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    private void initializeSudokuGrid(int[][] boardValues){
        sudokuGrid = new JButton[9][9];
        gridPanel = new JPanel(new GridLayout(9, 9));
        Border border = BorderFactory.createLineBorder(Color.black, 2);
        gridPanel.setBorder(border);

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                int value = boardValues[i][j];
                JButton btn = new JButton(value != 0 ? String.valueOf(value) : "");
                sudokuGrid[i][j] = btn;
                btn.setPreferredSize(new Dimension(60, 60));
                btn.setBackground(Color.white);
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JButton clickedButton = (JButton) e.getSource();
                        clickedButton.setBackground(Color.lightGray);
                        if(selectedBtn != null){
                            selectedBtn.setBackground(Color.white);
                        }
                        selectedBtn = clickedButton;
                        for(int i=0; i<9; i++){
                            for(int j=0; j<9; j++){
                                if(clickedButton == sudokuGrid[i][j]){
                                    currentCellRow = i;
                                    currentCellCol = j;
                                }
                            }
                        }
                    }
                });
                gridPanel.add(sudokuGrid[i][j]);
            }
        }
    }

    private void initalizeInputGrid(){
        inputGrid = new JButton[3][3];
        inputPanel = new JPanel(new GridLayout(3,3));
        
        int inputVal = 1;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                JButton btn = new JButton(String.valueOf(inputVal));
                inputGrid[i][j] = btn;
                btn.setPreferredSize(new Dimension(75, 75));
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        JButton clickedButton = (JButton) e.getSource();
                        if(currentCellRow != -1 && currentCellCol != -1){
                            SudokuMove move = new SudokuMove(currentCellRow, currentCellCol, Integer.parseInt(clickedButton.getText()));
                            boolean validMove = validator.checkMove(board, move);

                            if(validMove){
                                board.makeMove(move);
                                sudokuGrid[currentCellRow][currentCellCol].setText(clickedButton.getText());
                            } else{
                                game.removeLife();

                                if(game.getLives() == 0){
                                    endGame();
                                    setinfoMessage("Game Over, you ran out of lives.");
                                } else{
                                    setinfoMessage("Invalid move. You have " + game.getLives() + " lives.");
                                }
                            }
                        } else{
                            setinfoMessage("Select a cell before choosing a value.");
                        }
                        
                    }
                });
                inputPanel.add(inputGrid[i][j]);
                inputVal++;
            }
        }
    }

    private void initalizeInfoPanel(){
        infoPanel = new JPanel(new BorderLayout());
        gameMessage = new JTextField("Welcome to Sudoku!");
        infoPanel.add(gameMessage, BorderLayout.CENTER);
        checkWin = new JButton("Submit");
        checkWin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                boolean validBoard = validator.validBoard(board);
                if(validBoard){
                    endGame();
                    setinfoMessage("You Win!");
                } else {
                    setinfoMessage("Game board is not valid.");
                }
            }
        });

        infoPanel.add(checkWin, BorderLayout.EAST);
    }

    private void setinfoMessage(String msg){
        gameMessage.setText(msg);
    }

    private void endGame(){
        gridPanel.setEnabled(false);
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                sudokuGrid[i][j].setEnabled(false);
            }
        }

        inputPanel.setEnabled(false);
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                inputGrid[i][j].setEnabled(false);
            }
        }

        checkWin.setEnabled(false);
    }
}
