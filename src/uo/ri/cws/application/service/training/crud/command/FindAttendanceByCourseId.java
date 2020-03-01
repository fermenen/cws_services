package uo.ri.cws.application.service.training.crud.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.AttendanceRepository;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.EnrollmentDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Enrollment;

public class FindAttendanceByCourseId implements Command<List<EnrollmentDto>> {

    private String courseId;
    private AttendanceRepository repo = Factory.repository.forAttendance();
    private CourseRepository repoC = Factory.repository.forCourse();

    public FindAttendanceByCourseId(String courseId) {
	this.courseId = courseId;
    }

    @Override
    public List<EnrollmentDto> execute() throws BusinessException {
	BusinessCheck.isTrue(this.courseId != null);
	checkCourse(this.courseId);
	List<Enrollment> enroll = repo.findByIdCourse(courseId);
	BusinessCheck.isFalse(enroll.isEmpty(),
		"There is no assistance for this course");
	return DtoAssembler.toEnrollmentDtoList(enroll);
    }

    private void checkCourse(String courseId) throws BusinessException {
	BusinessCheck.isTrue(repoC.findById(courseId).isPresent(),
		"The course does not exist");

    }

}
