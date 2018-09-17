
package codingchallengestests;

import codingchallenges.GroupChecker;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Test class for validateGroup(String) method in the codingchalleges 
 * package. 'First Stage', 'Second Stage', and 'Third Stage' phrases used in the
 * names of the test methods correspond to the stages described in the comments
 * in the tested methods and are used here for readability purposes.
 * @author Adam Will
 */
public class ValidateGroupTest
{
    private GroupChecker gc;
    
    @Before
    public void setUp()
    {
        gc = new GroupChecker();
    }
    
    /**
     * Tests whether the method returns true when the group of brackets starts
     * with an opening bracket and all of the other conditions are satisfied. 
     */
    @Test
    public void validateGroup_firstStage_true()
    {
        assertEquals(true, gc.validateGroup("{()}[]{[]}"));
    }
    
    /**
     * Test whether the method returns false when the group of brackets starts
     * with a closing bracket and all of the other conditions are satisfied. 
     */
    @Test
    public void validateGroup_firstStage_false()
    {
        assertEquals(false, gc.validateGroup("}(){{}[]{[]}"));
    }
    
    /**
     * Tests whether the method returns true when every opening bracket is
     * followed by a corresponding closing bracket or an opening bracket of
     * another kind, and all of the other conditions are satisfied. 
     */
    @Test
    public void validateGroup_secondStage_true()
    {
        assertEquals(true, gc.validateGroup("{}[]()({[([{()}])]})"));
    }
    
    /**
     * Tests whether the method returns false when there is an opening bracket
     * that is followed by neither a corresponding closing bracket,
     * nor an opening bracket of another kind, and all of the other conditions
     * are satisfied. 
     */
    @Test
    public void validateGroup_secondStage_false()
    {
        assertEquals(false, gc.validateGroup("{}[]()({[([{(})}])]}){"));
    }
    
    /**
     * Tests whether the method returns true, when for
     * every kind of brackets the amount of opening brackets is equal to the
     * amount of the corresponding closing brackets, and all of the other
     * conditions are satisfied. 
     */
    @Test
    public void validateGroup_thirdStage_true()
    {
        assertEquals(true, gc.validateGroup("()[[]]{{{}}}"));
    }
    
    /**
     * Tests whether the method returns false, when for
     * any kind of brackets the amount of opening brackets is not equal to the
     * amount of the corresponding closing brackets, and all of the other
     * conditions are satisfied. 
     */
    @Test
    public void validateGroup_thirdStage_false()
    {
        //Test for the round brackets
        assertFalse("Third Stage, round bracket error not caught",
                gc.validateGroup("())[[]]{{{}}}"));
        //Test for the square brackets
        assertFalse("Third Stage, square bracket error not caught",
                gc.validateGroup("())[[[]]{{{}}}"));
        //Test for the round brackets
        assertFalse("Third Stage, curly bracket error not caught",
                gc.validateGroup("())[[]]{{{}}}}"));
    }
}
