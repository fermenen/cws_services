package uo.ri.cws.application.service.workorder.crud.command;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.VehicleRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.domain.WorkOrder;

/**
 * Work orders by vehicle
 * 
 * @version 2.0
 *
 */
public class FindWorkOrdersByPlateNumber
	implements Command<List<WorkOrderDto>> {

    private String plateId;
    private VehicleRepository repoVehicle = Factory.repository.forVehicle();
    private WorkOrderRepository repoWorkOrder = Factory.repository
	    .forWorkOrder();

    public FindWorkOrdersByPlateNumber(String plateId) {
	this.plateId = plateId;
    }

    @Override
    public List<WorkOrderDto> execute() throws BusinessException {
	Vehicle vehicle = checkVehicle(this.plateId);
	List<WorkOrder> listWorkOrders = repoWorkOrder
		.findByVehicle(vehicle.getId());
	return DtoAssembler.toWorkOrderDtoList(listWorkOrders);
    }

    private Vehicle checkVehicle(String plateId) throws BusinessException {
	Optional<Vehicle> v = repoVehicle.findByPlate(plateId);
	BusinessCheck.isTrue(v.isPresent(),
		"The plate number is not in the system");
	return v.get();
    }

}
