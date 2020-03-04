import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SherlockAndAnagrams {

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        int lenMaxAnagram = s.length()-1;
        char[] temp;
        String stemp;
        Map<String,Integer> dic = new HashMap<>();
        
        while(lenMaxAnagram > 0){
            for(int i=0;i <= s.length()-lenMaxAnagram;i++){
                temp = s.substring(i,i+lenMaxAnagram).toCharArray();
                
                Arrays.sort(temp);
                stemp = new String(temp);
                
                dic.put(stemp,dic.containsKey(stemp)?dic.get(stemp) + 1:1);
                
                System.out.println(stemp);
            }
            lenMaxAnagram--;
        };
        return dic.values()
                    .stream()
                    .filter(x->x > 1)
                    .mapToInt(x->fac(x-1)).sum();

    }
    public static int fac(int n) {
        if (n <= 1) {
            return n;
        }
        return n + fac(n - 1);
    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
