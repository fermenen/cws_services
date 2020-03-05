package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.List;

import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.cws.domain.WorkOrder.WorkOrderStatus;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class WorkOrderJpaRepository extends BaseJpaRepository<WorkOrder>
	implements WorkOrderRepository {

    @Override
    public List<WorkOrder> findByIds(List<String> idsAveria) {
	return Jpa.getManager()
		.createNamedQuery("WorkOrder.findByIds", WorkOrder.class)
		.setParameter(1, idsAveria).getResultList();
    }

    /**
     * Calculate list work orders by status
     */
    @Override
    public List<WorkOrder> findByStatus(List<WorkOrderStatus> workOrderStatus) {
	return Jpa.getManager()
		.createNamedQuery("WorkOrder.findByStatus", WorkOrder.class)
		.setParameter(1, workOrderStatus).getResultList();
    }

    /**
     * Calculate list work orders by status
     */
    @Override
    public List<WorkOrder> findByVehicle(String vehicleId) {
	return Jpa.getManager()
		.createNamedQuery("WorkOrder.findByVehicle", WorkOrder.class)
		.setParameter(1, vehicleId).getResultList();
    }

}
