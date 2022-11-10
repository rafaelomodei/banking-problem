import java.util.*;
import java.text.*;

//'Contador' é um objeto compartilhado pelas threads.
class Account {
  protected int balance = 100;

  public void add(int value) {
    balance = balance + value;
  }

  public void sub(int value) {
    balance = balance - value;
  }
}

class Bank implements Runnable {
  public Account account;

  public void run() {
    Thread thread = Thread.currentThread();
    if(thread.getName() == "deposit") account.add(100);
    if(thread.getName() == "loot") account.sub(50);
  }
}

public class Main {
  public static void main(String[] args) {
    Account account = new Account();

    Bank deposit = new Bank();
    deposit.account = account;

    Bank loot = new Bank();
    loot.account = account;

    Thread transactionDeposit = new Thread(deposit, "deposit");
    Thread transactionLoot = new Thread(loot, "loot");

    transactionDeposit.start();

    synchronized (transactionDeposit) {
      try {
        System.out.println("Aguardando o transactionDeposit completar...");
        transactionDeposit.wait();
      } catch (InterruptedException e) {
        System.out.println("Erro: Algo de errado ocorreu - transactionDeposit");
      }
    }
    
    transactionLoot.start();
    try { // Aguarda as duas threads encerrarem para terminar a main
      transactionDeposit.join();
      transactionLoot.join();
      System.out.println("Fazendo as transações");
    } catch (InterruptedException e) {
      System.out.println("Erro: Algo de errado ocorreu - transactionLoot");
    } finally {
      System.out.println("\n*** TRANSAÇÃO CONCLUIDA ***");
      System.out.println("*** Depósito: " + deposit.account.balance);
      System.out.println("*** Saque: " + loot.account.balance);
    }
  }
}