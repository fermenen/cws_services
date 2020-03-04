package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.Optional;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class MechanicJpaRepository extends BaseJpaRepository<Mechanic>
	implements MechanicRepository {

    @Override
    public Optional<Mechanic> findByDni(String dni) {
	return Jpa.getManager()
		.createNamedQuery("Mechanic.findByDni", Mechanic.class)
		.setParameter(1, dni).getResultList().stream().findFirst();
    }

    @Override
    public Long calcHoursCEnroll(String id) {
	return Jpa.getManager()
		.createNamedQuery("Mechanic.calculateHoursCourseEnroll",
			Long.class)
		.setParameter(1, id).getSingleResult();

    }

    @Override
    public Long calcHoursCAssit(String id) {
	return Jpa.getManager()
		.createNamedQuery("Mechanic.calculateHoursCourseAssit",
			Long.class)
		.setParameter(1, id).getSingleResult();

    }

    /**
     * Calculate enrolled hours by type
     */
    @Override
    public Long EnrolledHoursByType(String idMechanic, String idType) {
	return Jpa.getManager()
		.createNamedQuery("Mechanic.CalculateEnrolledHoursByType",
			Long.class)
		.setParameter(1, idMechanic).setParameter(2, idType)
		.getSingleResult();
    }

    /**
     * Calculate assisted hours by type
     */
    @Override
    public Long AssistedHoursByType(String idMechanic, String idType) {
	return Jpa.getManager()
		.createNamedQuery("Mechanic.CalculateAssistedHoursByType",
			Long.class)
		.setParameter(1, idMechanic).setParameter(2, idType)
		.getSingleResult();
    }

    /**
     * Calculate assisted hours by type and passed
     */
    @Override
    public Long calcHoursCAssitTypePassed(String idMechanic, String idType) {
	return Jpa.getManager()
		.createNamedQuery(
			"Mechanic.CalculateAssistedHoursByTypeAndPassed",
			Long.class)
		.setParameter(1, idMechanic).setParameter(2, idType)
		.setParameter(3, true).getSingleResult();
    }

}
