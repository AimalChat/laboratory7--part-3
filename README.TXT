Project: automaton
Aothors: David J. Barnes and Michael Kölling

This project is part of the material of the book

   Objects First with Java - A Practical Introduction using BlueJ
   Seventh edition
   David J. Barnes and Michael Kölling

It is discussed in chapter 7.

26. The following exercises work best if you ensure that the BlueJ Terminal window does not 
have the “Clear screen at method call” option turned on. Open the automaton-v1 project and 
create an AutomatonController object. A line containing a single * should be output in the 
terminal window, representing the initial state of the automaton. Call the step method a few 
times to see how the state progresses. Then try the run method. 

26b.Makes interesting repeating patterns!

27. After running the automaton, clear the Terminal window, call the reset method and repeat 
the process in the previous exercise. Do exactly the same patterns emerge? 

27b. Yes, it is the same output as the first time around.

28. How is the fill method of the java.util.Arrays class used in the reset method of 
Automaton?

28b. "Arrays.fill(state, 0);", it simply places 0 in all of the slots in the entire array.
In a way, it is putting the equivilant of null in all of its cells

29. Alter the reset method of Automaton so that more than one cell is initially in the 1-
state. Do different patterns emerge this time? Are those patterns deterministic – i.e., 
does a particular starting state for the automaton always result in the same pattern emerging? 

29b.
/**
     * Reset the automaton.
     */
    public void reset()
    {
        Arrays.fill(state, 0);
        // Seed the automaton with a single 'on' cell.
        state[numberOfCells / 2] = 1;
        state[0] = 1;
    }

Yes, it is different. The start of the automaton is now twice as present with the 
addition of my code. So now both dots lead to the same pattern which mesh together once their 
patterns start touching together, not seemlessly but they do mesh.

30. Rewrite the two if-else statements in the loop of the update method of the Automaton 
class of automaton-v1 so that the assignments to left and right use the conditional operator. 

30b.
    /**
     * Update the automaton to its next state.
     */
    public void update()
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
            
            nextState[i] = (left + center + right) % 2;
        }
        state = nextState;
    }

31. Why have we created a new array, nextState, in the update method, rather than using the 
state array directly? Change the assignment to nextState to be an assignment to state in the 
loop body, to see if it makes any different to the automaton’s behavior. If it does, can you
explain why? 

31b.Nothing appears on the terminal. I think its because, by not using a newState array, we 
keep on simply overwritting whatever calculations done onto the original state. So the 
nextState array is nothing, because we continously overwrite it. Also, we never end up using 
the newly created newState array because we still asign state to newState at the end of the 
method.

32. Can you find a way to avoid the problems illustrated in the previous exercise without 
using a new array? Hint: how many cells’ old values need to be retained on each iteration? In 
your opinion, which version is better: using a complete array or your solution? Justify your 
answer. 

32b.We need to retain the first and second values of the array, because they are the very 
begining of this array, so left is out of bounds when we start, so left = 0 and center is 
simply the first real cell to be found, the first cell of our array. So that leaves right, 
and right is always going to change depending on the loop. So after an iteration, center 
becomes the new left and the right becomes the new center. See how right is always being 
retrieved from the old array in order to calculate our state. In a way, we are making a 
sliding window of 3 cells. Each iteration, moves it by one, where, we need to get the newest 
value in the index i + 1. So we only need to remember that new number being found, not the 
left or center values.
/**
     * Update the automaton to its next state.
     */
    public void update()
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
33. What we really want to see in the body of a loop are statements to be executed on every 
iteration. Use the following code to rewrite the update method and avoid special cases for 
the first and last iterations:
int left = 0;
 int center = state[0];
 for (int i=0; i<state.length; i++){
 int right = i + 1 < state.length ? state[i+1] : 0;
 nextState[i] = (left + center + right) % 2;
 left = center;
 center = right;
 }

33b.ok!
/**
     * Update the automaton to its next state.
     */
    public void update()
    {
        // Build the new state in a separate array.
        int[] nextState = new int[state.length];
        // Naively update the state of each cell
        // based on the state of its two neighbors.
        int left = 0;
        int center = state[0];
        for (int i=0; i<state.length; i++){
            int right = i + 1 < state.length ? state[i+1] : 0;
            nextState[i] = (left + center + right) % 2;
            left = center;
            center = right;
        }
        state = nextState;
    }

34. Add a method calculateNextState to the Automaton class that takes the three values, left, 
center, and right, and returns the calculation of the value of the next state. The next state 
of a cell was previously calculated using the following line of code: 
nextState[i] = (left + center + right) % 2; 
Change this line to make use of your new method instead. 

34b.ok!
public int calculateNextState(int left, int center, int right)
    {
        int nextState;
        nextState = (left + center + right) % 2;
        return nextState;
    }

35. Experiment with different ways to calculate the next state of a cell through combining 
the values of left, center, and right, always ensuring that the result is calculated modulo 
2. You don’t need to include all three values in the calculation. Here are a couple of ways 
you might like to try: 
▪ (left + right) % 2
 ▪ (center + right + center * right + left * center * right) % 2 
How many different sets of unique rules do you think there are to calculate a cell’s next 0 
or 1 state given the three binary values in left, center, and right? Is there an infinite 
number of possibilities?

35b. I think so, because the modulo operator usually return either a 1 or a 0 to determine if 
it is either an odd or even number. So yes, I do think there is an infinity number of 
possibilities.

36. Improve your code to avoid having to keep testing for the end of the array when setting 
right. Extend the array by one element that contains the value 0 in order to serve as the 
right-hand neighbor of the last cell. However, that neighbor will not be included as a cell 
of the automaton. It will never be changed. 
The revised body of the update method should look like:
 int left = 0;
 int center = state[0];
 for (int i=0; i<numberOfCells; i++){
 int right = state[i+1];
 nextState[i] = calculateNextState(left, center, right);
 left = center;
 center = right;
 } 

36b. This one was hard, because the automaton project is not in the 5th edition!
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

37. Experiment with different initialization patterns of the lookup table in automaton-v4. 

37b.

38. Challenge exercise This exercise involves setting up the state table based on an 
additional integer parameter passed to the constructor of Automaton. The eight 1/0 values in 
the lookup table could be interpreted as eight binary digits encoding a single numerical 
value in the range 0–255, in much the same way as we have interpreted the triplet of cell 
states as representing an integer in the range 0–7. The least-significant bit would be the 
value in stateTable[0] and the most significant bit in stateTable[7]. So the pattern used in 
the initialization of stateTable, above, would be an encoding of the value 146 (128 + 16 + 
2). In fact, that is exactly how the 256 Wolfram codes are used – a code in the range 0-255 
is turned into its 8-bit binary representation and the binary digits are used as the settings 
of the state table. Add an integer parameter that holds a Wolfram code to the constructor of 
Automaton and use it to initialize stateTable. To do this you will need to find out 
how to extract the individual binary digits from an integer. Use the least-significant bit to 
set element 0 of the lookup table, the next to set element 1, and so on. Hint: You could 
either do this using integer operations such as % and /, or bit- manipulation operators such 
as >> and &.

38b.
