package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

public class UpdateMechanic implements Command<Void> {

    private MechanicDto dto;
    private MechanicRepository repo = Factory.repository.forMechanic();

    public UpdateMechanic(MechanicDto dto) {
	this.dto = dto;
    }

    public Void execute() throws BusinessException {
	Optional<Mechanic> mechanic = repo.findById(dto.id);
	BusinessCheck.isNotNull(mechanic.get(), "The mechanic does not exist");
	mechanic.get().setSurname(dto.surname);
	mechanic.get().setName(dto.name);
	return null;
    }

}
