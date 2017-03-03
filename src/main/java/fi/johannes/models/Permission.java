package fi.johannes.models;

import javax.persistence.*;

/**
 * johanness on 03/03/2017.
 */
@Entity
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Role role;

    private final String name;

    public Permission(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
