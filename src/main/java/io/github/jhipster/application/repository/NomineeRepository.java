package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Nominee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Nominee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NomineeRepository extends JpaRepository<Nominee, Long> {

}
