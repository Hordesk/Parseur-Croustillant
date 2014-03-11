package parseurCroustillant;

import org.tsaap.questions.Quiz;

public class ParserWikiverisity implements Parser {
	private String _input = "";
	private Quiz _output;

	public void setInput(String input) {
		_input = input;
	}

	public Quiz getQuiz() {
		return _output;
	}

	public Quiz parse() throws NoInputException, WrongSyntaxException {
		if (_input.isEmpty()) {
			throw new NoInputException();
		}
		
		checkSyntax();
		
		
		
		return null;
	}
	
	private void checkSyntax() throws WrongSyntaxException {
		if (_input.charAt(0) != '{'){
			throw new WrongSyntaxException("The input should start with a openning bracket.");
		}
		
		boolean encountered = false;
		char lastChar = '.';
		for(int i=0; i<_input.length(); ++i) {
			if(_input.charAt(i) == '\n') {
				if(lastChar == '}') {
					encountered = true;
				}
			}
			lastChar = _input.charAt(i);
		}
		
		if(!encountered) {
			throw new WrongSyntaxException("The question line should end with a closing bracket.");
		}
	}

}
