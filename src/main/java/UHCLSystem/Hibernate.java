package UHCLSystem;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

 
public class Hibernate implements Data{
	
	protected SessionFactory sessionFactory;
	
	public void setup()
	{
		final StandardServiceRegistry reg = new StandardServiceRegistryBuilder().configure().build();
		try
		{
			sessionFactory = new MetadataSources(reg).buildMetadata().buildSessionFactory();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(reg);
		}
	}
	
	public void exit()
	{
		sessionFactory.close();
	}
	

	public uhcluser login(String id, String psw)
	{
		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		uhcluser u = session.get(uhcluser.class, id);
		session.getTransaction().commit();
		
		if(u == null)
		{
			System.out.println("Your login ID is not found!");
			session.close();
			exit();
			return null;
		}
		else
		{
			if(psw.equals(u.getPassword()))
			{
				System.out.println("Your login is successful!");
				session.close();
				exit();
				return u;
			}
			else
			{
				System.out.println("Your password is incorrect!");
				session.close();
				exit();
				return null;
			}
		}
		
		 
	}
	
	public ArrayList<String> getMyCourses(String sid)
	{
		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "Select E.cid from Enrollment E where E.sid= :studentID";
		Query query = session.createQuery(hql);
		query.setParameter("studentID", sid);
		ArrayList<String> courses = (ArrayList<String>)query.list();
		session.getTransaction().commit();
		session.close();
		exit();
		return courses;
	}
	
	public ArrayList<String> getOpenCourses(String sid)
	{
		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		ArrayList<String> myCourses = getMyCourses(sid);
		String hql = "Select C.courseID from course C where C.status=:status";
		Query query = session.createQuery(hql);
		query.setParameter("status", "open");
		//query.setParameterList("courseList",  myCourses);
		
		ArrayList<String> openCourses = new ArrayList<String>();
		openCourses = (ArrayList<String>)query.list();
		
		session.getTransaction().commit();
		session.close();
		exit();
		return openCourses;
	}
	
	public void registerCourse(String sid, String cid)
	{
		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Enrollment e = new Enrollment(0, sid, cid);
		course c = session.load(course.class, cid);
		c.setEnrolled(c.getEnrolled()+1);
		if(c.getEnrolled()>=(c.getCap()-1))
		{
			c.setStatus("close");
		}
		session.update(c);
		session.save(e);
		session.getTransaction().commit();
		
		session.close();
		exit();
		
	}
	
	public ArrayList<String> getMyTeachingCourses(String sid)
	{
		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "Select C.courseID from course C where C.instructor= :instructor";
		Query query = session.createQuery(hql);
		query.setParameter("instructor", sid);
		ArrayList<String> courses = (ArrayList<String>)query.list();
		session.getTransaction().commit();
		session.close();
		exit();
		return courses;
	}
	
	
	public ArrayList<String> getEnrolledStudentNames(String cid)
	{
		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "Select E.sid from Enrollment E where E.cid=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", cid);
		
		ArrayList<String> names = (ArrayList<String>)query.list();
		session.getTransaction().commit();
		session.close();
		exit();
		return names;
		
		
	}
	
	public void writeNote(String writer, String content, String course, String datetime) {
		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

	    uhcluser user = (uhcluser) session.createQuery("FROM uhcluser WHERE loginID = :loginID")
	            .setParameter("loginID", writer)
	            .uniqueResult();

	    coursenote courseNote = new coursenote();
	    courseNote.setUserID(user.getLoginID());
	    courseNote.setCourseID(course);
	    courseNote.setContent(content);
	    courseNote.setDatetime(datetime);

	    session.save(courseNote);

		session.getTransaction().commit();
	    session.close();
	}


	public ArrayList<coursenote> getCourseNotes(String course) {
		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		ArrayList<coursenote> courseNotes;

	    courseNotes = (ArrayList<coursenote>) session.createQuery("FROM coursenote WHERE courseID = :courseID")
	            .setParameter("courseID",course)
	            .list();

	    session.close();

	    return courseNotes;
	}


	
	 
}
