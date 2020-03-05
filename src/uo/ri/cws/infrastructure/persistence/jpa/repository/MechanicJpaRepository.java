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

    /**
     * Calculate enrolled hours by mechanic
     */
    @Override
    public Long enrolledHoursByMechanic(String id) {
	return Jpa.getManager()
		.createNamedQuery("Mechanic.calculateHoursCourseEnroll",
			Long.class)
		.setParameter(1, id).getSingleResult();

    }

    /**
     * Calculate assisted hours by mechanic
     */
    @Override
    public Long assistedHoursByMechanic(String id) {
	return Jpa.getManager()
		.createNamedQuery("Mechanic.calculateHoursCourseAssit",
			Long.class)
		.setParameter(1, id).getSingleResult();

    }

    /**
     * Calculate enrolled hours by type
     */
    @Override
    public Long enrolledHoursByType(String idMechanic, String idType) {
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
    public Long assistedHoursByType(String idMechanic, String idType) {
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
    public Long assistedHoursByTypeAndPassed(String idMechanic, String idType) {
	return Jpa.getManager()
		.createNamedQuery(
			"Mechanic.CalculateAssistedHoursByTypeAndPassed",
			Long.class)
		.setParameter(1, idMechanic).setParameter(2, idType)
		.setParameter(3, true).getSingleResult();
    }

}
