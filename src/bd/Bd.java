package bd;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Bd {

	private Session session;
	private SessionFactory sessionFactory;

	public Bd() {
		@SuppressWarnings("unused")
		org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);

		Configuration config = new Configuration();
		config.configure("./hibernate.cfg.xml");

		sessionFactory = config.buildSessionFactory();
		session = sessionFactory.openSession();
	}

	public void close() {
		session.close();
		sessionFactory.close();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
