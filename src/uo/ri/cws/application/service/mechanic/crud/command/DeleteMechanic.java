package uo.ri.cws.application.service.mechanic.crud.command;

import uo.ri.cws.application.service.BusinessException;

public class DeleteMechanic {

    private String mechanicId;

    public DeleteMechanic(String idMecanico) {
	this.mechanicId = idMecanico;
    }

    public Void execute() throws BusinessException {

	return null;
    }

}
