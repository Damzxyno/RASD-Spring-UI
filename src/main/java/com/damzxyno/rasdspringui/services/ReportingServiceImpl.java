package com.damzxyno.rasdspringui.services;

import com.damzxyno.rasdspringui.models.api.PathItem;
import com.damzxyno.rasdspringui.models.api.RASD;
import com.damzxyno.rasdspringui.models.common.Operation;
import com.damzxyno.rasdspringui.models.ui.OperationUI;
import com.damzxyno.rasdspringui.models.ui.RasdUI;
import com.damzxyno.rasdspringui.models.ui.TagGroup;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportingServiceImpl implements  ReportingService  {
    @Value("${authorisation-report.api-ui.path:#{T(com.damzxyno.rasdspringui.utils.Constants).DEFAULT_API_DOCS_URL}}")
    private String relativeURL;

    @Override
    public RasdUI getRasdUi(HttpServletRequest request) {
        var baseURL = getBaseUrl(request);
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String raspApiUrl = baseURL + relativeURL;
            String response = restTemplate.getForObject(raspApiUrl, String.class);
            RASD rasd = mapper.readValue(response, RASD.class);
            RasdUI rasdUI = mapRASDToRasdUi(rasd, raspApiUrl);
            return rasdUI;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();

        return scheme + "://" + serverName + ":" + serverPort + contextPath;
    }

    private RasdUI mapRASDToRasdUi (RASD rasd, String rasdApiUrl){
        RasdUI rasdUI = new RasdUI();
        rasdUI.setRasdApiUrl(rasdApiUrl);

        Map<String, TagGroup> tagGroupMap = new HashMap<>();
        rasdUI.setTagGroup(tagGroupMap);

        rasdUI.setMetaData(rasd.getMetaData());

        rasd.getPaths().forEach((pathURL, pathItem) -> {
            processOperationMap(tagGroupMap, pathURL, pathItem, HttpMethod.GET, pathItem.getGet());
            processOperationMap(tagGroupMap, pathURL, pathItem, HttpMethod.PUT, pathItem.getPut());
            processOperationMap(tagGroupMap, pathURL, pathItem, HttpMethod.POST, pathItem.getPost());
            processOperationMap(tagGroupMap, pathURL, pathItem, HttpMethod.DELETE, pathItem.getDelete());
            processOperationMap(tagGroupMap, pathURL, pathItem, HttpMethod.OPTIONS, pathItem.getOptions());
            processOperationMap(tagGroupMap, pathURL, pathItem, HttpMethod.HEAD, pathItem.getHead());
            processOperationMap(tagGroupMap, pathURL, pathItem, HttpMethod.PATCH, pathItem.getPatch());
            processOperationMap(tagGroupMap, pathURL, pathItem, HttpMethod.TRACE, pathItem.getTrace());

        });
        return  rasdUI;
    }

    private void processOperationMap(Map<String, TagGroup> tagGroupMap, String pathURL, PathItem pathItem, HttpMethod httpMethod, Operation operation){
        if (operation == null){
            return;
        }
        operation.getTags().forEach(tag -> {
            tagGroupMap.compute(tag, (key, value) -> value == null ? new TagGroup() : value);
            TagGroup tagGroup = tagGroupMap.get(tag);
            tagGroup.compute(pathURL, (key, value) -> value == null ? new ArrayList<>() : value);
            OperationUI operationUi = mapOperationToOperationUi(operation, httpMethod);
            tagGroup.get(pathURL).add(operationUi);
        });
    }

    private OperationUI mapOperationToOperationUi(Operation operation, HttpMethod httpMethod) {
        OperationUI operationUI = new OperationUI(httpMethod.name());
        operationUI.setAuthenticated(operation.isAuthenticated());
        operationUI.setAuthorisationMod(operation.getAuthorisationMod());
        return operationUI;
    }
}
