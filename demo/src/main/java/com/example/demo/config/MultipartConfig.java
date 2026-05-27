package com.example.demo.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultipartConfig {

    @Bean
    public TomcatServletWebServerFactory tomcatFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        
        factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
            @Override
            public void customize(Connector connector) {
                connector.setProperty("relaxedPathChars", "");
                connector.setProperty("relaxedQueryChars", "");
                // Disable file count limit for multipart uploads
                connector.setProperty("maxFileSize", "52428800"); // 50MB
                connector.setProperty("maxRequestSize", "52428800"); // 50MB
            }
        });
        
        return factory;
    }
}
