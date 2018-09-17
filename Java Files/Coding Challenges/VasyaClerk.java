/*
 *Vasya - Clerk
 *https://www.codewars.com/kata/vasya-clerk/train/java
 *
 * Description: 
 * The new "Avengers" movie has just been released! There are a lot of
 * people at the cinema box office standing in a huge line. Each of them has
 * a single 100, 50 or 25 dollars bill. An "Avengers" ticket costs 25
 * dollars.
 * Vasya is currently working as a clerk. He wants to sell a ticket to every
 * single person in this line.
 * Can Vasya sell a ticket to each person and give the change if he
 * initially has no money and sells the tickets strictly in the order people
 * follow in the line?
 * Return YES, if Vasya can sell a ticket to each person and give the change
 * with the bills he has at hand at that moment. Otherwise return NO.
 * 
 * Examples:
 * 
 * Line.Tickets(new int[] {25, 25, 50}) // => YES 
 * Line.Tickets(new int []{25, 100}) // => NO. Vasya will not have enough
 * money to give change to 100 dollars
 */
package codingchallenges;

import java.util.HashMap;

/**
 * VasyaClerk class represent a clerk selling tickets in a cinema.
 * @author Adam Will
 */

public class VasyaClerk
{
    private HashMap<Integer, Integer> poach;
    private final int TICKET_PRICE = 25;
    
    /**
     * Creates a VasyaClerk objects and populates the <code>poach</code>
     * instance variable with note values $25, $50 and $100, and their respective
     * quantities equal to zero.
     */
    public VasyaClerk()
    {
        poach = new HashMap<>();
        poach.put(25, 0);
        poach.put(50, 0);
        poach.put(100, 0);
    }
    
    //instance methods
    
    /**
     * Takes an array of integers representing payments and checks if it is
     * possible for the clerk to sell tickets and give change to all of the
     * payments when they are being done in that particular order.
     * 
     * @param           paymentsInLine represents the the amount, value and
     *                  order of payments. The accepted values for elements in
     *                  an array are 25, 50 or 100.
     * @return          <code>String</code> "YES" if it is possible to take all the
     *                  payments in the line <br/>
     *                  <code>String</code> "NO" if it is not possible to take
     *                  all the payments in the line or any of the payments is
     *                  of incorrect value
     */
    public String isAbleToSell(int[] paymentsInLine)
    {
        for(int i=0; i<paymentsInLine.length; i++)
        {
            try
            {
                if(!this.cashIn(paymentsInLine[i])) return "NO";
            }
            catch(IncorrectAmountException e)
            {
                System.out.print("Payments allowed: $25, $50, $100 \nCurrent"
                        + " payment: $" + e.getAmount() + "\n");
                return "NO";
            }
        }
        return "YES";
    }
    
    /**
     * Checks that the current amount of money in the clerk's poach is sufficient
     * to give change to a payment 
     * @param           payment represents a payment for a ticket
     * @return          <code>true</code> if it is possible to give change <br/>
     *                  <code>false</code> there is not enough notes in the
     *                  poach to give change
     * @throws          IncorrectAmountException is thrown if the payment is of
     *                  incorrect amount (not equal to 25, 50 or 100)
     */
    private boolean cashIn(int payment) throws IncorrectAmountException
    {
        if(poach.containsKey(payment))
        {
            int change = payment - TICKET_PRICE;
        
            switch (change)
            {
                case 75: if((poach.get(50)>0)&&(poach.get(25)>0))
                        {
                            poach.replace(50, poach.get(50)-1);
                            poach.replace(25, poach.get(25)-1);
                            poach.replace(payment, poach.get(payment)+1);
                            return true;
                        }
                        else if(poach.get(25)>2)
                        {
                            poach.replace(25, poach.get(25)-3);
                            poach.replace(payment, poach.get(payment)+1);
                            return true;
                        }
                        else return false;
                case 25: if(poach.get(25)>0)
                        {
                            poach.replace(25, poach.get(25)-1);
                            poach.replace(payment, poach.get(payment)+1);
                            return true;
                        }
                        else return false;
                default:poach.replace(payment, poach.get(payment)+1);
                        return true;
            }
        }
        else
        {
            throw new IncorrectAmountException(payment);
        }
        
    }
}
