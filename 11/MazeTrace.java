/**
 * Created with IntelliJ IDEA.
 * User: vivek.rajendran
 * Date: 06/12/15
 * Time: 12:00 AM
 * Approach: DFS. From the current cell all 4 cells in each direction is checked
 *                with out getting into cycle
 *           Each Cell is taken and explored till the end
 * Input : 0s & 1s in matrix format. 0s -> blockers, 1s -> routes
 * Output: xs in the solution show the route from source to the goal
 */

import java.io.*;
import java.util.*;

public class MazeTrace
{
    static int ROW, COL;

    void print(String sol[][])
    {
        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++)
                System.out.print(" " + sol[i][j] +  " ");
            System.out.println();
        }
    }


    boolean isSafe(String maze[][], int x, int y)
    {
        return (x >= 0 && x < ROW && y >= 0 && y < COL  && maze[x][y].equalsIgnoreCase("1"));
    }


    boolean finder(String maze[][], String sol[][])
    {
        if (!finder(maze, 0, 0, sol))
        {
            return false;
        }
        return true;
    }


    boolean finder(String maze[][], int x, int y, String solution[][])
    {
        if (x == ROW - 1 && y == COL - 1)
        {
            solution[x][y] = "x";
            return true;
        }

        if (isSafe(maze, x, y))
        {
            solution[x][y] = "x";

            if ( isSafe(solution,x+1,y) && finder(maze, x+1, y, solution))
                return true;

            if ( isSafe(solution,x,y+1) && finder(maze, x, y+1, solution))
                return true;

            if ( isSafe(solution,x-1,y) && finder(maze, x-1, y, solution))
                return true;

            if ( isSafe(solution,x,y-1) && finder(maze, x, y-1, solution))
                return true;

            solution[x][y] = "1";
            return false;
        }
        return false;
    }

    public static void main(String args[])
    {
        MazeTrace route = new MazeTrace();

        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader("input.txt"));

            String line;
            List<String> rows = new ArrayList<String>();
            while ((line = br.readLine()) != null) {
                rows.add(line);
            }

            ROW = rows.size();
            String maze[][] = new String[ROW][];

            int rowNum = 0;
            for(String row : rows)
            {
                maze[rowNum++] = row.split(" ");
            }

            COL = maze[0].length;

            String solution[][] = new String[ROW][COL];

            for(int row=0; row < maze.length; row++)
                solution[row] = Arrays.copyOf(maze[row], maze[row].length);

            System.out.println("Input:");
            route.print(maze);
            if(route.finder(maze, solution))
            {
                System.out.println("Output:");
                route.print(solution);
            }
            else
            {
                System.out.print("No Route Exists");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}