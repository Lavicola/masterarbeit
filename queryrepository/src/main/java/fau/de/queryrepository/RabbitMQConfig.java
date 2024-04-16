package fau.de.queryrepository;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    // Query Feature
    @Value("${rabbitmq.query__feature.queryExecutionQueue}")
    private String executionQueue;

    @Value("${rabbitmq.query__feature.queryExecutionplanQueue}")
    private String queryExecutionplanQueue;

    @Value("${rabbitmq.query__feature.queryExecutionQueueKey}")
    private String queryExecutionQueueKey;
    // Device Feature
    @Value("${rabbitmq.device_feature.device_queue}")
    private String deviceQueue;
    @Value("${rabbitmq.device_feature.device_queueKey}")
    private String deviceQueueKey;


    // DatastreamFeature
    @Value("${rabbitmq.datastream_feature.metric_queue}")
    private String metricQueue;


    @Bean
    public Queue queryExecutionQueue(){
        return new Queue(executionQueue);
    }

    @Bean
    public Queue queryExecutionplanQueue(){
        return new Queue(queryExecutionplanQueue);
    }

    @Bean
    public Queue deviceQueue(){
        return new Queue(deviceQueue);
    }

    @Bean
    public Queue metricQueue(){
        return new Queue(metricQueue);
    }



    // spring bean for rabbitmq exchange
    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(exchange);
    }

    // binding for queryExecutionQueue
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queryExecutionQueue())
                .to(exchange())
                .with(queryExecutionQueueKey);
    }

    // binding for deviceQueue
    @Bean
    public Binding devicebinding() {
        return BindingBuilder.bind(deviceQueue())
                .to(exchange())
                .with(deviceQueueKey);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }



}
