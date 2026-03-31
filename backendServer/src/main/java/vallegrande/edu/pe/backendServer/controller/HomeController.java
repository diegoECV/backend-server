package vallegrande.edu.pe.backendServer.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        return Map.of(
                "message", "Backend Server ejecutándose",
                "status", "OK",
                "timestamp", LocalDateTime.now(),
                "apiEndpoints", Map.of(
                        "customers", "/api/customers",
                        "swagger", "/swagger-ui.html",
                        "api-docs", "/v3/api-docs"
                )
        );
    }
}
