package codingchallenges;

/**
 * Thrown when the input data is not compatible with the method preconditions.
 * Works only with <code>int</code> data type
 * @author Adam Will
 */
public class IncorrectAmountException extends Exception
{
    private int amount;
    
    public IncorrectAmountException(int amount)
    {
        this.amount = amount;
    }
    
    public double getAmount()
    {
        return amount;
    }
}
