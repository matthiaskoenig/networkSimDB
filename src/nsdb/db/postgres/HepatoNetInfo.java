package nsdb.db.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;


/**
 * Handles the information for the HepatoNet1
 * @author mkoenig
 *
 */
public class HepatoNetInfo {
	private String info = "";
	private String id;
	private String type;
	
	public HepatoNetInfo(CyNode node){
		
		CyAttributes nodeAttributes = Cytoscape.getNodeAttributes();
		System.out.println("NodeType: " + type);
		id = node.getIdentifier();
		type = nodeAttributes.getStringAttribute(id, "sbml type");
		if (type.equals("species")){
			id = id.split("_")[0];
		}
		
		
		
		loadInfo();
		//printInfo();
	}
	
	/** Load the information for the given identifier from the HepatoNet Database */
	public void loadInfo(){
		System.out.println("Load HepatoNet1 information: " + id);
		
		Connection c = null;
		// Create the connection to the database
		try {
			  Class.forName("org.postgresql.Driver");
			  try {
				//TODO: general settings for the database to connect to
			    c = DriverManager.getConnection("jdbc:postgresql://olli.charite.de:5433/testdb",
			                                    "koenig", "dirAfitE");
			  } catch (SQLException se) {
			    se.printStackTrace();
			  }
			} catch (ClassNotFoundException cnfe) {
			  System.err.println("Couldn't find driver class:");
			  cnfe.printStackTrace();
		}
			
		Statement s = null;
		try {
			 s = c.createStatement();
				// Load the information from the database for the given id
				if (type.equals("reaction")){
					info = createReactionInfo(s);
				}
				else if (type.equals("species")){
					info = createCompoundInfo(s);
				}
			c.close();
		} catch (SQLException se) {
			 System.out.println("We got an exception while creating a statement:" +
			                     "that probably means we're no longer connected.");
			  se.printStackTrace();
		}
		


	}
	
	/**
	 * r_id 	character varying(32) 	
	 * equation_hc_id 	character varying(512) 	
	 * equation_name 	character varying(512) 	
	 * reaction_category 	character varying(256) 	
	 * delta_g 	double precision 	
	 * kegg_reaction 	character varying(10) 	 	
	 * recon1 	character varying(32) 	 	
	 * references 	text
	 * @param s
	 * @return reaction information
	 */
	public String createReactionInfo(Statement s){
		String table = "reactions_publ_new";
		String info = "";
		ResultSet rs = null;
		try {
			 String query = "SELECT * FROM import." + table + " WHERE r_id='" + id + "'";
			 System.out.println("QUERY:\t" + query);
			 rs = s.executeQuery(query);
			
			 // move resultset to the first row
			 if (rs.next() == true){
				 // create the information String
				 info = "<table>" + 
			 			"<tr><td><b>" + rs.getString("r_id") + "<b></td><td></td></tr>" + 
			 			"<tr><td>equation_hc_id</td><td>" + rs.getString("equation_hc_id") + "</td></tr>" +
			 			"<tr><td>equation_name</td><td>" + rs.getString("equation_name") + "</td></tr>" +
			 			"<tr><td>reaction_category</td><td>" + rs.getString("reaction_category") + "</td></tr>" +
			 			"<tr><td>delta_g</td><td>" + rs.getString("delta_g") + "</td></tr>" +
			 			"<tr><td>kegg_reaction</td><td><a href='http://www.genome.jp/dbget-bin/www_bget?cpd+" + rs.getString("kegg_reaction") + "' target=_blank>" + rs.getString("kegg_reaction") + "</a></td></tr>" +
			 			"<tr><td>recon1</td><td>" + rs.getString("recon1") + "</td></tr>" +
			 			"<tr><td>references</td><td>" + createReferenceHTML(rs.getString("references")) + "</td></tr>" +
			 			"</table>";
				  if (rs.getString("kegg_reaction") != null){
				  		info += "<img src='http://www.genome.jp/Fig/compound/" + rs.getString("kegg_reaction") + ".gif'>";
				  }
			 }
			 else {
				 info = "no db information available";
			 }
		} catch (SQLException se) {
			  System.out.println("We got an exception while executing our query:" +
			                     "that probably means our SQL is invalid");
			  se.printStackTrace();
			}
		return info;
	}
	
	
	/**
	 * 
	 * hc_id 	character varying(10) 	 	
	 * name 	character varying(255) 	 	
	 * sum_formula 	character varying(64) 	 	
	 * compartments 	character varying(32) 	
	 * comment 	text 	
	 * synonym 	character varying(255)
	 * kegg_id 	character varying(10) 	 	
	 * chebi_id 	integer 	 	
	 * cas_registry 	character varying(32) 	 	
	 * recon1 	character varying(32) 	
	 * pubchem_id 	integer
	 * 
	 * 
	 * @param s
	 * @return compound information
	 */
	
	public String createCompoundInfo(Statement s){
		String table = "compounds_publ_new";
		String info = "";
		ResultSet rs = null;
		try {
			 String query = "SELECT * FROM import." + table + " WHERE hc_id='" + id + "'";
			 System.out.println("QUERY:\t" + query);
			 rs = s.executeQuery(query);
			 
			 // move resultset to the first row
			 if (rs.next() == true){
				 // create the information String
				 info = "<table>" +
				 		"<tr><td><b>" + rs.getString("hc_id") + "<b></td><td></td></tr>" + 
			  			"<tr><td>name</td><td>" + rs.getString("name") + "</td></tr>" +
			  			"<tr><td>sum_formula</td><td>" + rs.getString("sum_formula") + "</td></tr>" +
			  			"<tr><td>compartments</td><td>" + rs.getString("compartments") + "</td></tr>" +
			  			"<tr><td>comment</td><td>" + rs.getString("comment") + "</td></tr>" +
			  			"<tr><td>synonym</td><td>" + rs.getString("synonym") + "</td></tr>" +
			  			"<tr><td>kegg_id</td><td><a href='http://www.genome.jp/dbget-bin/www_bget?cpd+" + rs.getString("kegg_id") + "' target=_blank>" + rs.getString("kegg_id") + "</a></td></tr>" +
			  			"<tr><td>chebi_id</td><td><a href='http://www.ebi.ac.uk/chebi/searchId.do?chebiId=CHEBI%3A" +  rs.getString("chebi_id") + "' target=_blank> " +  rs.getString("chebi_id") + "</a></td></tr>" +
			  			"<tr><td>recon1</td><td>" + rs.getString("recon1") + "</td></tr>" + 
			  			"</table>";
			  	if (rs.getString("kegg_id") != null){
			  		info += "<img src='http://www.genome.jp/Fig/compound/" + rs.getString("kegg_id") + ".gif'>";
			  	}
			  	if (rs.getString("chebi_id") != null){
			  		info += "<img src='http://www.ebi.ac.uk/chebi/displayImage.do;jsessionid=040CC743F76BDD53C062A0D0C0A7E1B0?defaultImage=true&imageIndex=0&chebiId="+ rs.getString("chebi_id") +"'>";
			  	}
			  	
			  	//HepatoNetInfo.class.getClassLoader().get
			  	//getSystemResource("a.jpg").toString();
			  	info += "<img src='http://www.genome.jp/Fig/compound/C00010.gif'>";
			  	
			  	

			  			
			 }
			 else {
				 info = "no db information available";
			 }
		} catch (SQLException se) {
			  System.out.println("We got an exception while executing our query:" +
			                     "that probably means our SQL is invalid");
			  se.printStackTrace();
			}
		return info;
	}
	
	public static String createReferenceHTML(String refinfo){
		String html = "";
		if (refinfo == null){
			return html;
		}
		String[] items = refinfo.split(" ");
		for (String item : items){
			if (item.startsWith("PMID:")){
				item = item.substring(5);
				html += "<a href='http://www.ncbi.nlm.nih.gov/pubmed/" + item + "?dopt=AbstractPlus'>PMID:"+ item +"</a> ";	
			}
			
		}		
		return html;
	}
	
	
	
	/**
	 * Print the information to the Desktop
	 */
	public void printInfo(){
		System.out.println(info);
	}
	
	/**
	 * Create HTML information string 
	 */
	public String createHTMLInfo(){
		return info;
	}
}
