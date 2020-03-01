package uo.ri.cws.application.util;

import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.service.training.EnrollmentDto;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.domain.*;

public class EntityAssembler {

    public static Mechanic toEntity(MechanicDto dto) {
	return new Mechanic(dto.dni, dto.name, dto.surname);
    }

    public static WorkOrder toEntity(Vehicle v, WorkOrderDto dto) {
	return new WorkOrder(v, dto.description);
    }

    public static Enrollment toEntity(EnrollmentDto dto, Course c, Mechanic m) {
	return new Enrollment(c, m, dto.attendance, dto.passed);
    }

}
