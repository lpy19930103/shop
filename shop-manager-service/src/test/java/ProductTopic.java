
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

public class ProductTopic {

    public static void main(String[] arg) throws JMSException {
        // 1. 创建连接工厂ActiveMQConnectionFactory，需要ip和端口61616
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        // 2. 从连接工厂中创建连接对象
        Connection connection = factory.createConnection();

        // 3. 执行start方法开启连接
        connection.start();

        // 4. 从连接中创建session对象
        // 第一个参数，是否开启事务，JTA分布式事务
        // 第二个参数，是否自动应答，如果第一个参数为true，第二个参数失效
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 5. 从session中创建Destination对象，设置queue名称（有两种类型queue和topic）
        Topic topic = session.createTopic("test-topic");

        // 6. 从session中创建Product对象
        MessageProducer producer = session.createProducer(topic);

        // 7. 创建消息对象
        ActiveMQTextMessage message = new ActiveMQTextMessage();
        message.setText("test-topic开始发送消息了");

        // 8. 发送消息
        producer.send(message);

        // 9. 关闭session、连接
        producer.close();
        session.close();
        connection.close();

    }
}
