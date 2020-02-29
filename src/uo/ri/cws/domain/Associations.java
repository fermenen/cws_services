package uo.ri.cws.domain;

public class Associations {

    public static class Own {

	public static void link(Client c, Vehicle v) {
	    v._setClient(c);
	    c._getVehicles().add(v);
	}

	public static void unlink(Client c, Vehicle v) {
	    c._getVehicles().remove(v);
	    v._setClient(null);
	}

    }

    public static class Classify {
	public static void link(VehicleType t, Vehicle v) {
	    v._setType(t);
	    t._getVehicles().add(v);
	}

	public static void unlink(VehicleType t, Vehicle v) {
	    t._getVehicles().remove(v);
	    v._setType(null);
	}

    }

    public static class Pay {
	public static void link(PaymentMean medioPago, Client cliente) {
	    medioPago._setCliente(cliente);
	    cliente._getPaymentMeans().add(medioPago);
	}

	public static void unlink(Client cliente, PaymentMean medioPago) {
	    cliente._getPaymentMeans().remove(medioPago);
	    medioPago._setCliente(null);
	}
    }

    public static class Order {

	public static void link(Vehicle vehicle, WorkOrder workOrder) {
	    workOrder._setVehicle(vehicle);
	    vehicle._getWorkOrders().add(workOrder);

	}

	public static void unlink(Vehicle vehicle, WorkOrder workOrder) {
	    vehicle._getWorkOrders().remove(workOrder);
	    workOrder._setVehicle(null);

	}

    }

    public static class ToInvoice {

	public static void link(WorkOrder averia, Invoice factura) {
	    averia._setFactura(factura);
	    factura._getWorkOrders().add(averia);
	}

	public static void unlink(WorkOrder averia, Invoice factura) {
	    factura._getWorkOrders().remove(averia);
	    averia._setFactura(null);
	}
    }

    public static class Charges {
	public static void link(Invoice factura, Charge cargo,
		PaymentMean medioPago) {
	    cargo._setFactura(factura);
	    cargo._setMedioPago(medioPago);

	    factura._getCharges().add(cargo);
	    medioPago._getCharges().add(cargo);
	}

	public static void unlink(Charge cargo) {
	    cargo.getInvoice()._getCharges().remove(cargo);
	    cargo.getPaymentMean()._getCharges().remove(cargo);

	    cargo._setFactura(null);
	    cargo._setMedioPago(null);
	}
    }

    public static class Assign {
	public static void link(Mechanic mecanico, WorkOrder workOrder) {
	    workOrder._setMecanico(mecanico);
	    mecanico._getWorkOrders().add(workOrder);

	}

	public static void unlink(Mechanic mecanico, WorkOrder workOrder) {
	    mecanico._getWorkOrders().remove(workOrder);
	    workOrder._setMecanico(null);

	}

    }

    public static class Intervene {

	public static void link(WorkOrder averia, Intervention intervencion,
		Mechanic mecanico) {
	    intervencion._setAveria(averia);
	    intervencion._setMecanico(mecanico);

	    averia._getIntervenciones().add(intervencion);
	    mecanico._getInterventions().add(intervencion);
	}

	public static void unlink(Intervention intervencion) {
	    intervencion.getWorkOrder()._getIntervenciones()
		    .remove(intervencion);
	    intervencion.getMechanic()._getInterventions().remove(intervencion);

	    intervencion._setAveria(null);
	    intervencion._setMecanico(null);
	}
    }

    public static class Sustitute {
	public static void link(SparePart repuesto, Substitution sustitucion,
		Intervention intervencion) {
	    sustitucion._setRepuesto(repuesto);
	    sustitucion._setIntervencion(intervencion);

	    repuesto._getSustituciones().add(sustitucion);
	    intervencion._getSustitutions().add(sustitucion);
	}

	public static void unlink(Substitution sustitucion) {
	    sustitucion.getIntervention()._getSustitutions()
		    .remove(sustitucion);
	    sustitucion.getSparePart()._getSustituciones().remove(sustitucion);

	    sustitucion._setIntervencion(null);
	    sustitucion._setRepuesto(null);
	}
    }

    public static class Certificar {
	public static void link(Mechanic mechanic, Certificate certificate) {
	    certificate._setMechanic(mechanic);
	    mechanic._getCertificates().add(certificate);

	}

	public static void unlink(Mechanic mechanic, Certificate certificate) {
	    mechanic._getCertificates().remove(certificate);
	    certificate._setMechanic(null);
	}

    }

    public static class Enroll {
	public static void link(Mechanic mechanic, Enrollment enrollment) {
	    enrollment._setMechanic(mechanic);
	    mechanic._getEnrollments().add(enrollment);
	}

	public static void unlink(Mechanic mechanic, Enrollment enrollment) {
	    mechanic._getEnrollments().remove(enrollment);
	    enrollment._setMechanic(null);
	}

    }

    public static class AsDedication {
	public static void link(Course course, Dedication dedication) {
	    dedication.setCourse(course);
	    course.getDedications().add(dedication);
	}

	public static void unlink(Course course, Dedication dedication) {
	    course.getDedications().remove(dedication);
	    dedication.setCourse(null);
	}

    }

}
