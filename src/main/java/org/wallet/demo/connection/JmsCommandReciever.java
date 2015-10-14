package org.wallet.demo.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;
import org.wallet.demo.domain.AbstractCommand;
import org.wallet.demo.service.CommunicationService;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;


@Service
public class JmsCommandReciever implements SessionAwareMessageListener<ObjectMessage>{

    @Autowired
    private CommunicationService communicationService;

    @Override
    public void onMessage(ObjectMessage objectMessage, Session session) throws JMSException {
        communicationService.onCommandRecieve((AbstractCommand)objectMessage.getObject());
    }

}
