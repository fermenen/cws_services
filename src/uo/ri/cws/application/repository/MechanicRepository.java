package uo.ri.cws.application.repository;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.domain.Mechanic;

public interface MechanicRepository extends Repository<Mechanic> {

    /**
     * @param dni
     * @return the mechanic identified by the dni or null if none
     */
    Optional<Mechanic> findByDni(String dni);

    /**
     * @return a list with all mechanics (might be empty)
     */
    List<Mechanic> findAll();

    /**
     * @param id
     * @return horas totales cursos
     */
    Long calcHoursCEnroll(String id);

    /**
     * @param id
     * @return horas totales asistidas
     */
    Long calcHoursCAssit(String id);

    /**
     * @param idMecanico
     * @param idType
     * @return horas totales asistidas por tipo de vehiculo
     */
    Long calcHoursCAssitType(String idMecanico, String idType);

    /**
     * @param idMecanico
     * @param idType
     * @return horas totales asistidas por tipo de vehiculo aprobado
     */
    Long calcHoursCAssitTypePassed(String idMecanico, String idType);

}
