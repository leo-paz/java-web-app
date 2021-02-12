package Lab4.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab4Application {
	private static final Logger log = LoggerFactory.getLogger(Lab4Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Lab4Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(BuddyInfoRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new BuddyInfo("Jack", 123));
			repository.save(new BuddyInfo("Chloe", 456));
			repository.save(new BuddyInfo("Kim", 789));
			repository.save(new BuddyInfo("David", 1234));
			repository.save(new BuddyInfo("Michelle", 5678));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (BuddyInfo buddy : repository.findAll()) {
				log.info(buddy.toString());
			}
			log.info("");

			// fetch an individual customer by ID
//			BuddyInfo customer = repository.findById(1L);
//			log.info("Customer found with findById(1L):");
//			log.info("--------------------------------");
//			log.info(customer.toString());
//			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Customer bauer : repository.findByLastName("Bauer")) {
			//  log.info(bauer.toString());
			// }
			log.info("");
		};
	}

}
