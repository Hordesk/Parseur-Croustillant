package parseurcroustillant.view;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import org.tsaap.questions.Answer;
import org.tsaap.questions.AnswerBlock;
import org.tsaap.questions.Question;

public class ExclusiveChoiceQuestionPanel extends QuestionPanel {
	private static final long serialVersionUID = 1L;
	
	private List<ButtonGroup> mButtonGroups = new LinkedList<ButtonGroup>();
	private Map<JRadioButton, Float> mBoutons = new HashMap<JRadioButton, Float>();

	public ExclusiveChoiceQuestionPanel(Question q) {
		super(q);

		for(AnswerBlock answerBlock : getQuestion().getAnswerBlockList()) {
			ButtonGroup group = new ButtonGroup();
			mButtonGroups.add(group);
			for(Answer answer : answerBlock.getAnswerList()) {
				JRadioButton radio = new JRadioButton(answer.getTextValue());
				group.add(radio);
				mBoutons.put(radio, answer.getPercentCredit());
				add(radio);
			}
		}
	}

	@Override
	public float processSubmit() {
		float somme = 0;
		for(Entry<JRadioButton, Float> entry : mBoutons.entrySet()) {
		    if(entry.getKey().isSelected()) {
		    	somme += entry.getValue();
		    }
		}
		
		return somme;
	}

	@Override
	public void processCancel() {
		for(ButtonGroup g : mButtonGroups) {
			g.clearSelection();
		}
	}

}
