package nsdb.test;

public class GraphExamples {
	
	/* linear chain of four nodes */
	public static String[][] example_01 = {
		      { "Graph", "testgraph", "testgraph_01", "testuser" },
		      { "Node", "node", "n1"},
		      { "Node", "node", "n2"},
		      { "Node", "node", "n3"},
		      { "Node", "node", "n4"},
		      { "Edge", "edge", "e1", "n1", "n2"},
		      { "Edge", "edge", "e2", "n2", "n3"},
		      { "Edge", "edge", "e3", "n3", "n4"},
	};
	
    // circle of four nodes
    public static String[][] example_02 = new String[][] {
    	      { "Graph", "testgraph", "testgraph_02", "testuser" },
    	      { "Node", "node", "n1"},
    	      { "Node", "node", "n2"},
    	      { "Node", "node", "n3"},
    	      { "Node", "node", "n4"},
    	      { "Edge", "edge", "e1", "n1", "n2"},
    	      { "Edge", "edge", "e2", "n2", "n3"},
    	      { "Edge", "edge", "e3", "n3", "n4"},
    	      { "Edge", "edge", "e3", "n4", "n1"},
    };
    
    // fully connected three nodes (undirected edges)
    public static String[][] example_03 = new String[][] {
  	      { "Graph", "testgraph", "testgraph_03", "testuser" },
  	      { "Node", "node", "n1"},
  	      { "Node", "node", "n2"},
  	      { "Node", "node", "n3"},
  	      { "Edge", "edge", "e1", "n1", "n2"},
  	      { "Edge", "edge", "e2", "n2", "n1"},
  	      { "Edge", "edge", "e3", "n2", "n3"},
  	      { "Edge", "edge", "e4", "n3", "n2"},
  	      { "Edge", "edge", "e5", "n3", "n1"},
  	      { "Edge", "edge", "e6", "n1", "n3"},
  };
	
	
	
}
