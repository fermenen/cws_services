package uo.ri.cws.application.repository;

import java.util.List;

import uo.ri.cws.domain.WorkOrder;
import uo.ri.cws.domain.WorkOrder.WorkOrderStatus;

public interface WorkOrderRepository extends Repository<WorkOrder> {

    /**
     * @param List workOrders id's for the search
     * @return List of work orders that fulfill that request
     * 
     */
    List<WorkOrder> findByIds(List<String> workOrderIds);

    /**
     * @param List Status for the search
     * @return List of work orders that fulfill that status
     * 
     */
    List<WorkOrder> findByStatus(List<WorkOrderStatus> workOrderStatus);
}