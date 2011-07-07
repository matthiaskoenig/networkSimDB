/*
 Copyright (c) 2010, Matthias Koenig, Computational Systems Biochemistry, 
 Charite Berlin
 matthias.koenig [at] charite.de

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
*/

package nsdb.cytoscape;

import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

import nsdb.cytoscape.gui.CytoscapeSQLPanel;
import nsdb.db.postgres.HepatoCoreInfo;
import nsdb.db.postgres.HepatoNetInfo;
import nsdb.db.sqlite.ConnectSQLite;


import cytoscape.CyNode;
import cytoscape.Cytoscape;

import cytoscape.plugin.CytoscapePlugin;
import cytoscape.util.CytoscapeAction;

import cytoscape.view.CytoscapeDesktop;
import cytoscape.view.cytopanels.CytoPanel;
import cytoscape.view.cytopanels.CytoPanelState;
import ding.view.DGraphView;
import ding.view.InnerCanvas;

public class CytoscapeSQL extends CytoscapePlugin {
	/** Cytoscape Panel which holds the FluxViz panel. */
	private static CytoPanel cytoPanel;
	/** FluxViz panel on the left side. */
	private static CytoscapeSQLPanel csqlPanel;
	
	public CytoscapeSQLPanel getCytoscapeSQLPanel(){
		return CytoscapeSQL.csqlPanel;
	}
	
	
	/**
	 * This constructor creates an action and adds it to the Plugins menu.
	 */
	public CytoscapeSQL() {
		
		// Create a new action to respond to menu activation and set in menu
		CytoscapeSQLAction action = new CytoscapeSQLAction();
		action.setPreferredMenu("Plugins");
		Cytoscape.getDesktop().getCyMenus().addAction(action);
	
		// Initialise the information panel
		init();
	}

    /**
     * Initialisation of FluxViz components.
     * Logger is initialised and Logger FileHandler generated.
     * PropertyChangeListeners registered.
     * FluxViz Panel initialised.
     */
    public void init(){
    	// Property change listeners
    	DGraphView graphView = (DGraphView) Cytoscape.getCurrentNetworkView();
    	InnerCanvas canvas = (InnerCanvas) graphView.getCanvas();
    	canvas.addMouseMotionListener(new CytoscapeSQLMouseMotionListener()); 
    	
    	
		// Register this class as a listener to listen Cytoscape events
		Cytoscape.getDesktop().getSwingPropertyChangeSupport().addPropertyChangeListener(CytoscapeDesktop.NETWORK_VIEW_FOCUSED, this);
		Cytoscape.getPropertyChangeSupport().addPropertyChangeListener(Cytoscape.ATTRIBUTES_CHANGED, this);
		Cytoscape.getPropertyChangeSupport().addPropertyChangeListener(Cytoscape.SESSION_LOADED, this);

		// Create CytoscapeSQL Panel
    	CytoscapeDesktop desktop = Cytoscape.getDesktop();
    	cytoPanel = desktop.getCytoPanel (SwingConstants.EAST);
    	try{
    		
    		csqlPanel = new CytoscapeSQLPanel(this);
    		cytoPanel.add("CytoscapeSQL",csqlPanel);
    		cytoPanel.setState(CytoPanelState.DOCK);
    		
    		//set the FluxViz panel as active panel
    		int index = cytoPanel.indexOfComponent(csqlPanel);
    		cytoPanel.setSelectedIndex(index);
    	}
    	catch (Exception e){
    		System.out.println("Error" + e.getMessage());
    		e.printStackTrace();
    	}
    }
    
    /**
     * Loads the information for given Node (node_root_graph_index)
     */
    public static void loadNodeInformation(CyNode node){
    	//String type = "Hepatocore";
    	String mtype = "HepatoNet1";
    	String info = "No information available.";
    	
    	// get the id
    	if (mtype.equals("HepatoCore")){
    		String id = node.getIdentifier().split("_")[1];
    		System.out.println(id);
    		HepatoCoreInfo hepatoCoreInfo = new HepatoCoreInfo(id);
    		info = hepatoCoreInfo.createHTMLInfo();
    	}
    	// get the id
    	if (mtype.equals("HepatoNet1")){
    		HepatoNetInfo hepatoNetInfo = new HepatoNetInfo(node);
    		info = hepatoNetInfo.createHTMLInfo();
    	}
    	
    	// Set the information in the text window
    	csqlPanel.getInfoTextPane().setText(info);
    }
    
    
    /** Update the information in the right panel.
     * 	Loads the database information for the selected nodes
     **/
    public static void updateInformation(){
    
    	System.out.println("Add Mouse Listener to network !!!");
    	// TODO: has to added to the network view changes
    	// Property change listeners
    	DGraphView graphView = (DGraphView) Cytoscape.getCurrentNetworkView();
    	InnerCanvas canvas = (InnerCanvas) graphView.getCanvas();
    	canvas.addMouseMotionListener(new CytoscapeSQLMouseMotionListener()); 
    	
    	/*
    	// Get the information based on the selected nodes
    	// Hepatocore
    	String info = "";
    	String id = "";
    	CyNetwork network = Cytoscape.getCurrentNetwork();
    	if (network == null){
    		info = "No network information available (no network).";
    	}
    	else {
    		Set<CyNode> nodes = network.getSelectedNodes();
    		if (nodes.size() == 0){
    			info = "No network information available (no nodes selected).";
    		}
    		else{
    			for (CyNode node: nodes){
    				// create the id
    				id = node.getIdentifier().split("_")[1];
    				System.out.println(id);
    				HepatoCoreInfo hepatoCoreInfo = new HepatoCoreInfo(id);
    	    		info += "\n" + hepatoCoreInfo.createHTMLInfo();
    			}
    		}
    	}
    	// Set the information in the text window
    	csqlPanel.getInfoTextPane().setText(info);
    	*/
    }
	
	@SuppressWarnings("serial")
	
	/**
	 * Handle the menu action for cytoscape.
	 */
	public class CytoscapeSQLAction extends CytoscapeAction {
		/** The constructor sets the text that should appear on the menu item.*/
	    public CytoscapeSQLAction() {super("CytoscapeSQL");}
	    /** This method is called when the user selects the menu item.*/
	    public void actionPerformed(ActionEvent ae) {
	    	//JOptionPane.showMessageDialog(null, "CytoscapeSQL test", "CytoscapeSQL test", JOptionPane.WARNING_MESSAGE);
	    	
	    	// Connect to the given database
			ConnectSQLite.testConnection();
			ConnectSQLite.getDataFromTable("xref");
			
			// Test the postgres connection
			HepatoCoreInfo hcInfo = new HepatoCoreInfo("15926");
			hcInfo.printInfo();
			
	    }
	}


}
