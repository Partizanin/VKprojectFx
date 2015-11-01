package partizanin.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * Project name: VKproject.
 * Date: 18.10.2015.
 * Time: 16:33.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */

public class Accounts {
    private int lengthOfUnused;
    private int lengthOfUsed;

    private String date;

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    private List<Account> accounts;


    public Accounts() {
        accounts = new ArrayList<>();
    }

    public Accounts(int lengthOfUnused, int lengthOfUsed, String date, List<Account> accounts) {
        this.lengthOfUnused = lengthOfUnused;
        this.lengthOfUsed = lengthOfUsed;
        this.date = date;
        this.accounts = accounts;
    }

    public int getLength() {
        return accounts.size();
    }

    public int getLengthOfUnused() {
        return lengthOfUnused;
    }

    public int getLengthOfUsed() {
        return lengthOfUsed;
    }

    public List<Account> getAccounts() {
        return accounts;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
