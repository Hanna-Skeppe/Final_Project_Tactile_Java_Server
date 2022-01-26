package com.webapi.tactile;


import com.webapi.tactile.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TactileApplication {

	public static void main(String[] args) {
		SpringApplication.run(TactileApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api").allowedOrigins("http://localhost:3000");
			}
		};
	}

//	private AppUserRepository appUserRepository;
//
//	@Autowired
//	public TactileApplication(AppUserRepository appUserRepository) {
//		this.appUserRepository = appUserRepository;
//	}

//	@Override
//	public void run(String... args) throws Exception {
//		AppUser user1 = new AppUser("person1@mail.com", "abc123");
//		appUserRepository.save(user1);
//		AppUser user2 = new AppUser("person2@mail.com", "def456");
//		appUserRepository.save(user2);
//	}
}
