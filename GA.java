import java.util.ArrayList;
import java.util.Random;

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
    private int populationSize = 10;
    private int generations = 100;
    //mutation rate, crossover rate?
    private ArrayList<int[][]> population = new ArrayList<>();
    private int[][] initialBoard;

    public GA(int[][] initialBoard){
        this.initialBoard =  initialBoard;
    }

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

    /* 
    //minimization (fitness will be how many incorrect cells)
    private int fitness(){

    }

    private int tournamentSelection(){

    }

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
    }

}
