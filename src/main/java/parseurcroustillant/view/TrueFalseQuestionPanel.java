package parseurcroustillant.view;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import org.tsaap.questions.Question;

public class TrueFalseQuestionPanel extends QuestionPanel {
	private static final long serialVersionUID = 1L;
	
	private ButtonGroup mButtonGroup = new ButtonGroup();
	private JRadioButton vrai, faux;

	public TrueFalseQuestionPanel(Question q) {
		super(q);
		
		vrai = new JRadioButton("Vrai");
		faux = new JRadioButton("Faux");
		mButtonGroup.add(vrai);
		mButtonGroup.add(faux);
		add(vrai);
		add(faux);
	}

	@Override
	public float processSubmit() {
		return (vrai.isSelected() ? 1 : 0);
	}

	@Override
	public void processCancel() {
		mButtonGroup.clearSelection();
	}

}
