
package codingchallengestests;

import codingchallenges.CodingChallenges;
import org.junit.Test;
import org.junit.Assert;

/**
 * Test class for reduceDirections(String[]) method in the codingchalleges 
 * package.
 * @author Adam Will
 */
public class ReduceDirectionsTest
{
    @Test
    /**
     * Test for majority of directions being "NORTH" and "WEST"
     */
    public void directionsReduction_WWWNEWENS_NWW()
    {
        Assert.assertArrayEquals(new String[] {"NORTH", "WEST", "WEST"},
                CodingChallenges.reduceDirections(new String[] {"WEST", "WEST",
                    "WEST", "NORTH", "EAST", "WEST", "EAST", "NORTH", "SOUTH"}));
    }
    
    @Test
    /**
     * Test for majority of directions being "SOUTH" and "EAST"
     */
    public void directionsReduction_NESWSWEESNS_SSE()
    {
        Assert.assertArrayEquals(new String[] {"SOUTH", "SOUTH", "EAST"},
                CodingChallenges.reduceDirections(new String[] {"NORTH", "EAST",
                    "SOUTH", "WEST", "SOUTH", "WEST", "EAST", "EAST", "SOUTH", 
                    "NORTH", "SOUTH"}));
    }
    
    @Test
    /**
     * Test for equal number of opposing directions 
     * (both NORTH-SOUTH and EAST-WEST)
     */
    public void directionsReduction_ESWNNWSE_empty()
    {
        Assert.assertArrayEquals(new String[] {},
                CodingChallenges.reduceDirections(new String[] {"EAST", "SOUTH",
                    "WEST", "NORTH", "NORTH", "WEST", "SOUTH", "EAST"}));
    }
    
    @Test
    /**
     * Test for an empty string
     */
    public void directionsReduction_empty_empty()
    {
        Assert.assertArrayEquals(new String[] {},
                CodingChallenges.reduceDirections(new String[] {}));
    }
}
