package fi.johannes.models;

/**
 * johanness on 03/03/2017.
 */
public enum Role {

    SUPER_ADMIN("SUPER_ADMIN", "Super user", "su"),
    ADMIN("ADMIN", "Admin", "ad"),
    USER("USER", "User", "u");


    String role;
    String displayName;
    String shortenedName;

    Role(String role, String displayName, String shortenedName) {
        this.role = role;
        this.displayName = displayName;
        this.shortenedName = shortenedName;
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

}
