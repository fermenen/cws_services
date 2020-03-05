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
     * @param idMechanic
     * @return Enrolled hours by mechanic
     */
    Long enrolledHoursByMechanic(String idMechanic);

    /**
     * @param idMechanic
     * @return Assisted hours by mechanic
     */
    Long assistedHoursByMechanic(String idMechanic);

    
    /**
     * @param idMechanic
     * @param idVehicleType
     * @return Enrolled hours for vehicle type
     */
    Long enrolledHoursByType(String idMechanic, String idVehicleType);
    
    
    /**
     * @param idMechanic
     * @param idVehicleType
     * @return Assisted hours for vehicle type and mechanic
     */
    Long assistedHoursByType(String idMechanic, String idVehicleType);

    /**
     * @param idMechanic
     * @param idVehicleType
     * @return Assisted hours for vehicle type, mechanic and passed
     */
    Long assistedHoursByTypeAndPassed(String idMechanic, String idVehicleType);

}
