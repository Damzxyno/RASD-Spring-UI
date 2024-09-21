package com.damzxyno.rasdspringui.services;

import com.damzxyno.rasdspringui.models.api.RASD;
import com.damzxyno.rasdspringui.models.ui.RasdUI;

import javax.servlet.http.HttpServletRequest;

public interface ReportingService {
    RasdUI getRasdUi(HttpServletRequest request);
}
