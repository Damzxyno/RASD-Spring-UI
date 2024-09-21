package com.damzxyno.rasdspringui.controllers;

import com.damzxyno.rasdspringui.models.ui.RasdUI;
import com.damzxyno.rasdspringui.services.ReportingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class AuthorizationReportingWebUI {
    private  final ReportingService reportingService;

    public AuthorizationReportingWebUI(ReportingService reportingService) {
        this.reportingService = reportingService;
    }


    @GetMapping(
            value = {"${authorisation-report.api-ui.path:#{T(com.damzxyno.rasdspringui.utils.Constants).DEFAULT_API_UI_URL}}"}
    )
    public String authorizationReportUI(Model model, HttpServletRequest request) {
        RasdUI rasd = reportingService.getRasdUi(request);
        model.addAttribute("rasd", rasd);
        return "rasd-ui";
    }



}
