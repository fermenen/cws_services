package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Class abstract Payment Means
 * @version 2.0 - revised after correction
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TPAYMENTMEANS")
public abstract class PaymentMean extends BaseEntity {
	private double accumulated = 0.0;
	@ManyToOne
	protected Client client;
	@OneToMany(mappedBy = "paymentMean")
	protected Set<Charge> cargos = new HashSet<>();

	public PaymentMean() {
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
