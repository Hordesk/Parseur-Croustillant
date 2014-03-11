package parseurCroustillant;

import org.tsaap.questions.Quiz;

public class ParserWikiverisity implements Parser {
	private String _input = "";

	public void setInput(String input) {
		_input = input;
	}

	public Quiz parse() throws NoInputException {
		if (_input.isEmpty()) {
			throw new NoInputException();
		}
		return null;
	}

	public Quiz getQuiz() {
		return null;
	}

}
