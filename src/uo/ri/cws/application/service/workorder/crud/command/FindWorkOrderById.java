package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.dto.WorkOrderDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;

public class FindWorkOrderById implements Command<Optional<WorkOrderDto>> {

    private String woId;
    private WorkOrderRepository repo = Factory.repository.forWorkOrder();

    public FindWorkOrderById(String woId) {
	this.woId = woId;
    }

    @Override
    public Optional<WorkOrderDto> execute() throws BusinessException {
	Optional<WorkOrder> workOrder = repo.findById(woId);
	return ((workOrder.isPresent())
		? Optional.of(DtoAssembler.toDto(workOrder.get()))
		: Optional.empty());

    }

}
