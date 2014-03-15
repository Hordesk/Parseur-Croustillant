package parseurcroustillant.view;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.tsaap.questions.Answer;
import org.tsaap.questions.AnswerBlock;
import org.tsaap.questions.Question;
import org.tsaap.questions.Quiz;

public class View extends JFrame {
	private static final long serialVersionUID = 1704142895469455808L;
	
	private Quiz mQuiz;
	
	public View(Quiz quiz) {
		mQuiz = quiz;
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		showQuiz();
	}
	
	public void showQuiz() {
		for(Question question : mQuiz.getQuestionList()) {
			JPanel questionPanel = new JPanel();
			questionPanel.setBorder(BorderFactory.createTitledBorder(question.getTitle()));
			add(questionPanel);
			switch(question.getQuestionType()) {
				case EXCLUSIVE_CHOICE:
					showExclusiveChoice(questionPanel, question);
					break;
				case FILL_IN_THE_BLANK:
					showFillInTheBlank(questionPanel, question);
					break;
				case MULTIPLE_CHOICE:
					showMultipleChoice(questionPanel, question);
					break;
				case TRUE_FALSE:
					showTrueFalse(questionPanel, question);
					break;
				case UNDEFINED:
					showUndefined(questionPanel, question);
					break;
				default:
					break;
			}
		}
	}

	private void showUndefined(JPanel questionPanel, Question question) {
		questionPanel.add(new JLabel("Question en cours d'écriture"));
	}

	private void showTrueFalse(JPanel questionPanel, Question question) {
		ButtonGroup group = new ButtonGroup();
		JRadioButton vrai = new JRadioButton("Vrai");
		JRadioButton faux = new JRadioButton("False");
		group.add(vrai);
		group.add(faux);
		questionPanel.add(vrai);
		questionPanel.add(faux);
	}

	private void showFillInTheBlank(JPanel questionPanel, Question question) {
		questionPanel.add(new JTextField(50));
	}

	private void showMultipleChoice(JPanel questionPanel, Question question) {
		for(AnswerBlock answerBlock : question.getAnswerBlockList()) { 
			for(Answer answer : answerBlock.getAnswerList()) {
				questionPanel.add(new JCheckBox(answer.getTextValue()));
			}
		}
	}

	private void showExclusiveChoice(JPanel questionPanel, Question question) {
		for(AnswerBlock answerBlock : question.getAnswerBlockList()) {
			ButtonGroup group = new ButtonGroup();
			for(Answer answer : answerBlock.getAnswerList()) {
				JRadioButton radio = new JRadioButton(answer.getTextValue());
				group.add(radio);
				questionPanel.add(radio);
			}
		}
	}
	
	
}
