package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "TSPAREPARTS")
public class SparePart extends BaseEntity {

    @Column(unique = true)
    private String code;
    private String description;
    private double price;

    @OneToMany(mappedBy = "SparePart")
    private Set<Substitution> sustituciones = new HashSet<Substitution>();

    public SparePart() {
    }

    public SparePart(String codigo) {
	super();
	this.code = codigo;
    }

    public SparePart(String codigo, String descripcion, double precio) {
	this(codigo);
	this.description = descripcion;
	this.price = precio;
    }

    public String getCodigo() {
	return code;
    }

    public String getDescripcion() {
	return description;
    }

    public double getPrice() {
	return price;
    }

    Set<Substitution> _getSustituciones() {
	return sustituciones;
    }

    public Set<Substitution> getSustituciones() {
	return new HashSet<>(sustituciones);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((code == null) ? 0 : code.hashCode());
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
	SparePart other = (SparePart) obj;
	if (code == null) {
	    if (other.code != null)
		return false;
	} else if (!code.equals(other.code))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Repuesto [codigo=" + code + ", descripcion=" + description
		+ ", precio=" + price + "]";
    }

}
