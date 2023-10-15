import javax.swing.*;
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

    private int currentCellRow;
    private int currentCellCol;

    SudokuGUI(SudokuBoard board){
        this.board = board;
        int[][] boardValues = board.getBoard();

        currentCellRow = -1;
        currentCellCol = -1;

        frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        initializeSudokuGrid(boardValues);
        frame.add(gridPanel, BorderLayout.CENTER);

        initalizeInputGrid();
        frame.add(inputPanel, BorderLayout.EAST);

        frame.pack();
        frame.setVisible(true);
    }

    private void initializeSudokuGrid(int[][] boardValues){
        sudokuGrid = new JButton[9][9];
        gridPanel = new JPanel(new GridLayout(9, 9));

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                int value = boardValues[i][j];
                JButton btn = new JButton(value != 0 ? String.valueOf(value) : "");
                sudokuGrid[i][j] = btn;
                btn.setPreferredSize(new Dimension(50, 50));
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JButton clickedButton = (JButton) e.getSource();

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
                            sudokuGrid[currentCellRow][currentCellCol].setText(clickedButton.getText());
                            SudokuMove move = new SudokuMove(currentCellRow, currentCellCol, Integer.parseInt(clickedButton.getText()));
                            board.makeMove(move);
                        }
                        
                    }
                });
                inputPanel.add(inputGrid[i][j]);
                inputVal++;
            }
        }
    }
}
