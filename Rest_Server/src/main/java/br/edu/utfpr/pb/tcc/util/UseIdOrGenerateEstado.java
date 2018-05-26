package br.edu.utfpr.pb.tcc.util;

import java.io.Serializable;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentityGenerator;
import br.edu.utfpr.pb.tcc.model.Estado;



public class UseIdOrGenerateEstado extends IdentityGenerator  {
	private static final Logger log = Logger.getLogger(UseIdOrGenerateEstado.class.getName());

	@Override
	public Serializable generate(SessionImplementor session, Object obj) throws HibernateException {
		if (obj == null)
			throw new HibernateException(new NullPointerException());

		if (  ((Estado) obj).getId() == null) {
			Serializable id = super.generate(session, obj);
			return id;
		} else {
			return   ((Estado) obj).getId();

		}
	}
}