package partizanin.utils;

import partizanin.model.Account;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * Project name: VKprojectFx.
 * Date: 06.11.2015.
 * Time: 21:19.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class FileWriterReader {

    public static void main(String[] args) throws IOException {
        FileWriterReader fileUtils = new FileWriterReader();
       /* Accounts accounts = new Accounts();
        Account account = new Account();

        account.setId(1);
        account.setLogin("380634401004");
        account.setSecondLogin("380932534213212314");
        account.setPassword("aaaaa");
        account.setActive(true);
        account.setUsed(false);

        accounts.getAccounts().add(account);
        account = new Account();

        account.setId(2);
        account.setLogin("380634401004");
        account.setSecondLogin("380932534213212314");
        account.setPassword("bbbb");
        account.setActive(false);
        account.setUsed(true);

        accounts.getAccounts().add(account);
        account = new Account();

        account.setId(3);
        account.setLogin("380634401004");
        account.setSecondLogin("380932534213212314");
        account.setPassword("ccc");
        account.setActive(false);
        account.setUsed(true);

        accounts.getAccounts().add(account);
        accounts.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        fileUtils.writeTxt(accounts);*/
        System.out.println(fileUtils.getPath());


    }

    private void updateFile(String text) {

        String fileName = getPath() + "list.txt";
        Writer output;
        //clears file every time
        try {
            output = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName, true), "UTF-8"));
            output.append(text);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeTxt(List<Account> list) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for (Account ac : list) {

            if (!ac.getSecondLogin().getValue().isEmpty() && !ac.getSecondLogin().getValue().matches("^\\s*$")) {
                sb1.append(ac.getSecondLogin().getValue()).append(":").append(ac.getPassword().getValue()).append("\n");
            }
            sb2.append(ac.getLogin().getValue()).append(":").append(ac.getPassword().getValue())
                    .append(":").append(ac.getUsed().getValue()).append(":").append(ac.getActive().getValue())
                    .append(":").append(ac.getSecondLogin().getValue()).append(":").append(ac.getId().getValue()).append("\n");
        }

        writeToFile(String.valueOf(sb1), "numbers");
        writeToFile(String.valueOf(sb2), "list");
    }

    private String getPath() {
        String path = this.getClass().getClassLoader().getResource("").getPath();
        String result = "";

        int finish = path.indexOf("VKprojectFx") + 11;
        result = path.substring(0, finish);
        result += "/src/recourse/";
        return result.substring(1);
    }

    private void writeToFile(String source, String name) {
        PrintWriter pw = null;
        if (name.equals("list")) {
            name = "list.txt";
        } else {
            name = "numbers.txt";
        }
        String path = getPath();

        File file = new File(path + name);
        if (!file.exists() || file.isDirectory()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8), true);
            pw.print(source);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    public String readFromFile(String name) {
        if (name.equals("list")) {

            name = getPath() + "list.txt";

        } else {
            name = getPath() + "Неактив вк.txt";
        }

        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = new String(encoded, StandardCharsets.UTF_8);

        return removeEmptyElements(result);
    }

    private String removeEmptyElements(String text) {
        String result = "";
        String[] array = text.split("\n");
        for (String s : array) {
            if (!s.isEmpty()) {
                result += s + "\n";
            }
        }
        return result;
    }

    public void updateFiles(List<Account> list) {
/*todo make validation to rewrite file
* check login and password
* if login and password is null don't rewrite files*/
        boolean rewrite = true;
        for (Account account : list) {


            if (account.getLogin().getValue().isEmpty() || account.getPassword().getValue().isEmpty()) {
                rewrite = false;
            }
        }
        if (rewrite) {
            writeTxt(list);
        }
    }

    protected String loadNewNumbers(List<Account> list) {
        Parser parser = new Parser();
        String writeTxt = "";

        List<Account> newAccounts = parser.getObjectsFromFile(readFromFile("Неактив").split("\n"));
        boolean addNewAccount = true;

        for (Account newAccount : newAccounts) {
            for (Account account : list) {
                if (newAccount.getLogin().equals(account.getLogin())) {
                    addNewAccount = false;
                    break;
                }
            }
            if (addNewAccount) {
                writeTxt += newAccount.getLogin() + ":" + newAccount.getPassword() + "\n";
            }
            addNewAccount = true;
        }
        updateFile(writeTxt);
        return writeTxt;
    }
}
