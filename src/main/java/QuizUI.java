import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class QuizUI extends JFrame {

    public static List<Question> questions1 = new ArrayList<>();
    public static List<Question> questions2 = new ArrayList<>();

    private static final String text1 = "Technology has become a part of our daily lives, and its impacts can be seen everywhere we go. Oftentimes, we rely on technology and only pay attention to the positive side of technology, however, technology is not without its drawbacks. Technology affects both people and the environment in a variety of ways, positive or negative. \n" +
            "\n" +
            "People need company, however, this is not always possible, due to circumstances. For example, if you are on vacation, and want to have a chat with family, you can call them. Staying in touch with relatives and friends is extremely important, and being a part of a social media platform allows you to talk and stay connected with those people. Social media and technology make this process much easier and straightforward, imagine having to write your relatives letters by hand to keep in contact with them. However, these same social media platforms are designed to earn money, which means that they want you to spend as much time as possible on the platform. This behavior causes teenagers to live on social media, and reduce in-person contact.\n" +
            "\n" +
            "Spending too much time on a device has negative consequences, both mentally and physically. When people are constantly on social media, they tend to care about their reputation or to put it another way, the amount of likes and comments they get on their posts. This leads to a rush when they get a lot of likes, and a severe depression when they don't. This behavior causes depression and loneliness when the goal of social media is to remain social. Alongside the mental effect of technology, the physical effect is just as noticeable. Sleep deprivation, eye strain, bad posture, and lack of exercise, all of these are products of being addicted to social media and technology. All of these can have lasting effects on you and your social life. \n" +
            "\n" +
            "The issues that technology presents regarding mental and physical health are all solvable. Spending less time using a device is the main way to reduce the negatives of technology. The issue with this approach is that people are addicted to their devices, however, there are also solutions to this. Creating a specific schedule for your device usage, and limiting screen time gradually are ways to progressively reduce the amount of time spent on your phone or computer. If social media is still affecting your mental health, however, then getting a consultation from a trusted person is a good solution. A teacher, parent, sibling, or therapist can all help to overcome the challenges that social media presents.\n" +
            "\n" +
            "Technology has become a necessity in our lives, and learning to use it can help us connect with others, and become the best version of ourselves. Nevertheless, understanding the pitfalls of technology helps us away from the negatives.";

    private static final String text2 = "Technology is having an incredibly large impact on the environment. Technology is everywhere, and its impact is seen everywhere, from global warming, to pollution. When it comes to the environment, technology is its enemy, and if something isn't done to reduce the environmental impact, then there will be consequences.\n" +
            "Technology has a gigantic carbon footprint, every piece of technology produced has a carbon footprint. For example, fabricating your smartphone can produce anywhere from 60kg-120kg of carbon dioxide. Considering the sheer amount of smartphones being produced each day, this is a ludicrous amount of CO2 emissions from only the production of your smartphone. Also, to produce a smartphone, specific metals are needed, such as nickel, iron, and cobalt, which need to be mined from the earth. This mining process pollutes the environment, and also releases a large amount of CO2. Using your cell phone also isn't clean, since electricity is mainly produced by burning fossil fuels. About 60% of the electricity generated in the US is from fossil fuels, which also release CO2. This means that during the lifetime of your device, it is constantly negatively affecting the environment.\n" +
            "\n" +
            "Reducing the effects on the environment is not straightforward, and as an individual, your contribution is mostly insignificant. However, you can still be a part of the change for the better. For example, supporting the South Pole initiative can help reduce the emissions when transporting materials and other goods. Also, spreading awareness about the effect of technology on the environment, and encouraging renewable energy is another good way to help fight the effect of technology.\n";

    static {

        questions1.add(new Question("When do we use technology", List.of("Every waking second", "Everyday for various tasks", "Only in emergencies", "Never"), 1));
        questions1.add(new Question("Technology doesnt affect your health.", List.of("True", "False"), 1));
        questions1.add(new Question("What is social media used for?", List.of("Staying in touch with relatives", "Chatting with friends", "Gaining reputation", "All of the above"), 3));
        questions1.add(new Question("What phyiscal health effects does technology present?", List.of("Lack of sleep", "Dizziness", "Eye strain", "Raspy voice", "A & B only", "A & C only"), 5));
        questions1.add(new Question("Who can you to about the impact of technology on you?", List.of("Coworker", "Parent", "Stranger", "All of the above"), 1));
        questions1.add(new Question("Reducing & maintaining screen time at 5h/day is healthy.", List.of("True", "False"), 1));

        questions2.add(new Question("When does your cellphone become clean?", List.of("Its always clean", "After production", "After it stops working", "Never"), 2));
        questions2.add(new Question("What % of energy is produced from fossil fuels (US)?", List.of("30%", "40%", "50%", "60%", "70%"), 3));
        questions2.add(new Question("What does the South Pole initiative do?", List.of("Provide clean transportation", "Help penguins in the South Pole", "Raise funds and awareness for melting icebergs", "Help buinesses reduce their CO2 emissions"), 0));

    }

    public static void main(String[] args) {

        new QuizUI().setVisible(true);


    }

    private List<Question> activeQuestions;

    QuestionPanel questionPanel;
    int questionIndex;

    JButton nextButton;

    JLabel titleLabel;
    JTextArea textField;
    JLabel resultLabel;

    public QuizUI() {

        questionIndex = 0;
        setTitle("Environmental Quiz");
        setSize(800, 400);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                rescale(e.getComponent().getSize());
            }
        });

        resultLabel = new JLabel();
        titleLabel = new JLabel();
        textField = new JTextArea();
        textField.setLineWrap(true);
        textField.setEditable(false);

        titleLabel.setText("Effects of Technology Reading");
        textField.setText(text1);

        questionPanel = new QuestionPanel(new Question("Question", List.of("Option1", "Option2", "Option3", "Option4", "Option5"), 2));
        nextButton = new JButton();
        nextButton.setText("Next");
        nextButton.setMnemonic(KeyEvent.VK_N);
        nextButton.addActionListener(this::nextButtonActionListener);

        activeQuestions = questions1;

        add(titleLabel);
        add(textField);
        add(nextButton);

        this.rescale(getSize());
    }

    private void rescale(Dimension d) {

        titleLabel.setBounds((d.width - d.width / 2) / 2, 0, d.width - d.width / 14, d.height / 4);
        titleLabel.setFont(new Font("serif", Font.PLAIN, Math.round(d.height / 16.0f)));

        textField.setBounds((d.width - d.width / 2) / 2, d.height / 5, d.width / 2,  d.height / 2);
        textField.setFont(new Font("serif", Font.PLAIN, Math.round(d.height / 75.0f)));

        questionPanel.setBounds(d.width / 4, d.height / 4, d.width / 2, d.height / 2);
        questionPanel.rescale(d.width / 2, d.height / 2);

        nextButton.setBounds((d.width - d.width / 16) / 2 /*+ d.width / 16*/, d.height - d.height / 4, d.width / 16, d.height / 16);
        nextButton.setFont(new Font("serif", Font.PLAIN, Math.round(d.height / 32.0f)));

        resultLabel.setBounds((d.width - d.width / 2) / 2, (d.height - d.height / 2) / 2, d.width / 2, d.height / 2);
        resultLabel.setFont(new Font("serif", Font.PLAIN, Math.round(d.height / 32.0f)));

    }

    private void nextButtonActionListener(ActionEvent e) {

        textField.setVisible(false);

        questionPanel.markAnswer();

        remove(questionPanel);

        if (activeQuestions.size() <= questionIndex) {

            titleLabel.setText("Environmental Stewardship Reading");

            for (Component c : this.getContentPane().getComponents()) {

                c.setVisible(false);

            }

            if (this.activeQuestions == questions2) {

                int count = 0;

                List<Question> temp = new ArrayList<>();
                temp.addAll(questions1);
                temp.addAll(questions2);

                for (Question q : temp) {

                    if (q.isCorrect()) count++;

                }

                resultLabel.setText("Evaluation Results: " + count + "/" + temp.size() + " -> " + (count / temp.size() * 100) + "%");
                add(resultLabel);
                return;
            }

            activeQuestions = questions2;
            questionIndex = 0;
            textField.setText(text2);
            textField.setVisible(true);
            nextButton.setVisible(true);
            titleLabel.setText("Environmental Effects Reading");
            titleLabel.setVisible(true);

            return;
        }

        questionPanel.setVisible(true);
        questionPanel = new QuestionPanel(activeQuestions.get(questionIndex));

        questionIndex++;

        add(questionPanel);
        rescale(this.getSize());
    }

}
