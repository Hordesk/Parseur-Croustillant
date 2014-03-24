package parseurcroustillant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.tsaap.questions.AnswerBlock;
import org.tsaap.questions.QuestionType;
import org.tsaap.questions.Quiz;
import org.tsaap.questions.impl.DefaultAnswer;
import org.tsaap.questions.impl.DefaultAnswerBlock;
import org.tsaap.questions.impl.DefaultQuestion;
import org.tsaap.questions.impl.DefaultQuiz;

public class ParserWikiverisity implements Parser {
	private static final char GOOD_ANSWER = '+';
	private static final char BAD_ANSWER = '-';
	private static final String MULTIPLE_CHOICE_SYMBOLS = "[]";
	private static final String EXCLUSIVE_CHOICE_SYMBOLS = "()";
	private static final String NEW_LINE = "\n";
	private static final String END_BRACKET = "\"}\n";
	private static final String TYPE = "\n|type=\"";

	private String mInput = "";
	private Quiz mOutput;


	public void setInput(String input) {
		mInput = input;
		while(mInput.endsWith(NEW_LINE)) {
			mInput = mInput.substring(0, mInput.length() - 2);
		}
	}

	public Quiz getQuiz() {
		return mOutput;
	}

	public Quiz parse() throws NoInputException, WrongSyntaxException {
		if (mInput.isEmpty()) {
			throw new NoInputException();
		}

		DefaultQuiz quiz = new DefaultQuiz();

		for(String questionInput : separateQuestions()) {
			checkSyntax(questionInput);
			
			DefaultQuestion question = new DefaultQuestion();
			question.setTitle(extractTitle(questionInput));
			question.setQuestionType(extractType(questionInput));
			
			question.addAnswerBlock(extractAnswerBlock(questionInput));
			
			quiz.addQuestion(question);
		}

		mOutput = quiz;

		return getQuiz();
	}

	private String[] separateQuestions() {
		return mInput.split(NEW_LINE + NEW_LINE);
	}

	private String extractTitle(String input) {
		return input.substring(1, input.indexOf(TYPE));
	}

	private QuestionType extractType(String input) {
		String type = input.substring( input.indexOf(TYPE)+ TYPE.length(), input.indexOf(END_BRACKET));

		if(type.equals(EXCLUSIVE_CHOICE_SYMBOLS)) {
			return QuestionType.EXCLUSIVE_CHOICE;
		} else if(type.equals(MULTIPLE_CHOICE_SYMBOLS)) {
			return QuestionType.MULTIPLE_CHOICE;
		} else {
			return QuestionType.UNDEFINED;
		}
	}

	private AnswerBlock extractAnswerBlock(String input) {
		DefaultAnswerBlock answerBlock = new DefaultAnswerBlock();

		String[] answers = input.substring(input.indexOf(END_BRACKET) + END_BRACKET.length()).split(NEW_LINE);

		int goodResponsesCount = 0;
		for(String answer : answers) {
			if(answer.charAt(0) == GOOD_ANSWER) {
				++goodResponsesCount;
			}
		}

		for (String answer : answers) {
			DefaultAnswer a = new DefaultAnswer();
			a.setTextValue(answer.substring(2));
			switch(answer.charAt(0)) {
			case GOOD_ANSWER:
				a.setPercentCredit(1.0f / goodResponsesCount);
				break;
			case BAD_ANSWER:
			default:
				a.setPercentCredit(0.0f);
				break;
			}
			answerBlock.addAnswer(a);
		}

		return answerBlock;
	}

	private void checkSyntax(String input) throws WrongSyntaxException {
		Matcher matcherBraces = Pattern.compile("^\\{.*\\}\n", Pattern.DOTALL).matcher(input);
		Matcher matcherQuestionType = Pattern.compile("\\|type=\"(\\(\\)|\\[\\])\"\\}").matcher(input);
		Matcher matcherQuestionText = Pattern.compile("\\{.+\n\\|", Pattern.DOTALL).matcher(input);
		String regexAnswer = "[\\+-] (\\w|\\p{Punct})+";
		Matcher matcherAnswers = Pattern.compile("\\}\n(" + regexAnswer + "\n)*" + regexAnswer, Pattern.DOTALL).matcher(input);
		if(!matcherBraces.find()) {
			throw new WrongSyntaxException("Missing a brace");
		}
		if(!matcherQuestionType.find()) {
			throw new WrongSyntaxException("Missing question type");
		}
		if(!matcherQuestionText.find()) {
			throw new WrongSyntaxException("Missing question text");
		}
		if(!matcherAnswers.find()) {
			throw new WrongSyntaxException("Wrong answer syntax");
		}

		String answers = input.substring(input.indexOf("|type"));
		if(answers.indexOf('+') < 0) {
			throw new WrongSyntaxException("Missing a good answer");
		}
	}

}
