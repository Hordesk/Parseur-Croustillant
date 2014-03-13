package parseurcroustillant;

import org.tsaap.questions.Quiz;

public class ParserWikiverisity implements Parser {
	private String mInput = "";
	private Quiz mOutput;

	public void setInput(String input) {
		mInput = input;
	}

	public Quiz getQuiz() {
		return mOutput;
	}

	public Quiz parse() throws NoInputException, WrongSyntaxException {
		if (mInput.isEmpty()) {
			throw new NoInputException();
		}

		checkSyntax();



		return null;
	}

	private void checkSyntax() throws WrongSyntaxException {
		//First bracket
		if (mInput.charAt(0) != '{'){
			throw new WrongSyntaxException("The input should start with a openning bracket.");
		}

		//Second bracket
		boolean encountered = false;
		char lastChar = '.';
		for(int i=0; i<mInput.length(); ++i) {
			if(mInput.charAt(i) == '\n' && lastChar == '}') {
				encountered = true;
			}
			lastChar = mInput.charAt(i);
		}		
		if(!encountered) {
			throw new WrongSyntaxException("The question line should end with a closing bracket.");
		}
	}

}
