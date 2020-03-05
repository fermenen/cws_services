package uo.ri.cws.application.service.training.crud;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CourseReportService;
import uo.ri.cws.application.service.training.TrainingForMechanicRow;
import uo.ri.cws.application.service.training.TrainingHoursRow;
import uo.ri.cws.application.service.training.crud.command.ReportCertificatedByVehicleType;
import uo.ri.cws.application.service.training.crud.command.ReportTrainigByMechanicId;
import uo.ri.cws.application.service.training.crud.command.ReportTrainingByVehicleTypeAndMechanic;
import uo.ri.cws.application.service.training.dto.CertificateDto;
import uo.ri.cws.application.util.command.CommandExecutor;

public class CourseReportServiceImpl implements CourseReportService {

    private CommandExecutor executor = Factory.executor.forExecutor();

    @Override
    public List<TrainingForMechanicRow> findTrainigByMechanicId(String id)
	    throws BusinessException {
	return executor.execute(new ReportTrainigByMechanicId(id));

    }

    @Override
    public List<TrainingHoursRow> findTrainingByVehicleTypeAndMechanic()
	    throws BusinessException {
	return executor.execute(new ReportTrainingByVehicleTypeAndMechanic());
    }

    @Override
    public List<CertificateDto> findCertificatedByVehicleType()
	    throws BusinessException {
	return executor.execute(new ReportCertificatedByVehicleType());
    }

}
