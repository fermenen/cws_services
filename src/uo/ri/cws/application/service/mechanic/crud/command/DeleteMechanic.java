package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

public class DeleteMechanic implements Command<Void> {

    private String mechanicId;
    private MechanicRepository repo = Factory.repository.forMechanic();

    public DeleteMechanic(String idMecanico) {
	this.mechanicId = idMecanico;
    }

    public Void execute() throws BusinessException {

	BusinessCheck.isTrue(this.mechanicId != null);
	Optional<Mechanic> mechanic = repo.findById(mechanicId);
	BusinessCheck.isTrue(mechanic.isPresent(),
		"This mechanic does not exist");
	checkDeleteMechanic(mechanic.get());
	repo.remove(mechanic.get());
	return null;
    }

    private void checkDeleteMechanic(Mechanic m) throws BusinessException {
	BusinessCheck.isTrue(m.getInterventions().isEmpty(),
		"This mechanic cannot be eliminated, he has interventions");
	BusinessCheck.isTrue(m.getAssigned().isEmpty(),
		"This mechanic cannot be eliminated, he has work orders");

    }

}
