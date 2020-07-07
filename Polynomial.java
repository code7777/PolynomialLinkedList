
//Preston Porter
//CS275 homework 3
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Iterator;

public class Polynomial implements Cloneable {
    // this is a nested static class, it defines a type
   // all the instance varaibles can be access directly inside Polynomial class even though they have
   // private modifier
      private static class Term{
        
        private int exponent;
        private double coefficient;
        
        public Term(int exp, double coeff )
        {
            coefficient= coeff;
            exponent = exp;
 
        }
    }
    // instance variables of Polynomial
    // first is the term of the Polynomial with the highest degree
   private LinkedList<Term> termList; 
    //private Term first = 
   
    
  /**Postcondition:  Creates a polynomial which is 0.
    * **/
    public Polynomial() {
      termList = new LinkedList<Term>();
    termList.add(new Term(0,0));
    }
  
  /**Postcondition:  Creates a polynomial which has a single term a0*x^0
    * @param a0 The value to be set as the coefficient of the constant (x^0) term.
    * **/
  
public Polynomial(double a0) {
   termList = new LinkedList<Term>();
   termList.add(new Term(0,a0));
}
  /**
   * Generate a copy of this Polynomial.
   * @param - none
   * @return
   *   The return value is a copy of this Polynomial. Subsequent changes to the
   *   copy will not affect the original, nor vice versa. Note that the return
   *   value must be type cast to an Polynomial before it can be used.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/
  /* public Object clone( )
   {  // Clone a Polynomial  object.
      Polynomial answer;

      try
      {
         answer = (Polynomial) super.clone( );
      }
      catch (CloneNotSupportedException e)
      {  // This exception should not occur. But if it does, it would probably
         // indicate a programming error that made super.clone unavailable.
         // The most common error would be forgetting the "Implements Cloneable"
         // clause at the start of this class.
         throw new RuntimeException
         ("This class does not implement Cloneable");
      }

      answer.head = Term.listCopy(head);

      return answer;
   }*/
/** Postcondition:  Creates a copy of Polynomial p
  * @param p the Polynomial which is to be copied.
  * **/

public Polynomial(Polynomial p) {
    
 termList = new LinkedList<Term>();
      termList.addAll(p.termList);
    
}

/** Postcondition:  Adds the given amount to the coefficient of the specified exponent. 
  * @param amount The amount to be added to the coefficient.
  * @param exponent The degree of the term whose coefficient is to be modified.
  * (1) Note that the exponent can be arbitrary
  * (2) If you want, you can assume the amount is not 0, however, it is possible that 
  *   after you add the amount, the coefficient becomes 0, in which case, you should delete the TermNode
  * **/

public void add_to_coef(double amount, int exponent) {
int counter =0;
  ListIterator<Term> itr = termList.listIterator();
  while(itr.hasNext()) {
      Term term = itr.next();
       
    if(term.exponent==exponent)
    {
      term.coefficient=term.coefficient+amount;
      counter++;
    }
   /*  else if(term.exponent < exponent)
    {
      //System.out.println(term.exponent);
      
       //itr.add(new Term(exponent,amount));//exp,coeff
    }*/
    }
 if(counter==0)
  {
  //double a1=amount;
  //int e2 =exponent;
   if(amount!=0){
     termList.add(new Term(exponent,amount)); }//exp,coeff
   if(amount==0){
     termList.addLast(new Term(exponent,amount));
   }
  }
}

/** Postcondition:  Sets the coefficient of a specified term to a specified value.
  * @param coefficient The new value of the coefficient.
  * @param exponent The degree of the term whose coefficient is to be modified.
  * (1) Note that the exponent can be arbitrary
  * (2) The coefficient may be 0
  * **/
  
public void assign_coef(double coefficient, int exponent) { 

  int counter =0;
  ListIterator<Term> itr = termList.listIterator();
  while(itr.hasNext()) {
      Term term = itr.next();
       
    if(term.exponent==exponent)
    {
      term.coefficient=coefficient;
      counter++;
    }
    //else if(term.exponent < exponent)
    {
       //itr.add(new Term(exponent,coefficient));//exp,coeff
      //System.out.println(term.exponent);
       
    }
    }
  if(counter==0)
  {
  //double a1=coefficient;
  //int e2 =exponent;
  termList.add(new Term(exponent,coefficient));//exp,coeff
  }
  
  
  
}

/**  Postcondition:   Returns coefficient at specified exponent of this polynomial.
  * @param exponent The exponent of the term whose coefficient is sought.
  * @return The coefficient of the term.
  * @throws Exception if the degree of the activating polynomial is less than that of the requested term.
  * **/

public double coefficient(int exponent)  {
  int counter =0;
  double coef;
  ListIterator<Term> itr = termList.listIterator();
  while(itr.hasNext()) {
      Term term = itr.next();
       
    if(term.exponent==exponent)
    {
        //coef=(double)term.coefficient;
      return term.coefficient;
    }
    
    }
  return 0.0;
}

/** @return The value of this Polynomial with the given value for the variable x.
  * @param x The value at which the Polynomial is to be evaluated.
  * using Horner's method to evaluation
  * see the link here 
  * https://en.wikipedia.org/wiki/Horner%27s_method
  * 
  ***/

 public double eval(double x) {
  double result =0;
  
  ListIterator<Term> itr = termList.listIterator();
  while(itr.hasNext()) {
      Term term = itr.next();
     result+=  term.coefficient*Math.pow(x,term.exponent);
  }
/* for(int i =termList.size()-1; i>=0; i--)
  {
   // result += result*x + termList.get(i).coefficient ;
   result += 
  }
  return result;*/
  
  return result;
}

  
  /**@return Returns a string representing the polynomial expression with coefficients displayed to the tenths place, 
    * omitting any coefficients that are zero.  
    * If all coefficients are 0, then the zero function is reported.
    * 
    **/
   public String toString() {
      ListIterator<Term> itr = termList.listIterator();
      String result = "";
     int counter =0;
   while(itr.hasNext()) {
      Term term = itr.next();
      if(term.coefficient!=0.0)
      {
        //counter++;
        
        if(term.exponent!=0)
        { if(term.coefficient<0)
          {
          result += " "+ term.coefficient + "x" + "^" + term.exponent ;
          }
         if(term.coefficient>0)
         {
           result += " + " + term.coefficient + "x" + "^" + term.exponent ;
         }
        }
      else {result += term.coefficient ; }
      }
      
   }
        if (result == "")
        {result = "" +0.0;}
      //while(
        return result;
      
    }
    
/**@return Returns a Polynomial that is the sum of p and this Polynomial.
  * @param p The Polynomial to be added to the activating Polynomial.
  * **/
      public Polynomial add(Polynomial p) {
     // use the copy constructor
      //  Polynomial b4 = this;
      Polynomial answer = new  Polynomial();
   
        for (ListIterator<Term> itr= answer.termList.listIterator(); itr.hasNext(); ) {
          Term term = itr.next();
         // count1++;
          for (ListIterator<Term> itr2 = p.termList.listIterator(); itr2.hasNext();){
            //count2++;
            Term term2 = itr2.next();
            if(term.exponent ==term2.exponent)
            {//answer.itr.coefficient= answer.itr.coefficient + p.itr2.coefficient;
              term.coefficient+=term2.coefficient;
            }
           
            //answer.termList.add(new Term(term2.exponent,term2.coefficient));//exp,coeff}*/
          }
        }
      
    
       
 
  
      return answer;
  
      
        
      }



     /** Postcondition:  Returns a new polynomial obtained by multiplying this term and p. For example, if this polynomial is
     2x^2 + 3x + 4 and p is 5x^2 - 1x + 7, then at the end of this function, it will return the polynomial 10x^4 + 13x^3 + 31x^2 + 17x + 28.
     @param p The polynomial to be multiplied.
     @return The product of the activating Polynomial and p.
     **/
      
     public Polynomial multiply(Polynomial p) {
       Polynomial answer = new  Polynomial();
       
          for (ListIterator<Term> itr= this.termList.listIterator(); itr.hasNext(); ) {
          Term term = itr.next();
          
          for (ListIterator<Term> itr2 = p.termList.listIterator(); itr2.hasNext();){
            Term term2 = itr2.next();
            //if(term.exponent ==term2.exponent)
           // {///answer.itr.coefficient= answer.itr.coefficient + p.itr2.coefficient;
              answer.termList.add(new Term(term.exponent+term2.exponent,term.coefficient*term2.coefficient));
              
            //}
            //}
           /* else
              answer.termList.add(new Term(term2.exponent,term2.coefficient));//exp,coeff*/
          } //for loop
          }//for loop
         
           return answer;
  
      
        
      }
     public static void main(String[] args)
  {
    Polynomial p1 = new Polynomial();
    System.out.println("p1 = new Polynomial(): p1 = " + p1);
   
      
     Polynomial p2 = new Polynomial(p1);
     System.out.println("p2 = new Polynomial(): p2 = " + p2);
     p2.add_to_coef(2,3);
     System.out.println("p2 = new Polynomial(): p2 = " + p2);
      p2.add_to_coef(2,3);
     System.out.println("p2 = new Polynomial(): p2 = " + p2);
     p2.assign_coef(3,3);
     System.out.println("p2 = new Polynomial(): p2 = " + p2);
      p2.assign_coef(-4,4);
     System.out.println("p2 = new Polynomial(): p2 = " + p2);
      System.out.println(p2.coefficient(4));
       Polynomial p3 = new Polynomial();
       p3.assign_coef(-4,4);
       System.out.println("p3 = new Polynomial(): p3 = " + p3);
       p3.assign_coef(-4,2);
       System.out.println("p3 = new Polynomial(): p3 = " + p3);
        Polynomial p4 = p2.add(p3);
        System.out.println("p4 = new Polynomial(): p4 = " + p4);
     //p2.assign_coeff(2,3);
    // System.out.println("p2 = new Polynomial(): p2 = " + p2);
   
      }
}
