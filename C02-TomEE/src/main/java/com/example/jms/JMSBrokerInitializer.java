package com.example.jms;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Startup
@Singleton
public class JMSBrokerInitializer {

    @PostConstruct
    public void setup() {
        try {
            InitialContext ctx = new InitialContext();

  
            Topic topic = (Topic) ctx.lookup("jms/imageTopic");

         
            System.out.println("JMS Topic initialized successfully: " + topic.getTopicName());
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
            System.err.println("Failed to initialize JMS Topic.");
        }
    }
}
