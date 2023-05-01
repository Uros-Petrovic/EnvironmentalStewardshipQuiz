import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class QuestionPanel extends JPanel {

    private Question question;
    private ButtonGroup group;
    private JLabel promptLabel;
    private List<JRadioButton> radioButtons;

    public QuestionPanel(Question question) {
        super();

        this.group = new ButtonGroup();
        this.question = question;
        this.radioButtons = new ArrayList<>();

        promptLabel = new JLabel();
        promptLabel.setText(question.prompt);

        setLayout(null);
        setFont(new Font("serif", Font.PLAIN, 20));
        for (int x = 0; x < question.options.size(); x++) {

            String option = question.options.get(x);

            JRadioButton radioButton = new JRadioButton((char)(65 + x) + ". " + option);
            radioButton.setMnemonic((char) (65 + x));
            group.add(radioButton);

            radioButtons.add(radioButton);
            add(radioButton);
        }

        add(promptLabel);
    }

    public void rescale(int width, int height) {

        promptLabel.setBounds(width / 8, height / 4 - height / 8 , width, height / 8);
        promptLabel.setFont(new Font("serif", Font.PLAIN, Math.round(height / 16.0f)));

        for (int x = 0; x < this.radioButtons.size(); x++) {

            JRadioButton radioButton = this.radioButtons.get(x);

            radioButton.setFont(new Font("serif", Font.PLAIN, Math.round(height / 20.0f)));
            radioButton.setBounds(width / 8, height / 4 + height / 8 * x , width, height / 8);

        }

    }

    public void markAnswer() {

        for (int x = 0; x < question.options.size(); x++) {

            JRadioButton radioButton = this.radioButtons.get(x);

            if (radioButton.isSelected()) {

                this.question.answer = x;

            }

        }

    }


}
