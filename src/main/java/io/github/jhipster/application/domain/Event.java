package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Event.
 */
@Entity
@Table(name = "event")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "start_time")
    private LocalDate startTime;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JsonIgnoreProperties("events")
    private UserGroup group;

    @OneToMany(mappedBy = "event")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Category> categories = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public Event startTime(LocalDate startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public String getName() {
        return name;
    }

    public Event name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserGroup getGroup() {
        return group;
    }

    public Event group(UserGroup userGroup) {
        this.group = userGroup;
        return this;
    }

    public void setGroup(UserGroup userGroup) {
        this.group = userGroup;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Event categories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }

    public Event addCategory(Category category) {
        this.categories.add(category);
        category.setEvent(this);
        return this;
    }

    public Event removeCategory(Category category) {
        this.categories.remove(category);
        category.setEvent(null);
        return this;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
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
        Event event = (Event) o;
        if (event.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), event.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Event{" +
            "id=" + getId() +
            ", startTime='" + getStartTime() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
