package uo.ri.cws.application.service.training.crud.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

public class FindAllActiveMechanics implements Command<List<MechanicDto>> {

    private MechanicRepository repo = Factory.repository.forMechanic();

    @Override
    public List<MechanicDto> execute() throws BusinessException {
	List<Mechanic> mecanicos = repo.findAll();
	return DtoAssembler.toMechanicDtoList(mecanicos);
    }

}
