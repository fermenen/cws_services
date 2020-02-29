package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import alb.util.assertion.Argument;

@Entity
@Table(name = "TVEHICLETYPES")
public class VehicleType extends BaseEntity {

    @Column(unique = true)
    private String name;
    private double pricePerHour;
    private int minTrainingHours;
    @OneToMany(mappedBy = "type")
    private Set<Vehicle> vehiculos = new HashSet<>();
    @OneToMany(mappedBy = "vehicleType")
    private Set<Dedication> dedications = new HashSet<>();

    VehicleType() {
    }

    public VehicleType(String name) {
	super();
	Argument.isTrue(name != null);
	this.name = name;
    }

    public VehicleType(String name, double precio) {
	this(name);
	this.pricePerHour = precio;
    }

    public String getName() {
	return name;
    }

    public String getNombre() {
	return getName();
    }

    public double getPricePerHour() {
	return pricePerHour;
    }

    Set<Vehicle> _getVehicles() {
	return vehiculos;
    }

    public Set<Vehicle> getVehicles() {
	return new HashSet<Vehicle>(vehiculos);
    }

    public int getMinTrainingHours() {
	return minTrainingHours;
    }

    public Set<Dedication> getDedications() {
	return new HashSet<Dedication>(dedications);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
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
	VehicleType other = (VehicleType) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "VehicleType [name=" + name + ", pricePerHour=" + pricePerHour
		+ "]";
    }

}
