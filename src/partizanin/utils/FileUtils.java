package partizanin.utils;

import partizanin.model.Account;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * Project name: Partizanin
 * User: Partizanin
 * Date: 28.08.2014
 * Time:  21:30
 * To change this template use File|Setting|File Templates.
 */
public class FileUtils {


    private Parser parser = new Parser();
    private FileWriterReader fwr = new FileWriterReader();

    public String loadNewNumbers(List<Account> list,List<Account> newList) {

        return fwr.loadNewNumbers(list,newList);
    }

    public void updateFile(List<Account> list) {
        fwr.updateFiles(list);
    }

    public List<Account> getAccounts() {
        return parser.getAccounts();
    }

    public Object[] fileValidation(File file) {
        Object[] objects = new Object[2];
        String fileType = file.getName().substring(file.getName().indexOf(".") + 1);
        if (!fileType.equals("txt")) {
            objects[0] = "wrong File Type";
            return objects;
        }
        byte[] encoded = new byte[0];
        try {/*todo make read from exist file but not with path*/
            encoded = Files.readAllBytes(Paths.get(file.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String readFile = new String(encoded, StandardCharsets.UTF_8);
        String[] splitContent = removeEmptyElements(readFile.split("\n"));
        Object[] validationResult = validation(splitContent);
        List<Account> accountList = (List<Account>) validationResult[1];
        String validationResultValue = (String) validationResult[0];
        if (validationResultValue.isEmpty()) {
            validationResultValue = "true";
        }else {
            if (accountList.size() == 0) {
                validationResultValue = "all";
            }
        }
        objects[0] = validationResultValue;
        objects[1] = accountList;

        return objects;
    }

    private Object[] validation(String[] text) {
        Object[] result = new Object[2];
        List<Account> accounts = new ArrayList<>();
        String falseValidation = "";
        boolean isNullOrEmpty = false;
        for (String aSplitText : text) {
            isNullOrEmpty = false;
            Account account = getObjectFromText(aSplitText);
            if (account.getPassword().getValue() != null && account.getLogin().getValue() != null) {
                if (!account.getPassword().getValue().isEmpty() && !account.getLogin().getValue().isEmpty()) {

                    accounts.add(account);

                } else {
                    isNullOrEmpty = true;
                }
            } else {
                isNullOrEmpty = true;
            }

            if (isNullOrEmpty) {
                falseValidation += aSplitText + "\n";
            }
        }
        result[1] = accounts;
        result[0] = falseValidation;
        return result;
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
            try {
                text = text.replaceAll("\t", "");
                text = text.replaceAll("\n", "");
                text = text.replaceAll(" ", "");
                text = text.replaceAll("\r", "");
                login = text.split(":")[0];
                password = text.split(":")[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Validation Failed:" + text);
            }
            account = new Account(login, password, false, true, "", 0);

        } else {
            text = text.replaceAll("\t", "");
            text = text.replaceAll("\n", "");
            text = text.replaceAll(" ", "");
            text = text.replaceAll("\r", "");
            try {
                login = text.split(":")[0];
                password = text.split(":")[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Validation Failed:" + text);
            }
            boolean used = Boolean.parseBoolean(text.split(":")[2]);
            boolean active = Boolean.parseBoolean(text.split(":")[3]);
            String secondLogin = text.split(":")[4];
            int id = Integer.parseInt(text.split(":")[5]);

            account = new Account(login, password, used, active, secondLogin, id);

        }

        return account;
    }
    public void saveFile(File file) {
        FileWriter fileWriter = null;
        StringBuilder sb1 = new StringBuilder();
        getAccounts().stream().filter(account -> !account.getSecondLogin().getValue().isEmpty() && !account.getSecondLogin().getValue().matches("^\\s*$")).forEach(account -> {
            sb1.append(account.getSecondLogin().getValue()).append(":").append(account.getPassword().getValue()).append("\n");
        });
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(String.valueOf(sb1));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
