package uo.ri.cws.application.service.training.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.AttendanceRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Enrollment;

public class DeleteAttendance implements Command<Void> {

    private String id;
    private AttendanceRepository repo = Factory.repository.forAttendance();

    public DeleteAttendance(String id) {
	this.id = id;
    }

    @Override
    public Void execute() throws BusinessException {
	BusinessCheck.isTrue(this.id != null);
	Optional<Enrollment> asistencia = repo.findById(this.id);
	BusinessCheck.isTrue(asistencia.isPresent(),
		"The assistance is not in the system");
	repo.remove(asistencia.get());
	return null;

    }

}
