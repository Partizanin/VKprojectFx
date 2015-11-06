package partizanin.utils;

import partizanin.model.Account;

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

    public String loadNewNumbers (List<Account> list) {

        return fwr.loadNewNumbers(list);
    }

    public void updateFile(List<Account> list) {
        fwr.updateFiles(list);
    }

    public List<Account> getAccounts() {
        return parser.getAccounts();
    }


}
