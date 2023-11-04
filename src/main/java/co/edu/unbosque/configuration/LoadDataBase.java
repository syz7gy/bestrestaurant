package co.edu.unbosque.configuration;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.edu.unbosque.model.Plate;
import co.edu.unbosque.repository.PlateRepository;
import co.edu.unbosque.util.AESUtil;

@Configuration
public class LoadDataBase {

	private static final Logger LOG = LoggerFactory.getLogger(LoadDataBase.class);

	@Bean
	CommandLineRunner initDataBase(PlateRepository plateRepository) {
		return args -> {
			Optional<Plate> found = plateRepository.findByName(AESUtil.encrypt("admin"));
			if (found.isPresent()) {
				LOG.info("Admin already exist, skiping admin creation");
			} else {
				plateRepository.save(new Plate(null, "testPlate", "32134", false, null, null));
				LOG.info("Preloading admin information");
			}
		};
	}

}
