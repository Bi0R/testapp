package com.nojsoft.model;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by jorge on 22/01/17.
 */
@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue (strategy = IDENTITY)

    @Column (name = "id", unique = true, nullable = false)
    private Long id;

    @Column (name = "name", length = 60)
    private String name;

    @Column (name = "access_key", length = 60)
    private String accessKey;

    @Column (name = "token", length = 60)
    private String token;

    @Column (name = "authentication_type")
    private Integer authenticationType;
}
