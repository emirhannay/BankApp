package com.example.bankapp;

import com.example.bankapp.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class UserTests {

    @Autowired
    public CustomerRepository customerRepository;



    @Test
    void whenSaveCustomerCalledWithValidRequest_itShouldX() {
       /* CreateCustomerRequestDTO createCustomerRequestDTO = new CreateCustomerRequestDTO(
                "test@hotmail.com","testpass"
        );

        Customer customer = new Customer();
        User user = new User();
        user.setEmail("test@hotmail.com");
        user.setPassword("testpassword");
        user.setUserStatus(UserStatus.ACTIVE);
        user.setUserType(UserType.CUSTOMER);
        user.setCreatedAt(new Date());
        user.setDeleted(false);
        customer.setUser(user);
        Assertions.assertNotNull(customer.getId(), () -> "Customer id shouldn't be null");
        customerRepository.save(customer); */


        /*

        Customer user = new Customer();
        user.setUsername("baranbuyuk");
        user.setPassword("password");
        customerRepository.save(user);
        Assertions.assertThat(user.getId()).isNotNull();
        Optional<Customer> optionalUser = customerRepository.findById(user.getId());
        Assertions.assertThat(optionalUser).isPresent();
        Customer u = optionalUser.get();
        Assertions.assertThat(u.getUsername()).isNotEmpty();
        Assertions.assertThat(u.getUsername()).isEqualTo("baranbuyuk");
        Assertions.assertThat(u.getPassword()).isNotEmpty();
        Assertions.assertThat(u.getPassword()).isEqualTo("password");*/

    }


    @Test
    void should_match_encoded_user_password() {
       /* BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Customer customer = new Customer();
        customer.setUsername("baranbuyuk");
        customer.setPassword(passwordEncoder.encode("password"));
        customerRepository.save(customer);
        Assertions.assertThat(customer.getId()).isNotNull();
        Optional<Customer> optionalUser = customerRepository.findById(customer.getId());
        Assertions.assertThat(optionalUser).isPresent();
        Customer u = optionalUser.get();
        Assertions.assertThat(passwordEncoder.matches("password", u.getPassword())).isTrue(); */
    }

    @Test
    void should_not_match_when_type_wrong_password() {
        /*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Customer user = new Customer();
        user.setUsername("baranbuyuk");
        user.setPassword(passwordEncoder.encode("password"));
        customerRepository.save(user);
        Assertions.assertThat(user.getId()).isNotNull();
        Optional<Customer> optionalUser = customerRepository.findById(user.getId());
        Assertions.assertThat(optionalUser).isPresent();
        Customer u = optionalUser.get();
        Assertions.assertThat(passwordEncoder.matches("mypassword", u.getPassword())).isFalse();*/
    }

    //Araştırıp bulabilirsiniz.
    // Sorun transaction commit olmadan DB'ye yazmadığından dolayı, önce commitleyip, sonra tekrar yazmak gerekiyor.

   /* @Test
    void should_throw_uniqueConstraintException_when_create_duplicate_username() {
        User user = new User();
        user.setUsername("baranbuyuk");
        userRepository.save(user);

        User duplicateUser = new User();
        user.setUsername("baranbuyuk");
        userRepository.save(duplicateUser);

    }*/

}
