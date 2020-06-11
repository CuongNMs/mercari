//package com.cuongnm.mercari;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.cuongnm.mercari.model.Users;
//import com.cuongnm.mercari.repository.UserRepository;
//
//@SpringBootApplication
//public class App implements CommandLineRunner {
////    public static void main(String[] args) {
////        SpringApplication.run(App.class, args);
////    }
////
////    @Autowired
////    UserRepository userRepository;
////    @Autowired
////    PasswordEncoder passwordEncoder;
////
////    @Override
////    public void run(String... args) throws Exception {
////        // Khi chương trình chạy
////        // Insert vào csdl một user.
////        Users user = new Users(2L, "cuongnm", passwordEncoder.encode("cuongnm"));
////        userRepository.save(user);
////    }
//}