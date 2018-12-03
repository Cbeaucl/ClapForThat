package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Choice;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Choice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {

    @Query("select choice from Choice choice where choice.user.login = ?#{principal.username}")
    List<Choice> findByUserIsCurrentUser();

}
