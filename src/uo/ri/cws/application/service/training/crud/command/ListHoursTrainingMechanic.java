package uo.ri.cws.application.service.training.crud.command;

import java.util.ArrayList;
import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.*;
import uo.ri.cws.application.service.training.dto.TrainingDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.VehicleType;

public class ListHoursTrainingMechanic implements Command<TrainingDto> {

    private String mechanicId;
    private MechanicRepository repoM = Factory.repository.forMechanic();
    private VehicleTypeRepository repoTV = Factory.repository.forVehicleType();

    public ListHoursTrainingMechanic(String mechanicId) {
	this.mechanicId = mechanicId;

    }

    @Override
    public TrainingDto execute() throws BusinessException {
	BusinessCheck.isTrue(repoM.findById(mechanicId).isPresent(),
		"The mehanic is not in the system");
	TrainingDto training = new TrainingDto();
	training.trainingAll = trainingCoursesMechanic(mechanicId);
	training.trainingType = trainingCoursesByType(mechanicId);
	return training;
    }

    /**
     * Obtenemos horas totales de cursos y asistidas
     * 
     * @param mechanicId
     * @return TrainingForMechanicRow
     */
    private TrainingForMechanicRow trainingCoursesMechanic(String mechanicId) {
	TrainingForMechanicRow training = new TrainingForMechanicRow();
	training.vehicleTypeName = "ALL";
	training.attendedHours = repoM.calcHoursCAssit(mechanicId).intValue();
	training.enrolledHours = repoM.calcHoursCEnroll(mechanicId).intValue();
	return training;
    }

    /**
     * Obtenemos horas totales asistidas por tipo de vehiculo
     * 
     * @param mechanicId
     * @return TrainingHoursRow
     */
    private List<TrainingHoursRow> trainingCoursesByType(String mechanicId) {
	List<TrainingHoursRow> listTraining = new ArrayList<TrainingHoursRow>();
	for (VehicleType typeVehicle : repoTV.findAll()) {
	    TrainingHoursRow training = new TrainingHoursRow();
	    training.vehicleTypeName = typeVehicle.getName();
	    training.enrolledHours = repoM
		    .calcHoursCAssitType(mechanicId, typeVehicle.getId())
		    .intValue();
	    listTraining.add(training);
	}
	return listTraining;
    }

}
