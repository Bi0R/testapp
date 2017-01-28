package com.nojsoft.model;

import com.nojsoft.dao.GroupDao;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by jorge on 22/01/17.
 */
@Entity
@Table(name = "users")
public class User implements BaseModel {
    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", length = 60)
    private String name;

    @Column(name = "access_key", length = 60)
    private String accessKey;

    @Column(name = "token", length = 60)
    private String token;

    @Column(name = "authentication_type")
    private Integer authenticationType;


    private List<Group> groups;

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

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(Integer authenticationType) {
        this.authenticationType = authenticationType;
    }

    public List<Group> getGroups() {
        if (groups == null){
            GroupDao groupDao = new GroupDao();
            groups = groupDao.getGroupsByOwner(id);
        }
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
