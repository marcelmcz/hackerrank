import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CheckMagazine {

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {
        Map<String,Integer> mag = getDic(magazine);
        Map<String,Integer> not = getDic(note);
        String result = "Yes";

        for (String i : not.keySet()) {
           if(!mag.containsKey(i) || not.get(i) > mag.get(i)){
               result = "No";
               break;
           }
        }

        System.out.print(result);
    }

    static Map<String,Integer> getDic(String[] arr){
        Map<String,Integer> dic = new HashMap<>();

        for(String s:arr)
            dic.put(s,dic.containsKey(s)?dic.get(s) + 1:1);

        return dic;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }
}
