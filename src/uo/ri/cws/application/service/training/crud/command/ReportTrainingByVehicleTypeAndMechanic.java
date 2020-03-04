package uo.ri.cws.application.service.training.crud.command;

import java.util.List;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.TrainingHoursRow;
import uo.ri.cws.application.util.command.Command;

public class ReportTrainingByVehicleTypeAndMechanic implements Command<List<TrainingHoursRow>> {

    @Override
    public List<TrainingHoursRow> execute() throws BusinessException {
	
	return null;
    }

}
