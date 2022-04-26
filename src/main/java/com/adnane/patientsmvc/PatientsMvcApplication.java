package com.adnane.patientsmvc;

import com.adnane.patientsmvc.entities.Patient;
import com.adnane.patientsmvc.repositories.PatientRepository;
import com.adnane.patientsmvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            patientRepository.save(
                    new Patient(null, "Adnane", new Date(), false, 122));
            patientRepository.save(
                    new Patient(null, "Hassan", new Date(), true, 321));
            patientRepository.save(
                    new Patient(null, "Adam", new Date(), true, 165));
            patientRepository.save(
                    new Patient(null, "Amine", new Date(), false, 132));

            patientRepository.findAll().forEach(p ->
                    System.out.println(p.getNom()));

        };
    }



  //  @Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
          securityService.saveNewUser("Adnane","1234","1234");
            securityService.saveNewUser("Amine","1234","1234");
            securityService.saveNewUser("Hassan","1234","1234");


           securityService.saveNewRole("USER","");
           securityService.saveNewRole("ADMIN","");

           securityService.addRoleToUser("Adnane","USER");
            securityService.addRoleToUser("Adnane","ADMIN");
            securityService.addRoleToUser("Amine","USER");
            securityService.addRoleToUser("Hassan","USER");


        };
    }

}
