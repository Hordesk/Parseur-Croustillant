package parseurCroustillant;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.tsaap.questions.QuestionType;
import org.tsaap.questions.Quiz;
import parseurcroustillant.NoInputException;
import parseurcroustillant.Parser;
import parseurcroustillant.ParserWikiverisity;
import parseurcroustillant.WrongSyntaxException;

public class TestsParser {

	private Parser p;

	@Before
	public void setUp() {
		p = new ParserWikiverisity();
	}

	@Test
	public void testWrongSyntaxException() {
		String msg = "This is a default message";
		try {
			throw new WrongSyntaxException(msg);
		}
		catch (WrongSyntaxException e){
			assertEquals(e.getMessage(), msg);
		}
	}

	@Test
	public void testInterface() {
		//Base use of the parser
		String input =  "{La Suisse est membre de l'Union Européenne.\n" +
				"|type=\"()\"}\n" +
				"- Vrai\n" +
				"+ Faux.";
		p.setInput(input);

		// Generate the Quiz and returns it
		Quiz output = null;
		try {
			output = p.parse();
		} catch (NoInputException e) {
			fail("Should not catch input exception.");
		} catch (WrongSyntaxException e ) {
			fail("Should not catch syntax exception.");
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

	/*
	 * Syntaxe WIKIVERSITY :
	 * 
	 * { $_QUESTION
	 * $_MULTILIGNE \n
	 * | type = "$ () | [] $" } \n
	 * $ 1 to n $  $ - | + $  $REPONSE
	 */

	@Test
	public void testParseWhithoutBeginningBracket() {
		String input =  "La Suisse est membre de l'Union Européenne.\n" +
				"|type=\"()\"}\n" +
				"- Vrai\n" +
				"+ Faux.";
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
				"|type=\"[]\"\n" +
				"- Vrai\n" +
				"+ Faux.";
		p.setInput(input);
		try {
			p.parse();
			fail("Calling parse without a ending bracket should launch a WrongSyntaxException.");
		}
		catch(WrongSyntaxException e) {
			//It's what we want, end
		} catch (NoInputException e) {
			fail("Should not launch this exception.");
		}
	}
	
	@Test
	public void testMissingQuestionType() {
		String inputWithoutType = "{La Suisse est membre de l'Union Européenne.\n" +
				"|" + /* "type=\"()\"" +  */"}\n" +
				"- Vrai\n" +
				"+ Faux.";
		p.setInput(inputWithoutType);
		try {
			p.parse();
			fail("Calling parse without a type definition should launch a WrongSyntaxException.");
		}
		catch(WrongSyntaxException e) {
			//It's what we want, end
		} catch (NoInputException e) {
			fail("Should not launch this exception.");
		}
		
		String inputWithoutPipe = "{La Suisse est membre de l'Union Européenne.\n" +
				/*"|" + */ "type=\"()\"}\n" +
				" - Vrai\n" +
				" + Faux.";
		p.setInput(inputWithoutPipe);
		try {
			p.parse();
			fail("Calling parse without pipe before the type definition should launch a WrongSyntaxException.");
		}
		catch(WrongSyntaxException e) {
			//It's what we want, end
		} catch (NoInputException e) {
			fail("Should not launch this exception.");
		}
	}
	
	@Test
	public void testWrongQuestionType() {
		String input = "{La Suisse est membre de l'Union Européenne.\n" +
				"|type=\"<>\"}\n" +
				"- Vrai\n" +
				"+ Faux.";
		p.setInput(input);
		try {
			p.parse();
			fail("Calling parse without a wrong type definition should launch a WrongSyntaxException.");
		}
		catch(WrongSyntaxException e) {
			//It's what we want, end
		} catch (NoInputException e) {
			fail("Should not launch this exception.");
		}
	}
	
	@Test
	public void testQuestionText() {
		String input = "{" +
				"|type=\"()\"}\n" +
				"- Vrai\n" +
				"+ Faux.";
		p.setInput(input);
		try {
			p.parse();
			fail("Calling parse without a question text should launch a WrongSyntaxException.");
		}
		catch(WrongSyntaxException e) {
			//It's what we want, end
		} catch (NoInputException e) {
			fail("Should not launch this exception.");
		}
	}
	
	@Test
	public void testAnswersSyntax() {
		String input = "{La Suisse est membre de l'Union Européenne.\n" +
				"|type=\"[]\"}\n" +
				"Vrai\n" +
				"Faux.";
		p.setInput(input);
		try {
			p.parse();
			fail("Wrong answer syntax. Should launch WrongSyntaxExcpetion.");
		}
		catch(WrongSyntaxException e) {
			//It's what we want, end
		} catch (NoInputException e) {
			fail("Should not launch this exception.");
		}
		
		input = "{La Suisse est membre de l'Union Européenne.\n" +
				"|type=\"[]\"}\n" +
				"- Vrai\n" +
				"+ Faux.\n\n\n";
		p.setInput(input);
		try {
			p.parse();
		}
		catch(WrongSyntaxException e) {
			fail("Should not launch syntax exception.");
		} catch (NoInputException e) {
			fail("Should not launch input exception.");
		}
	}
	
	@Test
	public void testAnswersNotBeginWithWhitespace() {
		String input = "{La Suisse est membre de l'Union Européenne.\n" +
				"|type=\"()\"}\n" +
				" - Vrai\n" +
				" + Faux.";
		p.setInput(input);
		try {
			p.parse();
			fail("Calling parse withanswers beginning with a whitespace should launch a WrongSyntaxException.");
		}
		catch(WrongSyntaxException e) {
			//It's what we want, end
		} catch (NoInputException e) {
			fail("Should not launch this exception.");
		}
	}
	
	@Test
	public void testAtLeastAGoodAnswer() {
		String input = "{La Suisse est membre de l'Union Européenne.\n" +
				"|type=\"()\"}\n" +
				"- Vrai\n" +
				"- Faux.";
		p.setInput(input);
		try {
			p.parse();
			fail("Calling parse without a correct answer should launch a WrongSyntaxException.");
		}
		catch(WrongSyntaxException e) {
			//It's what we want, end
		} catch (NoInputException e) {
			fail("Should not launch this exception.");
		}
	}
	
	@Test
	public void testNoTextAnswer() {
		String input = "{La Suisse est membre de l'Union Européenne.\n" +
				"|type=\"()\"}\n" +
				"- \n" +
				"+ ";
		p.setInput(input);
		try {
			p.parse();
			fail("Calling parse withanswer(s) without text should launch a WrongSyntaxException.");
		}
		catch(WrongSyntaxException e) {
			//It's what we want, end
		} catch (NoInputException e) {
			fail("Should not launch this exception.");
		}
	}
	
	@Test
	public void testOrder() {
		String input = "{La Suisse est membre de l'Union Européenne.\n" +
				"}\n" +
				"|type=\"()\"" +
				"- Vrai\n" +
				"- Faux.";
		p.setInput(input);
		try {
			p.parse();
			fail("Calling parse with the type definition after second brace should launch a WrongSyntaxException.");
		}
		catch(WrongSyntaxException e) {
			//It's what we want, end
		} catch (NoInputException e) {
			fail("Should not launch this exception.");
		}
		
		input = "{La Suisse est membre de l'Union Européenne.\n" +
				"}\n" +
				"- Vrai\n" +
				"+ Faux.\n" +
				"|type=\"()\"";
		p.setInput(input);
		try {
			p.parse();
			fail("Calling parse with the type definition after the answers should launch a WrongSyntaxException.");
		}
		catch(WrongSyntaxException e) {
			//It's what we want, end
		} catch (NoInputException e) {
			fail("Should not launch this exception.");
		}
		
		input = "{La Suisse est membre |type=\"()\" de l'Union Européenne.\n" +
				"}\n" +
				"- Vrai\n" +
				"+ Faux.";
		p.setInput(input);
		try {
			p.parse();
			fail("Calling parse with the type definition in the question text should launch a WrongSyntaxException.");
		}
		catch(WrongSyntaxException e) {
			//It's what we want, end
		} catch (NoInputException e) {
			fail("Should not launch this exception.");
		}
	}
	
	@Test
	public void testOutputTitle() {
		//Base use of the parser
		String input =  "{La Suisse est membre de l'Union Européenne.\n" +
				"|type=\"[]\"}\n" +
				"- Vrai\n" +
				"+ Faux.";
		p.setInput(input);
		
		Quiz output = null;
		try {
			output = p.parse();
			
			assertEquals(output.getQuestionList().get(0).getTitle(), "La Suisse est membre de l'Union Européenne.");
		} catch (NoInputException e) {
			fail("Should not catch input exception.");
		} catch (WrongSyntaxException e ) {
			fail("Should not catch syntax exception.");
		}
	}
	
	@Test
	public void testOutputTypeSingleChoice() {
		//Base use of the parser
		String input =  "{La Suisse est membre de l'Union Européenne.\n" +
				"|type=\"()\"}\n" +
				"- Vrai\n" +
				"+ Faux.";
		p.setInput(input);
		
		Quiz output = null;
		try {
			output = p.parse();
			
			assertEquals(output.getQuestionList().get(0).getQuestionType(), QuestionType.EXCLUSIVE_CHOICE);
		} catch (NoInputException e) {
			fail("Should not catch input exception.");
		} catch (WrongSyntaxException e ) {
			fail("Should not catch syntax exception.");
		}
	}
	
	@Test
	public void testOutputTypeMultipleChoice() {
		//Base use of the parser
		String input =  "{La Suisse est membre de l'Union Européenne.\n" +
				"|type=\"[]\"}\n" +
				"- Vrai\n" +
				"+ Faux.";
		p.setInput(input);
		
		Quiz output = null;
		try {
			output = p.parse();
			
			assertEquals(output.getQuestionList().get(0).getQuestionType(), QuestionType.MULTIPLE_CHOICE);
		} catch (NoInputException e) {
			fail("Should not catch input exception.");
		} catch (WrongSyntaxException e ) {
			fail("Should not catch syntax exception.");
		}
	}
	
	@Test
	public void testOutputAnswers() {
		//Base use of the parser
		String input =  "{La Suisse est membre de l'Union Européenne.\n" +
				"|type=\"()\"}\n" +
				"- Vrai\n" +
				"+ Faux";
		p.setInput(input);
		
		Quiz output = null;
		try {
			output = p.parse();

			assertEquals(output.getQuestionList().get(0).getAnswerBlockList().get(0).getAnswerList().size(), 2);
			assertEquals(output.getQuestionList().get(0).getAnswerBlockList().get(0).getAnswerList().get(0).getTextValue(), "Vrai");
			assertEquals(output.getQuestionList().get(0).getAnswerBlockList().get(0).getAnswerList().get(0).getPercentCredit(), 0.0f, .01f);
			assertEquals(output.getQuestionList().get(0).getAnswerBlockList().get(0).getAnswerList().get(1).getTextValue(), "Faux");
			assertEquals(output.getQuestionList().get(0).getAnswerBlockList().get(0).getAnswerList().get(1).getPercentCredit(), 1.0f, .01f);
		} catch (NoInputException e) {
			fail("Should not catch input exception.");
		} catch (WrongSyntaxException e ) {
			fail("Should not catch syntax exception.");
		}
	}
	
	@Test
	public void testOutputAnswersPoints() {
		//Base use of the parser
		String input =  "{Choix multiple.\n" +
				"|type=\"[]\"}\n" +
				"- 1\n" +
				"+ 2\n" +
				"+ 3\n" +
				"+ 4";
		p.setInput(input);

		Quiz output = null;
		try {
			output = p.parse();

			assertEquals(output.getQuestionList().get(0).getAnswerBlockList().get(0).getAnswerList().get(1).getPercentCredit(), 1.0f / 3.0f, .01f);
			assertEquals(output.getQuestionList().get(0).getAnswerBlockList().get(0).getAnswerList().get(3).getPercentCredit(), 1.0f / 3.0f, .01f);
			assertEquals(output.getQuestionList().get(0).getAnswerBlockList().get(0).getAnswerList().get(3).getPercentCredit(), 1.0f / 3.0f, .01f);
		} catch (NoInputException e) {
			fail("Should not catch input exception.");
		} catch (WrongSyntaxException e ) {
			fail("Should not catch syntax exception.");
		}
	}
	
	@Test
	public void testOutputMultiQuestions() {
		//Base use of the parser
		String input =  "{Choix multiple.\n" +
				"|type=\"[]\"}\n" +
				"- 1\n" +
				"+ 2\n" +
				"+ 3\n" +
				"+ 4\n" +
				"\n" +
				"{Choix multiple.\n" +
				"|type=\"[]\"}\n" +
				"- 1\n" +
				"+ 2\n" +
				"+ 3\n" +
				"+ 4";
		p.setInput(input);

		Quiz output = null;
		try {
			output = p.parse();

			assertEquals(output.getQuestionList().size(), 2);
		} catch (NoInputException e) {
			fail("Should not catch input exception.");
		} catch (WrongSyntaxException e ) {
			fail("Should not catch syntax exception.");
		}
	}
	
	@Test
	public void testOutputMultiQuestionsNtEnoughtEmptyLinesBetweenEachQuestion() {
		//Base use of the parser
		String input =  "{Choix multiple.\n" +
				"|type=\"[]\"}\n" +
				"- 1\n" +
				"+ 2\n" +
				"+ 3\n" +
				"+ 4\n" +
				//"\n" +
				"{Choix multiple.\n" +
				"|type=\"[]\"}\n" +
				"- 1\n" +
				"+ 2\n" +
				"+ 3\n" +
				"+ 4";
		p.setInput(input);

		Quiz output = null;
		try {
			output = p.parse();

			assertNotEquals(output.getQuestionList().size(), 2);
		} catch (NoInputException e) {
			fail("Should not catch input exception.");
		} catch (WrongSyntaxException e ) {
			fail("Should not catch syntax exception.");
		}
	}
	
	@Test
	public void testOutputMultiQuestionsExactlyAnEmptyLineBetweenEachQuestion() {
		//Base use of the parser
		String input =  "{Choix multiple.\n" +
				"|type=\"[]\"}\n" +
				"- 1\n" +
				"+ 2\n" +
				"+ 3\n" +
				"+ 4\n" +
				"\n" +
				"{Choix multiple.\n" +
				"|type=\"[]\"}\n" +
				"- 1\n" +
				"+ 2\n" +
				"+ 3\n" +
				"+ 4";
		p.setInput(input);

		Quiz output = null;
		try {
			output = p.parse();

			assertEquals(output.getQuestionList().size(), 2);
		} catch (NoInputException e) {
			fail("Should not catch input exception.");
		} catch (WrongSyntaxException e ) {
			fail("Should not catch syntax exception.");
		}
	}
	
	@Test
	public void testOutputMultiQuestionsTooMuchEmptyLinesBetweenEachQuestion() {
		//Base use of the parser
		String input =  "{Choix multiple.\n" +
				"|type=\"[]\"}\n" +
				"- 1\n" +
				"+ 2\n" +
				"+ 3\n" +
				"+ 4\n" +
				"\n" +
				"\n" +
				"\n" +
				"{Choix multiple.\n" +
				"|type=\"[]\"}\n" +
				"- 1\n" +
				"+ 2\n" +
				"+ 3\n" +
				"+ 4";
		p.setInput(input);

		Quiz output = null;
		try {
			output = p.parse();

			fail("Should launch a syntax exception.");
		} catch (NoInputException e) {
			fail("Should not catch input exception.");
		} catch (WrongSyntaxException e ) {
			// Should have a syntax exception, because a question starts with a new line
		}
	}
}
