package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import alb.util.assertion.Argument;

@Entity
@Table(name = "TCLIENTS")
public class Client extends BaseEntity {

    @Column(unique = true)
    private String dni;
    private String name;
    private String surname;
    private String email;
    private String phone;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "client")
    private Set<Vehicle> vehicles = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private Set<PaymentMean> paymentMeans = new HashSet<>();

    public Client() {
    }

    public Client(String dni) {
	super();
	Argument.isTrue(dni != null);
	this.dni = dni;
    }

    public Client(String dni, String nombre, String apellidos) {
	this(dni);
	Argument.isTrue(nombre != null);
	this.name = nombre;
	Argument.isTrue(apellidos != null);
	this.surname = apellidos;
    }

    public Set<Vehicle> getVehicles() {
	return new HashSet<>(vehicles);
    }

    Set<Vehicle> _getVehicles() {
	return vehicles;
    }

    Set<PaymentMean> _getPaymentMeans() {
	return paymentMeans;
    }

    public Set<PaymentMean> getPaymentMeans() {
	return new HashSet<>(paymentMeans);
    }

    public String getDni() {
	return dni;
    }

    public String getName() {
	return name;
    }

    public String getSurname() {
	return surname;
    }

    public String getEmail() {
	return email;
    }

    public String getPhone() {
	return phone;
    }

    public Address getAddress() {
	return address;
    }

    public void setAddress(Address address) {
	this.address = address;
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
	Client other = (Client) obj;
	if (dni == null) {
	    if (other.dni != null)
		return false;
	} else if (!dni.equals(other.dni))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Client [dni=" + dni + ", name=" + name + ", surname=" + surname
		+ ", email=" + email + ", phone=" + phone + ", address="
		+ address + "]";
    }

}
