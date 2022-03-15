//package com.example.spring_api_nhansu.security;
//
//import com.example.spring_api_nhansu.entity.User;
//import com.example.spring_api_nhansu.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//@RequiredArgsConstructor
//@Slf4j
//public class MyUserDetailService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if(user == null){
//            log.error("User not found in the database");
//            throw new UsernameNotFoundException("User not found in the database");
//        }else{
//            log.error("User found in the database: {}", username);
//        }
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        user.getLevel_id();
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
//    }
//}
