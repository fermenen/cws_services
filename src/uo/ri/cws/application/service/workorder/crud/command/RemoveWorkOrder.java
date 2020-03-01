package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;

/**
 * @version 2.0
 *
 */
public class RemoveWorkOrder implements Command<Void> {

    private String workOrderId;
    private WorkOrderRepository repo = Factory.repository.forWorkOrder();

    public RemoveWorkOrder(String workOrderId) {
	this.workOrderId = workOrderId;
    }

    @Override
    public Void execute() throws BusinessException {
	BusinessCheck.isTrue(this.workOrderId != null);
	WorkOrder workOrder = checkWorkOrder(this.workOrderId);
	checkDeleteWorkOrder(workOrder);
	repo.remove(workOrder);
	return null;
    }


    private WorkOrder checkWorkOrder(String workOrderId)
	    throws BusinessException {
	Optional<WorkOrder> workOrder = repo.findById(workOrderId);
	BusinessCheck.isTrue(workOrder.isPresent(),
		"The work order does not exist");
	return workOrder.get();

    }

    private void checkDeleteWorkOrder(WorkOrder w) throws BusinessException {
	BusinessCheck.isTrue(w.getInterventions().isEmpty(),
		"This work order cannot be delated, have interventions ");
    }

}
