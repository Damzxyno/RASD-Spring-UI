package com.damzxyno.rasdspringui.models.api;

import com.damzxyno.rasdspringui.models.common.MetaData;

public class RASD {
    private String doc;
    private String version;
    private Paths paths = new Paths();
    private MetaData metaData = new MetaData();

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Paths getPaths() {
        return paths;
    }

    public void setPaths(Paths paths) {
        this.paths = paths;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
}
