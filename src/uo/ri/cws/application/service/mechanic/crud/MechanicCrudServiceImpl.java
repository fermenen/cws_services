package uo.ri.cws.application.service.mechanic.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.service.mechanic.crud.command.AddMechanic;
import uo.ri.cws.application.service.mechanic.crud.command.DeleteMechanic;
import uo.ri.cws.application.service.mechanic.crud.command.FindAllMechanics;
import uo.ri.cws.application.service.mechanic.crud.command.FindMechanicById;
import uo.ri.cws.application.service.mechanic.crud.command.UpdateMechanic;
import uo.ri.cws.application.util.command.CommandExecutor;

public class MechanicCrudServiceImpl implements MechanicCrudService {

//	private CommandExecutor executor = Factory.executor.forExecutor();

    @Override
    public MechanicDto addMechanic(MechanicDto mecanico)
	    throws BusinessException {
	return new AddMechanic(mecanico).execute();
    }

    @Override
    public void updateMechanic(MechanicDto mecanico) throws BusinessException {
	new UpdateMechanic(mecanico).execute();
    }

    @Override
    public void deleteMechanic(String idMecanico) throws BusinessException {
	new DeleteMechanic(idMecanico).execute();
    }

    @Override
    public List<MechanicDto> findAllMechanics() throws BusinessException {
	return new FindAllMechanics().execute();
    }

    @Override
    public Optional<MechanicDto> findMechanicById(String id)
	    throws BusinessException {
	return new FindMechanicById(id).execute();
    }

}