/**
 * Created by Mario on 7/23/2014.
 */
public class Stats {

    private static int correct;
    private static int wrong;

    public static int incCorrect() {
        correct++;
        return getScore();
    }
    public static int incWrong() {
        wrong++;
        return getScore();
    }
    public static int getCorrect() {
        return correct;
    }
    public static int getWrong() {
        return wrong;
    }
    public static int getScore() {
        if((correct + wrong) == 0) {
            return 0;
        } else {
            return (int) (((double)correct / (correct + wrong)) * 100);
        }
    }
    public static void clearStats() {
        correct = 0;
        wrong = 0;
    }

}
