package uo.ri.cws.application.service.mechanic.crud.command;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.EntityAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

public class AddMechanic implements Command<MechanicDto> {

    private MechanicDto dto;

    private MechanicRepository repo = Factory.repository.forMechanic();

    public AddMechanic(MechanicDto mecanico) {
	this.dto = mecanico;
    }

    public MechanicDto execute() throws BusinessException {
	BusinessCheck.isTrue(this.dto != null);
	checkNotRepeatedDni();
	Mechanic m = EntityAssembler.toEntity(dto);
	repo.add(m);
	this.dto.id = m.getId();
	return dto;
    }

    private void checkNotRepeatedDni() throws BusinessException {
	BusinessCheck.isNotNull(repo.findByDni(dto.dni),
		"This mechanic already exists");
    }

}
