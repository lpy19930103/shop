import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ConsumerTopic {
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

        // 6. 从session中创建Consumer
        MessageConsumer consumer = session.createConsumer(topic);

        // 7， 接收消息,直接获取
        while (true) {
            // 消息超时时间是20秒
            Message message = consumer.receive(20000);
            // 如果消息为空，则跳出死循环
            if (message == null) {
                break;
            }
            // 8. 打印消息
            if (message instanceof TextMessage) {
                // 获取消息
                TextMessage textMessage = (TextMessage) message;
                // 打印
                System.out.println(textMessage.getText());
            }
        }

        // 9. 关闭session、连接等
        consumer.close();
        session.close();
        connection.close();
    }
}
