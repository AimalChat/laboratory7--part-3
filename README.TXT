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

30. Rewrite the two if-else statements in the loop of the update method of the Automaton class of 
automaton-v1 so that the assignments to left and right use the conditional operator. 

30b.

31. Why have we created a new array, nextState, in the update method, rather than using the 
state array directly? Change the assignment to nextState to be an assignment to state in the 
loop body, to see if it makes any different to the automaton’s behavior. If it does, can you
explain why? 

31b.

32. Can you find a way to avoid the problems illustrated in the previous exercise without 
using a new array? Hint: how many cells’ old values need to be retained on each iteration? In 
your opinion, which version is better: using a complete array or your solution? Justify your 
answer. 

32b.

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

33b.

34. Add a method calculateNextState to the Automaton class that takes the three values, left, 
center, and right, and returns the calculation of the value of the next state. The next state 
of a cell was previously calculated using the following line of code: 
nextState[i] = (left + center + right) % 2; 
Change this line to make use of your new method instead. 

34b.

35. Experiment with different ways to calculate the next state of a cell through combining 
the values of left, center, and right, always ensuring that the result is calculated modulo 
2. You don’t need to include all three values in the calculation. Here are a couple of ways 
you might like to try: 
▪ (left + right) % 2
 ▪ (center + right + center * right + left * center * right) % 2 
How many different sets of unique rules do you think there are to calculate a cell’s next 0 
or 1 state given the three binary values in left, center, and right? Is there an infinite 
number of possibilities?

35b.

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

36b.

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
