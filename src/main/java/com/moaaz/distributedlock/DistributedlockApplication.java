package com.moaaz.distributedlock;

import com.moaaz.distributedlock.service.TestingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DistributedlockApplication implements CommandLineRunner {
	private final TestingService testingService;

    public DistributedlockApplication(TestingService testingService) {
        this.testingService = testingService;
    }

    public static void main(String[] args) {
		SpringApplication.run(DistributedlockApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		testingService.doYourMagic();
	}
}
