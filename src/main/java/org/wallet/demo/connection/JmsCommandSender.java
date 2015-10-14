package org.wallet.demo.connection;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.wallet.demo.domain.AbstractCommand;
import org.wallet.demo.domain.CommandType;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;


@Service
public class JmsCommandSender {

    private static final String REPLY_QUEUE_NAME = "Recv2Send";

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(final AbstractCommand abstractCommand){
        this.jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createObjectMessage(abstractCommand);
                message.setJMSReplyTo(new ActiveMQQueue(REPLY_QUEUE_NAME));
                return message;
            }
        });
    };

}
