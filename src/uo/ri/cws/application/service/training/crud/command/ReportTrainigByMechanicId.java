package uo.ri.cws.application.service.training.crud.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.*;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

/**
 * The system will request the identification of the mechanic and then, it will
 * show the total hours of courses in which he was enrolled, the total hours
 * attended and a break down of those attended by each type of vehicle.
 * 
 * @version 2.0
 *
 */
public class ReportTrainigByMechanicId
	implements Command<List<TrainingForMechanicRow>> {

    private String mechanicId;
    private MechanicRepository repoMechanic = Factory.repository.forMechanic();
    private VehicleTypeRepository repoTV = Factory.repository.forVehicleType();

    public ReportTrainigByMechanicId(String mechanicId) {
	this.mechanicId = mechanicId;

    }

    @Override
    public List<TrainingForMechanicRow> execute() throws BusinessException {
	Mechanic mechanic = checkMechanic(this.mechanicId);
	return calculoHorasByTipo(mechanic);
    }

    /**
     * Check if the mechanic exists in the system
     * 
     * @param mechanicId
     * @return
     * @throws BusinessException
     */
    private Mechanic checkMechanic(String mechanicId) throws BusinessException {
	Optional<Mechanic> m = repoMechanic.findById(mechanicId);
	BusinessCheck.isTrue(m.isPresent(), "The mechanic does not exist");
	return m.get();

    }

    /**
     * Hours enrolled and attended by type vehicle
     * 
     * @param mechanic
     * @return List of TrainingForMechanicRow
     */
    private List<TrainingForMechanicRow> calculoHorasByTipo(Mechanic mechanic) {
	List<TrainingForMechanicRow> listTraining = new ArrayList<TrainingForMechanicRow>();
	for (VehicleType typeVehicle : repoTV.findAll()) {
	    TrainingForMechanicRow training = new TrainingForMechanicRow();
	    training.vehicleTypeName = typeVehicle.getName();
	    Object enrolledHours = repoMechanic
		    .EnrolledHoursByType(mechanic.getId(), typeVehicle.getId());
	    training.enrolledHours = ((enrolledHours == null) ? 0
		    : ((Long) enrolledHours).intValue());
	    Object attendedHours = repoMechanic
		    .AssistedHoursByType(mechanic.getId(), typeVehicle.getId());
	    training.attendedHours = ((attendedHours == null) ? 0
		    : ((Long) attendedHours).intValue());
	    listTraining.add(training);
	}
	return listTraining;
    }

}
