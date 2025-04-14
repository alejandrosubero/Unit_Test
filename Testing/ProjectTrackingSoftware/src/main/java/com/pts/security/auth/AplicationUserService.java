package com.pts.security.auth;

import com.pts.entitys.User;
import com.pts.repository.UserRepository;

import com.pts.security.EncryptAES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AplicationUserService implements UserDetailsService {

    @Value("${saltAESKey}")
    private String saltAES;

    @Autowired
    private EncryptAES encryptAES;

    @Autowired
    com.pts.security.EncryptPassword encryptPassword;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AplicationUserService(@Qualifier("user") UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(username);
        user.orElseThrow(()-> new UsernameNotFoundException(String.format("Username %s not found", username)));

        String pass = encryptAES.decrypt(user.get().getPassword(), saltAES);

        user.get().setPassword(passwordEncoder.encode(pass));
        return user.map(userAuth -> new AplicationUser( userAuth)).get();
    }
}
