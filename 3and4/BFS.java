import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: vivek.rajendran
 * Date: 29/11/15
 * Time: 1:31 PM
 *  Input: Start state and Goal State in Command Line Arguments as
 *             1 2 3 4 5 6 7 8 9 10 11 12 13 14 0 15  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0
 *         with 0 denoting Empty tile
 *
 */

public class BFS {

    private Set<State> tracker = new HashSet<State>();
    private Queue<State> toVisit = new LinkedList<State>();

    public State getStartState() {
        return startState;
    }

    public State getGoalState() {
        return goalState;
    }

    private State startState, goalState;

    public BFS(State startState)
    {
        this.startState = startState;
        createGoalState();
    }

    public BFS(State startState, State goalState)
    {
        this.startState = startState;
        this.goalState  = goalState;
    }

    private void createGoalState()
    {
        int puzzleSize = startState.getPuzzleSize();
        int[][] goal = new int[puzzleSize][puzzleSize];

        for(int row = 0; row < puzzleSize; row++)
            for(int col = 0; col < puzzleSize; col++)
                goal[row][col] = row * puzzleSize + col + 1;

        goal[puzzleSize-1][puzzleSize-1] = 0;
        goalState = new State(goal,puzzleSize-1,puzzleSize-1,null,null);
    }


    public boolean traverse()
    {
        toVisit.add(startState);
        State nextState;

        while(!toVisit.isEmpty())
        {
            State visiting = toVisit.poll();
            tracker.add(visiting);

            if(visiting.isEquals(goalState))
            {
                goalState = visiting;
                return true;
            }

            nextState  = visiting.right();
            if(nextState != null && ! tracker.contains(nextState))
            {
                tracker.add(nextState);
                toVisit.add(nextState);
            }

            nextState = visiting.left();
            if(nextState != null && ! tracker.contains(nextState))
            {
                tracker.add(nextState);
                toVisit.add(nextState);
            }

            nextState = visiting.up();
            if(nextState != null && ! tracker.contains(nextState))
            {
                tracker.add(nextState);
                toVisit.add(nextState);
            }

            nextState = visiting.down();
            if(nextState != null && ! tracker.contains(nextState))
            {
                tracker.add(nextState);
                toVisit.add(nextState);
            }

        }
        return false;
    }


    public static void main(String[] args)
    {
         if(args.length < 4)
         {
             System.out.println("Puzzle size less than that of 4-Puzzle");;
         }


          /* These checks ensure the inputs(start & goal states) form a Perfect Square*/
         int root =  (int)Math.pow(args.length/2 , 0.5);
         int totalSquares = root*root;

         if( totalSquares*2 != args.length)
         {
             System.out.println("Puzzle is not a perfect square");
             return;
         }

         /* Create the Start State from 1st Half of command line args */
         int[][] start = new int[root][root];
         int emptyTileRow = root-1, emptyTileCol = root-1;
         for(int row = 0; row < root ; row++)
         {
             for(int col = 0; col < root; col++)
             {
                 start[row][col] = Integer.parseInt(args[row*root + col]);
                 if(start[row][col] == 0)
                 {
                     emptyTileRow = row;
                     emptyTileCol = col;
                 }
             }
         }
         State startState = new State(start, emptyTileRow, emptyTileCol, null, null);

         /* Create the Goal State from 2nd Half of command line args */
         int[][] goal  = new int[root][root];
         for(int row = 0; row < root ; row++)
         {
            for(int col = 0; col < root; col++)
            {
                goal[row][col] = Integer.parseInt(args[totalSquares + row*root + col]);
                if(start[row][col] == 0)
                {
                    emptyTileRow = row;
                    emptyTileCol = col;
                }
            }
         }
         State goalState  = new State(goal, emptyTileRow, emptyTileCol, null, null);



         BFS bfs = new BFS(startState, goalState);

         /* BFS traverse() will return TRUE on reaching GOAL state*/
         if(bfs.traverse())
         {
             Stack<State> solution = new Stack<State>();

             /* We walk through the nodes from Goal to Start path and PUSH them into Stack*/
             State solutionNode =  bfs.getGoalState();
             while(solutionNode != null)
             {
                 solution.add(solutionNode);
                 solutionNode = solutionNode.getPreviousState();
             }

             /* POPing the nodes in the Solution Stack*/
             while(!solution.isEmpty())
             {
                 State node = solution.pop();
                 node.print();
             }
         }
         else
         {
            System.out.println("Explored all Possibilities. No Solution Exists");
         }
    }
}
