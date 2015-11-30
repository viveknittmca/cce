import java.util.*;

public class CryptarithSolver 
{  
	  public String solve(String puzzle) 
	  {
		    char c = 0;
		    /* Get the 1st Letter in the Puzzle which needs to be replaced */
		    for (int i = 0; i < puzzle.length(); ++i) 
		    {
			      if (Character.isLetter(puzzle.charAt(i))) 
			      {
				        c = puzzle.charAt(i);
				        break;
			      }
		    }
		    
		    /* If C is 0 at this point Then all Letters have been replaced. So Evaluate now */
		    if (c == 0) 
		    {
			      String[] expression = puzzle.split("=");
			      int lhs = evaluate(expression[0]), rhs = evaluate(expression[1]);
			      if ( lhs == rhs) 
			    	  return puzzle;
			      else 
			    	  return "";
		    } 
		    else 
		    {
		    	  /* Keep track which Number(0-9) is YET to be used */
			      boolean[] numberUsed = new boolean[10];
			      
			      for (int i = 0; i < puzzle.length(); ++i)
			      {
			    	    /*If number is used in Puzzle then set its slot to TRUE in this array*/
				        if (Character.isDigit(puzzle.charAt(i)))
				           numberUsed[puzzle.charAt(i)-'0'] = true;
			      }
			      
			      /* Use the Unused Number so far.*/
			      for (int i = 0; i < 10; ++i) 
			      {
			    	    /* Avoid if it is replacing the 1st Letter in the Sum/Product to 0 */
			    	    String replacee = String.valueOf(c);	
			    	    String replacer = String.valueOf(i);
				        if (numberUsed[i] && !( (i==0) && puzzle.split("=")[1].trim().startsWith(replacee) )  ) 
				        {				        	  
					          String r = solve(puzzle.replaceAll(replacee, replacer));
					          //if (!r.isEmpty() && !r.split("=")[1].trim().startsWith("0")) 
					          if(!r.isEmpty())
					        	  return r;
				        }
			      }
		    }
		    return "";
	  }
	  
	  private int evaluate(String expression) 
	  {
		    int value = 0;
		    StringTokenizer tokens = new StringTokenizer(expression, "*/+-", true);
		    while (tokens.hasMoreTokens()) 
		    {
			      String next = tokens.nextToken().trim();
			      // LHS will get evaluated in the if & else if conditions
			      if (next.equals("+")) 
			      {
			        value += Integer.parseInt(tokens.nextToken().trim());
			      } 
			      else if (next.equals("-")) 
			      {
			        value -= Integer.parseInt(tokens.nextToken().trim());
			      } 
			      else if (next.equals("*")) 
			      {
			        value *= Integer.parseInt(tokens.nextToken().trim());
			      } 
			      else if (next.equals("/")) 
			      {
			        value /= Integer.parseInt(tokens.nextToken().trim());
			      } 
			      else 
			      {
			        // RHS will get evaluated here	  
			        value = Integer.parseInt(next);
			      }
		    }
		    return value;
	  }
	  
      public static void main(String[] args) 
      {    
    	  CryptarithSolver solver = new CryptarithSolver();    
    	  
    	  String solvedPuzzle = solver.solve(args[0]+args[1]+args[2]+args[3]+args[4]);
    	  
    	  if(!solvedPuzzle.isEmpty())
    		  System.out.println(solvedPuzzle);
    	  else 
    		  System.out.println("No Solution Exists");
      }
  
}
