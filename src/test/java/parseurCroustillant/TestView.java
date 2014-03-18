package parseurCroustillant;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tsaap.questions.QuestionType;
import org.tsaap.questions.impl.DefaultAnswer;
import org.tsaap.questions.impl.DefaultAnswerBlock;
import org.tsaap.questions.impl.DefaultQuestion;
import org.tsaap.questions.impl.DefaultQuiz;

import parseurcroustillant.Run;
import parseurcroustillant.view.QuizzWindow;

public class TestView {

	@Test
	public void testWindow() {
		Run.runDefaultView();
	}

}
