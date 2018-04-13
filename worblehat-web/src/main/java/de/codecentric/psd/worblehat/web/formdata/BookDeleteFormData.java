package de.codecentric.psd.worblehat.web.formdata;

import org.hibernate.validator.constraints.NotEmpty;

public class BookDeleteFormData {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotEmpty
    private String id;

}
