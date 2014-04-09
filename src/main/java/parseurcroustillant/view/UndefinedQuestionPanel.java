package parseurcroustillant.view;

import javax.swing.JLabel;

import org.tsaap.questions.Question;

public class UndefinedQuestionPanel extends QuestionPanel {
	private static final long serialVersionUID = 1L;

	public UndefinedQuestionPanel(Question q) {
		super(q);

		add(new JLabel("Question en cours d'�criture..."));
	}

	/**
	 * Process the submission of the question
	 * @return the score
	 */
	public float processSubmit() {
		// return a bad score
		return 0;
	}

	/**
	 * Process the cancel
	 */
	public void processCancel() {
		
	}

}
