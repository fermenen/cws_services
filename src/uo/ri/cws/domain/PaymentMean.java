package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TPAYMENTMEANS")
public abstract class PaymentMean extends BaseEntity {
    private double accumulated = 0.0;
    @Column(name = "DTYPE")
    private String type;
    @ManyToOne
    protected Client client;
    @OneToMany(mappedBy = "paymentMean")
    protected Set<Charge> cargos = new HashSet<>();

    public PaymentMean(String type) {
	this.type = type;
    }

    public void pay(double importe) {
	this.accumulated += importe;
    }

    public Client getClient() {
	return client;
    }

    void _setCliente(Client cliente) {
	this.client = cliente;
    }

    public void setAcumulado(double acumulado) {
	this.accumulated = acumulado;
    }

    Set<Charge> _getCharges() {
	return cargos;
    }

    public Set<Charge> getCharges() {
	return new HashSet<>(cargos);
    }

    public double getAcumulado() {
	return accumulated;
    }

    @Override
    public String toString() {
	return "MedioPago [acumulado=" + accumulated + ", cliente=" + client
		+ "]";
    }
}
