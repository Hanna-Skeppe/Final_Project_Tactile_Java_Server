package com.webapi.tactile;

import com.webapi.tactile.entities.AppUser;
import com.webapi.tactile.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TactileApplication {

	public static void main(String[] args) {
		SpringApplication.run(TactileApplication.class, args);
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
