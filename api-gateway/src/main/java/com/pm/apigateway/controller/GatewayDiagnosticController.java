package com.pm.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class GatewayDiagnosticController {

    @Autowired(required = false)
    private RouteDefinitionLocator routeDefinitionLocator;

    @Autowired(required = false)
    private RouteLocator routeLocator;

    @GetMapping("/diagnose")
    public Map<String, Object> diagnose() {
        Map<String, Object> result = new HashMap<>();

        result.put("routeDefinitionLocator", routeDefinitionLocator != null ? "Available" : "Not Available");
        result.put("routeLocator", routeLocator != null ? "Available" : "Not Available");

        if (routeDefinitionLocator != null) {
            List<Map<String, Object>> routes = new ArrayList<>();
            routeDefinitionLocator.getRouteDefinitions()
                    .collectList()
                    .subscribe(defs -> {
                        defs.forEach(def -> {
                            Map<String, Object> route = new HashMap<>();
                            route.put("id", def.getId());
                            route.put("uri", def.getUri().toString());
                            route.put("predicates", def.getPredicates().toString());
                            routes.add(route);
                        });
                    });
            result.put("routes", routes);
        }

        return result;
    }
}
