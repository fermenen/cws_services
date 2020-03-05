package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Optional;
import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.VehicleRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.dto.WorkOrderDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.EntityAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.domain.WorkOrder;

/**
 * @version 2.0
 *
 */
public class RegisterNewWorkOder implements Command<WorkOrderDto> {

    private WorkOrderDto wDto;
    private WorkOrderRepository repo = Factory.repository.forWorkOrder();
    private VehicleRepository repoV = Factory.repository.forVehicle();

    public RegisterNewWorkOder(WorkOrderDto wDto) {
	this.wDto = wDto;
    }

    @Override
    public WorkOrderDto execute() throws BusinessException {
	BusinessCheck.isTrue(this.wDto != null);
	Vehicle v = checkVehicle(this.wDto.vehicleId);
	checkDescriptcion(this.wDto);
	WorkOrder workOrder = EntityAssembler.toEntity(v, wDto);
	repo.add(workOrder);
	this.wDto.id = workOrder.getId();
	return wDto;
    }


    private Vehicle checkVehicle(String vehicleId) throws BusinessException {
	Optional<Vehicle> v = repoV.findById(vehicleId);
	BusinessCheck.isTrue(v.isPresent(), "The vehicle is not in the system");
	return v.get();
    }

    private void checkDescriptcion(WorkOrderDto wDto) throws BusinessException {
	int minNumberDescription = 4;
	BusinessCheck.isTrue(wDto.description.length() >= minNumberDescription,
		"The description of the work order must contain a minimum of "
			+ minNumberDescription + " characters");
    }

}
