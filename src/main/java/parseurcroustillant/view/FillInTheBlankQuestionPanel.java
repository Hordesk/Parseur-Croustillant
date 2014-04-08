package parseurcroustillant.view;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JTextField;

import org.tsaap.questions.Answer;
import org.tsaap.questions.AnswerBlock;
import org.tsaap.questions.Question;

public class FillInTheBlankQuestionPanel extends QuestionPanel {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_LINEEDIT_LENGHT = 50;
	
	private JTextField mAnswerTextField = new JTextField(DEFAULT_LINEEDIT_LENGHT);
	private Map<JTextField, SimpleEntry<String, Float>> mTextField = new HashMap<JTextField, SimpleEntry<String, Float>>();


	public FillInTheBlankQuestionPanel(Question q) {
		super(q);
		for(AnswerBlock answerBlock : getQuestion().getAnswerBlockList()) {
			for(Answer answer : answerBlock.getAnswerList()) {	
				add(mAnswerTextField);
				mTextField.put(mAnswerTextField, new SimpleEntry<String, Float>(answer.getTextValue(), answer.getPercentCredit()));
			}
		}
	}

	@Override
	public float processSubmit() {
		float somme = 0;
		for(Entry<JTextField, SimpleEntry<String, Float>> entry : mTextField.entrySet()) {
		    if(entry.getKey().getText().equals(entry.getValue().getKey())) {
		    	somme += entry.getValue().getValue();
		    }
		}
		
		return somme;
	}

	@Override
	public void processCancel() {
		mAnswerTextField.setText("");
	}

}
