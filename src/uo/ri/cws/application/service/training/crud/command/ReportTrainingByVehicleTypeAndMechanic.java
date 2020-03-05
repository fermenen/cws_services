package uo.ri.cws.application.service.training.crud.command;

import java.util.ArrayList;
import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.TrainingHoursRow;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

/**
 * Mechanics with training course by type of vehicle. It will display, for each
 * type of vehicle, the number of hours accumulated by each mechanic.
 *
 */
public class ReportTrainingByVehicleTypeAndMechanic
	implements Command<List<TrainingHoursRow>> {

    private MechanicRepository repoMechanic = Factory.repository.forMechanic();
    private VehicleTypeRepository repoTypeVehicle = Factory.repository
	    .forVehicleType();

    @Override
    public List<TrainingHoursRow> execute() throws BusinessException {

	return aunNoseNombre();
    }

    public List<TrainingHoursRow> aunNoseNombre() {
	List<TrainingHoursRow> listTraining = new ArrayList<TrainingHoursRow>();
	for (VehicleType typeVehicle : repoTypeVehicle.findAll()) {
	    for (Mechanic mechanic : repoMechanic.findAll()) {
		Object attendedHours = repoMechanic.AssistedHoursByType(
			mechanic.getId(), typeVehicle.getId());
		int hoursAttended = ((attendedHours == null) ? 0
			: ((Long) attendedHours).intValue());
		if (hoursAttended > 0) {
		    TrainingHoursRow training = new TrainingHoursRow();
		    training.mechanicFullName = mechanic.getFullName();
		    training.vehicleTypeName = typeVehicle.getName();
		    training.enrolledHours = hoursAttended;
		    listTraining.add(training);
		}

	    }
	}
	return listTraining;
    }
}
