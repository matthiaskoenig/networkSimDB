package nsdb.cytoscape;

import giny.view.NodeView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import cytoscape.CyNode;
import cytoscape.Cytoscape;
import ding.view.DGraphView;

public class CytoscapeSQLMouseMotionListener implements MouseMotionListener{

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// XXX Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//System.out.println("Mouse moved");
        //super.mouseMoved(e);
    	DGraphView graphView = (DGraphView) Cytoscape.getCurrentNetworkView();
        NodeView nv = graphView.getPickedNodeView(e.getPoint());
        if (nv != null){
        	CyNode n = (CyNode) nv.getNode(); 
        	CytoscapeSQL.loadNodeInformation(n);
        }
	}

}
