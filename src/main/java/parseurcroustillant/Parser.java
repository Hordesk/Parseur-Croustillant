package parseurcroustillant;

import org.tsaap.questions.Quiz;

public interface Parser {

	/**
	 * This method sets the input file of the parser but does nothing more.
	 * @param input The content of the input (Not the URL)
	 */
	public void setInput(String input);

	/**
	 * This method parses the input file and returns the generated quiz
	 * @return The generated quiz
	 */
	public Quiz parse() throws NoInputException, WrongSyntaxException;

	/**
	 * This method returns the last generated quiz
	 * @return The last generated quiz
	 */
	public Quiz getQuiz();

}
