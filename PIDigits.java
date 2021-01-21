import java.lang.Math.sqrt;
import java.math.RoundingMode;
import java.lang.Math;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

// Calculating the digits of PI using the Chudnovsky algorithm
public class PIDigits {
    // Utility function to calculate factorials
    public static double factorial(int n)
    {
        if(n==0)
        {
            return 1;
        }
        else
        {
            return n*factorial(n-1);
        }
    }

    /** 
    * Returns a term of from the infinite series in the Chudnovsky algorithm.
    * @param n represents the term (nth term)
    * @return  a double representing the nth term, approximately equal to 1/Pi
    */
    public static double chudnovsky(int n)
    {
        // From the numerator 
        double numerator = Math.pow(-1,n)*factorial(6*n) * (545140134*n + 13591409);
        // From the denominator
        double denominator = factorial(3*n) * Math.pow(factorial(n), 3) * Math.pow(640320,3.0*n+3.0/2.0);
        
        if (n==0)
        {
            return 12.0*numerator/denominator;
        }
        
        return 12.0*numerator/denominator + chudnovsky(n-1);
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of digits you want to see from PI.\nUp to 14 digits");
        
        int n = 0;
        while(sc.hasNext())
        {
            // If invalid input, exit the loop/program
            try
            {
                n = sc.nextInt();
            }
            catch(InputMismatchException e)
            {
                System.out.println("Invalid value (letters, float, etc). EXITING");
                break;
            }
            finally{
                if(n>14)
                {
                    n=14;
                }
                else if(n<0)
                {
                    System.out.println("Invalid value (negative value)");
                    n = sc.nextInt();
                }    

                // Formatting the output to have specified digits
                NumberFormat fmt = NumberFormat.getInstance(Locale.US);
                
                fmt.setMaximumFractionDigits(n);
                fmt.setRoundingMode(RoundingMode.FLOOR);

                System.out.println(fmt.format(1.0/chudnovsky(2)));
            }
        }
        sc.close();
    }
    
}
