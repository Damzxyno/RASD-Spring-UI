package com.damzxyno.rasdspringui.controllers;

import com.damzxyno.rasdspringui.models.RASD;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;


@Controller
public class AuthorizationReportingWebUI {
    @Value("${authorisation-report.api-ui.path:#{T(com.damzxyno.rasdspringui.utils.Constants).DEFAULT_API_DOCS_URL}}")
    private String relativeURL;

    @GetMapping(
            value = {"${authorisation-report.api-ui.path:#{T(com.damzxyno.rasdspringui.utils.Constants).DEFAULT_API_UI_URL}}"}
    )
//    @ResponseBody
    public String authorizationReportUI(Model model, HttpServletRequest request) {
        var baseURL = getBaseUrl(request);
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String response = restTemplate.getForObject(baseURL + relativeURL, String.class);
            RASD rasd = mapper.readValue(response, RASD.class);
            model.addAttribute("rasd", rasd);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "rasd-ui";
    }


    private String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();

        return scheme + "://" + serverName + ":" + serverPort + contextPath;
    }
}
