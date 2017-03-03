package fi.johannes.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

/**
 * johanness on 03/03/2017.
 */
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    final String role;
    final String displayName;
    final String shortenedName;

    @OneToMany
    Collection<Permission> permissions;

    Role(String role, String displayName, String shortenedName) {
        this.permissions = new HashSet<>();
        this.role = role;
        this.displayName = displayName;
        this.shortenedName = shortenedName;
    }

    public String getRole() {
        return role;
    }

    public void addPermission(Permission permission){
        this.permissions.add(permission);
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getShortenedName() {
        return shortenedName;
    }

}
