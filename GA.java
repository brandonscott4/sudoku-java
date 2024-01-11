import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

//Maybe move this into a seperate part of the app that focuses on trying different algos

/*
 * generate initial population
    repeat
        rank the solutions
        repeat
            randomly select two solutions from the population
            randomly choose a crossover point
            recombine the solutions to produce n new solutions
            apply the mutation operator to the solutions
        until a new population has been produced
    until a solution is found or the maximum number of generations is reached
 */

public class GA {
    private final int populationSize = 10;
    private final int generations = 100;
    private final int tournamentSize = 3;
    //mutation rate, crossover rate?

    private ArrayList<int[][]> population = new ArrayList<>();
    private int[] fitness = new int[populationSize];
    private int[][] initialBoard;

    public GA(int[][] board){
        this.initialBoard =  board;
    }

    //ISSUE need to make sure only cells WE filled in get mutated and crossed-over as can't change initial puzzle

    //generate potential solutions with no duplicates in grids
    private void populate(){
        for(int i=0; i<populationSize; i++){
            int[][] initialBoardCopy = deepCopy(initialBoard);
            population.add(initialBoardCopy);
        }

        Random random = new Random();

        for(int i=0; i < populationSize; i++){
            int[][] solution = population.get(i);

            //[0][0]  [0][3]  [0][6]
            //[3][0]  [3][3]  [3][6]
            //[6][0]  [6][3]  [6][6]
            for(int j=0; j<=6; j+=3){
                for(int k=0; k<=6; k+=3){
                    //fill all squares with 1-9 for each solution
                    ArrayList<Integer> values = new ArrayList<>();
                    values.add(1);
                    values.add(2);
                    values.add(3);
                    values.add(4);
                    values.add(5);
                    values.add(6);
                    values.add(7);
                    values.add(8);
                    values.add(9);

                    //remove values from arraylist that exist in current square
                    for(int l=0; l<3; l++){
                        if(values.contains(solution[j+l][k])){
                            values.remove(values.indexOf(solution[j+l][k]));
                        }

                        if(values.contains(solution[j+l][k+1])){
                            values.remove(values.indexOf(solution[j+l][k+1]));
                        }

                        if(values.contains(solution[j+l][k+2])){
                            values.remove(values.indexOf(solution[j+l][k+2]));
                        }
                    }

                    //fill empty cells with randomly selected remaining values
                    for(int l=0; l<3; l++){
                        if(solution[j+l][k] == 0){
                            int value = values.get(random.nextInt(values.size()));
                            values.remove(values.indexOf(value));
                            solution[j+l][k] = value;
                        }

                        if(solution[j+l][k+1] == 0){
                            int value = values.get(random.nextInt(values.size()));
                            values.remove(values.indexOf(value));
                            solution[j+l][k+1] = value;
                        }

                        if(solution[j+l][k+2] == 0){
                            int value = values.get(random.nextInt(values.size()));
                            values.remove(values.indexOf(value));
                            solution[j+l][k+2] = value;
                        }
                    }
                }
            }

            //print board
            for (int row = 0; row < 9; row++) {
                if (row % 3 == 0 && row != 0) {
                    System.out.println("-----------|-----------|-----------");
                }
                for (int col = 0; col < 9; col++) {
                    if (col % 3 == 0 && col != 0) {
                        System.out.print("| ");
                    }
                    System.out.print(population.get(i)[row][col] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }


    //minimization (fitness will be how many incorrect cells)
    private void fitness(){
        int fitnessValue = 0;

        //ISSUE should i only be checking if the cells i have added/populated are invalid?
        for(int i=0; i<populationSize; i++){
            int[][] board = population.get(i);
            for(int j=0; j<9; j++){
                for(int k=0; k<9; k++){
                    //check for how many duplicates in rows and columns (I ensured grids contain no duplicates) ... not if the move is valid
                    fitnessValue = findDuplicates(board);
                    fitness[i] = fitnessValue;
                }
            }
        }
    }

    private int tournamentSelection(){
        Random rand = new Random();
        HashSet<Integer> indexes = new HashSet<>();

        while(indexes.size() < tournamentSize){
            indexes.add(rand.nextInt(populationSize));
        }

        Integer[] indexesArr = indexes.toArray(new Integer[tournamentSize]);
        int bestSolutionIndex = 0;

        for(int index : indexesArr){
            if(fitness[index] > fitness[bestSolutionIndex]){
                bestSolutionIndex = index;
            }
        }

        return bestSolutionIndex;
    }

    /*
    private void crossover(){

    }

    private void mutation(){
        
    }
    */
    
    private int[][] deepCopy(int[][] initialBoard){
        int[][] initialBoardCopy = new int[9][9];
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                initialBoardCopy[i][j] = initialBoard[i][j];
            }
        }

        return initialBoardCopy;
    }

    private int findDuplicates(int[][] initialBoard){
        int duplicates = 0;

        for(int i=0; i<9; i++){
            HashMap<Integer, Integer> columnDuplicates = new HashMap<>();
            HashMap<Integer, Integer> rowDuplicates = new HashMap<>();
            for(int j=0; j<9; j++){
                int rowCell =  initialBoard[i][j];
                if(rowDuplicates.containsKey(rowCell)){
                    rowDuplicates.put(rowCell, rowDuplicates.get(rowCell) + 1);
                } else {
                    rowDuplicates.put(rowCell, 1);
                }

                int colCell = initialBoard[j][i];
                if(columnDuplicates.containsKey(colCell)){
                    columnDuplicates.put(colCell, columnDuplicates.get(colCell) + 1);
                } else {
                    columnDuplicates.put(colCell, 1);
                }

            }

            for(HashMap.Entry<Integer, Integer> pair : rowDuplicates.entrySet()){
                int value = pair.getValue();
                if(value >=2){
                    duplicates += pair.getValue() - 1 ;
                }
            }

            for(HashMap.Entry<Integer, Integer> pair : columnDuplicates.entrySet()){
                int value = pair.getValue();
                if(value >=2){
                    duplicates += pair.getValue() - 1 ;
                }
            }

        }

        return duplicates;
    }

    public static void main(String[] args) {
        int[][] initialValues = {
            {6, 8, 0, 0, 0, 7, 0, 0, 0},
            {0, 1, 5, 0, 4, 2, 8, 0, 0},
            {4, 0, 0, 0, 6, 0, 1, 3, 0},
            {1, 3, 2, 6, 8, 0, 7, 0, 0},
            {5, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 6, 0, 2, 0, 0, 3, 0, 5},
            {8, 7, 1, 0, 2, 0, 0, 5, 0},
            {0, 0, 9, 0, 5, 1, 2, 6, 7},
            {2, 0, 0, 0, 0, 3, 9, 0, 0}
        };

        GA geneticAlgo = new GA(initialValues);
        geneticAlgo.populate();
        geneticAlgo.fitness();
        geneticAlgo.tournamentSelection();
    }

}
