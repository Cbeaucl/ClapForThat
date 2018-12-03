package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.ClapForThatApp;

import io.github.jhipster.application.domain.Nominee;
import io.github.jhipster.application.repository.NomineeRepository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the NomineeResource REST controller.
 *
 * @see NomineeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClapForThatApp.class)
public class NomineeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private NomineeRepository nomineeRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNomineeMockMvc;

    private Nominee nominee;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NomineeResource nomineeResource = new NomineeResource(nomineeRepository);
        this.restNomineeMockMvc = MockMvcBuilders.standaloneSetup(nomineeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Nominee createEntity(EntityManager em) {
        Nominee nominee = new Nominee()
            .name(DEFAULT_NAME);
        return nominee;
    }

    @Before
    public void initTest() {
        nominee = createEntity(em);
    }

    @Test
    @Transactional
    public void createNominee() throws Exception {
        int databaseSizeBeforeCreate = nomineeRepository.findAll().size();

        // Create the Nominee
        restNomineeMockMvc.perform(post("/api/nominees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nominee)))
            .andExpect(status().isCreated());

        // Validate the Nominee in the database
        List<Nominee> nomineeList = nomineeRepository.findAll();
        assertThat(nomineeList).hasSize(databaseSizeBeforeCreate + 1);
        Nominee testNominee = nomineeList.get(nomineeList.size() - 1);
        assertThat(testNominee.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createNomineeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nomineeRepository.findAll().size();

        // Create the Nominee with an existing ID
        nominee.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNomineeMockMvc.perform(post("/api/nominees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nominee)))
            .andExpect(status().isBadRequest());

        // Validate the Nominee in the database
        List<Nominee> nomineeList = nomineeRepository.findAll();
        assertThat(nomineeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNominees() throws Exception {
        // Initialize the database
        nomineeRepository.saveAndFlush(nominee);

        // Get all the nomineeList
        restNomineeMockMvc.perform(get("/api/nominees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nominee.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getNominee() throws Exception {
        // Initialize the database
        nomineeRepository.saveAndFlush(nominee);

        // Get the nominee
        restNomineeMockMvc.perform(get("/api/nominees/{id}", nominee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nominee.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNominee() throws Exception {
        // Get the nominee
        restNomineeMockMvc.perform(get("/api/nominees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNominee() throws Exception {
        // Initialize the database
        nomineeRepository.saveAndFlush(nominee);

        int databaseSizeBeforeUpdate = nomineeRepository.findAll().size();

        // Update the nominee
        Nominee updatedNominee = nomineeRepository.findById(nominee.getId()).get();
        // Disconnect from session so that the updates on updatedNominee are not directly saved in db
        em.detach(updatedNominee);
        updatedNominee
            .name(UPDATED_NAME);

        restNomineeMockMvc.perform(put("/api/nominees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNominee)))
            .andExpect(status().isOk());

        // Validate the Nominee in the database
        List<Nominee> nomineeList = nomineeRepository.findAll();
        assertThat(nomineeList).hasSize(databaseSizeBeforeUpdate);
        Nominee testNominee = nomineeList.get(nomineeList.size() - 1);
        assertThat(testNominee.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingNominee() throws Exception {
        int databaseSizeBeforeUpdate = nomineeRepository.findAll().size();

        // Create the Nominee

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNomineeMockMvc.perform(put("/api/nominees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nominee)))
            .andExpect(status().isBadRequest());

        // Validate the Nominee in the database
        List<Nominee> nomineeList = nomineeRepository.findAll();
        assertThat(nomineeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNominee() throws Exception {
        // Initialize the database
        nomineeRepository.saveAndFlush(nominee);

        int databaseSizeBeforeDelete = nomineeRepository.findAll().size();

        // Get the nominee
        restNomineeMockMvc.perform(delete("/api/nominees/{id}", nominee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Nominee> nomineeList = nomineeRepository.findAll();
        assertThat(nomineeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Nominee.class);
        Nominee nominee1 = new Nominee();
        nominee1.setId(1L);
        Nominee nominee2 = new Nominee();
        nominee2.setId(nominee1.getId());
        assertThat(nominee1).isEqualTo(nominee2);
        nominee2.setId(2L);
        assertThat(nominee1).isNotEqualTo(nominee2);
        nominee1.setId(null);
        assertThat(nominee1).isNotEqualTo(nominee2);
    }
}
