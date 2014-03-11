package parseurCroustillant;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tsaap.questions.Quiz;

public class TestsParser {

	@Test
	public void testInterface() {
		//Base use of the parser
		Parser p = new ParserWikiverisity();
		
		String input =  "{La Suisse est membre de l'Union Européenne.\n" +
				 		"|type=\"()\"}\n" +
				 		" - Vrai\n" +
				 		" + Faux.";
		p.setInput(input);
		
		// Generate the Quiz and returns it
		Quiz output = null;
		try {
			output = p.parse();
		} catch (Exception e) {
			fail("Should work.");
		}
		
		// Returns the generated quiz, or calls parse if no quiz exists
		Quiz sameOutput = p.getQuiz();
		assertEquals(output, sameOutput);
	}
	
	@Test
	public void testSetInput(){
		//Just a setter, nothing to test
	}

	@Test
	public void testParseWhithoutInput(){
		Parser p = new ParserWikiverisity();
		try {
			p.parse();
			fail("Calling parse without an input should launch a NoInputException.");
		}
		catch(NoInputException e) {
			//It's what we want, end
		}
	}
	
}
