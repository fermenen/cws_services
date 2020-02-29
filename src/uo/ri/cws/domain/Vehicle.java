package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import alb.util.assertion.Argument;

@Entity
@Table(name = "TVEHICLES")
public class Vehicle extends BaseEntity {

	@Column(unique = true)
	private String plateNumber;
	private String make;
	private String model;
	@ManyToOne
	private Client client = null;
	@ManyToOne
	@JoinColumn(name = "VEHICLETYPE_ID")
	private VehicleType type;

	@OneToMany(mappedBy = "vehicle")
	private Set<WorkOrder> workOrders = new HashSet<>();

	public Vehicle() {
	}

	public Vehicle(String plateNumber) {
		super();
		Argument.isTrue(plateNumber != null);
		this.plateNumber = plateNumber;
	}

	public Vehicle(String matrcula, String make, String modelo) {
		this(matrcula);
		Argument.isTrue(make != null);
		this.make = make;
		Argument.isTrue(modelo != null);
		this.model = modelo;

	}

	Set<WorkOrder> _getWorkOrders() {
		return workOrders;
	}

	public Set<WorkOrder> getWorkOrders() {
		return new HashSet<WorkOrder>(workOrders);
	}

	public VehicleType getVehicleType() {
		return type;
	}

	void _setType(VehicleType type) {
		this.type = type;
	}

	void _setClient(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return this.client;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public String getPlate() {
		return getPlateNumber();
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((plateNumber == null) ? 0 : plateNumber.hashCode());
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
		Vehicle other = (Vehicle) obj;
		if (plateNumber == null) {
			if (other.plateNumber != null)
				return false;
		} else if (!plateNumber.equals(other.plateNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehicle [plateNumber=" + plateNumber + ", make=" + make
				+ ", model=" + model + "]";
	}

}
