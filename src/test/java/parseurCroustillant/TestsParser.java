package parseurCroustillant;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tsaap.questions.Quiz;

public class TestsParser {
	
	@Test
	public void testParseur() {
		//Base use of the parser
		Parser p = new Parser();
		
		p.setInput("An input");
		
		// Generate the Quiz and returns it
		Quiz output = p.parse();
		
		// Returns the generated quiz, or calls parse if no quiz exists
		Quiz sameOutput = p.getQuiz();
	}

}
