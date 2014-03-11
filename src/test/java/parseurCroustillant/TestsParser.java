package parseurCroustillant;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.tsaap.questions.Quiz;

public class TestsParser {
	
	private Parser p;
	
	@Before
	public void setUp() {
		p = new ParserWikiverisity();
	}

	@Test
	public void testInterface() {
		//Base use of the parser
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
			fail ("Should not launch any exception.");
		}
		
		// Returns the generated quiz, or calls parse if no quiz exists
		Quiz sameOutput = p.getQuiz();
		assertEquals(output, sameOutput);
	}

	@Test
	public void testSetInput() {
		//Just a setter, nothing to test
	}

	@Test
	public void testGetQuiz() {
		//Just a getter, nothing to test
	}

	@Test
	public void testParseWhithoutInput() {
		try {
			p.parse();
			fail("Calling parse without an input should launch a NoInputException.");
		}
		catch(NoInputException e) {
			//It's what we want, end
		} catch (WrongSyntaxException e) {
			fail ("Should not launch this exception.");
		}
	}
	
	@Test
	public void testParseWhithoutBeginningBracket() {
		String input =  "La Suisse est membre de l'Union Européenne.\n" +
		 		"|type=\"()\"}\n" +
		 		" - Vrai\n" +
		 		" + Faux.";
		p.setInput(input);
		try {
			p.parse();
			fail("Calling parse without a beginning bracket should launch a WrongSyntaxException.");
		}
		catch(WrongSyntaxException e) {
			//It's what we want, end
		} catch (NoInputException e) {
			fail ("Should not launch this exception.");
		}
		
	}

	@Test
	public void testParseWhithoutEndingBracket() {
		String input =  "{La Suisse est membre de l'Union Européenne.\n" +
		 		"|type=\"()\"\n" +
		 		" - Vrai\n" +
		 		" + Faux.";
		p.setInput(input);
		try {
			p.parse();
			fail("Calling parse without a ending bracket should launch a WrongSyntaxException.");
		}
		catch(WrongSyntaxException e) {
			//It's what we want, end
		} catch (NoInputException e) {
			fail ("Should not launch this exception.");
		}
		
	}
}
