package uo.ri.cws.application.service.training.crud.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CertificateDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Certificate;

/**
 * Qualified mechanics by type of vehicle
 *
 */
public class ReportCertificatedByVehicleType
	implements Command<List<CertificateDto>> {

    @Override
    public List<CertificateDto> execute() throws BusinessException {
	return calculateCertificates();
    }

    /**
     * @return List of CertificateDto
     */
    private List<CertificateDto> calculateCertificates() {

	List<Certificate> listCertificates = Factory.repository.forCertificate()
		.findAll();
	List<CertificateDto> listCertificatesDtos = DtoAssembler
		.toCertificateDtoList(listCertificates);
	return listCertificatesDtos;

    }

}
