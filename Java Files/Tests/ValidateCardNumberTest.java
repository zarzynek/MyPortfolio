package codingchallengestests;

import codingchallenges.CodingChallenges;
import org.junit.Test;
import org.junit.Assert;

/**
 * Test class for validateCardNumber(String) method in the codingchalleges 
 * package.
 * @author Adam Will
 */
public class ValidateCardNumberTest
{

    @Test
    public void validateCardNumber_891_returns_false()
    {
        Assert.assertEquals(false, CodingChallenges.validateCardNumber("891"));
    }

    @Test
    public void validateCardNumber_8675309_false()
    {
        Assert.assertEquals(false,
                CodingChallenges.validateCardNumber("8675309"));
    }

    @Test
    public void validateCardNumber_262626262626226_true()
    {
        Assert.assertEquals(true, CodingChallenges.validateCardNumber(
                "262626262626226"));
    }

    @Test
    public void validateCardNumber_26_true()
    {
        Assert.assertEquals(true, CodingChallenges.validateCardNumber("26"));
    }
}
