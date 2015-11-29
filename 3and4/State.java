import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: vivek.rajendran
 * Date: 29/11/15
 * Time: 5:55 PM
 * State:  Variables
 *         1. Current Board State (sequence of squares across the row from 1 to Last)
 *         2. Empty Tile position
 *         3. Previous Board State
 *         4. Move which resulted Current State from Previous State.
 *         Methods:
 *         1. To move the Empty Tile in all 4 directions with checks.
 *            Invalid Move(outside board) will return null
 *         2. isEquals() : to compare every current node with GOAL Node
 *         3. swap(): to swap Empty tile with any other Number Tile
 *         4. getMove() print() : cosmetic methods to print the Board Moves
 */

public class State
{
    int[][] current;
    int puzzleSize;
    int emptyTileRow, emptyTileCol;
    State previousState;
    MoveTypes toMove;

    public State(int[][] startState, int emptyTileRow, int emptyTileCol, State previousState, MoveTypes toMove)
    {
        this.current = startState;
        this.puzzleSize = startState.length;
        this.emptyTileRow = emptyTileRow;
        this.emptyTileCol = emptyTileCol;
        this.previousState = previousState;
        this.toMove =  toMove;
    }

    public int getPuzzleSize()
    {
        return puzzleSize;
    }

    State getPreviousState()
    {
        return previousState;
    }

    private int[][] copy()
    {
        int[][] copied = new int[puzzleSize][puzzleSize];
        for(int row = 0; row < puzzleSize; row++)
            copied[row] = Arrays.copyOf(current[row], puzzleSize);
        return copied;
    }
    public State right()
    {
        State nextState = null;
        if(emptyTileCol != puzzleSize-1)
        {
            int[][] next =  copy();
            swap(next, emptyTileRow, emptyTileCol, emptyTileRow, emptyTileCol + 1);
            nextState = new State(next,emptyTileRow, emptyTileCol+1, this, MoveTypes.RIGHT);
        }
        return nextState;
    }

    public State left()
    {
        State nextState = null;
        if(emptyTileCol != 0)
        {
            int[][] next =  copy();
            swap(next, emptyTileRow, emptyTileCol, emptyTileRow, emptyTileCol-1);
            nextState = new State(next, emptyTileRow, emptyTileCol-1, this, MoveTypes.LEFT);
        }
        return nextState;
    }

    public State up()
    {
        State nextState = null;
        if(emptyTileRow != 0)
        {
            int[][] next =  copy();
            swap(next, emptyTileRow, emptyTileCol, emptyTileRow-1, emptyTileCol);
            nextState = new State(next,emptyTileRow-1, emptyTileCol, this, MoveTypes.UP);
        }
        return nextState;
    }

    public State down()
    {
        State nextState = null;
        if(emptyTileRow != puzzleSize-1)
        {
            int[][] next =  copy();
            swap(next, emptyTileRow, emptyTileCol, emptyTileRow+1, emptyTileCol);
            nextState = new State(next, emptyTileRow+1, emptyTileCol, this, MoveTypes.DOWN);
        }
        return nextState;
    }

    public boolean isEquals(State givenState)
    {
        for(int row = 0; row < puzzleSize; row++)
        {
            for(int col = 0; col < puzzleSize; col++)
            {
                if(current[row][col] != givenState.current[row][col])
                    return false;
            }
        }
        return true;
    }

    private void swap(int[][] input, int fromTileRow, int fromTileCol, int toTileRow, int toTileCol)
    {
        if(!(fromTileRow==toTileRow && fromTileCol == toTileCol))
        {
            int temp = input[fromTileRow][fromTileCol];
            input[fromTileRow][fromTileCol] = input[toTileRow][toTileCol];
            input[toTileRow][toTileCol] = temp;
        }
    }

    public String getMove()
    {
        String move = "";
        if(toMove == MoveTypes.RIGHT)
        {
            move = emptyTileRow+","+ (emptyTileCol-1) +" => "+ emptyTileRow +","+ emptyTileCol;
        }
        if(toMove == MoveTypes.LEFT)
        {
            move = emptyTileRow+","+ (emptyTileCol+1) +" => "+ emptyTileRow +","+ emptyTileCol;
        }
        if(toMove == MoveTypes.UP)
        {
            move = (emptyTileRow+1)+","+ emptyTileCol +" => "+ emptyTileRow +","+ emptyTileCol;
        }
        if(toMove == MoveTypes.DOWN)
        {
            move = (emptyTileRow-1)+","+ emptyTileCol +" => "+ emptyTileRow +","+ emptyTileCol;
        }

        if(move.isEmpty()) return "Start State:";
        return "Moved: " + move;
    }

    public void print()
    {
        System.out.println(getMove());
        for(int row = 0; row < puzzleSize; row++)
        {
            for(int col = 0; col < puzzleSize; col++)
            {
                System.out.print(current[row][col] + " ");
            }
            System.out.println("");
        }
        System.out.println("========================");
    }
}
