package com.distant.system.entity;

import java.io.Serializable;
import java.util.Objects;

public class Language implements Serializable {

    public Language() {
    }

    private int languageID;
    private String language;

    public int getLanguageID() {
        return languageID;
    }

    public void setLanguageID(int languageID) {
        this.languageID = languageID;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language1 = (Language) o;
        return languageID == language1.languageID &&
                Objects.equals(language, language1.language);
    }

    @Override
    public int hashCode() {

        return Objects.hash(languageID, language);
    }
}
