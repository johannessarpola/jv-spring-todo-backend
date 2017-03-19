package fi.johannes.models;

import fi.johannes.common.Strings;

/**
 * johanness on 03/03/2017.
 */
public enum Role {

    SUPER_ADMIN("SUPER_ADMIN", "Super user", "su", 1),
    ADMIN("ADMIN", "Admin", "ad", 2),
    USER("USER", "User", "u", 3);


    String role;
    String displayName;
    String shortenedName;
    int rolePosition;

    Role(String role, String displayName, String shortenedName, int rolePosition) {
        this.role = role;
        this.displayName = displayName;
        this.shortenedName = shortenedName;
        this.rolePosition = rolePosition;
    }

    public String getRole() {
        return role;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getShortenedName() {
        return shortenedName;
    }

    public int getRolePosition() {
        return rolePosition;
    }

    public String getAsDefaultRole() {
        return Strings.defaultRolePrefix+role;
    }
}
