package fau.de.queryrepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan({"fau.deviceConfiguration", "fau.dataMart","fau.datastreamMetric"})
@ComponentScan(basePackages = {"fau"})
@EnableJpaRepositories(basePackages = {"fau.datastreamMetric", "fau.deviceConfiguration", "fau.dataMart"})
@EnableScheduling
public class KrakenRepositoryApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


	@Autowired
	public CorsFilter corsFilter;

	public static void main(String[] args) {
		SpringApplication.run(KrakenRepositoryApplication.class, args);
	}

}
