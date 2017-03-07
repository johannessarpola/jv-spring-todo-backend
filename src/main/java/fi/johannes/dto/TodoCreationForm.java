package fi.johannes.dto;

import lombok.Data;

/**
 * johanness on 07/03/2017.
 */
@Data
public class TodoCreationForm {

    String entry;
    String deadline;
    Boolean done;
    String[] keywords;

}
