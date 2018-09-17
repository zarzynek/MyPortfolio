
package codingchallengestests;

import codingchallenges.VasyaClerk;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

/**
 * Test class for isAbleToSell(int[]) method in the codingchalleges 
 * package.
 * @author Adam Will
 */
public class IsAbleToSellTest
{
    
    private VasyaClerk tester;
    
    @Before
    public void setUp()
    {
        tester = new VasyaClerk();
    }
    
    @Test
    public void isAbleToSell_$25$25$50_YES()
    {
        assertEquals("YES", tester.isAbleToSell(new int[] {25, 25, 50}));
    }
    
    @Test
    public void isAbleToSell_$25_YES()
    {
        assertEquals("YES", tester.isAbleToSell(new int[] {25}));
    }
    
    @Test
    public void isAbleToSell_$25$25$50$100_YES()
    {
        assertEquals("YES", tester.isAbleToSell(new int[] {25, 25, 50, 100}));
    }
    
    @Test
    public void isAbleToSell_$50$25$50_NO()
    {
        assertEquals("NO", tester.isAbleToSell(new int[] {50, 25, 50}));
    }
    
    @Test
    public void isAbleToSell_$25$25$100_NO()
    {
        assertEquals("NO", tester.isAbleToSell(new int[] {25, 25, 100}));
    }
    
    @Test 
    public void isAbleToSell_$25$1$25_NO()
    {
        assertEquals("NO", tester.isAbleToSell(new int[] {25, 1, 25}));
    }
    
    @Test
    public void isAbleToSell_$24_NO()
    {
        assertEquals("NO", tester.isAbleToSell(new int[] {24}));
    }
    
}
