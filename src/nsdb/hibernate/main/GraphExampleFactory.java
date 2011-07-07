package nsdb.hibernate.main;

import java.util.Set;

import nsdb.NSDBSettings;
import nsdb.hibernate.entity.Edge;
import nsdb.hibernate.entity.Graph;
import nsdb.hibernate.entity.Node;
import nsdb.hibernate.entity.Owner;
import nsdb.hibernate.entity.Type;
import nsdb.hibernate.entity.Xref;
import nsdb.hibernate.util.HibernateUtil;
import nsdb.test.GraphExamples;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

/** 
 * Create example Graphs for networkSimDB.
 * Examples are defined in GraphExamples.
 * @author Matthias Koenig & Michael Weidlich
 *
 */
public class GraphExampleFactory{

  private SessionFactory sessionFactory;
  private Graph g = null; 
  
  
  public GraphExampleFactory(){
    try {
    	
      //TODO: THIS IS BAD - session factory should only once defined for complete code
      sessionFactory = HibernateUtil.getSessionFactory(); 
    } catch( HibernateException ex ) {
      ex.printStackTrace();
      System.exit( 5 );   
    }
  }

  /** Create the example graphs in the database.
   * For generation of single examples use generateExampleGraph() */
  public static void generateExampleGraphs(){
    // Generate the example graphs
    Graph g = null;
    
    // Example 1
    	// linear chain of four nodes
        String[][] example1 = GraphExamples.example_01;
    	g = generateExampleGraph(example1);
    	g.printGraph();
    // Example 2
        // circle of four nodes
        String[][] example2 = GraphExamples.example_02;
    	g = generateExampleGraph(example2);
    	g.printGraph();
    // Example 3
        // fully connected three nodes (undirected edges)
        String[][] example3 = GraphExamples.example_03;
    	g = generateExampleGraph(example3);
    	g.printGraph();
  }
  
  /**
   * Generates and returns a single example Graph.
   * String Arrary of Graph, Node and Edge information necessary.
   * For examples see the GraphExamples.java
   * @param example
   * @return generated Graph
   */
  public static Graph generateExampleGraph(String[][] example){
	  GraphExampleFactory fac = new GraphExampleFactory();
	  for(String[] data : example) {
		  fac.execute(data);
	  }
	  return fac.g;
  }

 
  /** Write the Graph examples
   *  data is Graph, Node or Edge data
   *  Based on the first item and the length of the String[] the
   *  Graph, Node and Edge are separated.
   *  Graph has to be set before the nodes and edges are set.
   * @param exampleFac
   * @param data
   */
  private void execute(String[] data ){
	// Graph
    if(data.length  == 4 && data[0].equalsIgnoreCase( "Graph" ) ) {
      g = addGraph(data);
    }
    // Node 
    if(g != null && data.length  == 3 && data[0].equalsIgnoreCase( "Node" ) ) {
        addNode(data, g);
    }
    // Edge
    if(g != null && data.length  == 5 && data[0].equalsIgnoreCase( "Edge" ) ) {
        addEdge(data, g);
    }
  }

 
 /** Add new graph object from graph data 
  * { "Graph", "testgraph", "testgraph_01", "testuser" },
  * @param data
  */
  private Graph addGraph(String[] data){
	//System.out.println("addGraph()");
    Graph g = null;
	Session     sess = null;
    Transaction trx  = null;
    
    //String table_data = data[0];
    String type_data = data[1];
    String xref_data = data[2];
    String owner_data = data[3];
    
    try { 	
      sess = sessionFactory.openSession();
      trx  = sess.beginTransaction();
     
      Type type = new Type();
      type.setName(type_data);
      sess.saveOrUpdate(type);
      
      
      Xref xref = new Xref();
      xref.setName(xref_data);
      sess.saveOrUpdate(xref);
      
      
      Owner owner = new Owner();
      owner.setName(owner_data);
      sess.saveOrUpdate(owner);
      
      g = new Graph();
      g.setType(type);
      g.setXref(xref);
      g.setOwner(owner);
      

      // ?? enough to save graph ? what about user, xref, type ?
      sess.save(g);
      trx.commit();
      
    } catch( HibernateException ex ) {
      if( trx != null )
        try { trx.rollback(); } catch( HibernateException exRb ) {}
      
      throw new RuntimeException( ex.getMessage() );
    } finally {
      try { if( sess != null ) sess.close(); } catch( Exception exCl ) {}
    }
    
    return g;

  }

 
  /** Add new node object from node data 
   * { "Node", "node", "n1"}
   * @param data
   */
  private void addNode(String[] data, Graph g){
	//System.out.println("addNode()");
    Session     sess = null;
    Transaction trx  = null;
    
    //String table_data = data[0];
    String type_data = data[1];
    String xref_data = data[2];
    
    try {

      sess = sessionFactory.openSession();
      trx  = sess.beginTransaction();
      /*
      try {
		Class c = Class.forName("entity.Type");
		c.getConstructor(null).newInstance(arg0)
		System.out.println(obj.getClass().toString());
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	*/
      /*
      Type type =(Type) getObjectByAttributeSet("Type", String[] columnnames, Objects[] values);
      if (type == null){
    	  type = new Type();
      }
      */
      
      Type type = new Type();
      type.setName(type_data);
      sess.saveOrUpdate(type);
      
      Xref xref = new Xref();
      xref.setName(xref_data);
      sess.saveOrUpdate(xref);
      
      Node n = new Node();
      n.setType(type);
      n.setXref(xref);
      //nodes always new
      sess.save(n);
      
      // Add node to graph
      g.getNodes().add(n);
      //Not necessary, save is handled by transaction
      //sess.save(g);
      sess.update(g);
      
      trx.commit();

    } catch( HibernateException ex ) {

      if( trx != null )

        try { trx.rollback(); } catch( HibernateException exRb ) {}

      throw new RuntimeException( ex.getMessage() );

    } finally {

      try { if( sess != null ) sess.close(); } catch( Exception exCl ) {}

    }
  }

  
  /** Add new node object from node data 
   * { "Edge", "edge", "e1", "n1", "n2"},
   * @param data
   */
private void addEdge(String[] data, Graph g){
	//System.out.println("addEdge()");
    Session     sess = null;
    Transaction trx  = null;
    
//    String table_data = data[0];
    String type_data = data[1];
    String xref_data = data[2];
    String source_data = data[3];
    String target_data = data[4];

    try {

      sess = sessionFactory.openSession();
      trx  = sess.beginTransaction();

      //TODO: get via attributes the existing object
      Type type = new Type();
      type.setName(type_data);
      sess.saveOrUpdate(type);
      
      Xref xref = new Xref();
      xref.setName(xref_data);
      sess.saveOrUpdate(xref);
     
      Edge e = new Edge();
      e.setType(type);
      e.setXref(xref);
      
      // Get source and target
      // This is not good at all -> better way to get node by id
      Node source = null;
      Node target = null;
      String tmp_xref_name;
      Set<Node> nodes = g.getNodes();
      for (Node n : nodes){
    	  tmp_xref_name = n.getXref().getName(); 
    	  if (tmp_xref_name.equals(source_data)){
    		  source = n;
    	  }
    	  if (tmp_xref_name.equals(target_data)){
    		  target = n;
    	  }
      }
      
      // Get source and target better version (Query ????)
      e.setSource(source);
      e.setTarget(target);
      
      sess.save(e);
      
      // Add edge to graph
      g.getEdges().add(e);
      sess.update(g);
      trx.commit();

    } catch( HibernateException ex ) {

      if( trx != null )

        try { trx.rollback(); } catch( HibernateException exRb ) {}

      throw new RuntimeException( ex.getMessage() );

    } finally {

      try { if( sess != null ) sess.close(); } catch( Exception exCl ) {}
    }
  }
}


