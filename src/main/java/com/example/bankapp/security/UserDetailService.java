package com.example.bankapp.security;


import com.example.bankapp.entity.User;
import com.example.bankapp.repository.CustomerRepository;
import com.example.bankapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
        return new UserDetail(user.getId(),user.getEmail(), user.getPassword(), user.getUserStatus(),user.getRoles());
    }
}
