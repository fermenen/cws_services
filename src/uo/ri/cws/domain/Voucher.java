package uo.ri.cws.domain;

import javax.persistence.*;

@Entity
@Table(name = "TVOUCHERS")
public class Voucher extends PaymentMean {
	@Column(unique = true)
	private String code;
	private double available = 0.0;
	private String description;

	public Voucher() {
		super();
	}

	public Voucher(String codigo) {
		this();
		this.code = codigo;
	}

	public Voucher(String codigo, double disponible, String descripcion) {
		this(codigo);
		this.available = disponible;
		this.description = descripcion;
	}

	public Voucher(String codigo, double disponible) {
		this(codigo, disponible, "");
	}

	public String getCodigo() {
		return code;
	}

	public double getDisponible() {
		return available;
	}

	public String getDescripcion() {
		return description;
	}

	public double getAccumulated() {

		return super.getAcumulado();
	}

	@Override
	/**
	 * Augments the accumulated and decrements the available
	 * 
	 * @throws IllegalStateException if not enough available to pay
	 */
	public void pay(double importe) {
		if (available == 0 || importe > available)
			throw new IllegalStateException("No se puede usar este bono");

		super.pay(importe);
		available -= importe;
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
		Voucher other = (Voucher) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bono [codigo=" + code + ", disponible=" + available
				+ ", descripcion=" + description + "]";
	}

	public void setDescripcion(String string) {
		this.description = string;
	}

}
