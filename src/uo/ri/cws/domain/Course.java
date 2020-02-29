package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import alb.util.assertion.Argument;

@Entity
@Table(name = "TCOURSES")
public class Course extends BaseEntity {

    @Column(unique = true)
    private String code;
    private String name;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private int hours;

    @OneToMany(mappedBy = "course")
    private Set<Dedication> dedications = new HashSet<>();

    Course() {
    }

    public Course(String code) {
	super();
	Argument.isTrue(code != null && !code.isEmpty());
	this.code = code;
    }

    public Course(String code, String name, String description, Date startDate,
	    Date endDate, int duration) {
	this(code);
	Argument.isTrue(!name.isEmpty());
	this.name = name;
	Argument.isTrue(!description.isEmpty());
	this.description = description;
	Argument.isTrue(startDate != null);
	this.startDate = new Date(startDate.getTime());
	Argument.isTrue(endDate != null);
	Argument.isTrue(endDate.getTime() > startDate.getTime());
	this.endDate = new Date(endDate.getTime());
	Argument.isTrue(duration > 0);
	this.hours = duration;

    }

    public String getCode() {
	return code;
    }

    public String getName() {
	return name;
    }

    public String getDescription() {
	return description;
    }

    public Date getStartDate() {
	return new Date(startDate.getTime());
    }

    public Date getEndDate() {
	return new Date(endDate.getTime());
    }

    public Set<Dedication> getDedications() {
	return dedications;
    }

    public int getHours() {
	return hours;
    }

    public void addDedications(Map<VehicleType, Integer> percentages) {
	if (!getDedications().isEmpty())
	    throw new IllegalStateException("Ya esta registrado al curso");
	int por = 0;
	for (Integer u : percentages.values())
	    por += u;
	Argument.isTrue(por == 100);
	for (VehicleType type : percentages.keySet()) {
	    Dedication d = new Dedication(percentages.get(type), type);
	    Associations.AsDedication.link(this, d);
	}
    }

    public void clearDedications() {
	dedications.clear();
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((code == null) ? 0 : code.hashCode());
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
	Course other = (Course) obj;
	if (code == null) {
	    if (other.code != null)
		return false;
	} else if (!code.equals(other.code))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Course [code=" + code + ", name=" + name + ", description="
		+ description + ", hours=" + hours + "]";
    }

}
