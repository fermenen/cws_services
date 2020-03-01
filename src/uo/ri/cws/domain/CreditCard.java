package uo.ri.cws.domain;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "TCREDITCARDS")
public class CreditCard extends PaymentMean {
    @Column(unique = true)
    private String number;
    private String type;
    private Date validThru;

    public CreditCard() {
	super();
    }

    public CreditCard(String numero) {
	this();
	this.number = numero;
    }

    public CreditCard(String numero, String tipo, Date validez) {
	this(numero);
	this.type = tipo;
	this.validThru = validez;
    }

    public String getNumber() {
	return number;
    }

    public String getType() {
	return type;
    }

    public Date getValidThru() {
	return validThru;
    }

    public double getAccumulated() {

	return super.getAcumulado();
    }

    @Override
    public void pay(double importe) {
	if (new Date().after(validThru))
	    throw new IllegalStateException("No se puede usar esta tarjeta");

	super.pay(importe);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((number == null) ? 0 : number.hashCode());
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
	CreditCard other = (CreditCard) obj;
	if (number == null) {
	    if (other.number != null)
		return false;
	} else if (!number.equals(other.number))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "TarjetaCredito [numero=" + number + "]";
    }
}
