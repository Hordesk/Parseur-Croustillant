package parseurcroustillant;

import org.tsaap.questions.QuestionType;
import org.tsaap.questions.impl.DefaultAnswer;
import org.tsaap.questions.impl.DefaultAnswerBlock;
import org.tsaap.questions.impl.DefaultQuestion;
import org.tsaap.questions.impl.DefaultQuiz;

import parseurcroustillant.view.View;

public final class Run {

	private Run() {
		
	}
	
	public static void runDefaultView() {
		DefaultQuiz quiz = new DefaultQuiz();
	
		DefaultQuestion question = new DefaultQuestion();
		question.setTitle("Question 1 : Quel est la couleur du cheval blanc d'Henry IV ?");
		question.setQuestionType(QuestionType.EXCLUSIVE_CHOICE);
		quiz.addQuestion(question);
		quiz.addQuestion(question);
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
		
		View view = new View(quiz);
		view.setVisible(true);
	}
	
	public static void main(String[] args) {
		Run.runDefaultView();
	}
	

}
