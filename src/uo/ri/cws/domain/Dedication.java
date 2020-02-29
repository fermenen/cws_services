package uo.ri.cws.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import alb.util.assertion.Argument;

@Entity
@Table(name = "TDEDICATIONS", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "COURSE_ID", "VEHICLETYPE_ID" }) })
public class Dedication extends BaseEntity {

	private int percentage;
	@ManyToOne
	private Course course;
	@ManyToOne
	private VehicleType vehicleType;

	protected Dedication() {
	}

	protected Dedication(int percentage, VehicleType vehicleType) {
		Argument.isTrue(vehicleType != null);
		Argument.isTrue(percentage >= 0 && percentage <= 100);
		this.percentage = percentage;
		this.vehicleType = vehicleType;
	}

	public Integer getPercentage() {
		return percentage;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public Object getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result
				+ ((vehicleType == null) ? 0 : vehicleType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dedication other = (Dedication) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (vehicleType == null) {
			if (other.vehicleType != null)
				return false;
		} else if (!vehicleType.equals(other.vehicleType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Dedication [porcentage=" + percentage + ", course=" + course
				+ ", vehicleType=" + vehicleType + "]";
	}

}
