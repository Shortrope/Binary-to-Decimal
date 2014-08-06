import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Binary to Decimal training program allows the user to practice converting
 * binary numbers into decimal.  This is a worthwhile skill for taking Cisco 
 * certification tests.
 * The program continually displays a new binary number and a set of 4 answer
 * choices.  After the user answers each question the stats section updates
 * your score.
 * 
 * ToDo:
 *      Implement the Singleton Design Pattern with the Num and Stats Classes
 *      Visual (color) indicator of correct/wrong answer
 *      Add '%' to the score
 *      Better Stats table
 *      Resize 'Clear Stats' button
 *      Give answer buttons a fixed size so they do not change size with larger
 *      numbers
 *      The text area should only show the binary / answer to incorrect answers
 *      Give the textArea (log) a scroll bar
 *      The log should be diplayed in two ways:
 *          1. all incorrect answers as they occur
 *          2. incorrect answers and their frequency listed in decending order
 * 
 * 7/24/2014.
 * @author Mario Kaack
 */
public class BtoDGui extends JFrame {

    Num number = new Num();

    JLabel title = new JLabel("Binary to Decimal Trainer");
    JLabel numDigitsLabel = new JLabel("Select Number of Binary Digits");
    String[] numDigits_arr = new String[] {"2","3","4","5","6","7","8","Subnet Mask"};
    JComboBox<String> numDigitsSelector;
    JLabel binaryStingLabel;
    JButton answerButtons[] = new JButton[4];
    JLabel correct;
    JLabel wrong;
    JLabel score;
    JButton clearStats;
    JTextArea logArea = new JTextArea(20,20);


    public BtoDGui() {

        super("Binary to Decimal");
        GridLayout gridLayout = new GridLayout(7,1);
        this.setLayout(gridLayout);


        JPanel headerPanel = new JPanel();
        this.title.setFont(new Font("Serif", Font.BOLD, 28));
        headerPanel.add(this.title);
        this.add(headerPanel);

        JPanel numDigitsPanel = new JPanel();
        this.numDigitsSelector = new JComboBox<String>(numDigits_arr);
        this.numDigitsSelector.setSelectedIndex(2);
        ListenForNumDigitsChange lForNumDigitsChange = new ListenForNumDigitsChange();
        this.numDigitsSelector.addActionListener(lForNumDigitsChange);
        numDigitsLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        numDigitsPanel.add(numDigitsLabel);
        numDigitsPanel.add(numDigitsSelector);
        this.add(numDigitsPanel);

        JPanel binaryStringPanel = new JPanel();
        this.binaryStingLabel = new JLabel(number.getBinaryString());
        this.binaryStingLabel.setFont(new Font("SansSerif", Font.PLAIN, 48));
        binaryStringPanel.add(this.binaryStingLabel);
        this.add(binaryStringPanel);

        JPanel answerPanel = new JPanel();
        int[] answers = number.getAnswerChoices();
        ListenForAnswer lForAnswer = new ListenForAnswer();
        for(int i = 0; i < this.answerButtons.length; i++) {
            this.answerButtons[i] = new JButton();
            this.answerButtons[i].addActionListener(lForAnswer);
            this.answerButtons[i].setText(Integer.toString(answers[i]));
        }
        for (int i = 0; i < this.answerButtons.length ; i++) {
            answerPanel.add(this.answerButtons[i]);
        }
        this.add(answerPanel);

        GridLayout gridForStatsPanel = new GridLayout(3,3);
        JPanel statsPanel = new JPanel(gridForStatsPanel);
        //this.setLayout(gridLayout);
        JLabel correctLabel = new JLabel("Correct");
        JLabel wrongLabel = new JLabel("Wrong");
        JLabel scoreLabel = new JLabel("Score");
        correct = new JLabel("0");
        wrong = new JLabel("0");
        score = new JLabel("0");
        this.clearStats = new JButton("Clear Stats");
        ListenForClearStats lForClearStats = new ListenForClearStats();
        clearStats.addActionListener(lForClearStats);
        statsPanel.add(correctLabel);
        statsPanel.add(wrongLabel);
        statsPanel.add(scoreLabel);
        statsPanel.add(correct);
        statsPanel.add(wrong);
        statsPanel.add(score);
        statsPanel.add(this.clearStats);
        this.add(statsPanel);

        JPanel logPanel = new JPanel();
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        logPanel.add(logArea);
        this.add(logPanel);

        this.setSize(350, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Binary to Decimal");
        this.setVisible(true);
    }

    private class ListenForNumDigitsChange implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox)e.getSource();
            Integer numDigits = new Integer((String) cb.getSelectedItem());
            number.setNumBinaryDigits(numDigits.intValue());
            binaryStingLabel.setText(number.getBinaryString());
            int[] answers = number.getAnswerChoices();
            for(int i = 0; i < answerButtons.length; i++) {
                answerButtons[i].setText(Integer.toString(answers[i]));
            }
        }
    }

    private class ListenForAnswer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String answer = ((JButton)e.getSource()).getText();
            logArea.append(e.getActionCommand() + ", ");
            if (Integer.parseInt(answer) == number.getDecimal()) {
                Stats.incCorrect();
                newQuestion();
            } else {
                Stats.incWrong();
            }
            updateStats();
        }
    }

    private class ListenForClearStats implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Stats.clearStats();
            updateStats();

        }
    }

    // Displays a new binary number and its answer choices
    private void newQuestion() {
        binaryStingLabel.setText(number.getNextBinaryString());
        int[] answers = number.getAnswerChoices();
        for(int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setText(Integer.toString(answers[i]));
        }

    }

    // Stats are calculated and displayed.  Done after an answer has been chosen
    private void updateStats() {
        correct.setText(Integer.toString(Stats.getCorrect()));
        wrong.setText(Integer.toString(Stats.getWrong()));
        score.setText(Integer.toString(Stats.getScore()));
    }


    public static void main(String[] args) {

        new BtoDGui();
            
    }
}

