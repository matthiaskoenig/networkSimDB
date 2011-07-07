package nsdb.test;

import nsdb.hibernate.entity.Graph;
import nsdb.hibernate.main.GraphExampleFactory;

public class GraphGetTest {
	
	public static void main(String[] args){
		//Create all examples
		//GraphExampleFactory.generatEeExampleGraphs();
		
		//Create single examples
		Graph g = GraphExampleFactory.generateExampleGraph(GraphExamples.example_01);
		g.printGraph();
		g = GraphExampleFactory.generateExampleGraph(GraphExamples.example_02);
		g.printGraph();
		g = GraphExampleFactory.generateExampleGraph(GraphExamples.example_03);
		g.printGraph();
		
	}
}
