package uo.ri.cws.application.repository;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.domain.Enrollment;

public interface AttendanceRepository extends Repository<Enrollment> {

    Optional<Enrollment> findByIdCourseMechanic(String idCourse,
	    String idMechanic);

    List<Enrollment> findByIdMechanic(String idMechanic);

    List<Enrollment> findByIdCourse(String idCourse);

}
