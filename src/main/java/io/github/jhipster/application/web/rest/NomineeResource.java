package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Nominee;
import io.github.jhipster.application.repository.NomineeRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Nominee.
 */
@RestController
@RequestMapping("/api")
public class NomineeResource {

    private final Logger log = LoggerFactory.getLogger(NomineeResource.class);

    private static final String ENTITY_NAME = "nominee";

    private final NomineeRepository nomineeRepository;

    public NomineeResource(NomineeRepository nomineeRepository) {
        this.nomineeRepository = nomineeRepository;
    }

    /**
     * POST  /nominees : Create a new nominee.
     *
     * @param nominee the nominee to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nominee, or with status 400 (Bad Request) if the nominee has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nominees")
    @Timed
    public ResponseEntity<Nominee> createNominee(@RequestBody Nominee nominee) throws URISyntaxException {
        log.debug("REST request to save Nominee : {}", nominee);
        if (nominee.getId() != null) {
            throw new BadRequestAlertException("A new nominee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Nominee result = nomineeRepository.save(nominee);
        return ResponseEntity.created(new URI("/api/nominees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nominees : Updates an existing nominee.
     *
     * @param nominee the nominee to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nominee,
     * or with status 400 (Bad Request) if the nominee is not valid,
     * or with status 500 (Internal Server Error) if the nominee couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nominees")
    @Timed
    public ResponseEntity<Nominee> updateNominee(@RequestBody Nominee nominee) throws URISyntaxException {
        log.debug("REST request to update Nominee : {}", nominee);
        if (nominee.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Nominee result = nomineeRepository.save(nominee);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nominee.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nominees : get all the nominees.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of nominees in body
     */
    @GetMapping("/nominees")
    @Timed
    public List<Nominee> getAllNominees() {
        log.debug("REST request to get all Nominees");
        return nomineeRepository.findAll();
    }

    /**
     * GET  /nominees/:id : get the "id" nominee.
     *
     * @param id the id of the nominee to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nominee, or with status 404 (Not Found)
     */
    @GetMapping("/nominees/{id}")
    @Timed
    public ResponseEntity<Nominee> getNominee(@PathVariable Long id) {
        log.debug("REST request to get Nominee : {}", id);
        Optional<Nominee> nominee = nomineeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(nominee);
    }

    /**
     * DELETE  /nominees/:id : delete the "id" nominee.
     *
     * @param id the id of the nominee to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nominees/{id}")
    @Timed
    public ResponseEntity<Void> deleteNominee(@PathVariable Long id) {
        log.debug("REST request to delete Nominee : {}", id);

        nomineeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
