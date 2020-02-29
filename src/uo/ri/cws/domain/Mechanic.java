package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import alb.util.assertion.Argument;

@Entity
@Table(name = "TMECHANICS")
public class Mechanic extends BaseEntity {

    @Column(unique = true)
    private String dni;
    private String surname;
    private String name;

    @OneToMany(mappedBy = "Mechanic")
    private Set<WorkOrder> workOrders = new HashSet<WorkOrder>();
    @OneToMany(mappedBy = "Mechanic")
    private Set<Intervention> intervenciones = new HashSet<>();
    @OneToMany(mappedBy = "Mechanic")
    private Set<Certificate> certificates = new HashSet<>();
    @OneToMany(mappedBy = "mechanic")
    private Set<Enrollment> enrollments = new HashSet<>();

    Mechanic() {
    }

    public Mechanic(String dni) {
	super();
	Argument.isTrue(dni != null);
	this.dni = dni;
    }

    public Mechanic(String dni, String nombre, String apellidos) {
	this(dni);
	Argument.isTrue(nombre != null);
	this.name = nombre;
	Argument.isTrue(apellidos != null);
	this.surname = apellidos;

    }

    public String getDni() {
	return dni;
    }

    public String getSurname() {
	return surname;
    }

    public void setSurname(String surName) {
	this.surname = surName;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Set<WorkOrder> getAssigned() {
	return new HashSet<>(workOrders);
    }

    Set<WorkOrder> _getWorkOrders() {
	return workOrders;
    }

    protected Set<Intervention> _getInterventions() {
	return intervenciones;
    }

    protected Set<Enrollment> _getEnrollments() {
	return enrollments;
    }

    public Set<Intervention> getInterventions() {
	return new HashSet<>(intervenciones);
    }

    public Set<Certificate> getCertificates() {
	return new HashSet<>(certificates);
    }

    protected Set<Certificate> _getCertificates() {
	return certificates;
    }

    /**
     * Calcula todas las asistencias a cursos por tipo de vehiculo
     * 
     * @param type_vehicle
     * @return Set_Enrollment
     */
    public Set<Enrollment> getEnrollmentsFor(VehicleType type) {
	Set<Enrollment> enroll = new HashSet<>();
	for (Enrollment e : enrollments) {
	    for (Dedication d : e.getCourse().getDedications()) {
		if (d.getVehicleType().equals(type))
		    enroll.add(e);
	    }
	}
	return enroll;
    }

    public boolean isCertifiedFor(VehicleType type) {
	if (type != null) {
	    for (Certificate c : certificates) {
		if (c.getVehicletype().getName().equals(type.getName()))
		    return true;
	    }
	}
	return false;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((dni == null) ? 0 : dni.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Mechanic other = (Mechanic) obj;
	if (dni == null) {
	    if (other.dni != null)
		return false;
	} else if (!dni.equals(other.dni))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Mechanic [dni=" + dni + ", surname=" + surname + ", name="
		+ name + "]";
    }

}
