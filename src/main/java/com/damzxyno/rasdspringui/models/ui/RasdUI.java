package com.damzxyno.rasdspringui.models.ui;

import com.damzxyno.rasdspringui.models.common.MetaData;

import java.util.Map;

public class RasdUI {
    private String rasdApiUrl;
    private MetaData metaData;
    private Map<String, TagGroup> tagGroup;



    public String getRasdApiUrl() {
        return rasdApiUrl;
    }

    public void setRasdApiUrl(String rasdApiUrl) {
        this.rasdApiUrl = rasdApiUrl;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public Map<String, TagGroup> getTagGroup() {
        return tagGroup;
    }

    public void setTagGroup(Map<String, TagGroup> tagGroup) {
        this.tagGroup = tagGroup;
    }
}
