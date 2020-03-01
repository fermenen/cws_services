package uo.ri.cws.application.service.mechanic.crud.command;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;

public class AddMechanic {

    private MechanicDto dto;

    public AddMechanic(MechanicDto mecanico) {
	this.dto = mecanico;
    }

    public MechanicDto execute() throws BusinessException {

	return dto;
    }

}
