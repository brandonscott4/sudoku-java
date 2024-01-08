import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenuGUI {

    private JFrame frame;
    private JPanel panel;
    private JLabel title;
    private JButton newGame;
    private JButton loadGame;
    private JPanel buttonPanel;

    public MainMenuGUI(){
        frame = new JFrame("Sudoku Menu");
        panel = new JPanel(new BorderLayout());
        buttonPanel = new JPanel(new FlowLayout());
        title = new JLabel("Sudoku", SwingConstants.CENTER);
        newGame = new JButton("New Game");
        loadGame = new JButton("Load");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,150);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                SudokuGame game = new SudokuGame();
            }
        });

        loadGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                SaveHandler handler = new SaveHandler();
                SudokuSave save = handler.loadGame();
                //need to add visual response to no save
                if(save.getNoSave() == false){
                    SudokuGame game = new SudokuGame(save);
                }
            }
        });

        buttonPanel.add(newGame);
        buttonPanel.add(loadGame);

        panel.add(title, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        MainMenuGUI menuGUI = new MainMenuGUI();
    }
}
