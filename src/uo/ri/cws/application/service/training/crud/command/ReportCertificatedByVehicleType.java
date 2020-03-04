package uo.ri.cws.application.service.training.crud.command;

import java.util.List;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.dto.CertificateDto;
import uo.ri.cws.application.util.command.Command;

public class ReportCertificatedByVehicleType implements Command<List<CertificateDto>>{

    @Override
    public List<CertificateDto> execute() throws BusinessException {
	return null;
    }

}
