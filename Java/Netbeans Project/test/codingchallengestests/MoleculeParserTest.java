package codingchallengestests;


import java.util.HashMap;
import org.junit.Test;
import codingchallenges.MoleculeParser;
import org.junit.Before;
/**
 * A set of tests for getAtoms(String) method from the MoleculeParser class.
 * @author Adam Will
 */
public class MoleculeParserTest
{    
    private MoleculeParser tester;
    
    @Before
    public void setUp()
    {
        tester = new MoleculeParser();
    }
    
    @Test
    public void getAtoms_noBracketsOrMultpliers()
    {
        //gets the output
        HashMap<String, Integer> output = tester.getAtoms("KZnOK");
        //sets the Map of the expected result
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("K", 2);
        expected.put("Zn", 1);
        expected.put("O", 1);
        //checks whether 'output' and 'expected' have the same keys and values
        assert output.equals(expected) : "The formula without any brackets and"
                + " multpliers was not processed correctly";
    }
    
    @Test
    public void getAtoms_multipliersNoBrackets()
    {
        //gets the output
        HashMap<String, Integer> output = tester.getAtoms("H2O44Pb354");
        //sets the Map of the expected result
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("H", 2);
        expected.put("O", 44);
        expected.put("Pb", 354);
        //checks whether 'output' and 'expected' have the same keys and values
        assert output.equals(expected) : "The formula with multipliers and no"
                + " brackets was not processed correctly";
    }
    
    @Test
    public void getAtoms_roundBracketsWithMultipliers()
    {
        //gets the output
        HashMap<String, Integer> output = tester.getAtoms("(H2O)4K(SO)Es3(K4Br)");
        //sets the Map of the expected result
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("H", 8);
        expected.put("O", 5);
        expected.put("K", 5);
        expected.put("S", 1);
        expected.put("Es", 3);
        expected.put("Br", 1);
        //checks whether 'output' and 'expected' have the same keys and values
        assert output.equals(expected) : "The formula with round brackets and"
                + " multpliers was not processed correctly";
    }
    
    @Test
    public void getAtoms_squareBracketsWithMultipliers()
    {
        //gets the output
        HashMap<String, Integer> output = tester.getAtoms("[H2O]4K[SO]Es3[K4Br]");
        //sets the Map of the expected result
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("H", 8);
        expected.put("O", 5);
        expected.put("K", 5);
        expected.put("S", 1);
        expected.put("Es", 3);
        expected.put("Br", 1);
        //checks whether 'output' and 'expected' have the same keys and values
        assert output.equals(expected) : "The formula with square brackets and"
                + " multpliers was not processed correctly";
    }
    
    @Test
    public void getAtoms_squareAndRoundBrackets()
    {
        //gets the output
        HashMap<String, Integer> output = tester.getAtoms("[Si(H222O)][(H2O)55]33");
        //sets the Map of the expected result
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("Si", 1);
        expected.put("H", 3852);
        expected.put("O", 1816);
        //checks whether 'output' and 'expected' have the same keys and values
        assert output.equals(expected) : "The formula with both square and round"
                + " brackets, and multipliers was not processed correctly";
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void getAtoms_invalidElement_exceptionThrown()
    {
        HashMap<String, Integer> output =  tester.getAtoms("H2O(Pie4O)5Zn");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void getAtoms_roundClosingBracketsMissing_exceptionThrown()
    {
        HashMap<String, Integer> output =  tester.getAtoms("[H2O]3(S4O5(Zn)");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void getAtoms_squareClosingBracketsMissing_exceptionThrown()
    {
        HashMap<String, Integer> output =  tester.getAtoms("(H2O)3[S4O5[Zn]");
    }
}
