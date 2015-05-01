package org.levelsbeyond.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Session;
import org.levelsbeyond.domain.Notes;

@Path("/notes")
public class NotesService {


	/*
	 * For persisting notes into Database: Method: GET
	 * 
	 * http://localhost:8080/api/notes
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getSpecficProductsbydate(Notes claim) throws Exception {
		Session session = HibernateUtil.getSessionAnnotationFactory()
				.getCurrentSession();

		session.beginTransaction();
		session.save(claim);

		System.out.println("Hibernate serviceRegistry created");

		session.getTransaction().commit();

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", claim.getId());
		jsonObj.put("body", claim.getBody());
		return jsonObj;
	}
	
	/*
	 * For getting notes based on id from Database: Method: GET
	 * 
	 * http://localhost:8080/api/notes/1
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Notes> getSpecficnote(@PathParam("id") String id) throws Exception {
		Session session = HibernateUtil.getSessionAnnotationFactory()
				.getCurrentSession();

		org.hibernate.Transaction tx = session.beginTransaction();
		org.hibernate.Query q = session
				.createQuery("SELECT m FROM Notes m WHERE m.id = :id");
		q.setParameter("id", Integer.parseInt(id));
		List<Notes> value = q.list();
		return value;
	}
	
	/*
	 * For getting all notes from Database: Method: GET
	 * 
	 * http://localhost:8080/api/notes/getallnotes
	 */
	
	@GET
	@Path("/getallnotes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Notes> getAllProducts() throws Exception {
		Session session = HibernateUtil.getSessionAnnotationFactory()
				.getCurrentSession();

		org.hibernate.Transaction tx = session.beginTransaction();

		List<Notes> list = session.createCriteria(Notes.class).list();

		return list;
	}
	
	/*
	 * For getting notes based on query parameters from Database: Method: GET
	 * 
	 * http://localhost:8080/api/notes?query=ilk
	 */
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Notes> getquerylist(@QueryParam("query") String wildcard) throws Exception {
		Session session = HibernateUtil.getSessionAnnotationFactory()
				.getCurrentSession();

		org.hibernate.Transaction tx = session.beginTransaction();
		System.out.println(wildcard);
		org.hibernate.Query q = session
				.createQuery("SELECT m FROM Notes m WHERE notes LIKE :wild0 OR notes LIKE :wild1 OR notes LIKE :wild2");
		q.setParameter("wild0", wildcard+ "%");
		q.setParameter("wild1",  "%"+wildcard+ "%");
		q.setParameter("wild2",  "%"+wildcard);

		List<Notes> list = q.list();

		return list;
	}
	
}
