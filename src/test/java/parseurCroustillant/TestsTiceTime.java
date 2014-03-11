package parseurCroustillant;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tsaap.questions.Quiz;
import org.tsaap.questions.impl.DefaultAnswer;
import org.tsaap.questions.impl.DefaultAnswerBlock;
import org.tsaap.questions.impl.DefaultQuestion;
import org.tsaap.questions.impl.DefaultQuiz;

public class TestsTiceTime {

	@Test
	public void testInterface() {
		DefaultQuiz quiz = new DefaultQuiz();
		
		DefaultQuestion question = new DefaultQuestion();
		question.setTitle("Question 1 : Quel est la couleur du cheval blanc d'Henry 4 ?");
		quiz.addQuestion(question);
		
		DefaultAnswerBlock answerBlock = new DefaultAnswerBlock();
		question.addAnswerBlock(answerBlock);

		DefaultAnswer answerRouge = new DefaultAnswer();
		answerRouge.setTextValue("Rouge");
		answerRouge.setPercentCredit(0.0f);
		answerRouge.setIdentifier("rouge");
		answerRouge.setFeedback("Ce n'est pas la bonne réponse.");
		answerBlock.addAnswer(answerRouge);
		
		DefaultAnswer answerBlanc = new DefaultAnswer();
		answerBlanc.setTextValue("Blanc");
		answerBlanc.setPercentCredit(1.0f);
		answerBlanc.setIdentifier("blanc");
		answerBlanc.setFeedback("Félicitation !");
		answerBlock.addAnswer(answerBlanc);
		
		assertEquals(1, quiz.getQuestionList().size());
		assertEquals(question, quiz.getQuestionList().get(0));
		
		// If it goes down to the answers, we assume that everything is put together
		assertEquals(2, quiz.getQuestionList().get(0).getAnswerBlockList().get(0).getAnswerList().size());
	}

}
