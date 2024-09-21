package com.damzxyno.rasdspringui.models.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

public class Operation {
    private AuthorisationMod authorisationMod = null;
    private Set<String> tags = null;
    private String summary = null;
    private boolean isAuthenticated;


    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public AuthorisationMod getAuthorisationMod() {
        if (authorisationMod == null){
            this.authorisationMod = new AuthorisationMod();
        }
        return authorisationMod;
    }

    public  void setAuthorisationMod(AuthorisationMod authorisationMod){
        this.authorisationMod = authorisationMod;
    }

    public Set<String> getTags() {
        if (this.tags == null) {
            this.tags = new HashSet<>();
        }
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
    public Operation addTagsItem(String tagsItem) {
        if (this.tags == null) {
            this.tags = new HashSet<>();
        }

        this.tags.add(tagsItem);
        return this;
    }
}
