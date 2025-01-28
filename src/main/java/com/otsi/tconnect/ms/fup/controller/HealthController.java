package com.otsi.tconnect.ms.fup.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/fup")
public class HealthController {

    @GetMapping("/health")
    public Map<String, String> healthCheck() {
        Map<String, String> healthStatus = new HashMap<>();
        healthStatus.put("application", "up");
        return healthStatus;
    }
}