package uo.ri.cws.application.service.training.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.AttendanceRepository;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.EnrollmentDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.EntityAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Mechanic;

public class RegisterNewAttendance implements Command<EnrollmentDto> {

    private EnrollmentDto enroll;
    private AttendanceRepository repoA = Factory.repository.forAttendance();
    private MechanicRepository repoM = Factory.repository.forMechanic();
    private CourseRepository repoC = Factory.repository.forCourse();

    public RegisterNewAttendance(EnrollmentDto enroll) {
	this.enroll = enroll;
    }

    @Override
    public EnrollmentDto execute() throws BusinessException {
	BusinessCheck.isTrue(this.enroll != null);
	Course course = checkCourse(enroll.courseId);
	Mechanic mechanic = checkMechanic(enroll.mechanicId);
	checkNotRegistred(course.getId(), mechanic.getId());
	checkAsistence(enroll.passed, enroll.attendance);
	Enrollment atten = EntityAssembler.toEntity(enroll, course, mechanic);
	repoA.add(atten);
	this.enroll.id = atten.getId();
	return enroll;
    }

    private Course checkCourse(String courseId) throws BusinessException {
	Optional<Course> c = repoC.findById(courseId);
	BusinessCheck.isFalse(c.isEmpty(), "The course does not exist");
	return c.get();

    }

    private Mechanic checkMechanic(String mechanicId) throws BusinessException {
	Optional<Mechanic> m = repoM.findById(mechanicId);
	BusinessCheck.isFalse(m.isEmpty(), "The mechanic does not exist");
	return m.get();

    }

    private void checkAsistence(boolean pass, int asistence)
	    throws BusinessException {
	if (pass) {
	    int minAsistence = 85;
	    int maxAsistence = 100;
	    BusinessCheck.isFalse(
		    asistence < minAsistence || asistence > maxAsistence,
		    "Attendance must be greater than 85 and maximum 100");
	}

    }

    private void checkNotRegistred(String idCourse, String idMechanic)
	    throws BusinessException {
	Optional<Enrollment> attendance = repoA.findByIdCourseMechanic(idCourse,
		idMechanic);
	BusinessCheck.isFalse(attendance.isPresent(),
		"The assistance is already registered");

    }

}
