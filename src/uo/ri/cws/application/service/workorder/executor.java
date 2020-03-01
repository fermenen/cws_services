package uo.ri.cws.application.service.workorder;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.application.util.command.CommandExecutor;

public class executor implements CommandExecutor {

    @Override
    public <T> T execute(Command<T> cmd) throws BusinessException {
	EntityManager em = Persistence.createEntityManagerFactory("carworkshop")
		.createEntityManager();
	EntityTransaction trx = em.getTransaction();
	T result = null;
	try {
	    trx.begin();
	    result = cmd.execute();
	} catch (BusinessException be) {
	    if (trx.isActive())
		trx.rollback();
	    throw be;

	} finally {
	    em.close();
	}
	return result;
    }

}
