package com.example.bankapp.service.cronjob;

import com.example.bankapp.entity.CreditCard;
import com.example.bankapp.entity.enums.CreditCardStatus;
import com.example.bankapp.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class CreditCardCronJob {

    private final CreditCardRepository creditCardRepository;

    //This cron job was created to block credit cards of customers who do not pay their debt.
    //It checks every night at 00:01.
    @Scheduled(cron = "0 1 0 * * *",zone = "Europe/Istanbul")
    public void checkCardDueDateAndDebt(){
        List<CreditCard> creditCards = creditCardRepository.getAllActiveCreditCards();
        if( !creditCards.isEmpty() ){
            for (int i = 0; i< creditCards.size(); i++){
                CreditCard creditCard  = creditCards.get(i);
                Date currentDate = new Date();
                Date cardPaymentDueDate = creditCard.getPaymentDueDate();
                int res = creditCard.getLastOutstandingBalance().compareTo(BigDecimal.ZERO);
                if(cardPaymentDueDate.before(currentDate) && res == 1 ) {
                    creditCard.setCreditCardStatus(CreditCardStatus.BLOCKED);
                    creditCardRepository.save(creditCard);
                }


            }
        }
    }

    //This cron job was created to update date information such as credit card due date
    //It updates the date information of the cards at 00:02 every night.
    @Scheduled(cron = "0 2 0 * * *",zone = "Europe/Istanbul")
    public void updateCreditCardDates() {
        List<CreditCard> creditCards = creditCardRepository.getAllActiveCreditCards();
        if( !creditCards.isEmpty() ){
            for(int i = 0; i< creditCards.size(); i++){
                CreditCard creditCard = creditCards.get(i);
                Date currentDate = new Date();
                Date cutOffDate = creditCard.getCutoffDate();
                if( cutOffDate.before(currentDate) ){
                    Date tempNextCutOffDate = creditCard.getNextCutoffDate();

                    Calendar c = Calendar.getInstance();
                    c.setTime(tempNextCutOffDate);
                    c.add(Calendar.DAY_OF_MONTH,30);
                    Date nextCutOffDate= c.getTime();

                    Calendar c1 = Calendar.getInstance();
                    c1.setTime(creditCard.getNextCutoffDate());
                    c1.add(Calendar.DAY_OF_MONTH,10);
                    Date paymentDueDate= c1.getTime();

                    Calendar c2 = Calendar.getInstance();
                    c2.setTime(paymentDueDate);
                    c2.add(Calendar.MONTH,1);
                    Date nextPaymentDueDate = c2.getTime();

                    creditCard.setCutoffDate(creditCard.getNextCutoffDate());
                    creditCard.setNextCutoffDate(nextCutOffDate);
                    creditCard.setPaymentDueDate(paymentDueDate);
                    creditCard.setNextPaymentDueDate(nextPaymentDueDate);
                    creditCardRepository.save(creditCard);
                }
            }

        }
    }
}
