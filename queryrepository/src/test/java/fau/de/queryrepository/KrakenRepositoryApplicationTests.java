package fau.de.queryrepository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest
class KrakenRepositoryApplicationTests {

	@Test
	void contextLoads() {
	}
	@Bean
	@Primary
	public CachingConnectionFactory rabbitAdmin() {
		return Mockito.mock(CachingConnectionFactory.class);
	}

}
