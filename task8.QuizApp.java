import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApp {
    private JFrame frame;
    private JPanel panel;
    private JLabel questionLabel, timerLabel;
    private JRadioButton option1, option2, option3, option4;
    private ButtonGroup optionsGroup;
    private JButton submitButton;
    private Timer timer;
    private int timeLeft = 30; // 30 seconds for the quiz

    private String[] questions = {
        "What is the capital of India?",
        "Which planet is known as the Red Planet?",
        "What is 2 + 2?"
    };

    private String[][] options = {
        {"Mumbai", "Banglore", "Hyderabad", "Delhi"},
        {"Mars", "Earth", "Venus", "Jupiter"},
        {"3", "4", "5", "6"}
    };

    private int[] correctAnswers = {3, 0, 1}; // Index of correct answers

    private int currentQuestion = 0;
    private int score = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizApp().createAndShowGUI());
    }

    public void createAndShowGUI() {
        frame = new JFrame("Quiz App");
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Font largeFont = new Font("Arial", Font.BOLD, 20);
        questionLabel = new JLabel(questions[currentQuestion]);
        questionLabel.setFont(largeFont);
        panel.add(questionLabel);

        option1 = new JRadioButton(options[currentQuestion][0]);
        option2 = new JRadioButton(options[currentQuestion][1]);
        option3 = new JRadioButton(options[currentQuestion][2]);
        option4 = new JRadioButton(options[currentQuestion][3]);

        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        panel.add(option1);
        panel.add(option2);
        panel.add(option3);
        panel.add(option4);

        submitButton = new JButton("Submit Answer");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                if (currentQuestion < questions.length - 1) {
                    currentQuestion++;
                    loadNextQuestion();
                } else {
                    endQuiz();
                }
            }
        });
        panel.add(submitButton);

        timerLabel = new JLabel("Time Left(10sec for each Question): " + timeLeft + " sec");
        panel.add(timerLabel);

        frame.add(panel);
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        startTimer();
    }

    private void loadNextQuestion() {
        questionLabel.setText(questions[currentQuestion]);
        option1.setText(options[currentQuestion][0]);
        option2.setText(options[currentQuestion][1]);
        option3.setText(options[currentQuestion][2]);
        option4.setText(options[currentQuestion][3]);

        optionsGroup.clearSelection();
    }

    private void checkAnswer() {
        if (option1.isSelected() && option1.getText().equals(options[currentQuestion][correctAnswers[currentQuestion]])) {
            score++;
        } else if (option2.isSelected() && option2.getText().equals(options[currentQuestion][correctAnswers[currentQuestion]])) {
            score++;
        } else if (option3.isSelected() && option3.getText().equals(options[currentQuestion][correctAnswers[currentQuestion]])) {
            score++;
        } else if (option4.isSelected() && option4.getText().equals(options[currentQuestion][correctAnswers[currentQuestion]])) {
            score++;
        }
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time Left(10sec for each Question): " + timeLeft + " seconds");
                if (timeLeft <= 0) {
                    timer.stop();
                    endQuiz();
                }
            }
        });
        timer.start();
    }

    private void endQuiz() {
        JOptionPane.showMessageDialog(frame, "Quiz Over! Your score is: " + score);
        System.exit(0);
    }
}
