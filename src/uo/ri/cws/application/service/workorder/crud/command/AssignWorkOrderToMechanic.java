package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.cws.domain.WorkOrder.WorkOrderStatus;

public class AssignWorkOrderToMechanic implements Command<Void> {

    private String woId, mechanicId;
    private WorkOrderRepository repoWorkOrder = Factory.repository
	    .forWorkOrder();
    private MechanicRepository repoMechanic = Factory.repository.forMechanic();

    public AssignWorkOrderToMechanic(String woId, String mechanicId) {
	super();
	this.woId = woId;
	this.mechanicId = mechanicId;
    }

    @Override
    public Void execute() throws BusinessException {
	WorkOrder workOrder = checkWorkOrder(this.woId);
	Mechanic mechanic = checkMechanic(this.mechanicId);
	checkMechanicForType(workOrder, mechanic);
	checkStatusWorkOrder(workOrder);
	workOrder.assignTo(mechanic);
	return null;
    }

    private void checkStatusWorkOrder(WorkOrder w) throws BusinessException {
	WorkOrderStatus permitedStatus1 = WorkOrder.WorkOrderStatus.OPEN;
	BusinessCheck.isTrue(w.getStatus().equals(permitedStatus1),
		"Cannot assign a " + w.getStatus().toString().toLowerCase()
			+ " work order");

    }

    private void checkMechanicForType(WorkOrder workOrder, Mechanic mechanic)
	    throws BusinessException {
	VehicleType vtype = workOrder.getVehicle().getVehicleType();
	BusinessCheck.isTrue(mechanic.isCertifiedFor(vtype),
		"The mechanic is not certificate for " + vtype.getName()
			+ " type");

    }

    private WorkOrder checkWorkOrder(String workOrderId)
	    throws BusinessException {
	Optional<WorkOrder> workOrder = repoWorkOrder.findById(workOrderId);
	BusinessCheck.isTrue(workOrder.isPresent(),
		"The work order does not exist");
	return workOrder.get();

    }

    private Mechanic checkMechanic(String mechanicId) throws BusinessException {
	Optional<Mechanic> mechanic = repoMechanic.findById(mechanicId);
	BusinessCheck.isNotNull(mechanic.get(), "The mechanic does not exist");
	return mechanic.get();

    }

}
