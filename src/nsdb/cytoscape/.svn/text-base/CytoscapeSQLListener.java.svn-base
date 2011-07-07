package nsdb.cytoscape;

import nsdb.db.postgres.HepatoCoreInfo;
import giny.view.GraphViewChangeEvent;
import giny.view.GraphViewChangeListener;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import cytoscape.data.SelectEvent;
import cytoscape.data.SelectEventListener;

public class CytoscapeSQLListener implements SelectEventListener, GraphViewChangeListener{

	@Override
	public void onSelectEvent(SelectEvent event) {
		// XXX Auto-generated method stub
		
	}

	@Override
	public void graphViewChanged(GraphViewChangeEvent event) {
		// XXX Auto-generated method stub
		if (event.isNodesSelectedType()){
			CyNode[] selectedNodes = (CyNode[]) event.getSelectedNodes();
			String[] infos = new String[selectedNodes.length];
			CyAttributes nodeAttributes = Cytoscape.getNodeAttributes();
			String hc_id;
			for (int i=0; i<=selectedNodes.length; ++i){
				// Get the hc identifier of the selected nodes.
				hc_id = nodeAttributes.getStringAttribute(selectedNodes[i].getIdentifier(), "hepatocore_id");
				System.out.println("Selected node:" + hc_id);
				// Get database information for the hc entry
				HepatoCoreInfo hc_info = new HepatoCoreInfo(hc_id);
				infos[i] = hc_info.createHTMLInfo();
			}
			// Print the complete info for the selected entries
			System.out.println("SELECTED INFO");
			for (String info: infos){
				System.out.println(info);
			}
			
		}
	}
}
