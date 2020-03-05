package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.cws.domain.WorkOrder.WorkOrderStatus;

/**
 * @version 2.0
 *
 */
public class UpdatedWorkOrder implements Command<Void> {

    private WorkOrderDto wDto;
    private WorkOrderRepository repo = Factory.repository.forWorkOrder();

    public UpdatedWorkOrder(WorkOrderDto wDto) {
	this.wDto = wDto;
    }

    @Override
    public Void execute() throws BusinessException {
	WorkOrder workOrder = checkWorkOrder(this.wDto.id);
	checkStatusWorkOrder(workOrder);
	checkDescriptcion(wDto);
	workOrder.setDescription(wDto.description);
	return null;
    }

    private WorkOrder checkWorkOrder(String workOrderId)
	    throws BusinessException {
	Optional<WorkOrder> workOrder = repo.findById(workOrderId);
	BusinessCheck.isTrue(workOrder.isPresent(),
		"The work order does not exist");
	return workOrder.get();

    }

    private void checkStatusWorkOrder(WorkOrder w) throws BusinessException {
	WorkOrderStatus permitedStatus1 = WorkOrder.WorkOrderStatus.OPEN;
	WorkOrderStatus permitedStatus2 = WorkOrder.WorkOrderStatus.ASSIGNED;
	BusinessCheck.isTrue(
		w.getStatus().equals(permitedStatus1)
			|| w.getStatus().equals(permitedStatus2),
		"Cannot edit a " + w.getStatus().toString().toLowerCase()
			+ " work order");

    }

    private void checkDescriptcion(WorkOrderDto wDto) throws BusinessException {
	int minNumberDescription = 4;
	BusinessCheck.isTrue(wDto.description.length() >= minNumberDescription,
		"The description of the work order must contain a minimum of "
			+ minNumberDescription + " characters");
    }

}
