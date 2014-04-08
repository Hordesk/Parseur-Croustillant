package parseurCroustillant;

import java.util.logging.Level;
import java.util.logging.Logger;

import parseurcroustillant.Parser;
import parseurcroustillant.ParserWikiverisity;
import parseurcroustillant.view.QuizzWindow;

public final class Run {
	
	private static final Logger LOGGER = Logger.getLogger(Run.class .getName());
	
	private Run() {

	}

	public static void runParsedView() {
		Parser p = new ParserWikiverisity();

		p.setInput("{Question 0 : La Suisse est membre de l'Union Européenne.\n" +
				"|type=\"()\"}\n" +
				"- Vrai.\n" +
				"+ Faux.\n" +
				"\n" +
				"{Question 1 : Quelle est la couleur du cheval blanc d'Henry 4 ?\n" +
				"|type=\"()\"}\n" +
				"- Rouge.\n" +
				"+ Blanc.\n" +
				"\n" +
				"{Question 2 : Lesquels de ces langages sont orientés objet ?\n" +
				"|type=\"[]\"}\n" +
				"+ Java.\n" +
				"- HTML.\n" +
				"+ C++.\n" +
				"\n" +
				"{Question 3 : La syntaxe est elle issue d'un groupe de travail d'Oracle ?\n" +
				"|type=\"()\"}\n" +
				"- Oui.\n" +
				"+ Non.\n");

		QuizzWindow view;
		try {
			view = new QuizzWindow(p.parse());
			view.setVisible(true);

		} catch (Exception e) {
			LOGGER.setLevel(Level.SEVERE);
			LOGGER.severe(e.getMessage());
		}
	}


	public static void main(String[] args) {
		Run.runParsedView();
	}


}
