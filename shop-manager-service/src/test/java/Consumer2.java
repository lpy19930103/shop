import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer2 {
    public static void main(String[] args) throws JMSException, InterruptedException {

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
        Queue queue = session.createQueue("test-session");

        // 6. 从session中创建Consumer
        MessageConsumer consumer = session.createConsumer(queue);

        // 7.接收消息
        // 监听器的方式实际上是开启了一个新的线程，专门处理消息的接受
        // 现在的情况是，主线程执行完就结束了，新的线程也跟着没了
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    // 获取消息
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        // 打印
                        System.out.println(textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        // 让主线程等待一会，监听器能够有时间执行
        Thread.sleep(10000);

        // 9. 关闭session、连接等
        consumer.close();
        session.close();
        connection.close();

    }
}
