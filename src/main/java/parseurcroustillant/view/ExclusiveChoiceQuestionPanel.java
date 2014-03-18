package parseurcroustillant.view;

import java.util.List;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import org.tsaap.questions.Answer;
import org.tsaap.questions.AnswerBlock;
import org.tsaap.questions.Question;

public class ExclusiveChoiceQuestionPanel extends QuestionPanel {
	private static final long serialVersionUID = 1L;
	
	List<ButtonGroup> mButtonGroups = new LinkedList<ButtonGroup>();

	public ExclusiveChoiceQuestionPanel(Question q) {
		super(q);

		for(AnswerBlock answerBlock : mQuestion.getAnswerBlockList()) {
			ButtonGroup group = new ButtonGroup();
			mButtonGroups.add(group);
			for(Answer answer : answerBlock.getAnswerList()) {
				JRadioButton radio = new JRadioButton(answer.getTextValue());
				group.add(radio);
				add(radio);
			}
		}
	}

	@Override
	public int processSubmit() {
		for(ButtonGroup g : mButtonGroups) {
			//g.getElements()
		}
		
		return 0;
	}

	@Override
	public void processCancel() {
		for(ButtonGroup g : mButtonGroups) {
			g.clearSelection();
		}
	}

}
