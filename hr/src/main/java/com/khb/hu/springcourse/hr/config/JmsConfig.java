package com.khb.hu.springcourse.hr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfig {

    @Value("${activemq.broker.enabled:false}")
    private boolean startBroker;

    @Bean
    public MessageConverter jacksonJmsMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(objectMapper);
        converter.setTypeIdPropertyName("_type");
        converter.setTargetType(MessageType.TEXT);
        return converter;
    }

    @Bean
    public BrokerService broker() throws Exception {
        BrokerService brokerService = new BrokerService();
        if(startBroker)
            brokerService.addConnector("tcp://localhost:9999");
        //brokerService.setPersistent(false); //--> kikapcsolható a perzisztencia
        return brokerService;
    }
}
