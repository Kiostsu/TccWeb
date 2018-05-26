package br.edu.utfpr.pb.tcc.util;

import java.io.Serializable;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentityGenerator;
import br.edu.utfpr.pb.tcc.model.Categoria;



public class UseIdOrGenerateCategoria extends IdentityGenerator  {
	private static final Logger log = Logger.getLogger(UseIdOrGenerateCategoria.class.getName());

	@Override
	public Serializable generate(SessionImplementor session, Object obj) throws HibernateException {
		if (obj == null)
			throw new HibernateException(new NullPointerException());

		if (  ((Categoria) obj).getId() == null) {
			Serializable id = super.generate(session, obj);
			return id;
		} else {
			return   ((Categoria) obj).getId();

		}
	}
}