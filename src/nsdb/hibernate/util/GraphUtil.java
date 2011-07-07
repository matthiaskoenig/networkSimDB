package nsdb.hibernate.util;


import nsdb.hibernate.entity.Graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GraphUtil {
	
	/**
	 * Returns HashMap of Graphs.
	 * Returns empty HashSet if no graphs available.
	 * Returns null if error occurs.
	 * @return
	 */
	public static Map<Long, Graph> getGraphs(){
	       Session session = HibernateUtil.getSessionFactory().openSession();
	       Transaction transaction = null;
	       Map<Long, Graph> map = new HashMap<Long, Graph>();
           try {
	            transaction = session.beginTransaction();
	            List graphs = session.createQuery("from Graph").list();
	            for (Iterator iterator = graphs.iterator(); iterator.hasNext();)
	            {
	                Graph g = (Graph) iterator.next();
	                map.put(g.getId(), g);
	                //System.out.println("Graph: " + g.getId());
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	            map = null;
	        } finally {
	            session.close();
	        }
	        return map;
	    }
}
