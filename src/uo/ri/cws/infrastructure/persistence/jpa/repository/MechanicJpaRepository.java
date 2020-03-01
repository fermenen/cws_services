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

    @Override
    public Long calcHoursCAssitType(String id, String idType) {
	return Jpa.getManager()
		.createNamedQuery("Mechanic.calculateHoursCourseAssitType",
			Long.class)
		.setParameter(1, id).setParameter(2, idType).getSingleResult();
    }

    @Override
    public Long calcHoursCAssitTypePassed(String idMecanico, String idType) {
	return Jpa.getManager()
		.createNamedQuery(
			"Mechanic.calculateHoursCourseAssitTypePassed",
			Long.class)
		.setParameter(1, idMecanico).setParameter(2, idType)
		.setParameter(3, true).getSingleResult();
    }

}
