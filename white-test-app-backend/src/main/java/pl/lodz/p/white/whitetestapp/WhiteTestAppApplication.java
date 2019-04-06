package pl.lodz.p.white.whitetestapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
public class WhiteTestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhiteTestAppApplication.class, args);
	}

}
