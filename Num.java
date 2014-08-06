import java.util.Arrays;
import java.util.Random;

/**
 * The class Num represents the decimal number and the variables
 * required to create the test question for this number.
 *
 * The number of binary digits is required first.
 * The max decimal value is calculated from the number of binary digits
 * The decimal is chosen at random with in the range 0 to maxDecimalValue
 * Then an array of 4 unique answer choices is created
 *
 * setNumBinaryDigits() calls setMaxDecimalValue() which calls 
 * setNextDecimal() whitch calls createChoices().
 * 
 * 7/23/2014.
 *
 * @author  Mario Kaack
 */
public class Num {

    private static Random rgen = new Random();

    private int numBinaryDigits;
    private int maxDecimalValue;
    private int decimal;
    private int[] answerChoices;

    /**
     * By calling setNumBinaryDigits() all the other methods required for
     * creating the random number and creating the supporting variables to create the 
     * question is set in motion.
     * The default number of binary digits is 4.
     */ 
    public Num() {
        setNumBinaryDigits(4);
    }
    
    /**
     * The max number of binary digits allowed is 8, min is 2.
     * If the number of binary digits is changed then the maximum deciaml value
     * must be updated also.
     */
    public void setNumBinaryDigits(int numDigits) {
        if (numDigits >= 2 && numDigits <= 8) numBinaryDigits = numDigits;
        else numBinaryDigits = 2;
        setMaxDecimalValue();
    }

    /**
     * Calculates the largest decimal value possible given the number of 
     * binary digits.
     * If this method is called it is because the number of binary digits has 
     * changed, so the 'decimal' value must be updated as well.
     */
    private void setMaxDecimalValue() {
        maxDecimalValue = (int) Math.pow(2, numBinaryDigits) - 1;
        setNextDecimal();
    }
    
    /**
     * Generates the next random decimal number. If the 'decimal' value changes
     * so must the answer choices.
     *
     * @return A new random decimal number.
     */
    private int setNextDecimal() {
        int nextNum = rgen.nextInt(maxDecimalValue + 1);
        decimal = nextNum;
        createChoices();
        return nextNum;
    }

    /**
     * Generates an array of 4 answer choices for the current 'decimal' number.
     * The first is the current 'decimal' number (the correct answer choice). 
     * 3 more unique random numbers are generated within the current range of 
     * numbers and added to the array.  The array is then sorted and returned.
     *
     * @return A sorted array of 4 unique answer choices.
     */
    private int[] createChoices() {
        int[] choices = new int[4];
        choices[0] = decimal;
        for (int i = 1; i < 4 ; i++) {
            boolean unique;
            do {
                unique = true;
                int newChoice = rgen.nextInt(maxDecimalValue + 1);
                for (int j = 0; j < i; j++) {
                    if (choices[j] == newChoice) {
                        unique = false;
                        break;
                    } else {
                        choices[i]  = newChoice;
                    }
                }
            } while(!unique);
        }
        Arrays.sort(choices);
        answerChoices = choices;
        return choices;
    }

    /**
     * Creates the binary string of the current 'decimal' number.
     *
     * @return A binary string equal to the current 'decimal' number.
     */
    public String getBinaryString() {
        StringBuilder bString = new StringBuilder("");
        String bStr = Integer.toBinaryString(decimal);
        for (int i = 7; i >= bStr.length(); i--) {
            bString.append("0");
        }
        bString.append(bStr);
        bString.insert(4,' ');
        return bString.toString();
    }

    /**
     * Generates a new random decimal number which will generate new answer
     * choices as well, and return the new binary string.
     * 
     * @return Binary string of the newly generated decimal number.
     */
    public String getNextBinaryString() {
        setNextDecimal();
        return getBinaryString();
    }

    /**
     * Return the current 'decimal' number.
     *
     * @return Current 'decimal' number.
     */
    public int getDecimal() {
        return decimal;
    }

    /**
     * Retruns the current answer choices.
     *
     * @return Answer choices as an int array.
     */
    public int[] getAnswerChoices() {
        return answerChoices;
    }




}
