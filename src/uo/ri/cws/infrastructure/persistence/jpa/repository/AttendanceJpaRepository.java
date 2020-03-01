package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.repository.AttendanceRepository;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class AttendanceJpaRepository extends BaseJpaRepository<Enrollment>
	implements AttendanceRepository {

    @Override
    public Optional<Enrollment> findByIdCourseMechanic(String idCourse,
	    String idMechanic) {

	return Jpa.getManager()
		.createNamedQuery("Enrollment.findByCourseMechanic",
			Enrollment.class)
		.setParameter(1, idCourse).setParameter(2, idMechanic)
		.getResultList().stream().findFirst();
    }

    @Override
    public List<Enrollment> findByIdCourse(String idCourse) {
	return Jpa.getManager()
		.createNamedQuery("Enrollment.findByCourse", Enrollment.class)
		.setParameter(1, idCourse).getResultList();
    }

    @Override
    public List<Enrollment> findByIdMechanic(String idMechanic) {
	return Jpa.getManager()
		.createNamedQuery("Enrollment.findByMechanic", Enrollment.class)
		.setParameter(1, idMechanic).getResultList();
    }

}
