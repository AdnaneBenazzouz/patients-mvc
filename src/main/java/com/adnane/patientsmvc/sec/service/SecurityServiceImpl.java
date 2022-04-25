package com.adnane.patientsmvc.sec.service;

import com.adnane.patientsmvc.sec.entities.AppRole;
import com.adnane.patientsmvc.sec.entities.AppUser;
import com.adnane.patientsmvc.sec.repositories.AppRoleRepository;
import com.adnane.patientsmvc.sec.repositories.AppUserRepository;
import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new RuntimeException("Passwords not match");
        String hashedPWD=passwordEncoder.encode(password);
        AppUser appUser=new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        AppUser saveAppUser=appUserRepository.save(appUser);
        return saveAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        return null;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {

    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return null;
    }
}
