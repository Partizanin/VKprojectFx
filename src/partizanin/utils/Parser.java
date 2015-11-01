package partizanin.utils;

import partizanin.model.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * Project name: VKproject.
 * Date: 18.10.2015.
 * Time: 16:29.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class Parser {

    private FileUtils fileUtils = new FileUtils();

    public static void main(String[] args) {
        Parser parser = new Parser();
        parser.mainFileToObjects().forEach(System.out::println);


    }

    private String[] removeEmptyElements(String[] array) {
        ArrayList<String> result = new ArrayList<>();

        for (String s : array) {
            if (!s.isEmpty() && !s.equals("\r")) {
                result.add(s);
            }
        }
        return result.toArray(new String[result.size()]);
    }

    private Account getObjectFromText(String text) {
        String login = null;
        String password = null;
        Account account = new Account();
        char[] array = text.toCharArray();
        int cont = 0;
        for (char c : array) {
            if (c == ':') {
                cont++;
            }
        }
        if (cont < 3) {
            text = text.replaceAll("\t", "");
            text = text.replaceAll("\n", "");
            text = text.replaceAll(" ", "");
            text = text.replaceAll("\r", "");
            login = text.split(":")[0];
            password = text.split(":")[1];
            account = new Account(login, password, false, true, "", 0);

        }else {
            text = text.replaceAll("\t", "");
            text = text.replaceAll("\n", "");
            text = text.replaceAll(" ", "");
            text = text.replaceAll("\r", "");
            login = text.split(":")[0];
            password = text.split(":")[1];
            boolean used = Boolean.parseBoolean(text.split(":")[2]);
            boolean active = Boolean.parseBoolean(text.split(":")[3]);
            String secondLogin = text.split(":")[4];
            int id = Integer.parseInt(text.split(":")[5]);

            account = new Account(login, password, used, active, secondLogin, id);

        }

        return account;
    }

    protected List<Account> getObjectsFromFile(String[] text) {
        ArrayList<Account> accounts = new ArrayList<>();

        for (String aSplitText : text) {
            accounts.add(getObjectFromText(aSplitText));
        }
        return accounts;
    }

    protected List<Account> mainFileToObjects() {
        String[] splitText = fileUtils.readFromFile("list").split("\\n");

            return getObjectsFromFile(splitText);
    }

    protected List<Account> txtFileToObjects() {
        ArrayList<Account> accounts = new ArrayList<>();
        String[] splitText = fileUtils.readFromFile("numbers").split("\\n");
        for (int i = 0; i < splitText.length; i++) {
            String login = splitText[i].split(":")[0];
            String password = splitText[i].split(":")[1];
            accounts.add(new Account(login, password, false, true, "", i + 1));
        }
        return accounts;
    }


    public List<Account> getAccounts() {

        mainFileToObjects();

        return mainFileToObjects();
    }

}
