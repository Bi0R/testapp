package com.nojsoft.model;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by alan on 1/14/17.
 */
@Entity
@Table(name = "groups")
public class Group implements BaseModel {

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", length = 60)
    private String name;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Transient
    private List<User> requesters;
    @Transient
    private List<User> participants;

    public Group() {
    }

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public List<User> getRequesters() {
        return requesters;
    }

    public void setRequesters(List<User> requesters) {
        this.requesters = requesters;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
}
