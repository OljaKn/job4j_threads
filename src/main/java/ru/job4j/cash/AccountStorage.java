package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(account.id(), account) != null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), accounts.get(account.id()), account);
    }

    public synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean rsl = false;
            Account fromAccount = accounts.get(fromId);
            Account toAccount = accounts.get(toId);
        if (fromAccount != null && toAccount != null && fromAccount.amount() >= amount) {
            fromAccount = new Account(fromAccount.id(), fromAccount.amount() - amount);
            toAccount = new Account(toAccount.id(), toAccount.amount() + amount);
            accounts.put(fromId, fromAccount);
            accounts.put(toId, toAccount);
            rsl = true;
        }
        return rsl;
    }
}
