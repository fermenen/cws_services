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

/**
 * Certificate generation
 *
 */
public class GenerateCertificates implements Command<Integer> {

    private CertificateRepository repoCertificates = Factory.repository
	    .forCertificate();
    private MechanicRepository repoMechanic = Factory.repository.forMechanic();
    private VehicleTypeRepository repoVehiclesType = Factory.repository
	    .forVehicleType();

    @Override
    public Integer execute() throws BusinessException {
	List<Certificate> listCertificates = calculateCertificates();
	return listCertificates.size();
    }

    /**
     * Calculate all certificates
     * 
     * @return list of certificates
     */
    private List<Certificate> calculateCertificates() {
	List<Certificate> listCertificates = new ArrayList<Certificate>();
	for (Mechanic mechanic : repoMechanic.findAll()) {
	    for (VehicleType typeVehicle : repoVehiclesType.findAll()) {
		Optional<Long> hours = Optional
			.ofNullable(repoMechanic.assistedHoursByTypeAndPassed(
				mechanic.getId(), typeVehicle.getId()));
		if (hours.isPresent()
			&& hours.get() >= typeVehicle.getMinTrainingHours()
			&& !checkCertificateExist(mechanic, typeVehicle)) {
		    Certificate certificado = new Certificate(mechanic,
			    typeVehicle);
		    repoCertificates.add(certificado);
		    listCertificates.add(certificado);
		}
	    }
	}
	return listCertificates;
    }

    /**
     * Check if the certificate already exists in the system
     * 
     * @param mechanic
     * @param cNew
     * @return
     */
    private boolean checkCertificateExist(Mechanic mechanic,
	    VehicleType typeV) {
	for (Certificate cBd : repoCertificates.findAll())
	    if (cBd.getMechanic().equals(mechanic)
		    && cBd.getVehicletype().equals(typeV))
		return true;
	return false;
    }

}
