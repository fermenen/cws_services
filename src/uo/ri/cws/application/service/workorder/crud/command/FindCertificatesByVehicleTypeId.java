package uo.ri.cws.application.service.workorder.crud.command;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CertificateDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.VehicleType;

/**
 * List of certificates for the vehicle type.
 * 
 * @version 2.0
 *
 */
public class FindCertificatesByVehicleTypeId
	implements Command<List<CertificateDto>> {

    CertificateRepository repoCertificates = Factory.repository
	    .forCertificate();
    VehicleTypeRepository repoVehicleType = Factory.repository.forVehicleType();
    private String vehicleTypeId;

    public FindCertificatesByVehicleTypeId(String vehicleTypeId) {
	super();
	this.vehicleTypeId = vehicleTypeId;
    }

    @Override
    public List<CertificateDto> execute() throws BusinessException {
	VehicleType vehicleType = checkVehicle(this.vehicleTypeId);
	List<Certificate> listCertificates = repoCertificates
		.findByVehicleType(vehicleType.getId());
	return DtoAssembler.toCertificateDtoList(listCertificates);
    }

    private VehicleType checkVehicle(String vehicleTypeId)
	    throws BusinessException {
	Optional<VehicleType> vt = repoVehicleType.findById(vehicleTypeId);
	BusinessCheck.isTrue(vt.isPresent(),
		"The type vehicle is not in the system");
	return vt.get();
    }

}
