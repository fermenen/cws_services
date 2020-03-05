package uo.ri.cws.application.service.workorder.crud.command;

import java.util.ArrayList;
import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.cws.domain.WorkOrder.WorkOrderStatus;

/**
 * Work orders either in the OPEN or ASSIGN status
 * 
 * @version 2.0
 *
 */
public class FindUnfinishedWorkOrders implements Command<List<WorkOrderDto>> {

    private WorkOrderRepository repoWorkOrder = Factory.repository
	    .forWorkOrder();

    @Override
    public List<WorkOrderDto> execute() throws BusinessException {

	List<WorkOrderStatus> listStatusWorkOrder = new ArrayList<WorkOrderStatus>();
	listStatusWorkOrder.add(WorkOrderStatus.OPEN);
	listStatusWorkOrder.add(WorkOrderStatus.ASSIGNED);
	List<WorkOrder> listWorkOrder = repoWorkOrder
		.findByStatus(listStatusWorkOrder);
	return DtoAssembler.toWorkOrderDtoList(listWorkOrder);
    }

}
