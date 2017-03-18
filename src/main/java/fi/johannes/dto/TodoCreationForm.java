package fi.johannes.dto;

import lombok.Data;

/**
 * johanness on 07/03/2017.
 */
@Data
public class TodoCreationForm {

    private String entry;
    private String deadline;
    private Boolean done;
    private String[] keywords;

    public boolean isValid(){
        return entry != null && !entry.isEmpty() && deadline != null && !deadline.isEmpty();
    }
}
