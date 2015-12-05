/**
 * User: Vivek Rajendran
 * Date: 05/12/15
 * Time: 4:15 PM
 * Usage : For Example,
 *             Invoke in Command prompt as > java TowersOfHanoi 6
 *             Here 6 is the number of disks in tower 1. We can give any other number to check
 *
 * Approach: Problem is broken down into
 *             moving top (N-1) disks from given tower to buffer tower  (recursively)
 *             moving the Nth disk from given tower to destination tower
 *             moving those (N-1) disks from buffer to destination tower (recursively)
 *  It takes POW(2,DisksCount)-1 steps to reach the solution
 */

import java.util.*;

public class TowersOfHanoi {
    private Stack<Integer> disks;  // Stack to hold the disks
    private int id;                // ID num of the tower
    private static int steps;      // To count steps to reach the solution

    public TowersOfHanoi(int i) {
        disks = new Stack<Integer>();
        id = i;
    }

    public int index() {
        return id;
    }

    public void add(int d) {
        if (!disks.isEmpty() && disks.peek() <= d) {
            // Checking if tower has some disk then the incoming disk d obey the size rule
            System.out.println("Error placing disk " + d);
        }
        else {
            disks.push(d);
        }
    }

    public void moveTopTo(TowersOfHanoi t) {
        int top = disks.pop();   // Pick the top disk from this tower
        t.add(top);              // add that disk to given tower t
        System.out.println( ++steps +". Disk: "+ top +"  Tower:  " + index() + " => " + t.index());
    }

    // Moving Disks in this tower to destination using the buffer tower
    public void moveDisks(int n, TowersOfHanoi destination, TowersOfHanoi buffer) {
        if (n > 0) {
            // 1. Moving top n-1 disks from this tower to buffer using destination tower
            this.moveDisks(n - 1, buffer, destination);
            // 2. Moving the Bottom most disk from this tower to destination using buffer
            moveTopTo(destination);
            // 3. Moving those n-1 disks in buffer tower to destination using tower recursively
            buffer.moveDisks(n - 1, destination, this);
        }
    }

    public static void main(String[] args) {
        // Default #Disks = 3
        int disksCount= 3;

        //Setting the #Disks if available/overridden by command prompt args
        if( args.length > 0 )
            disksCount =  Integer.parseInt(args[0]);

        // Initializing Towers with their ID numbers from 1 to 3
        TowersOfHanoi source      = new TowersOfHanoi(1);
        TowersOfHanoi middle      = new TowersOfHanoi(2);
        TowersOfHanoi destination = new TowersOfHanoi(3);

        // Adding Disks to Source tower
        for (int i = disksCount; i >= 1; i--) {
            source.add(i);
        }

        // Moving disks from Source Tower(1) to Destination Tower (3)
        // using Middle Tower (2) (recursively)
        source.moveDisks(disksCount, destination, middle);
    }
}
