/**
 * Created with IntelliJ IDEA.
 * User: vivek.rajendran
 * Date: 28/11/15
 * Time: 6:59 PM
 * Logic: 1. Generate every permutation of cities
 *        2. Calculate the travel distance for that permutation
 *        3. If   this travel distance < the previous ones
 *           Then update the optimal solution
 *        4. Order: O(n!) - As we check every possible permutation of cities
 */


public class BruteForceTSP
{
    private static TspSolution optimalSolution = new TspSolution();
    private static int[][] tspMatrix = {{ 0, 4, 8,10, 7,14,15},
                                        { 4, 0, 7, 7,10,12, 5},
                                        { 8, 7, 0, 4, 6, 8,10},
                                        {10, 7, 4, 0, 2, 5, 8},
                                        { 7,10, 6, 2, 0, 6, 7},
                                        {14,12, 8, 5, 6, 0, 5},
                                        {15, 5,10, 8, 7, 5, 0}
                                       };

    public static void main(String[] args)
    {

        int[] orderOfCities = new int[tspMatrix.length];
        for(int order=0 ; order < orderOfCities.length ; order++)
            orderOfCities[order] = order+1;
        permuteCities(orderOfCities, orderOfCities.length, 0);

        System.out.println("Optimal Distance = " + optimalSolution.getDistance());
        System.out.println("Optimal TSP Order : ");
        int [] tspOrder = optimalSolution.getTravelOrder();
        for(int i = 0; i < tspOrder.length ; i++)
        {
            int  from = tspOrder[i], to ;
            if(i != tspOrder.length - 1)
                to = tspOrder[i+1];
            else
                to = tspOrder[0];
            System.out.println(from + " -> " + to + " = " + tspMatrix[from-1][to-1]);
        }
    }



    private static void permuteCities (int orderOfCities[], int n, int i)
    {
        int	j;
        if (i == n)
        {
            //  Calculate Distance
            int distance = calculateDistance(orderOfCities);
            if(optimalSolution.getTravelOrder() == null || distance < optimalSolution.getDistance())
            {
                optimalSolution.setTravelOrder(orderOfCities.clone());
                optimalSolution.setDistance(distance);
            }
        }
        else
        {
            for (j=i; j<n; j++)
            {
                swap (orderOfCities, i, j);
                permuteCities(orderOfCities, n, i + 1);
                swap (orderOfCities, i, j);
            }
        }
    }

    private static int calculateDistance(int[] orderOfCities)
    {
         int distance = 0;
         for(int i = 0; i < orderOfCities.length; i++)
         {
              int  from = orderOfCities[i], to ;
              if(i != orderOfCities.length - 1)
                  to = orderOfCities[i+1];
              else
                  to = orderOfCities[0];
              distance += tspMatrix[from - 1][ to - 1];
         }
        return distance;
    }


    private static void swap (int[] orderOfCities, int i, int j)
    {
        if(i!=j)
        {
            int	t;
            t = orderOfCities[i];
            orderOfCities[i] = orderOfCities[j];
            orderOfCities[j] = t;
        }
    }
}


class TspSolution{
    int[] travelOrder;
    int distance;

    int[] getTravelOrder() {
        return travelOrder;
    }

    void setTravelOrder(int[] orderOfCities) {
        this.travelOrder =  orderOfCities;
    }

    int getDistance() {
        return distance;
    }

    void setDistance(int distance) {
        this.distance = distance;
    }
}

