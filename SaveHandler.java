import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;

public class SaveHandler {

    File saveFile;
    
    public SaveHandler(){
        try {
            saveFile = new File("saves.txt");
            if(saveFile.createNewFile()){
                System.out.println("File created");
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void saveGame(int[][] gameBoard, int lives, int[][] solvedBoard){
        try {
            FileWriter writer = new FileWriter(saveFile);
            for(int i=0; i<9; i++){
                writer.write(Arrays.toString(gameBoard[i]));
                if(i!=8){
                    writer.write("\n");
                }
            }

            writer.write("\n");

            writer.write(Integer.toString(lives));

            writer.write("\n");

            for(int i=0; i<9; i++){
                writer.write(Arrays.toString(solvedBoard[i]));
                if(i!=8){
                    writer.write("\n");
                }
            }

            writer.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public SudokuSave loadGame(){
        int[][] board = new int[9][9];
        int[][] solvedBoard = new int[9][9];
        int lives = -1;
        boolean noSave = false;
        try {
            Scanner sc = new Scanner(saveFile);
            if(!sc.hasNextLine()){
                sc.close();
                return new SudokuSave(board, lives, solvedBoard, true);
            }
            int rowIndex = 0;
            while(rowIndex < 9){
                String line = sc.nextLine();
                line = line.substring(1, line.length()-1);
                String[] values = line.split(",");
                for(int i=0; i<9; i++){
                    board[rowIndex][i] = Integer.parseInt(values[i].trim());
                }
                rowIndex++;
            }

            lives = Integer.parseInt(sc.nextLine());

            rowIndex = 0;
            while(rowIndex < 9){
                String line = sc.nextLine();
                line = line.substring(1, line.length()-1);
                String[] values = line.split(",");
                for(int i=0; i<9; i++){
                    solvedBoard[rowIndex][i] = Integer.parseInt(values[i].trim());
                }
                rowIndex++;
            }
            sc.close();
        } catch (IOException e) {
            noSave = true;
            System.err.println(e);
        }

        SudokuSave save = new SudokuSave(board, lives, solvedBoard, noSave);
        return save;
    }

}
