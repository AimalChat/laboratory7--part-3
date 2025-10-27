import java.util.Arrays;

/**
 * Model a 1D elementary cellular automaton.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.1
 */
public class Automaton
{
    // The number of cells.
    private final int numberOfCells;
    // The state of the cells.
    private int[] state;
    
    /**
     * Create a 1D automaton consisting of the given number of cells.
     * @param numberOfCells The number of cells in the automaton.
     */
    public Automaton(int numberOfCells)
    {
        this.numberOfCells = numberOfCells;
        state = new int[numberOfCells];
        // Seed the automaton with a single 'on' cell in the middle.
        state[numberOfCells / 2] = 1;
    }
    
    /**
     * Print the current state of the automaton.
     */
    public void print()
    {
        for(int cellValue : state) {
            if(cellValue == 1) {
                System.out.print("*");
            }
            else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }   
    
    public int calculateNextState35(int left, int center, int right)
    {
        int nextState;
        nextState = (center + right) % 2;
        return nextState;
    }
    
    public int calculateNextState(int left, int center, int right)
    {
        int nextState;
        nextState = (left + center + right) % 2;
        return nextState;
    }
    
    /**
     * Update the automaton to its next state.
     * Acts like a sliding window of 3 cells where only the right
     * neighbour of the center psositon is fetched from the copy of
     * state array. Now, do a sliding window motion by an increment of 1
     * So,after moving center position by one, we shift by one.
     * Meaning, center becomes new left, and right becomes new center.
     */
    public void update()
    {
        // Build the new state in a separate array.
        int[] nextState = new int[state.length];
        //Create the final extended array with ghost cell.(is the next step)
        final int[] copy = Arrays.copyOf(state, state.length + 1);
        // Naively update the state of each cell
        // based on the state of its two neighbors.
        int left = 0;
        //read from our new safe array
        //Though I believe reading from the OG state works?
        int center = copy[0];
        /**
         * The conditon is state.length, not copy.length because we want 
         * to read only from indexes available and not extra ghost cell 
         * from the copy one, I think.
         */
        for (int i=0; i<state.length; i++){
            //base on the copy, because it has that i+1 cell that state has not.
            int right = copy[i+1];
            //calculate to find either 1 or 0.
            nextState[i] = calculateNextState(left, center, right);
            //sliding window described above.
            left = center;
            center = right;
        }
        /**
         * We put all the newly found values of the nextState array into 
         * state in order to print it when doing whether run or step method.
         */
        state = nextState;
    }
    
    /**
     * Update the automaton to its next state.
     */
    public void updateOG3()
    {
        // Build the new state in a separate array.
        int[] nextState = new int[state.length];
        // Naively update the state of each cell
        // based on the state of its two neighbors.
        int left = 0;
        int center = state[0];
        for (int i=0; i<state.length; i++){
            int right = i + 1 < state.length ? state[i+1] : 0;
            nextState[i] = calculateNextState(left,center,right);
            left = center;
            center = right;
        }
        state = nextState;
    }
    
    /**
     * Update the automaton to its next state.
     */
    public void update32()
    {
        int left, center, right;
        left = 0;
        center = state[0];
        // Naively update the state of each cell
        // based on the state of its two neighbors.
        for(int i = 0; i < state.length; i++) {            
            right = (i + 1 < state.length) ? state[i + 1] : 0;
            
            state[i] = (left + center + right) % 2;
            
            left = center;
            center = right;
        }
    }
    
    /**
     * Update the automaton to its next state.
     */
    public void updateOG2()
    {
        // Build the new state in a separate array.
        int[] nextState = new int[state.length];
        // Naively update the state of each cell
        // based on the state of its two neighbors.
        for(int i = 0; i < state.length; i++) {
            int left, center, right;
            
            left = (i == 0) ? 0 : state[i - 1];
            
            center = state[i];
            
            right = (i + 1 < state.length) ? state[i + 1] : 0;
            
            state[i] = (left + center + right) % 2;
            
            
        }
        state = nextState;
    }
    
        /**
     * Update the automaton to its next state.
     */
    public void updateOG()
    {
        // Build the new state in a separate array.
        int[] nextState = new int[state.length];
        // Naively update the state of each cell
        // based on the state of its two neighbors.
        for(int i = 0; i < state.length; i++) {
            int left, center, right;
            
            if(i == 0){
                left = 0;
            }
            else {
                left = state[i - 1];
            }
            
            center = state[i];
            
            if(i + 1 < state.length) {
                right = state[i + 1];
            }
            else {
                right = 0;
            }
            nextState[i] = (left + center + right) % 2;
        }
        state = nextState;
    }
    
    /**
     * Reset the automaton.
     */
    public void reset()
    {
        Arrays.fill(state, 0);
        // Seed the automaton with a single 'on' cell.
        state[numberOfCells / 2] = 1;
    }
}
