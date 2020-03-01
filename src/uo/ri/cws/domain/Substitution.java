package uo.ri.cws.domain;

import javax.persistence.*;

@Entity
@Table(name = "TSUBSTITUTIONS", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "SPAREPART_ID",
		"INTERVENTION_ID" }) })
public class Substitution extends BaseEntity {

    @ManyToOne
    private SparePart sparePart;
    @ManyToOne
    private Intervention intervention;
    private int quantity;

    public Substitution() {
    }

    public Substitution(SparePart repuesto, Intervention intervencion) {
	Associations.Sustitute.link(repuesto, this, intervencion);
    }

    public Substitution(SparePart repuesto, Intervention intervencion,
	    int cantidad) {
	this(repuesto, intervencion);

	if (cantidad <= 0) {
	    Associations.Sustitute.unlink(this);
	    throw new IllegalArgumentException("Hace falta algun repuesto");
	}

	this.quantity = cantidad;
    }

    public SparePart getSparePart() {
	return sparePart;
    }

    public Intervention getIntervention() {
	return intervention;
    }

    public int getCantidad() {
	return quantity;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((intervention == null) ? 0 : intervention.hashCode());
	result = prime * result
		+ ((sparePart == null) ? 0 : sparePart.hashCode());
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
	Substitution other = (Substitution) obj;
	if (intervention == null) {
	    if (other.intervention != null)
		return false;
	} else if (!intervention.equals(other.intervention))
	    return false;
	if (sparePart == null) {
	    if (other.sparePart != null)
		return false;
	} else if (!sparePart.equals(other.sparePart))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Sustitucion [repuesto=" + sparePart + ", intervencion="
		+ intervention + ", cantidad=" + quantity + "]";
    }

    void _setRepuesto(SparePart repuesto) {
	this.sparePart = repuesto;
    }

    void _setIntervencion(Intervention intervencion) {
	this.intervention = intervencion;
    }

    public double getImporte() {
	return sparePart.getPrice() * quantity;
    }

}
