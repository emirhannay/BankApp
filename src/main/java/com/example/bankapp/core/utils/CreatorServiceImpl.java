package com.example.bankapp.core.utils;


import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Service
public class CreatorServiceImpl implements CreatorService {


    //It generates a 26 digit iban.
    @Override
    public String createIban()
    {
        String val = "TR";
        Random r = new Random();
        Long numbers = 1000000000+ (long)(r.nextFloat() * 899900);
        Long numbers2 = 1000000000 + (long)(r.nextFloat() * 899900);
        Long numbers3 = 1000 + (long)(r.nextFloat() * 8999);
        val += String.valueOf(numbers);
        val += String.valueOf(numbers2);
        val += String.valueOf(numbers3);
        return val;
    }
    //It generates a 16 digit card number.
    @Override
    public String createCardNo() {
        String val = "";
        Random r = new Random();
        Long numbers = 1000000000+ (long)(r.nextFloat() * 899900);
        Long numbers2 = 100000 + (long)(r.nextFloat() * 899900);
        val += String.valueOf(numbers);
        val += String.valueOf(numbers2);
        return val;
    }
    //It generates a 3 digit random number.
    @Override
    public String createCvv() {
        String val = "";
        Random r = new Random();
        Long numbers = 100+ (long)(r.nextFloat() * 899);
        val += String.valueOf(numbers);
        return val;
    }
    //It creates an expiration date by adding 10 years to the current date.
    @Override
    public Date createExpirationDate() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR,10);
        Date expirationDate = c.getTime();
        return expirationDate;
    }

    public Date createCutOffDate(){
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH,1);
        Date cutOffDate= c.getTime();
        return cutOffDate;
    }

    @Override
    public Date createNextCutOffDate() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH,2);
        Date cutOffDate= c.getTime();
        return cutOffDate;
    }
    @Override
    public Date createPaymentDueDate() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH,40);
        Date cutOffDate= c.getTime();
        return cutOffDate;
    }
    @Override
    public Date createNextPaymentDueDate() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH,80);
        Date cutOffDate= c.getTime();
        return cutOffDate;
    }

}
