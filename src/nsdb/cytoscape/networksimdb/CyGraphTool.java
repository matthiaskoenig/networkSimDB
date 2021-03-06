package nsdb.cytoscape.networksimdb;


import java.util.List;

import javax.swing.JOptionPane;

import cytoscape.CyEdge;
import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import cytoscape.data.Semantics;

import nsdb.hibernate.entity.Edge;
import nsdb.hibernate.entity.Graph;
import nsdb.hibernate.entity.Node;
import nsdb.hibernate.main.GraphExampleFactory;
import nsdb.hibernate.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.hql.ast.util.SessionFactoryHelper;

import sun.security.action.GetLongAction;

/**
 * Class for the interaction between Cytoscape and NetworkSimDB on graph 
 * level.
 * Functions for loading networks from the db into Cytoscape and for 
 * storing Cytoscape networks in the database.
 * 
 * Test case for Hibernate object relational mapping.
 * 
 * 
 * @author Matthias Koenig [24.Juni 2010]
 * 
 *
 */
public class CyGraphTool{
	
	/** Load an available graph as a Cytoscape network */
	public static void loadGraphFromDB(Long id){
		
		Session sess = HibernateUtil.getSessionFactory().openSession();		
		sess.beginTransaction();
		
		Graph g = (Graph) sess.get(Graph.class, id);
		
		sess.getTransaction().commit();
		System.out.println("Graph g:" + g);
		
		// Create a new cytoscape network
		String title = g.getXref().getName() + " - " + g.getOwner().getName();
		CyNetwork network = Cytoscape.createNetwork(title);
		
		// Add nodes to cytoscape network
		CyNode cyNode;
		String cyId;
		CyAttributes nodeAttributes = Cytoscape.getNodeAttributes();
		
		for (Node node : g.getNodes()){
			cyId = node.getXref().getName();
			// create cytoscape node and add to network
			cyNode = Cytoscape.getCyNode(cyId, true);
			nodeAttributes.setAttribute(cyId, "NetworkSimDB id", node.getId().toString());
			nodeAttributes.setAttribute(cyId, "NetworkSimDB type", node.getType().getName());
			if (node.getXref().getLink() == null){
				//nodeAttributes.removeAttribute(cyId, "NetworkSimDB link");
			}
			else{
				nodeAttributes.setAttribute(cyId, "NetworkSimDB link", node.getXref().getLink());
			}
			network.addNode(cyNode);
		}
		
		
		// Add edges to network
		CyEdge cyEdge;
		CyNode source = null;
		CyNode target = null;
		String source_id;
		String target_id;
		
		
		CyAttributes edgeAttributes = Cytoscape.getEdgeAttributes();
		
		for (Edge edge : g.getEdges()){
			cyId = edge.getXref().getName();
			source_id = edge.getSource().getXref().getName();
			source = Cytoscape.getCyNode(source_id, false);
			
			target_id = edge.getTarget().getXref().getName();
			target = Cytoscape.getCyNode(target_id, false);
			
			cyEdge = Cytoscape.getCyEdge(source, target, Semantics.INTERACTION, edge.getType().getName(), true, true);
			
			// TODO: Attributes are missing !
			edgeAttributes.setAttribute(cyId, "NetworkSimDB id", edge.getId().toString());
			edgeAttributes.setAttribute(cyId, "NetworkSimDB type", edge.getType().getName());
			if (edge.getXref().getLink() != null){
				edgeAttributes.setAttribute(cyId, "NetworkSimDB link", edge.getXref().getLink());
			}
			
			network.addEdge(cyEdge);	
		}
		
		
	}
	
	/** Generates graph in database via indirect generation 
	 * of String[][] example data. Only temporal solution.
	 * TODO: implement with direct transaction into database.
	 */
	public static boolean commitGraphToDB2(){
		
		//JOptionPane.showMessageDialog(null, "commit graph", "commit graph", JOptionPane.WARNING_MESSAGE);
		
		// Get the current Cytoscape network
		CyNetwork network = Cytoscape.getCurrentNetwork();
		if (network == null){// Generate Type based on the available attributes
			System.out.println("No current network view");
			return false;
		}
		
		// Store the example data
		int maxCount = 1 + network.getNodeCount() + network.getEdgeCount(); 
		String[][] example = new String[maxCount][];
		int count = 0;
		
		
		// [1] Create graph 
		// Information for User, Type and XRef
		// get or create a user object
		String user_name = "test";
		String xref_name = network.getIdentifier();
		String type_name = "metabolic network";
		String type_description = "metabolic network description";
		
		String[] graph_data = {"Graph", type_name, xref_name, user_name}; 
		System.out.println(count);
		example[count] = graph_data; 
		count ++;
				
		// [3] create nodes 
		CyAttributes nodeAttributes = Cytoscape.getNodeAttributes();
		List<CyNode> cyNodesList = (List<CyNode>) network.nodesList();
		String cyType;
		String id;
		
		for (CyNode cyNode: cyNodesList){
			id = cyNode.getIdentifier();
			System.out.println("Node:" + id);
			cyType = nodeAttributes.getStringAttribute(id, "sbml type");
			String[] node_data = {"Node", "node", id}; 
			example[count] = node_data; 
			count ++;
		}
		
		// [4] Generate edges
		CyAttributes edgeAttributes = Cytoscape.getNodeAttributes();
		List<CyEdge> cyEdgeList = network.edgesList(); 			//edges in Cytoscape network
		
		for (CyEdge cyEdge: cyEdgeList){	
			String[] edge_data = {"Edge", "edge", cyEdge.getIdentifier(), cyEdge.getSource().getIdentifier(), cyEdge.getTarget().getIdentifier()}; 
			example[count] = edge_data; 
			count ++;
			
		}
	
		
		// Commit the data
		Graph g = GraphExampleFactory.generateExampleGraph(example);
		JOptionPane.showMessageDialog(null, "Graph commited: ID " + g.getId(), "graph commited", JOptionPane.WARNING_MESSAGE);
		return true;
	}
	
	
	
	
	/** Commit the current network to the database */
	/*
	public static boolean commitGraphToDB(){
		
		//JOptionPane.showMessageDialog(null, "commit graph", "commit graph", JOptionPane.WARNING_MESSAGE);
		
		// Get the current Cytoscape network
		CyNetwork network = Cytoscape.getCurrentNetwork();
		if (network == null){// Generate Type based on the available attributes
			System.out.println("No current network view");
			return false;
		}
		
		
		// [1] Create graph 
		// Information for User, Type and XRef
		// get or create a user object
		String user_name = "test";
		User user = getOrCreateObjectByAttributes(Class=User, name=user_name);
		
		// get or create a xref
		String xref_name = "test_graph_01";
		Xref xref = getOrCreateObjectByAttributes(Class=XRef, name=xref_name, link=null);
		
		// get or create type
		String type_name = "metabolic network";
		String type_description = "metabolic network description";
		Type type = getOrCreateObjectByAttributes(Class=Type, name=type_name, description=type_description);
		
		// create graph 
		Graph g = new Graph()
		g.setUser(user);
		g.setType(type);
		g.setXref(xref);
		
		
		// [3] create nodes 
		// Define the node types
		Type typeSpecies = getOrCreateObjectByAttributes(Class=Type, name="species", 
											description="species in metabolic network");
		Type typeReaction = getOrCreateObjectByAttributes(Class=Type, name="reaction", 
				description="reaction in metabolic network");
		Type typeNull = getOrCreateObjectByAttributes(Class=Type, name="null", 
				description="not defined node in metabolic network");

		CyAttributes nodeAttributes = Cytoscape.getNodeAttributes();
		List<CyNode> cyNodesList = network.nodesList(); 					  //nodes in Cytoscape network
		Map<String, Node> nodeMap = new HashMap<String, Node>(); 			  //nodes in hibernate graph
		Node n;
		String cyType;
		String id;
		
		for (CyNode cyNode: cyNodesList){
			id = cyNode.getIdentifier();
			
			// Generate xref with Cytoscape id
			xref = getOrCreateObjectByAttributes(Class=XRef, name=id, link=null);
			
			// Generate Type based on the available attributes
			cyType = nodeAttributes.getStringAttribute(id, "sbml type");
			if (cyType.equals("species")){		 type = typeSpecies; 	}
			else if (cyType.equals("reaction")){ type = typeReaction;	}
			else{								 type = typeNull;		}
			
			// create node and set data
			n = new Node();
			Node.setXref(xref);
			Node.setType(type);
			
			nodeMap.put(id, Node);
		}
		g.setNodes( (Set<Node>) nodeMap.values());
		
		// [4] Generate edges
		CyAttributes edgeAttributes = Cytoscape.getNodeAttributes();
		List<CyEdge> cyEdgeList = network.edgesList(); 			//edges in Cytoscape network
		Set<Edge> edgeSet = new HashSet<Edge>(); 			  	//edges in hibernate graph
		
		// One type for all edges
		type = getOrCreateObjectByAttributes(Class=Type, name="edge", 
				description="edge in metabolic network");
		
		Edge e;
		Node source;
		Node target;
		
		for (CyEdge cyEdge: cyEdgeList){
			
			// Generate xref with Cytoscape id
			xref = getOrCreateObjectByAttributes(Class=XRef, name=cyEdge.getIdentifier(), link=null);
			
			// Get source and target
			source = nodeMap.get(cyEdge.getSource().getIdentifier());
			target = nodeMap.get(cyEdge.getTarget().getIdentifier());
			
			// create edge and set data
			e = new Edge();
			Edge.setXref(xref);
			Edge.setType(type);
			Edge.setSource(source);
			Edge.setTarget(target);
			edgeSet.add(Edge);
		}
		g.setEdges(edgeSet);
		
		
		// Here the commit is performed.
		// All objects have been generated or loaded now commit
		g.commit();
		
		
		return true;
	}
	*/

}
