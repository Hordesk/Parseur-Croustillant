package parseurcroustillant.view;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JCheckBox;

import org.tsaap.questions.Answer;
import org.tsaap.questions.AnswerBlock;
import org.tsaap.questions.Question;

public class MultipleChoiceQuestionPanel extends QuestionPanel {
	private static final long serialVersionUID = 1L;
	
	private List<JCheckBox> mCheckboxes = new LinkedList<JCheckBox>();
	private List<HashMap<JCheckBox, Float>> mBoutons = new LinkedList<HashMap<JCheckBox, Float>>();

	public MultipleChoiceQuestionPanel(Question q) {
		super(q);

		for(AnswerBlock answerBlock : getQuestion().getAnswerBlockList()) { 
			HashMap<JCheckBox, Float> groupeDeBoutons = new HashMap<JCheckBox, Float>();
			for(Answer answer : answerBlock.getAnswerList()) {
				JCheckBox cb = new JCheckBox(answer.getTextValue());
				mCheckboxes.add(cb);
				groupeDeBoutons.put(cb, answer.getPercentCredit());

				add(cb);
			}
			mBoutons.add(groupeDeBoutons);
		}
	}

	@Override
	public float processSubmit() {
		float somme = 0;
		for (HashMap<JCheckBox, Float> map : mBoutons) {
			float ssSomme = 0;
			for(Entry<JCheckBox, Float> entry : map.entrySet()) {
				if(entry.getKey().isSelected() && entry.getValue() == 0) {
					ssSomme = 0;
					break;
				}
				else if (entry.getKey().isSelected()) {
					ssSomme += entry.getValue();
				}
			}
			somme += ssSomme;
		}
		
		return somme;
	}

	@Override
	public void processCancel() {
		for(JCheckBox cb : mCheckboxes) {
			cb.setSelected(false);
		}
	}

}
