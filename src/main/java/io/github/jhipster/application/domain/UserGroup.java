package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A UserGroup.
 */
@Entity
@Table(name = "user_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "user_group_id",
               joinColumns = @JoinColumn(name = "user_groups_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "ids_id", referencedColumnName = "id"))
    private Set<User> ids = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Event> ids = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public UserGroup name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isActive() {
        return active;
    }

    public UserGroup active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<User> getIds() {
        return ids;
    }

    public UserGroup ids(Set<User> users) {
        this.ids = users;
        return this;
    }

    public UserGroup addId(User user) {
        this.ids.add(user);
        user.getIds().add(this);
        return this;
    }

    public UserGroup removeId(User user) {
        this.ids.remove(user);
        user.getIds().remove(this);
        return this;
    }

    public void setIds(Set<User> users) {
        this.ids = users;
    }

    public Set<Event> getIds() {
        return ids;
    }

    public UserGroup ids(Set<Event> events) {
        this.ids = events;
        return this;
    }

    public UserGroup addId(Event event) {
        this.ids.add(event);
        event.setId(this);
        return this;
    }

    public UserGroup removeId(Event event) {
        this.ids.remove(event);
        event.setId(null);
        return this;
    }

    public void setIds(Set<Event> events) {
        this.ids = events;
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
        UserGroup userGroup = (UserGroup) o;
        if (userGroup.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userGroup.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserGroup{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", active='" + isActive() + "'" +
            "}";
    }
}
