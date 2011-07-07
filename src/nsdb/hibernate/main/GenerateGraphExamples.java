package nsdb.hibernate.main;

import nsdb.hibernate.main.GraphExampleFactory;

/**
 * Helper class to generate example graphs in db.
 * @author mkoenig
 *
 */
public class GenerateGraphExamples {
	
	public static void main(String[] args){

		//Create all examples
		GraphExampleFactory.generateExampleGraphs();
		
		//Create single example
		//GraphExampleFactory.generateExampleGraph(GraphExamples.example_01);
		
	}
}
