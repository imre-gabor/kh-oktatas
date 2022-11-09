package com.khb.hu.springcourse.hr.config;

import com.sun.xml.ws.transport.http.servlet.WSSpringServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:/ws-beans.xml")
public class WsConfig {

    @Bean
    public ServletRegistrationBean jaxwsServlet(){
        return new ServletRegistrationBean(new WSSpringServlet(), "/ws");
    }
}
