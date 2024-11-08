/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.ConnectionFactory;

/**
 *
 * @author Rafael de Luca
 */
@Named(value = "jsfProducer")
@RequestScoped
public class JsfProducer {

    //@JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    //private JMSContext context;
    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "java/Fila")
    private Queue queue;
    
    private String clientMessage;

    public String getClientMessage() {
        return clientMessage;
    }

    public void setClientMessage(String clientMessage) {
        this.clientMessage = clientMessage;
    }   

    public JsfProducer() {

    }        
    
    public void send() {

        try {
            JMSContext jmsContext = connectionFactory.createContext();
            //jmsContext.createProducer().send(queue, "Testing messange on queue");
            jmsContext.createProducer().send(queue, clientMessage);
        } catch (Exception exp) {
            System.out.println("Error");
            System.out.println(exp.getMessage());
        }

    }

}