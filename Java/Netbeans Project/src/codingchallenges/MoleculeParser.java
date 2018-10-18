/*
Molecule to atoms
https://www.codewars.com/kata/molecule-to-atoms/train/java

For a given chemical formula represented by a string, count the number of atoms
of each element contained in the molecule and return an object (associative
array in PHP, Dictionary<string, int> in C#, Map in Java).

For example:

String water = "H2O";
parseMolecule.getAtoms(water); // return [H: 2, O: 1]

String magnesiumHydroxide = "Mg(OH)2";
parseMolecule.getAtoms(magnesiumHydroxide); // return ["Mg": 1, "O": 2, "H": 2]

String fremySalt = "K4[ON(SO3)2]2";
parseMolecule.getAtoms(fremySalt); // return ["K": 4, "O": 14, "N": 2, "S": 4]

parseMolecule.getAtoms("pie"); // throw an IllegalArgumentException

As you can see, some formulas have brackets in them. The index outside the
brackets tells you that you have to multiply count of each atom inside the
bracket on this index. For example, in Fe(NO3)2 you have one iron atom, two
nitrogen atoms and six oxygen atoms.

Note that brackets may be round, square or curly and can also be nested. Index
after the braces is optional.
 */
package codingchallenges;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Extracts atoms from a given formula.
 *
 * @author Adam Will
 */
public class MoleculeParser
{

    private int currentMarker = 0;
    final static ArrayList<String> ATOMS;

    static
    {
        ATOMS = new ArrayList<>();

        ATOMS.add("Ac");
        ATOMS.add("Al");
        ATOMS.add("Am");
        ATOMS.add("Sb");
        ATOMS.add("Ar");
        ATOMS.add("As");
        ATOMS.add("At");
        ATOMS.add("Ba");
        ATOMS.add("Be");
        ATOMS.add("Bk");
        ATOMS.add("Bi");
        ATOMS.add("Bh");
        ATOMS.add("B");
        ATOMS.add("Br");
        ATOMS.add("Cd");
        ATOMS.add("Ca");
        ATOMS.add("Cf");
        ATOMS.add("C");
        ATOMS.add("Ce");
        ATOMS.add("Cs");
        ATOMS.add("Cl");
        ATOMS.add("Cr");
        ATOMS.add("Co");
        ATOMS.add("Cu");
        ATOMS.add("Cm");
        ATOMS.add("Ds");
        ATOMS.add("Db");
        ATOMS.add("Dy");
        ATOMS.add("Es");
        ATOMS.add("Er");
        ATOMS.add("Eu");
        ATOMS.add("Fm");
        ATOMS.add("F");
        ATOMS.add("Fr");
        ATOMS.add("Gd");
        ATOMS.add("Ga");
        ATOMS.add("Ge");
        ATOMS.add("Au");
        ATOMS.add("Hf");
        ATOMS.add("Hs");
        ATOMS.add("He");
        ATOMS.add("Ho");
        ATOMS.add("H");
        ATOMS.add("In");
        ATOMS.add("I");
        ATOMS.add("Ir");
        ATOMS.add("Fe");
        ATOMS.add("Kr");
        ATOMS.add("La");
        ATOMS.add("Lr");
        ATOMS.add("Pb");
        ATOMS.add("Li");
        ATOMS.add("Lu");
        ATOMS.add("Mn");
        ATOMS.add("Mg");
        ATOMS.add("Mt");
        ATOMS.add("Md");
        ATOMS.add("Hg");
        ATOMS.add("Mo");
        ATOMS.add("Nd");
        ATOMS.add("Ne");
        ATOMS.add("Np");
        ATOMS.add("Ni");
        ATOMS.add("Nb");
        ATOMS.add("N");
        ATOMS.add("No");
        ATOMS.add("Uuo");
        ATOMS.add("Os");
        ATOMS.add("O");
        ATOMS.add("Pd");
        ATOMS.add("Pt");
        ATOMS.add("P");
        ATOMS.add("Pu");
        ATOMS.add("Po");
        ATOMS.add("K");
        ATOMS.add("Pr");
        ATOMS.add("Pm");
        ATOMS.add("Pa");
        ATOMS.add("Ra");
        ATOMS.add("Rn");
        ATOMS.add("Re");
        ATOMS.add("Rh");
        ATOMS.add("Rg");
        ATOMS.add("Rb");
        ATOMS.add("Ru");
        ATOMS.add("Rf");
        ATOMS.add("Sm");
        ATOMS.add("Sc");
        ATOMS.add("Sg");
        ATOMS.add("Se");
        ATOMS.add("Si");
        ATOMS.add("Ag");
        ATOMS.add("Na");
        ATOMS.add("Sr");
        ATOMS.add("S");
        ATOMS.add("Ta");
        ATOMS.add("Tc");
        ATOMS.add("Tb");
        ATOMS.add("Te");
        ATOMS.add("Tl");
        ATOMS.add("Th");
        ATOMS.add("Tm");
        ATOMS.add("Sn");
        ATOMS.add("Ti");
        ATOMS.add("W");
        ATOMS.add("Uub");
        ATOMS.add("Uuh");
        ATOMS.add("Uup");
        ATOMS.add("Uuq");
        ATOMS.add("Uus");
        ATOMS.add("Uut");
        ATOMS.add("U");
        ATOMS.add("V");
        ATOMS.add("Xe");
        ATOMS.add("Yb");
        ATOMS.add("Y");
        ATOMS.add("Zn");
        ATOMS.add("Zr");
    }

    /**
     * Creates an instance of the MoleculeParser class. Initialises the <code>
     * currentMarker</code> instance variable.
     */
    public MoleculeParser()
    {
        currentMarker = 0;
    }

    /**
     * Orchestrates the process of extracting atoms from the given formula.
     *
     * @param formula           a String representing a chemical formula
     * @return                  a Map object with chemical elements as keys and
     *                          their quantities
     *                          in the given formula as values
     * @throws IllegalArgumentException <br/>
     *  -> if any of the extracted elements is not a valid element (cannot be
     * found in the periodic table of elements) <br/>
     *  -> if any of the opening bracket misses the corresponding closing
     * brackets <br/>
     *  -> if the string representing the chemical formula is empty, equal to
     * <code>null</code>, or starts with a lower case letter
     */
    public HashMap<String, Integer> getAtoms(String formula)
            throws IllegalArgumentException
    {
        formula = expandAllBrackets(formula);
        HashMap<String, Integer> result = countAtoms(formula);
        currentMarker = 0;
        return result;
    }

    /**
     * Rewrites the given formula by removing all the brackets and repeating the
     * formulas contained inside each pair of the brackets with respect to their
     * corresponding multipliers.
     *
     * @param formula       a String representing a chemical formula
     * @return              a String representing the same formula with all the
     *                      brackets being extended
     * @throws IllegalArgumentException <br/>
     *  -> if any of the opening bracket misses the corresponding closing
     * brackets 
     */
    private String expandAllBrackets(String formula)
            throws IllegalArgumentException
    {
        int openingSquareBracketMarker = 0;
        int closingSquareBracketMarker = 0;
        int openingRoundBracketMarker = 0;
        int closingRoundBracketMarker = 0;

        StringBuilder tempFormula = new StringBuilder(formula);

        for (currentMarker = 0; currentMarker < tempFormula.length(); currentMarker++)
        {
            if (tempFormula.charAt(currentMarker) == '[')
            {
                openingSquareBracketMarker = currentMarker;
                while (tempFormula.charAt(currentMarker) != ']')
                {
                    if (tempFormula.charAt(currentMarker) == '(')
                    {
                        openingRoundBracketMarker = currentMarker;
                        while (tempFormula.charAt(currentMarker) != ')')
                        {
                            currentMarker++;
                            if (currentMarker == tempFormula.length())
                            {
                                throw new IllegalArgumentException(
                                        "Missing closing brackets.");
                            }
                        }
                        closingRoundBracketMarker = currentMarker;

                        tempFormula = expandCurrentBrackets(tempFormula,
                                openingRoundBracketMarker,
                                closingRoundBracketMarker);

                        if (currentMarker == tempFormula.length())
                        {
                            return tempFormula.toString();
                        }

                        openingRoundBracketMarker = 0;
                        closingRoundBracketMarker = 0;
                    }
                    currentMarker++;
                    if (currentMarker == tempFormula.length())
                    {
                        throw new IllegalArgumentException(
                                "Missing closing brackets.");
                    }
                }
                closingSquareBracketMarker = currentMarker;

                tempFormula = expandCurrentBrackets(tempFormula,
                        openingSquareBracketMarker,
                        closingSquareBracketMarker);

                if (currentMarker == tempFormula.length())
                {
                    return tempFormula.toString();
                }

                openingSquareBracketMarker = 0;
                closingSquareBracketMarker = 0;
            }

            if (tempFormula.charAt(currentMarker) == '(')
            {
                openingRoundBracketMarker = currentMarker;
                while (tempFormula.charAt(currentMarker) != ')')
                {
                    currentMarker++;
                    if (currentMarker == tempFormula.length())
                    {
                        throw new IllegalArgumentException(
                                "Missing closing brackets.");
                    }
                }
                closingRoundBracketMarker = currentMarker;

                tempFormula = expandCurrentBrackets(tempFormula,
                        openingRoundBracketMarker,
                        closingRoundBracketMarker);

                if (currentMarker == tempFormula.length())
                {
                    return tempFormula.toString();
                }

                openingRoundBracketMarker = 0;
                closingRoundBracketMarker = 0;
            }
        }
        return tempFormula.toString();
    }

    /**
     * Extracts and counts all the elements from the pre-expanded formula (all
     * the brackets have already been expanded with respects to their
     * multipliers)
     * 
     * @param formula       a String representing chemical formula with all the
     *                      brackets being already expanded
     * @return              a Map object with chemical elements as keys and
     *                      their quantities in the given formula as values
     * @throws IllegalArgumentException <br/>
     *  -> if the string representing the chemical formula is empty, equal to
     * <code>null</code>, or starts with a lower case letter
     */
    private HashMap<String, Integer> countAtoms(String formula)
            throws IllegalArgumentException
    {
        if (formula == null || formula.length() == 0 ||
                !Character.isUpperCase(formula.charAt(0)))
        {
            throw new IllegalArgumentException("The string representing the"
                    + " formula cannot be empty and must start with a capital"
                    + " letter");
        }

        HashMap<String, Integer> result = new HashMap<>();
        int currentMultiplier = 1;
        StringBuilder currentElement = new StringBuilder("");
        char currentChar = '0';

        //Code for handling the first character of the formula
        currentElement.append(formula.charAt(0));
        
        //Code for handling all the following characters of the formula
        for (int i = 1; i < formula.length(); i++)
        {
            currentChar = formula.charAt(i);

            if (Character.isUpperCase(currentChar))
            {
                result = addElement(result, currentElement, currentMultiplier);
                /*
                As the 'currentElement' is added to the 'result' Map,
                the 'currentMultiplier' and 'currentElement' varaibles are being
                reset.
                */
                currentMultiplier = 1;
                currentElement.delete(0, currentElement.length());
                currentElement.append(currentChar);
            }
            else if (Character.isLowerCase(currentChar))
            {
                currentElement.append(currentChar);
            }
            else if (Character.isDigit(currentChar))
            {
                currentMultiplier = extractMultiplier(formula.substring(i));
                i += Integer.toString(currentMultiplier).length() - 1;
            }
            else
            {
                throw new IllegalArgumentException("The formula must consist of"
                        + " letters and numbers only");
            }
        }

        result = addElement(result, currentElement, currentMultiplier);

        return result;
    }

    /**
     * Checks whether the pre-extracted element is present in the periodic
     * table of elements, and if so, adds the element to the Map object
     * containing elements present in the current formula.
     * 
     * @param result            a Map object with chemical elements as keys and
     *                          their quantities in the given formula as values
     * @param currentElement    a StringBuilder object representing a potential
     *                          element
     * @param multiplier        an integer representing the multiplier
     *                          associated with the <code>currentElement</code>
     * @return                  the <code>result</code> Map object with the
     *                          <code>currentElement</code> added to its key set,
     *                          and the corresponding value modified by the
     *                          value of the multiplier.
     * @throws IllegalArgumentException <br/>
     *  ->if any of the extracted elements is not a valid element (cannot be
     * found in the periodic table of elements) <br/>
     */
    private HashMap<String, Integer> addElement(
            HashMap<String, Integer> result, StringBuilder currentElement,
            int multiplier) throws IllegalArgumentException
    {

        if (ATOMS.contains(currentElement.toString()))
        {
            Integer previousValue = result.putIfAbsent(
                    currentElement.toString(),
                    multiplier);
            if (previousValue != null)
            {
                result.put(currentElement.toString(),
                        previousValue + multiplier);
            }
        }
        else
        {
            throw new IllegalArgumentException("\"" + currentElement.toString()
                    + "\"" + " is not a known element");
        }

        return result;
    }

    /**
     * Takes a string and expands brackets in that string that were indicated by
     * the <code>currentMarker</code> instance variable. If the brackets are
     * followed by a multiplier, the value of the multiplier is extracted. If a 
     * multiplier is not present, its value is set to 1. <br/>
     * The formula inside the brackets is then repeated <code>n</code> times,
     * where <code>n</code> is equal to the value of the multiplier. Finally,
     * the brackets are removed and position of the <code>currentMarker</code> 
     * adjusted.
     *
     * @param initialFormula        a String representing a chemical formula
     * @param openingBracket        an int representing the index of the
     *                              opening bracket
     * @param closingBracket        an int representing the index of the
     *                              closing bracket
     * @return                      the <code>initialFormula</code> with the
     *                              indicated brackets being expanded
     */
    private StringBuilder expandCurrentBrackets(
            StringBuilder initialFormula,
            int openingBracket, int closingBracket)
    {
        StringBuilder result;
        int initialLength = initialFormula.length();
        int multiplier = extractMultiplier(initialFormula.substring(
                currentMarker + 1));
        boolean multiplierIsAbsent = (closingBracket
                == initialLength - 1) || (!Character.isDigit(
                        initialFormula.charAt(closingBracket + 1)));
        String innerFormula = initialFormula.substring(
                openingBracket + 1,
                closingBracket);

        if (multiplierIsAbsent)
        {
            result = initialFormula.replace(openingBracket,
                    closingBracket + 1,
                    multiplyInnerFormula(innerFormula,
                            multiplier));
            currentMarker += result.length() - initialLength;
        }
        else
        {
            result = initialFormula.replace(openingBracket,
                    closingBracket
                    + Integer.toString(multiplier).length() + 1,
                    multiplyInnerFormula(innerFormula,
                            multiplier));
            currentMarker += result.length() - initialLength + Integer.toString(
                    multiplier).length();
        }

        return result;
    }
    
    /**
     * Helper method for expandCurrentBrackets(StringBuilder, int, int) method.
     * If the first character of the  part of the formula is a digit,
     * extracts any number represented by the first and the following characters.
     * If the first character is not a digit, the method returns 1.
     *
     * @param subFormula        a part of the given formula, that potentially
     *                          starts with a multiplier
     * @return                  '1' if the <code>subFormula</code> does not
     *                          start with characters representing digits,
     *                          the value represented by the first characters
     *                          otherwise
     */
    private int extractMultiplier(String subFormula)
    {
        if (subFormula.length() == 0 || !Character.isDigit(subFormula.charAt(0)))
        {
            return 1;
        }

        int iterator = 0;
        while (iterator < subFormula.length()
                && Character.isDigit(subFormula.charAt(iterator)))
        {
            iterator++;
        }
        return Integer.parseInt(subFormula.substring(0, iterator));
    }
    
     /**
     * Helper method for expandCurrentBrackets(StringBuilder, int, int) method.
     * Takes a string and an integer and returns a string representing the
     * result of repeating the initial string n times, where n is equal to the
     * passed integer.
     *
     * @param innerFormula          a string to be repeated
     * @param multiplier            an integer indicating how many times the
     *                              <code>innerFormula</code> is to be repeated
     * @return                      a string representing the result of
     *                              repeating the <code>innerFormula</code>
     *                              <code>multiplier</code> times
     */
    private String multiplyInnerFormula(String innerFormula,
            int multiplier)
    {
        String result = "";
        for (int i = 0; i < multiplier; i++)
        {
            result += innerFormula;
        }
        return result;
    }

}
