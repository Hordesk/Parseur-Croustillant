package parseurcroustillant.view;

import javax.swing.JTextField;

import org.tsaap.questions.Question;

public class FillInTheBlankQuestionPanel extends QuestionPanel {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_LINEEDIT_LENGHT = 50;
	
	private JTextField mAnswerTextField = new JTextField(DEFAULT_LINEEDIT_LENGHT);

	public FillInTheBlankQuestionPanel(Question q) {
		super(q);
		
		add(mAnswerTextField);
	}

	@Override
	public int processSubmit() {
		//TODO
		return 0;
	}

	@Override
	public void processCancel() {
		mAnswerTextField.setText("");
	}

}
