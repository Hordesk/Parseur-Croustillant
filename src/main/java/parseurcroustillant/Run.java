package parseurcroustillant;

import org.tsaap.questions.QuestionType;
import org.tsaap.questions.impl.DefaultAnswer;
import org.tsaap.questions.impl.DefaultAnswerBlock;
import org.tsaap.questions.impl.DefaultQuestion;
import org.tsaap.questions.impl.DefaultQuiz;

import parseurcroustillant.view.QuizzWindow;

public final class Run {

	private Run() {

	}

	public static void runParsedView() {
		Parser p = new ParserWikiverisity();

		p.setInput("{Question 0 : La Suisse est membre de l'Union Européenne.\n" +
				"|type=\"()\"}\n" +
				"- Vrai.\n" +
				"+ Faux.\n" +
				"\n" +
				"{Question 1 : Quelle est la couleur du cheval blanc d'Henry 4 ?\n" +
				"|type=\"()\"}\n" +
				"- Rouge.\n" +
				"+ Blanc.\n" +
				"\n" +
				"{Question 2 : Lesquels de ces langages sont orientés objet ?\n" +
				"|type=\"[]\"}\n" +
				"+ Java.\n" +
				"- HTML.\n" +
				"+ C++.\n" +
				"\n" +
				"{Question 3 : La syntaxe est elle issue d'un groupe de travail d'Oracle ?\n" +
				"|type=\"()\"}\n" +
				"- Oui.\n" +
				"+ Non.\n");

		QuizzWindow view;
		try {
			view = new QuizzWindow(p.parse());
			view.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void runDefaultView() {
		DefaultQuiz quiz = new DefaultQuiz();

		///

		DefaultQuestion questionExcl = new DefaultQuestion();
		questionExcl.setTitle("Question 1 : Quel est la couleur du cheval blanc d'Henry IV ?");
		questionExcl.setQuestionType(QuestionType.EXCLUSIVE_CHOICE);
		quiz.addQuestion(questionExcl);

		DefaultAnswerBlock answerBlockExcl = new DefaultAnswerBlock();
		questionExcl.addAnswerBlock(answerBlockExcl);

		DefaultAnswer answerRouge = new DefaultAnswer();
		answerRouge.setTextValue("Rouge");
		answerRouge.setPercentCredit(0.0f);
		answerRouge.setIdentifier("rouge");
		answerRouge.setFeedback("Ce n'est pas la bonne réponse.");
		answerBlockExcl.addAnswer(answerRouge);

		DefaultAnswer answerBlanc = new DefaultAnswer();
		answerBlanc.setTextValue("Blanc");
		answerBlanc.setPercentCredit(1.0f);
		answerBlanc.setIdentifier("blanc");
		answerBlanc.setFeedback("Félicitation !");
		answerBlockExcl.addAnswer(answerBlanc);

		///

		DefaultQuestion questionMult = new DefaultQuestion();
		questionMult.setTitle("Question 2 : Lesquels de ces langages sont orient�s objet ?");
		questionMult.setQuestionType(QuestionType.MULTIPLE_CHOICE);
		quiz.addQuestion(questionMult);

		DefaultAnswerBlock answerBlockMult = new DefaultAnswerBlock();
		questionMult.addAnswerBlock(answerBlockMult);

		DefaultAnswer answerJava = new DefaultAnswer();
		answerJava.setTextValue("Java");
		answerJava.setPercentCredit(0.5f);
		answerJava.setIdentifier("java");
		answerJava.setFeedback("Good.");
		answerBlockMult.addAnswer(answerJava);

		DefaultAnswer answerHtml = new DefaultAnswer();
		answerHtml.setTextValue("HTML");
		answerHtml.setPercentCredit(0.0f);
		answerHtml.setIdentifier("html");
		answerHtml.setFeedback("Bad.");
		answerBlockMult.addAnswer(answerHtml);

		DefaultAnswer answerCpp = new DefaultAnswer();
		answerCpp.setTextValue("C++");
		answerCpp.setPercentCredit(0.5f);
		answerCpp.setIdentifier("cpp");
		answerCpp.setFeedback("Nice.");
		answerBlockMult.addAnswer(answerCpp);

		///

		DefaultQuestion questionTF = new DefaultQuestion();
		questionTF.setTitle("Question 3 : La syntaxe est elle issue d'un groupe de travail d'Oracle ?");
		questionTF.setQuestionType(QuestionType.TRUE_FALSE);
		quiz.addQuestion(questionTF);

		DefaultAnswerBlock answerBlockTF = new DefaultAnswerBlock();
		questionTF.addAnswerBlock(answerBlockTF);

		DefaultAnswer answerTrue = new DefaultAnswer();
		answerTrue.setTextValue("Vrai");
		answerTrue.setPercentCredit(0.0f);
		answerTrue.setIdentifier("true");
		answerTrue.setFeedback("Bad.");
		answerBlockTF.addAnswer(answerTrue);

		DefaultAnswer answerFalse = new DefaultAnswer();
		answerFalse.setTextValue("Faux");
		answerFalse.setPercentCredit(1.0f);
		answerFalse.setIdentifier("false");
		answerFalse.setFeedback("Good.");
		answerBlockTF.addAnswer(answerFalse);

		///

		DefaultQuestion questionFITB = new DefaultQuestion();
		questionFITB.setTitle("Question 4 : Quel est le nom de l'enseignant encadrant ?");
		questionFITB.setQuestionType(QuestionType.FILL_IN_THE_BLANK);
		quiz.addQuestion(questionFITB);

		DefaultAnswerBlock answerBlockFITB = new DefaultAnswerBlock();
		questionFITB.addAnswerBlock(answerBlockFITB);

		DefaultAnswer answer = new DefaultAnswer();
		answer.setTextValue("Franck Sylvestre");
		answer.setPercentCredit(1.0f);
		answer.setIdentifier("fitb");
		answer.setFeedback("Good.");
		answerBlockFITB.addAnswer(answer);

		///

		DefaultQuestion questionTODO = new DefaultQuestion();
		questionTODO.setTitle("Question 5 : TODO");
		questionTODO.setQuestionType(QuestionType.UNDEFINED);
		quiz.addQuestion(questionTODO);

		QuizzWindow view = new QuizzWindow(quiz);
		view.setVisible(true);
	}

	public static void main(String[] args) {
		//Run.runDefaultView();
		Run.runParsedView();
	}


}
