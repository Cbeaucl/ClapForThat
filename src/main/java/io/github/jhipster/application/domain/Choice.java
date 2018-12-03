package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Choice.
 */
@Entity
@Table(name = "choice")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Choice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Nominee> ids = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("")
    private User id;

    @ManyToOne
    @JsonIgnoreProperties("ids")
    private Category id;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Nominee> getIds() {
        return ids;
    }

    public Choice ids(Set<Nominee> nominees) {
        this.ids = nominees;
        return this;
    }

    public Choice addId(Nominee nominee) {
        this.ids.add(nominee);
        nominee.setId(this);
        return this;
    }

    public Choice removeId(Nominee nominee) {
        this.ids.remove(nominee);
        nominee.setId(null);
        return this;
    }

    public void setIds(Set<Nominee> nominees) {
        this.ids = nominees;
    }

    public User getId() {
        return id;
    }

    public Choice id(User user) {
        this.id = user;
        return this;
    }

    public void setId(User user) {
        this.id = user;
    }

    public Category getId() {
        return id;
    }

    public Choice id(Category category) {
        this.id = category;
        return this;
    }

    public void setId(Category category) {
        this.id = category;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Choice choice = (Choice) o;
        if (choice.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), choice.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Choice{" +
            "id=" + getId() +
            "}";
    }
}
