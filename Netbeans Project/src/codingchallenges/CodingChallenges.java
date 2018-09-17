
package codingchallenges;

/**
 * @author Adam Will
 */
public class CodingChallenges
{

    /**
     * The main method.
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        
    }
    
    
    //========================================================================
    /*
     * Validate Card Number
     * https://www.codewars.com/kata/validate-credit-card-number
     *
     * Description:
     * Given a positive integer of up to 16 digits, return true if it is a valid
     * credit card number, and false if it is not.
     *
     * Here is the algorithm:
     *
     * Double every other digit, scanning from right to left, starting from the
     * second digit (from the right).
     *
     * Another way to think about it is: if there are an even number of digits,
     * double every other digit starting with the first; if there are an odd
     * number of digits, double every other digit starting with the second:
     *
     * 1714 ==> [1*, 7, 1*, 4] ==> [2, 7, 2, 4]
     *
     * 12345 ==> [1, 2*, 3, 4*, 5] ==> [1, 4, 3, 8, 5]
     *
     * 891 ==> [8, 9*, 1] ==> [8, 18, 1] 
     *
     * If a resulting number is greater than 9, replace it with the sum of its
     * own digits (which is the same as subtracting 9 from it):
     *
     * [8, 18*, 1] ==> [8, (1+8), 1] ==> [8, 9, 1]
     *
     * or:
     *
     * [8, 18*, 1] ==> [8, (18-9), 1] ==> [8, 9, 1] 
     *
     * Sum all of the final digits:
     *
     * [8, 9, 1] ==> 8 + 9 + 1 = 18 
     * 
     * Finally, take that sum and divide it by 10.
     * If the remainder equals zero, the original credit card number is valid.
     */
    
    /**
     * Checks whether the provided integer is a valid credit card number.
     * Validity of a credit card number is determined by: <br/> -> Doubling
     * every other digit, scanning from right to left, starting from the second
     * digit (from the right). <br/> -> If a resulting number is greater than 9,
     * replacing it with the sum of its own digits. <br/> -> Adding up all of
     * the remaining digits, <br/> -> Taking that sum and dividing it by 10. 
     * <br/> If the remainder equals zero, the original credit card number is
     * valid.
     * 
     * @param cardNumber    the card number to be checked (must be a positive
     *                      integer consisting of up to 16 digits).
     * @return              <code> true </code> if the provided card number is
     *                      valid,  
     *                      <code> false </code> otherwise.
     */
    public static boolean validateCardNumber(String cardNumber)
    {
        int[] cardNumberDigits = new int[cardNumber.length()];
        
        for (int i = 0; i < cardNumber.length(); i++)
        {
            cardNumberDigits[i] = Character.getNumericValue(cardNumber.charAt(i));
        }
        
        cardNumberDigits = doubleEveryOtherDigit(cardNumberDigits);
        int sumOfDigits = sumTheDigits(cardNumberDigits);
        
        return sumOfDigits % 10 == 0;
    }

    /**
     * Helper method for validateCardNumber(String). Multiplies every
     * other element of the supplied array of integers by two.  If after
     * modification the element is larger than 9, the method replaces it with
     * the result of adding adding 1 to the remainder of the division of the
     * initial element by 10.
     * <p>
     * If the length of anArray is an even number, the modifications process
     * starts with the first element. Otherwise, it starts with the second
     * element.
     *
     * @param digitArray    an array of integers, each integer represents a
     *                      digit from the card number
     *                      (must not be empty)
     * @return              an array of integers, representing digits of a
     *                      modified card number
     */
    private static int[] doubleEveryOtherDigit(int[] digitArray)
    {
        if (digitArray.length % 2 == 0)
        {
            for (int i = 0; i < digitArray.length; i += 2)
            {
                digitArray[i] = digitArray[i] * 2;
                if (digitArray[i] > 9)
                {
                    digitArray[i] = digitArray[i % 10] + 1;
                }
            }
        }
        else
        {
            for (int i = 1; i < digitArray.length; i += 2)
            {
                digitArray[i] = digitArray[i] * 2;
                if (digitArray[i] > 9)
                {
                    digitArray[i] = digitArray[i % 10] + 1;
                }
            }
        }
        return digitArray;
    }

    /**
     * The helper method for the validateCardNumber(String) method. Adds up all
     * of the elements of the provided array.
     *
     * @param digitsArray   an array of integers (must not be empty)
     * @return              an integer representing the sum of all the elements
     *                      in the supplied array
     */
    private static int sumTheDigits(int[] digitArray)
    {
        int sum = 0;
        for (int i = 0; i < digitArray.length; i++)
        {
            sum = sum + digitArray[i];
        }
        return sum;
    }
    
    
    
        //========================================================================
    /*
     * Simple Encryption #1 - Alternating Split
     * https://www.codewars.com/kata/simple-encryption-number-1-alternating-split
     *
     * Description:
     * For building the encrypted string: Take every 2nd char from the string,
     * then the other chars, that are not every 2nd char, and concatenate them
     * as new String. Do this n times!
     *
     * Examples:
     *
     * "This is a test!", 1 -> "hsi  etTi sats!" "This is a test!", 2 -> "hsi
     * etTi sats!" -> "s eT ashi tist!"
    
     * Write two methods:
     *
     * String encrypt(String text, int n)
     * String decrypt(String encryptedText, int n) 
     *
     * For both methods: If the input-string is null or empty return exactly
     * this value! If n is  <=0 then return the input text.
     */
    
    /**
     * Encrypts the provided message. The encryption process is as follows: <br/>
     * -> two empty strings are created <br/>
     * -> the first string is populated with every second character from the 
     * original message, starting from the second character <br/>
     * -> the second string is populated with every second character from the 
     * original message, starting from the first character <br/>
     * -> the two strings are joined together
     * <p>
     * If the string representing the message is equal to <code> null</code> or
     * "", or the number of times the method is to be run (represented by
     * the integer <code>n</code>) is equal to zero or negative, the methods
     * returns the initial message.
     * <p>
     * This methods checks whether the provided message is not equal to
     * <code>null</code> or "", and whether the parameter <code>n</code> is
     * positive. The actual encryption process is being done by the
     * doEncrypt(String) helper method.
     * 
     * @param message   represents a message that is to be encrypted
     * @param n         an integer indicating how many times the encryption
     *                  process is to be run. 
     * @return          a string representing the encrypted message
     */
    public static String encrypt(String message, int n)
    {
        if (!((message == null) || (message.equals("")) || (n <= 0)))
        {
            for(int i=0; i<n; i++)
            {
                message = doEncryption(message);
            }
        }
        return message;
    }

    /**
     * Helper method for the encrypt() method. Executes the actual encryption
     * process.
     *
     * @param message   represents a message that needs to be encrypted
     * @return          a string representing the encrypted message
     */
    private static String doEncryption(String message)
    {
            String evenChars = "";
            String unevenChars = "";

            for (int j = 0; j < message.length(); j++)
            {
                if (j % 2 == 0)
                {
                    unevenChars = unevenChars + message.charAt(j);
                }
                else
                {
                    evenChars = evenChars + message.charAt(j);
                }
            }
            return evenChars + unevenChars;
        
    }
    /**
     * Decrypts the provided message. The decryption process is as follows: <br/>
     * -> the original message is split into two halves <br/>
     * -> a new message is created by adding one character from each of
     * the halves in turns, until all of the characters are being used
     * <p>
     * If the string representing the message is equal to <code> null</code> or
     * "", or the number of times the method is to be run (represented by
     * the integer <code>n</code>) is negative or equal to zero, the methods
     * returns the initial message.
     * <p>
     * This methods only checks whether the message is not equal to <code>null</code>
     * or "", and whether the parameter <code>n</code> is positive. The 
     * actual decryption process is being done by the doDecrypt(String) helper
     * method.
     * 
     *
     * @param message   represents a message that needs to be decrypted
     * @param n         an integer indicating how many times the decryption
     *                  process is to be run. 
     * @return          a string representing the decrypted message
     */
    public static String decrypt(String message, int n)
    {

        if (!((message == null) || (message.equals("")) || (n <= 0)))
        {
            for (int i = 0; i < n; i++)
            {
                message = doDecryption(message);
            }
        }
        return message;
    }

    /**
     * Helper method for the decrypt() method. Executes the actual decryption
     * process.
     *
     * @param message   represents a message that needs to be decrypted
     * @return          a string representing the decrypted message
     */
    private static String doDecryption(String message)
    {
        int messageLength = message.length();
        
        String unevenChars = "";
        String evenChars = "";


        evenChars = message.substring(0, messageLength / 2);
        unevenChars = message.substring(messageLength / 2);
            
        message = "";
            
        for (int j = 0; j < messageLength / 2; j++)
        {
            message = message + unevenChars.charAt(j) + evenChars.charAt(j);
        }
            
        //If the length of the original message is of uneven length, the method
        //adds the last character of the unevenChars string to the final message
            if(messageLength % 2 == 1)  message =
                    message + unevenChars.charAt(
                    messageLength / 2);
        
        return message;
    }
    
        //========================================================================
    /*
     * Directions Reduction
     * https://www.codewars.com/kata/directions-reduction
     *
     * Description: 
     * Once upon a time, on a way through the old wild west,… … a man was given
     * directions to go from one point to another. The directions were "NORTH",
     * "SOUTH", "WEST", "EAST". Clearly "NORTH" and "SOUTH" are opposite, "WEST"
     * and "EAST" too. Going to one direction and coming back the opposite
     * direction is a needless effort. Since this is the wild west, with
     * dreadful weather and not much water, it's important to save yourself some
     * energy, otherwise you might die of thirst!
     */
    
    /** Eliminates any opposing pairs of directions (i.e. NORTH and SOUTH, EAST
     * and WEST) from the provided array and returns an array with reduced
     * number of directions.
     * 
     * @param directions    represents a list of directions (directions must be
     *                      given as Strings and read "NORTH", "SOUTH", "EAST",
     *                      or "WEST")
     * @return              an array of Strings representing a reduced list of
     *                      directions
     */
    public static String[] reduceDirections(String[] directions)
    {
        int northMinusSouth = 0;
        int eastMinusWest = 0;

        /*
         * Iterates over the initial set and counts the
         * quantity of each direction
         */
        for (String direction : directions)
        {
            switch (direction)
            {
                case "NORTH":
                    northMinusSouth++;
                    break;
                case "SOUTH":
                    northMinusSouth--;
                    break;
                case "EAST":
                    eastMinusWest++;
                    break;
                default:
                    eastMinusWest--;
            }
        }    
        
        return createNewDirections(northMinusSouth, eastMinusWest);
    }
    
    
    /**
     * Helper method for reduceDirections(String[]). Takes two integers and based
     * on their values creates an array of strings representing directions.
     * @param northSouth represents the relation between "NORTH" and "SOUTH"
     *                   directions in the initial array. If positive, strings
     *                   "NORTH" are added to the new array. If negative,
     *                   strings "SOUTH" are added to the new array.
     * @param eastWest   represents the relation between "EAST" and "WEST"
     *                   directions in the initial. If positive, strings "EAST"
     *                   are added to the new array. If negative, strings
     *                   "NORTH" are added to the new array.
     * @return           an array of strings representing directions
     */
    private static String[] createNewDirections
        (int northMinusSouth, int eastMinusWest)
    {
        String[] reducedDirections
            = new String[Math.abs(northMinusSouth) + Math.abs(eastMinusWest)];

        int mainCounter = 0;
        int subCounter = 0;

        //If northSouth is positive adds "NORTH" string to the array northSouth
        //times.
        if (northMinusSouth > 0)
        {
            subCounter = 0;
            for (int i = mainCounter; i < northMinusSouth + mainCounter; i++)
            {
                reducedDirections[i] = "NORTH";
                subCounter++;
            }
            mainCounter += subCounter;
        }
        //If northSouth is negative adds "SOUTH" string to the array n times, 
        // where n is equal to the absolute value of northSouth.
        if (northMinusSouth < 0)
        {
            subCounter = 0;
            for (int i = mainCounter; i < Math.abs(northMinusSouth) + mainCounter; i++)
            {
                reducedDirections[i] = "SOUTH";
                subCounter++;
            }
            mainCounter += subCounter;
        }
        //If eastWest is positive adds "EAST" string to the array eastWest
        //times.
        if (eastMinusWest > 0)
        {
            subCounter = 0;
            for (int i = mainCounter; i < eastMinusWest + mainCounter; i++)
            {
                reducedDirections[i] = "EAST";
                subCounter++;
            }
            mainCounter += subCounter;
        }
        //If eastWest is negative adds "WEST" string to the array n times, 
        // where n is equal to the absolute value of eastWest.
        if (eastMinusWest < 0)
        {
            subCounter = 0;
            for (int i = mainCounter; i < Math.abs(eastMinusWest) + mainCounter; i++)
            {
                reducedDirections[i] = "WEST";
                subCounter++;
            }
            mainCounter += subCounter;
        }
        
        return reducedDirections;
    }
}
