package com.blissstock.mappingSite;

import com.blissstock.mappingSite.config.ApplicationProperties;
import com.blissstock.mappingSite.service.StorageService;
import javax.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.blissstock.mappingSite.entity")
@EnableJpaRepositories("com.blissstock.mappingSite.repository")
@EnableConfigurationProperties(ApplicationProperties.class)
public class MappingSiteApplication implements CommandLineRunner {

  @Resource
  StorageService storageService;

  public static void main(String[] args) {
    SpringApplication.run(MappingSiteApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    storageService.init();
  }
}
