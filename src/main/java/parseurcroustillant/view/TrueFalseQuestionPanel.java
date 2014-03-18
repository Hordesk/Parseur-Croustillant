package parseurcroustillant.view;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import org.tsaap.questions.Question;

public class TrueFalseQuestionPanel extends QuestionPanel {
	private static final long serialVersionUID = 1L;
	
	ButtonGroup mButtonGroup = new ButtonGroup();

	public TrueFalseQuestionPanel(Question q) {
		super(q);
		
		JRadioButton vrai = new JRadioButton("Vrai");
		JRadioButton faux = new JRadioButton("Faux");
		mButtonGroup.add(vrai);
		mButtonGroup.add(faux);
		add(vrai);
		add(faux);
	}

	@Override
	public int processSubmit() {
		// TODO
		return 0;
	}

	@Override
	public void processCancel() {
		mButtonGroup.clearSelection();
	}

}
