package uo.ri.cws.application.repository;

import java.util.List;
import uo.ri.cws.domain.Certificate;

public interface CertificateRepository extends Repository<Certificate> {

    /**
     * @return a list with all certificates (might be empty)
     */
    List<Certificate> findAll();

}
