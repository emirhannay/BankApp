package com.example.bankapp.service;

import com.example.bankapp.converter.CustomerConverter;
import com.example.bankapp.dto.request.CreateCustomerRequestDTO;
import com.example.bankapp.dto.request.UpdateCustomerRequestDTO;
import com.example.bankapp.dto.response.GetCustomerResponseDTO;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Customer;
import com.example.bankapp.entity.User;
import com.example.bankapp.entity.enums.UserStatus;
import com.example.bankapp.entity.enums.UserType;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.helper.UserHelper;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.CustomerRepository;
import com.example.bankapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final CustomerConverter customerConverter;
    private final UserHelper userHelper;

    @Override
    public Customer getCustomerWithId(Long id) {
       Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found")
        );
       User loggedInUser = userHelper.getLoggedInUser();
       if( !(customer.getUser() == loggedInUser) &  !(userHelper.isLoggedInUserAdmin()) ){
           throw new BusinessServiceOperationException.GetCustomerFailedException("Get customer failed");
       }
        log.info("Getting customer was successfully -> {}",customer.getId());
        return customer;
    }

    @Override
    public List<GetCustomerResponseDTO> getCustomers() {
        List<GetCustomerResponseDTO> customers = customerRepository.getAllByDeleteStatus().
                stream()
                .map(customerConverter::getCustomerResponseDTO)
                .toList();
        return customers;
    }

    @Override
    public void save(CreateCustomerRequestDTO createCustomerRequestDTO) {
        User checkEmailUser = userRepository.findByEmail(createCustomerRequestDTO.email());
        Customer checkIdentityNoCustomer = customerRepository.findByIdentityNo(createCustomerRequestDTO.identityNo());
        if( !(Objects.isNull(checkEmailUser)) ){
            throw  new BusinessServiceOperationException.CreateCustomerFailedException("Already registered with this email address");
        }
        if( !(Objects.isNull(checkIdentityNoCustomer)) ){
            throw  new BusinessServiceOperationException.CreateCustomerFailedException("Already registered with this identity number");
        }

        Customer customerToBeRegistered = customerConverter.toCustomer(createCustomerRequestDTO);
        customerRepository.save(customerToBeRegistered);
        log.info("Customer created successfully -> {}",customerToBeRegistered.getId());
    }
    @Override
    public void update(Long id,UpdateCustomerRequestDTO updateCustomerRequestDTO) {
        Customer customer = customerConverter.toCustomer(id,updateCustomerRequestDTO);
        User loggedInUser = userHelper.getLoggedInUser();
        if(customer.getUser() == loggedInUser || userHelper.isLoggedInUserAdmin()){
            customerRepository.save(customer);
            log.info("Customer updated successfully -> {}",customer.getId());
            return;
        }
        else {
            throw new BusinessServiceOperationException
                    .DeleteCustomerFailedException("Update customer failed");
        }

    }
    @Override
    public void delete(Long id,boolean hardDelete){
        Customer customer = userHelper.getLoggedInCustomer();
        User user = userHelper.getLoggedInUser();
        Customer customerToDelete = customerRepository.findById(id).orElseThrow(
                () -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found")
        );



        if(customer == customerToDelete || userHelper.isLoggedInUserAdmin()){
            List<Account> accounts = accountRepository.getAccountsWithMoney(customerToDelete.getId());
            if(accounts != null){
                throw new BusinessServiceOperationException.DeleteCustomerFailedException("You cannot delete your user while you have funds in your account.");
            }

            if(hardDelete ){
                customerRepository.delete(customer);
                log.info("Hard delete customer was successfully -> {}",customer.getId());
                return;
            }
            if (user.isDeleted()) {

                throw new BusinessServiceOperationException.CustomerAlreadyDeletedException("Customer already deleted");
            }
            user.setDeletedAt(new Date());
            user.setDeleted(true);
            user.setUserStatus(UserStatus.LOCKED);
            customer.setUser(user);
            customerRepository.save(customer);
            log.info("Delete customer was successfully -> {}",customer.getId());

        }
        else {
            throw new BusinessServiceOperationException.DeleteCustomerFailedException("Delete customer failed");
        }

    }
}
