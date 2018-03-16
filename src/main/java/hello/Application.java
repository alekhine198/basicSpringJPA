package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private final static Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                //save customers
                repository.save(new Customer("Jack", "Bauer"));
                repository.save(new Customer("Chloe", "O'Brian"));
                repository.save(new Customer("Kim", "Bauer"));
                repository.save(new Customer("David", "Palmer"));
                repository.save(new Customer("Michelle", "Dessler"));

                //fetch customers
                LOG.info("Customers find with findAll() method");
                LOG.info("------------------------------------");
                for (Customer customer: repository.findAll()){
                    LOG.info(customer.toString());
                }
                LOG.info("");

                repository.findById(1l)
                    .ifPresent(customer -> {
                        LOG.info("Customer found with findById(1L)");
                        LOG.info("--------------------------------");
                        LOG.info(customer.toString());
                        LOG.info("");
                    });

                LOG.info("Find customer using findByLastName()");
                LOG.info("------------------------------------");
                repository.findByLastName("Bauer").forEach(bauer -> LOG.info(bauer.toString()));
                LOG.info("");
            }
        };
    }
}
