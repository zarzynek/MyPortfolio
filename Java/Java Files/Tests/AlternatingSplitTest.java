
package codingchallengestests;

import codingchallenges.CodingChallenges;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test class for encrypt(String) and decrypt(String) methods in the
 * codingchalleges package.
 * @author Adam Will
 */
public class AlternatingSplitTest
{

    @Test
    public void encrypt_basicStringAnd0Cycles_sameString()
    {
        assertEquals("This is a test!",
                CodingChallenges.encrypt("This is a test!", 0));
    }
    
    @Test
    public void encrypt_basicStringAnd4Cycles_encrypted4times()
    {
        assertEquals("This is a test!",
                CodingChallenges.encrypt("This is a test!", 4));
    }
    
    @Test
    public void encrypt_basicStringAndMinus1Cycles_sameString()
    {
        assertEquals("This is a test!",
                CodingChallenges.encrypt("This is a test!", -1));
    }
    
    @Test
    public void encrypt_emptyStringAnd2Cycles_emptyString()
    {
        assertEquals("", CodingChallenges.encrypt("", 2));
    }
    
    @Test
    public void encrypt_nullValueAnd2Cycles_nullValue()
    {
        assertEquals(null, CodingChallenges.encrypt(null, 2));
    }

    @Test
    public void decrypt_basicStringAnd0Cycles_sameString()
    {
        assertEquals("This is a test!",
                CodingChallenges.decrypt("This is a test!", 0));
    }
    
    @Test
    public void decrypt_basicStringAnd4Cycles_decrypted4times()
    {
        assertEquals("This is a test!",
                CodingChallenges.decrypt("This is a test!", 4));
    }
    
    @Test
    public void decrypt_basicStringAndMinus1Cycles_sameString()
    {
        assertEquals("This is a test!",
                CodingChallenges.decrypt("This is a test!", -1));
    }
    
    @Test
    public void decrypt_emptyStringAnd2Cycles_emptyString()
    {
        assertEquals("", CodingChallenges.decrypt("", 2));
    }
    
    @Test
    public void decrypt_nullValueAnd2Cycles_nullValue()
    {
        assertEquals(null, CodingChallenges.decrypt(null, 2));
    }
}
