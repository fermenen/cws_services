package uo.ri.cws.application.service.training.crud;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.service.training.CourseAttendanceService;
import uo.ri.cws.application.util.command.CommandExecutor;
import uo.ri.cws.application.service.training.crud.command.DeleteAttendance;
import uo.ri.cws.application.service.training.crud.command.FindAllActiveCourses;
import uo.ri.cws.application.service.training.crud.command.FindAllActiveMechanics;
import uo.ri.cws.application.service.training.crud.command.RegisterNewAttendance;
import uo.ri.cws.application.service.training.dto.CourseDto;
import uo.ri.cws.application.service.training.dto.EnrollmentDto;
import uo.ri.cws.application.service.training.crud.command.FindAttendanceByCourseId;

public class CourseAttendanceServiceImpl implements CourseAttendanceService {

    private CommandExecutor executor = Factory.executor.forExecutor();

    @Override
    public EnrollmentDto registerNew(EnrollmentDto dto)
	    throws BusinessException {
	return executor.execute(new RegisterNewAttendance(dto));
    }

    @Override
    public void deleteAttendace(String id) throws BusinessException {
	executor.execute(new DeleteAttendance(id));
    }

    @Override
    public List<EnrollmentDto> findAttendanceByCourseId(String id)
	    throws BusinessException {
	return executor.execute(new FindAttendanceByCourseId(id));
    }

    @Override
    public List<CourseDto> findAllActiveCourses() throws BusinessException {
	return executor.execute(new FindAllActiveCourses());
    }

    @Override
    public List<MechanicDto> findAllActiveMechanics() throws BusinessException {
	return executor.execute(new FindAllActiveMechanics());
    }

}
