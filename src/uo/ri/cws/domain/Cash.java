package uo.ri.cws.domain;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "TCASHES")
public class Cash extends PaymentMean {

    public Cash() {
	super("Cash");
    }

    public Cash(Client cliente) {
	this();
	Associations.Pay.link(this, cliente);
    }

    public double getAccumulated() {

	return super.getAcumulado();
    }

    protected Set<Charge> _getCharges() {
	return super._getCharges();
    }

    @Override
    public String toString() {
	return "Metalico [toString()=" + super.toString() + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((client == null) ? 0 : client.hashCode());
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
	PaymentMean other = (PaymentMean) obj;
	if (client == null) {
	    if (other.client != null)
		return false;
	} else if (!client.equals(other.client))
	    return false;
	return true;
    }

}
