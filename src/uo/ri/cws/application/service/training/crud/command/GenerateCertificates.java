package uo.ri.cws.application.service.training.crud.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

public class GenerateCertificates implements Command<Integer> {

    private CertificateRepository repoC = Factory.repository.forCertificate();
    private MechanicRepository repoM = Factory.repository.forMechanic();
    private VehicleTypeRepository repoVt = Factory.repository.forVehicleType();

    @Override
    public Integer execute() throws BusinessException {
	return calculate().size();
    }

    /**
     * Calculate all certificates
     * 
     * @return list certificates
     */
    private List<Certificate> calculate() {
	List<Certificate> listCertificates = new ArrayList<Certificate>();
	for (Mechanic m : repoM.findAll()) {
	    for (VehicleType typeVehicle : repoVt.findAll()) {
		Optional<Long> hours = Optional
			.ofNullable(repoM.calcHoursCAssitTypePassed(m.getId(),
				typeVehicle.getId()));
		if (hours.isPresent()
			&& hours.get() >= typeVehicle.getMinTrainingHours()
			&& !checkCertificateExist(m, typeVehicle)) {
		    Certificate certificado = new Certificate(m, typeVehicle);
		    repoC.add(certificado);
		    listCertificates.add(certificado);
		}
	    }
	}
	return listCertificates;
    }

    /**
     * Compruebo si el certificado ya existe en el sistema
     * 
     * @param cNew
     * @return
     */
    private boolean checkCertificateExist(Mechanic m, VehicleType typeV) {
	for (Certificate cBd : repoC.findAll())
	    if (cBd.getMechanic().equals(m)
		    && cBd.getVehicletype().equals(typeV))
		return true;
	return false;
    }

}
