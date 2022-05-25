package com.example.bankapp.core.utils;

import java.util.Date;

public interface CreatorService {

    String createIban();
    String createCardNo();
    String createCvv();
    Date createExpirationDate();
}
