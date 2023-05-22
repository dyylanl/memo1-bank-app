package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.exceptions.InvalidWithdrawalSumException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.InvalidTransactionException;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionService transactionService;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long cbu) {
        return accountRepository.findById(cbu);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void deleteById(Long cbu) {
        accountRepository.deleteById(cbu);
    }

    @Transactional
    public Account withdraw(Long cbu, Double sum) {
        Account account = accountRepository.findAccountByCbu(cbu);

        if (sum <= 0) {
            throw new InvalidWithdrawalSumException("Invalid sum to withdrawal.");
        }

        if (account.getBalance() < sum) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - sum);
        accountRepository.save(account);
        // Almaceno la operacion
        transactionService.createTransaction(new Transaction(cbu, sum, "WITHDRAWAL"));
        return account;
    }

    @Transactional
    public Account deposit(Long cbu, Double sum) {

        if (sum <= 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }

        double MIN_DEPOSIT_PROMO = 2000.0;
        double MAX_VALUE_PROMO = 500;
        double PERCENT_PROMO = 0.1;

        if (sum >= MIN_DEPOSIT_PROMO){
            double bonus = sum * PERCENT_PROMO;
            if(bonus > MAX_VALUE_PROMO){
                bonus = MAX_VALUE_PROMO;
            }
            sum += bonus;
        }

        Account account = accountRepository.findAccountByCbu(cbu);
        account.setBalance(account.getBalance() + sum);
        accountRepository.save(account);
        // Almaceno la operacion
        transactionService.createTransaction(new Transaction(cbu, sum, "DEPOSIT"));
        return account;
    }

}
