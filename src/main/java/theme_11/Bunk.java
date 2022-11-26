package theme_11;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class Bank {

    private Map<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        synchronized (accounts){
            if (amount > 50_000){
                try {
                    isFraud(fromAccountNum, toAccountNum, amount);
                    wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Account fromAccount = accounts.get(fromAccountNum);
            Account toAccount = accounts.get(toAccountNum);
            fromAccount.setMoney(fromAccount.getMoney() - amount);
            toAccount.setMoney(toAccount.getMoney() + amount);
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        synchronized (accountNum){
            return accounts.get(accountNum).getMoney();
        }
    }

    public long getSumAllAccounts() {
        synchronized (accounts){
            java.util.concurrent.atomic.AtomicLong allSum = new java.util.concurrent.atomic.AtomicLong(0L);
            accounts.values().stream().map(account -> allSum.addAndGet(account.getMoney()));
            return allSum.get();
        }
    }

    public void setAccounts(int count){
        int ind = accounts.size();
        for (int i = ind; i < ind + count; i++){
            Account account = new Account();
            account.setMoney(200_000L);
            account.setAccNumber(String.valueOf(i));
        }
        //TODO метод, который создаёт <count> аккаунтов
    }
}

class Account {
    private long money;
    private String accNumber;

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }
}

class TransferRun implements Runnable {

    private static Bank bank; //поле банка
    private final java.util.Random random = new java.util.Random();
    private int count; //количество аккаутов
    private int operationsCount; //количество операций, которые нужно совершить

    public TransferRun(Bank bank, int count, int operationsCount){
        TransferRun.bank = bank;
        this.count = count;
        this.operationsCount = operationsCount;
    }

    @Override
    public void run() {
        for (int i = 0; i < operationsCount; i++){
            if (random.nextInt() % 2 == 0){
                bank.transfer(
                        String.valueOf(random.nextInt(count)),
                        String.valueOf(random.nextInt(count)),
                        random.nextInt(1_000, 100_00));
            } else {
                bank.getSumAllAccounts();
            }
        }
        //TODO реализуйте здесь <operationsCount> случайных операций
    }
}
