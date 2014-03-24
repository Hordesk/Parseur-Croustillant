package parseurcroustillant.view;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JCheckBox;

import org.tsaap.questions.Answer;
import org.tsaap.questions.AnswerBlock;
import org.tsaap.questions.Question;

public class MultipleChoiceQuestionPanel extends QuestionPanel {
	private static final long serialVersionUID = 1L;
	
	private List<JCheckBox> mCheckboxes = new LinkedList<JCheckBox>();

	public MultipleChoiceQuestionPanel(Question q) {
		super(q);

		for(AnswerBlock answerBlock : getQuestion().getAnswerBlockList()) { 
			for(Answer answer : answerBlock.getAnswerList()) {
				JCheckBox cb = new JCheckBox(answer.getTextValue());
				mCheckboxes.add(cb);
				add(cb);
			}
		}
	}

	@Override
	public float processSubmit() {
		// TODO 
		return 0;
	}

	@Override
	public void processCancel() {
		for(JCheckBox cb : mCheckboxes) {
			cb.setSelected(false);
		}
	}

}
