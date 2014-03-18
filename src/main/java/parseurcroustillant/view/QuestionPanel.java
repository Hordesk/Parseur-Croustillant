package parseurcroustillant.view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.tsaap.questions.Question;

public abstract class QuestionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	protected Question mQuestion;
	
	public QuestionPanel(Question q) {
		mQuestion = q;
		
		setBorder(BorderFactory.createTitledBorder(mQuestion.getTitle()));
		
	}
	
	/**
	 * Process the submission of the question
	 * @return the score
	 */
	public abstract int processSubmit();
	
	/**
	 * Process the cancel
	 */
	public abstract void processCancel();
}
