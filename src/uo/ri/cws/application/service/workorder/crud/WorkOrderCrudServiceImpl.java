package uo.ri.cws.application.service.workorder.crud;

import java.util.List;
import java.util.Optional;
import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderCrudService;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.service.workorder.crud.command.FindWorkOrderById;
import uo.ri.cws.application.service.workorder.crud.command.RegisterNewWorkOder;
import uo.ri.cws.application.service.workorder.crud.command.RemoveWorkOrder;
import uo.ri.cws.application.service.workorder.crud.command.UpdatedWorkOrder;
import uo.ri.cws.application.util.command.CommandExecutor;

public class WorkOrderCrudServiceImpl implements WorkOrderCrudService {

    private CommandExecutor executor = Factory.executor.forExecutor();

    @Override
    public WorkOrderDto registerNew(WorkOrderDto dto) throws BusinessException {
	return executor.execute(new RegisterNewWorkOder(dto));
    }

    @Override
    public void updateWorkOrder(WorkOrderDto dto) throws BusinessException {
	executor.execute(new UpdatedWorkOrder(dto));
    }

    @Override
    public void deleteWorkOrder(String id) throws BusinessException {
	executor.execute(new RemoveWorkOrder(id));
    }

    @Override
    public Optional<WorkOrderDto> findWorkOrderById(String woId)
	    throws BusinessException {
	return executor.execute(new FindWorkOrderById(woId));
    }

    @Override
    public List<WorkOrderDto> findWorkOrdersByVehicleId(String id)
	    throws BusinessException {
	//Auto-generated method stub
	return null;
    }

    @Override
    public List<WorkOrderDto> findWorkOrdersByPlateNumber(String plate)
	    throws BusinessException {
	//Auto-generated method stub
	return null;
    }

}
