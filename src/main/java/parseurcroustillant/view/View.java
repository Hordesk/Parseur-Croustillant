package parseurcroustillant.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.sound.sampled.LineEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.tsaap.questions.Answer;
import org.tsaap.questions.AnswerBlock;
import org.tsaap.questions.Question;
import org.tsaap.questions.QuestionBlock;
import org.tsaap.questions.Quiz;

public final class View extends JFrame {
	private static final long serialVersionUID = 1704142895469455808L;
	private static final int DEFAULT_WINDOW_SIZE = 600;
	
	private Quiz mQuiz;
	private JPanel mCentralWidget;
	private JLabel mInfoLabel;
	
	private List<QuestionPanel> mQuestionPanels = new LinkedList<QuestionPanel>();

	private class QuizSubmitListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// SUBMIT CLICKED
			Integer score = new Integer(0);
			for(QuestionPanel panel : mQuestionPanels) {
				score += panel.processSubmit();
			}
			score /= mQuestionPanels.size();
		
			//JOptionPane.showMessageDialog(new JFrame(),
				//    "Vous avez : " + score.toString() + " / 100.");
			mInfoLabel.setText("Vous avez : " + score.toString() + " / 100.");

			// Show score
		}
		
	}
	
	private class QuizCancelListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// CANCEL CLICKED
			for(QuestionPanel panel : mQuestionPanels) {
				panel.processCancel();
			}
		}
		
	}
	
	public View(Quiz quiz) {
		mQuiz = quiz;
		setSize(DEFAULT_WINDOW_SIZE, DEFAULT_WINDOW_SIZE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		mCentralWidget = new JPanel();
		setLayout(new BorderLayout());
		add(mCentralWidget, BorderLayout.CENTER);
		mCentralWidget.setLayout(new BoxLayout(mCentralWidget, BoxLayout.Y_AXIS));
		
		showQuiz();
		
		mInfoLabel = new JLabel();
		mCentralWidget.add(mInfoLabel);
		
		JPanel submitPanel = new JPanel();
		JButton submitButton = new JButton("Valider");
		JButton cancelButton = new JButton("Effacer");
		submitButton.addActionListener(new QuizSubmitListener());
		cancelButton.addActionListener(new QuizCancelListener());
		submitPanel.add(submitButton);
		submitPanel.add(cancelButton);
		add(submitPanel, BorderLayout.SOUTH);
		
	}
	
	public void showQuiz() {
		for(Question question : mQuiz.getQuestionList()) {
			QuestionPanel panel;
			switch(question.getQuestionType()) {
			case EXCLUSIVE_CHOICE:
				panel = new ExclusiveChoiceQuestionPanel(question);
				break;
			case FILL_IN_THE_BLANK:
				panel = new FillInTheBlankQuestionPanel(question);
				break;
			case MULTIPLE_CHOICE:
				panel = new MultipleChoiceQuestionPanel(question);
				break;
			case TRUE_FALSE:
				panel = new TrueFalseQuestionPanel(question);
				break;
			case UNDEFINED:
			default:
				panel = new UndefinedQuestionPanel(question);
				break;
			}
			
			mCentralWidget.add(panel);
			mQuestionPanels.add(panel);
			
		}
	}
}
