/**
 * The Stats class tracks the number of correct and wrong answers and 
 * calculates the perscent correct answers (score).
 * 7/23/2014
 *
 * @author   Mario Kaack
 * @version 1.0
 */


public class Stats {


    private static int correct;
    private static int wrong;

    /**
     * Increments the number of correct answers
     *
     * @return  percentage of correct answers
     */
    public static int incCorrect() {
        correct++;
        return getScore();
    }

    /**
     * Increments the number of wrong answers
     *
     * @return  percentage of correct answers
     */
    public static int incWrong() {
        wrong++;
        return getScore();
    }

    /**
     * Returns the number of correct answers since the start of the app or the
     * last time clearStats() was called
     *
     * @return  the number of correct answers
     */
    public static int getCorrect() {
        return correct;
    }

    /**
     * Returns the number of worng answers since the start of the app or the
     * last time clearStats() was called
     *
     * @return  the number of wrong answers.
     */
    public static int getWrong() {
        return wrong;
    }

    /**
     * Calculats the percentage of correct answers since the start of the app
     * or the last time clearStats() was called.
     *
     * @return  the percentage of correct answers (score) as an int
     */
    public static int getScore() {
        if((correct + wrong) == 0) {
            return 0;
        } else {
            return (int) (((double)correct / (correct + wrong)) * 100);
        }
    }

    /**
     * Resets the values of 'correct' and 'wrong' to zero
     */
    public static void clearStats() {
        correct = 0;
        wrong = 0;
    }

}
