package Management;

import java.io.*;
import java.util.*;

public class DictionaryManagement {

    // them tu tu file
    public void insertFromFile(String pathFile) throws FileNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(pathFile);
        Scanner scanner = new Scanner(fileInputStream);
        StringBuilder word = new StringBuilder();
        String test = "";
        while (scanner.hasNextLine()) {
            test = scanner.nextLine() + "\n";
            word.append(test);
        }
        String[] S = word.toString().split("&");
        String target = new String();
        String explain = new String();

        for (int i = 1; i < S.length; i++) {
            if (S[i].contains("/")) {
                target = S[i].substring(0, S[i].indexOf("/") - 1);
                explain = S[i].substring(S[i].indexOf("/"), S[i].length() - 1);
                Dictionary.hashMap.put(target, explain);
            } else {
                if (S[i].contains("\n")) {
                    String[] s = S[i].split("\n", 2);
                    Dictionary.hashMap.put(s[0], s[1]);
                }
            }
        }
    }

    // Them tu
    public void addWord(String target, String explain) {
        Dictionary.hashMap.put(target, explain);
    }

    // Xoa tu 
    public void removeWord(String target) {
        Dictionary.hashMap.remove(target);
    }

    // Sua tu 
    public void modifyWord(String target, String explain) {
        Dictionary.hashMap.replace(target, explain);
    }

    //Tim kiem
    public boolean dictionaryLookUp(String target) {
        return (Dictionary.hashMap.get(target) != null);
    }

    // In cac tu trong tu dien vao file
    public void dictionaryExportToFile() throws FileNotFoundException, IOException {
        FileOutputStream fout = new FileOutputStream("updated.txt");

        try (BufferedOutputStream bout = new BufferedOutputStream(fout)) {
            Set set = Dictionary.hashMap.entrySet();
            Iterator iterator = set.iterator();

            while (iterator.hasNext()) {
                Map.Entry mentry = (Map.Entry) iterator.next();
                String line = mentry.getKey() + "\n" + mentry.getValue();
                bout.write(line.getBytes());
                bout.write(System.lineSeparator().getBytes());
            }
        }
    }
}
