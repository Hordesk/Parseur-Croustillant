package parseurcroustillant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.tsaap.questions.Quiz;
import org.tsaap.questions.impl.DefaultQuiz;

public class ParserWikiverisity implements Parser {
    private String mInput = "";
    private Quiz mOutput;

    public void setInput(String input) {
        mInput = input;
        while(mInput.endsWith("\n")) {
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

        checkSyntax();

        mOutput = new DefaultQuiz();
        
        return getQuiz();
    }

    private void checkSyntax() throws WrongSyntaxException {
    	Matcher matcherBraces = Pattern.compile("^\\{.*\\}\n", Pattern.DOTALL).matcher(mInput);
    	Matcher matcherQuestionType = Pattern.compile("\\|type=\"(\\(\\)|\\[\\])\"\\}").matcher(mInput);
    	Matcher matcherQuestionText = Pattern.compile("\\{.+\n\\|", Pattern.DOTALL).matcher(mInput);
    	String regexAnswer = "[\\+-] (\\w|\\p{Punct})+";
    	Matcher matcherAnswers = Pattern.compile("\\}\n(" + regexAnswer + "\n)*" + regexAnswer, Pattern.DOTALL).matcher(mInput);
    	if(!matcherBraces.find()) {
    		throw new WrongSyntaxException("Missing first brace");
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
    	
    	String answers = mInput.substring(mInput.indexOf("|type"));
    	if(answers.indexOf('+') < 0) {
    		throw new WrongSyntaxException("Missing a good answer");
    	}
    }

}
