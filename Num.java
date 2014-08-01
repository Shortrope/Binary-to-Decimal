import java.util.Arrays;
import java.util.Random;

/**
 * Created by Mario on 7/23/2014.
 * The class Num represents the decimal number and all the other variables
 * required to create the test question for this number.
 *
 * The number of binary digits is required first.
 * The max decimal value is calculated from the number of binary digits
 * The decimal is chosen at random w/in the range 0 to maxDecimalValue
 * Then an array of 4 unique answer choices is created
 *
 */
public class Num {

    private static Random rgen = new Random();

    private int numBinaryDigits;
    private int maxDecimalValue;
    private int decimal;
    private int[] answerChoices;
    private int nextDecimal;

    public Num() {
        numBinaryDigits = 4;
        setMaxDecimalValue();
        // setNextDecimal() is called in setMaxDecimalValue()
        // createChoices() is called int getNextDecimal()
    }

    public void setNumBinaryDigits(int numDigits) {
        numBinaryDigits = numDigits;
        setMaxDecimalValue();

    }

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

    public String getNextBinaryString() {
        setNextDecimal();
        return getBinaryString();
    }

    public int getDecimal() {
        return decimal;
    }

    public int[] getAnswerChoices() {
        return answerChoices;
    }

    private void setMaxDecimalValue() {
        maxDecimalValue = (int) Math.pow(2, numBinaryDigits) - 1;
        setNextDecimal();
    }

    private int setNextDecimal() {
        int nextNum = rgen.nextInt(maxDecimalValue + 1);
        decimal = nextNum;
        createChoices();
        return nextNum;
    }

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

}
