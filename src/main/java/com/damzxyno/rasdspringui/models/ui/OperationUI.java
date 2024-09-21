package com.damzxyno.rasdspringui.models.ui;

import com.damzxyno.rasdspringui.models.common.Operation;

public class OperationUI extends Operation {
    private String httpMethod;

    public OperationUI(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }
}
