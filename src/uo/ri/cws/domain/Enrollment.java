package uo.ri.cws.domain;

import javax.persistence.*;

import alb.util.assertion.Argument;

@Entity
@Table(name = "TENROLLMENTS", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "COURSE_ID", "MECHANIC_ID" }) })
public class Enrollment extends BaseEntity {

    private Course course;
    @ManyToOne
    private Mechanic mechanic;
    private int attendance;
    private boolean passed;

    Enrollment() {
    }

    public Enrollment(Course course, Mechanic mechanic, int attendance,
	    boolean passed) {
	super();
	Argument.isTrue(mechanic != null);
	this.course = course;
	Argument.isTrue(mechanic != null);
	this.attendance = attendance;
	if (passed)
	    Argument.isTrue(attendance >= 85);
	this.passed = passed;
	Associations.Enroll.link(mechanic, this);
    }

    public Enrollment(Mechanic mechanic, Course course, int attendance,
	    boolean passed) {
	this(course, mechanic, attendance, passed);
    }

    public Course getCourse() {
	return course;
    }

    public Mechanic getMechanic() {
	return mechanic;
    }

    protected void _setMechanic(Mechanic mechanic) {
	this.mechanic = mechanic;
    }

    public int getAttendance() {
	return attendance;
    }

    public boolean isPassed() {
	return passed;
    }

    public int getAttendedHoursFor(VehicleType type) {
	int hoursTotal = 0, hoursAttend = 0;
	for (Dedication d : course.getDedications()) {
	    if (d.getVehicleType().equals(type)) {
		hoursTotal += (course.getHours() * d.getPercentage() / 100);
		hoursAttend += (hoursTotal * attendance) / 100;
	    }
	}
	return hoursAttend;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((course == null) ? 0 : course.hashCode());
	result = prime * result
		+ ((mechanic == null) ? 0 : mechanic.hashCode());
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
	Enrollment other = (Enrollment) obj;
	if (course == null) {
	    if (other.course != null)
		return false;
	} else if (!course.equals(other.course))
	    return false;
	if (mechanic == null) {
	    if (other.mechanic != null)
		return false;
	} else if (!mechanic.equals(other.mechanic))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Enrollment [course=" + course + ", mechanic=" + mechanic
		+ ", attendance=" + attendance + ", passed=" + passed + "]";
    }

}
