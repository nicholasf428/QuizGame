import java.awt.event.*;
import java.awt.*;
import java.sql.Time;
import javax.swing.*;

public class Quiz implements ActionListener {

    String[] questions = {
            "What is Lady Gaga's real name?",
            "In which year did Lady Gaga release her debut album?",
            "What is the title of Lady Gaga's first hit single?",
            "Which song by Lady Gaga won an Academy Award for Best Original Song?",
            "How many Grammy Awards has Lady Gaga won?",
            "Lady Gaga performed at the halftime show of which Super Bowl?",
            "Which song by Lady Gaga became the best-selling digital single in the United States?",
            "Lady Gaga has a fanbase known as what?",
            "Lady Gaga's album \"The Fame\" includes the hit single \"Poker Face,\" but what was its follow-up single?",
            "Lady Gaga's iconic meat dress was worn at which awards ceremony?"


    };

    String[][] options = {
            {"Stefani Joanne Angelica Germanotta", "Stefani Angelina Joanne Germanotta",
                    "Stefani Joanne Angelina Germanotta", "BooBoo The Fool"},
            {"1989", "2008", "2007", "2009"},
            {"Poker Face", "Bad Romance", "Just Dance", "The Fame"},
            {"Born This Way", "G.U.Y.", "The Cure", "Shallow"},
            {"15","12","11","6"},
            {"Super Bowl LI (51)","Super Bowl XLV (45)","Super Bowl LII (52)","Super Bowl XLIX (49)"},
            {"Born This Way","Rain On Me","Bad Romance","Jewels N' Drugs"},
            {"Little Critters","Little Monsters","Queers","Faggots"},
            {"Beautiful Dirty Rich","Bad Romance","Telephone","LoveGame"},
            {"MTV Video Music Awards","Grammy Awards","Academy Awards","Billboard Music Awards"}

    };

    char[] answers = {
            'C', 'B', 'C', 'D', 'C', 'A', 'C', 'B', 'D', 'A'
    };

    char guess;
    char answer;
    int index;
    int correctGuesses = 0;
    int totalQuestions = questions.length;
    int result;
    int seconds = 20;

    JFrame frame = new JFrame();
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answerLabelA = new JLabel();
    JLabel answerLabelB = new JLabel();
    JLabel answerLabelC = new JLabel();
    JLabel answerLabelD = new JLabel();
    JLabel timeLabel = new JLabel();
    JLabel secondsLeft = new JLabel();
    JTextField numberRight = new JTextField();
    JTextField percentageCorrect = new JTextField();

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           seconds--;
           secondsLeft.setText(String.valueOf(seconds));
           if(seconds<=0){
               displayAnswer();
           }
        }
    });

    public Quiz() {
        //Establish size and color of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 650);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(null);
        frame.setResizable(false);

        //Establish Quiz Game text at the top
        textField.setBounds(0, 0, 650, 50);
        textField.setBackground(new Color(25, 25, 25));
        textField.setForeground(new Color(25, 255, 0));
        textField.setFont(new Font("Ink Free", Font.PLAIN, 30));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);

        //Establish area where question will be displayed
        textArea.setBounds(0, 50, 650, 50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(25, 25, 25));
        textArea.setForeground(new Color(25, 255, 0));
        textArea.setFont(new Font("MV Boli", Font.PLAIN, 15));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);

        //Establish area for buttons
        buttonA.setBounds(0, 100, 100, 100);
        buttonA.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0, 200, 100, 100);
        buttonB.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(0, 300, 100, 100);
        buttonC.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(0, 400, 100, 100);
        buttonD.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        //Establish area for answers
        answerLabelA.setBounds(125, 100, 500, 100);
        answerLabelA.setBackground(new Color(50, 50, 50));
        answerLabelA.setForeground(new Color(25, 255, 0));
        answerLabelA.setFont(new Font("MMV Boli", Font.PLAIN, 25));

        answerLabelB.setBounds(125, 200, 500, 100);
        answerLabelB.setBackground(new Color(50, 50, 50));
        answerLabelB.setForeground(new Color(25, 255, 0));
        answerLabelB.setFont(new Font("MMV Boli", Font.PLAIN, 25));

        answerLabelC.setBounds(125, 300, 500, 100);
        answerLabelC.setBackground(new Color(50, 50, 50));
        answerLabelC.setForeground(new Color(25, 255, 0));
        answerLabelC.setFont(new Font("MMV Boli", Font.PLAIN, 25));

        answerLabelD.setBounds(125, 400, 500, 100);
        answerLabelD.setBackground(new Color(50, 50, 50));
        answerLabelD.setForeground(new Color(25, 255, 0));
        answerLabelD.setFont(new Font("MMV Boli", Font.PLAIN, 25));

        //Establish timer at bottom right corner of app
        secondsLeft.setBounds(535, 510, 100, 100);
        secondsLeft.setBackground(new Color(25, 25, 25));
        secondsLeft.setForeground(new Color(255, 0, 0));
        secondsLeft.setFont(new Font("Ink Free", Font.BOLD, 60));
        secondsLeft.setBorder(BorderFactory.createBevelBorder(1));
        secondsLeft.setOpaque(true);
        secondsLeft.setHorizontalAlignment(JTextField.CENTER);
        secondsLeft.setText(String.valueOf(seconds));

        //Label above timer
        timeLabel.setBounds(535, 475, 100, 25);
        timeLabel.setBackground(new Color(50, 50, 50));
        timeLabel.setForeground(new Color(255, 0, 0));
        timeLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        timeLabel.setText("Timer");

        //Establish area for number of correct answer
        numberRight.setBounds(225, 225, 200, 100);
        numberRight.setBackground(new Color(25, 25, 25));
        numberRight.setForeground(new Color(25, 255, 0));
        numberRight.setFont(new Font("Ink Free", Font.BOLD, 50));
        numberRight.setBorder(BorderFactory.createBevelBorder(1));
        numberRight.setHorizontalAlignment(JTextField.CENTER);
        numberRight.setEditable(false);

        //Display percentage right
        percentageCorrect.setBounds(225, 325, 200, 100);
        percentageCorrect.setBackground(new Color(25, 25, 25));
        percentageCorrect.setForeground(new Color(25, 255, 0));
        percentageCorrect.setFont(new Font("Ink Free", Font.BOLD, 50));
        percentageCorrect.setBorder(BorderFactory.createBevelBorder(1));
        percentageCorrect.setHorizontalAlignment(JTextField.CENTER);
        percentageCorrect.setEditable(false);


        //Frame adds

        frame.add(timeLabel);
        frame.add(secondsLeft);
        frame.add(answerLabelA);
        frame.add(answerLabelB);
        frame.add(answerLabelC);
        frame.add(answerLabelD);

        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textArea);
        frame.add(textField);
        frame.setVisible(true);

        nextQuestion();
    }

    //Methods
    public void nextQuestion() {

        if (index >= totalQuestions) {
            finalResults();
        } else {
            textField.setText("Question " + (index + 1));
            textArea.setText(questions[index]);
            answerLabelA.setText(options[index][0]);
            answerLabelB.setText(options[index][1]);
            answerLabelC.setText(options[index][2]);
            answerLabelD.setText(options[index][3]);
            timer.start();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (e.getSource() == buttonA) {
            answer = 'A';
            if (answer == answers[index]) {
                correctGuesses++;
            }
        } else if (e.getSource() == buttonB) {
            answer = 'B';
            if (answer == answers[index]) {
                correctGuesses++;
            }
        } else if (e.getSource() == buttonC) {
            answer = 'C';
            if (answer == answers[index]) {
                correctGuesses++;
            }
        } else if (e.getSource() == buttonD) {
            answer = 'D';
            if (answer == answers[index]) {
                correctGuesses++;
            }
        }
        displayAnswer();
    }

    public void displayAnswer() {

        timer.stop();
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (answers[index] != 'A') {
            answerLabelA.setForeground(new Color(255, 0, 0));
        }
        if (answers[index] != 'B') {
            answerLabelB.setForeground(new Color(255, 0, 0));
        }
        if (answers[index] != 'C') {
            answerLabelC.setForeground(new Color(255, 0, 0));
        }
        if (answers[index] != 'D') {
            answerLabelD.setForeground(new Color(255, 0, 0));
        }

        Timer pause = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answerLabelA.setForeground(new Color(25, 255, 0));
                answerLabelB.setForeground(new Color(25, 255, 0));
                answerLabelC.setForeground(new Color(25, 255, 0));
                answerLabelD.setForeground(new Color(25, 255, 0));
                answer = ' ';
                seconds = 10;
                secondsLeft.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();
            }
        });
        pause.setRepeats(false);
        pause.start();
    }

    public void finalResults() {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        result = (int) ((correctGuesses / (double) totalQuestions) * 100);

        textField.setText("RESULTS:");
        textArea.setText("");
        answerLabelA.setText("");
        answerLabelB.setText("");
        answerLabelC.setText("");
        answerLabelD.setText("");

        numberRight.setText("(" + correctGuesses + "/" + totalQuestions + ")");
        percentageCorrect.setText(result+"%");

        frame.add(percentageCorrect);
        frame.add(numberRight);

    }
}
