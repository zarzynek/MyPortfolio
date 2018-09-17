
/*
 * Checking Groups
 * https://www.codewars.com/kata/checking-groups
 *
 * Descritpion: 
 * In English and programming, groups can be made using symbols such as () and
 * {} that change meaning. However, these groups must be closed in the correct
 * order to maintain correct syntax.
 * Your job in this kata will be to make a program that checks a string for
 * correct grouping. 
 * 
 * For instance, the following groups are done correctly:
 * ({})
 * [{()}]
 * 
 * The next are done incorrectly:
 * {(})
 * ([]
 * [])
 * 
 * A correct string cannot close groups in the wrong order, open a group but
 * fail to close it, or close a group before it is opened.
 * Your function will take an input string that may contain any of the symbols
 * (), {} or [] to create groups.
 * It should return True if the string is empty or otherwise grouped correctly,
 * or False if it is grouped incorrectly.
 */

package codingchallenges;

import java.util.ArrayList;
import java.util.List;

/**
 * Checks whether a group of brackets is in a right order.
 * @author Adam Will
 * @version 1.0
 */

public class GroupChecker
{
    private List<Character> symbols;
    
    /**
     * Creates a GroupChecker object and populates the <code>symbols</code> 
     * instance variable with the expected bracket symbols.
     */
    public GroupChecker()
    {
        symbols = new ArrayList<>();
        symbols.add('(');
        symbols.add(')');
        symbols.add('{');
        symbols.add('}');
        symbols.add('[');
        symbols.add(']');        
    }
    
    
    /**
     * Checks whether all the characters in a provided string are in a
     * correct order, by checking that: <br/>
     * -> the string starts with an opening bracket <br/>
     * -> every opening bracket is followed by a corresponding closing bracket,
     * or by an opening bracket of another kind <br/>
     * -> for every kind of brackets the amount of opening brackets is equal to
     * the amount of the corresponding closing brackets. <br/>

     * @param group     represents a group of brackets (the group must not
     *                  include characters other that: '(', ')', '[', ']',
     *                  '{', '}')
     * @return          boolean <code>true</code> if the order in a group is valid,
     *                  <code>false</code> otherwise
     */
    public boolean validateGroup(String group)
    {
        boolean result;
        /*
        First stage. The method checks that the string starts with an
        opening bracket. If not, the method returns false.
        */
        char firstChar = group.charAt(0);
        result = firstChar == '(' || firstChar == '{' || firstChar == '{';
        if(!result) return result;
        
        /*
        Second stage. The method invokes a helper method that checks whether
        every opening bracket is followed by a corresponding closing bracket, or
        an opening bracket of another kind. If not, the method returns false.
        */
        result = this.checkNextSymbol(group);
        if(!result) return result;
        
        /*
        Third stage. The method invokes a helper method that checks whether for
        every kind of brackets the amount of opening brackets is equal to the
        amount of the coresponding closing brackets. If not, the method returns
        false.
        */        
        result = this.countSymbols(group);
        return result;
        
    }
    
    /**
     * Helper method for the validateGroup(String) method. Iterates over the
     * string representing a group of brackets and checks that for any opening
     * bracket, the following bracket is either a corresponding closing 
     * bracket, or an opening bracket.
     * 
     * @param           group represents a group of brackets
     * @return          boolean <code>true</code> if the condition is met,
     *                  <code>false</code> otherwise
     */
    private boolean checkNextSymbol(String group)
    {        
        for(int i=0; i<group.length()-1; i++)
        {
            int currentSymbolIndex = symbols.indexOf(group.charAt(i));
            int nextSymbolIndex = symbols.indexOf(group.charAt(i+1));

            if(currentSymbolIndex%2 == 0)
            {
                if((nextSymbolIndex != currentSymbolIndex+1) && 
                        (nextSymbolIndex%2 != 0)) return false;        
            }
        }
        return true;
    }
    
    /**
     * Helper method for the validateGroup(String) method. Checks that for every
     * kind of brackets, the amount of opening brackets is equal to the amount
     * of the corresponding closing brackets.
     * 
     * @param           group represents a group of brackets
     * @return          boolean <code>true</code> if the condition is met,
     *                  <code>false</code> otherwise
     */
    private boolean countSymbols(String group)
    {
        int roundBracketCounter = 0;
        int curlyBracketCounter = 0;
        int squareBracketCounter = 0;
        boolean result = true;
        
        for (char bracket : group.toCharArray())
        {
            switch(symbols.indexOf(bracket))
            {
                case(0) : roundBracketCounter ++;
                          break;
                case(1) : roundBracketCounter --;
                          break;
                case(2) : curlyBracketCounter ++;
                          break;
                case(3) : curlyBracketCounter --;
                          break;
                case(4) : squareBracketCounter ++;
                          break;
                default : squareBracketCounter --;                
            }
            if((roundBracketCounter < 0) || (curlyBracketCounter < 0) 
                    || (squareBracketCounter < 0)) result = false;
        }
        
        if((roundBracketCounter != 0) || (curlyBracketCounter != 0) 
                    || (squareBracketCounter != 0)) result = false;
        
        return result;
    }
}
