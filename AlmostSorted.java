import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the almostSorted function below.
    static void almostSorted(int[] arr) {
        int[] sortedArray = Arrays.copyOf(arr, arr.length);
        int[] modifiedArray = Arrays.copyOf(arr, arr.length);
        String operation = "";
        Arrays.sort(sortedArray);

        int differences = countDifferences(arr,sortedArray);
        int[] coord = getXYDifference(arr,sortedArray);

        if(differences == 2){            
            swap(modifiedArray,coord[0],coord[1]);
            operation = String.format("swap %s %s",coord[0]+1,coord[1]+1);
        }else if(differences > 2){
            swapArea(modifiedArray,coord[0],coord[1]);
            operation = String.format("reverse %s %s",coord[0]+1,coord[1]+1);
        }

        if(Arrays.equals(sortedArray,modifiedArray))
            System.out.printf("yes\n%s\n",operation);
        else
            System.out.println("no");

    }

    static int countDifferences(int[] a,int[] b){
        int diff = 0;
        for(int i=0;i<a.length;i++)
            diff += a[i] != b[i]?1:0;
        return diff;
    }
    static int[] getXYDifference(int[] a,int[] b){
        int[] diff = new int[2];

        for(int i=0,d=0;i<a.length;i++)
            if(a[i] != b[i])
                diff[Math.min(d++,1)] = i;

        return diff;
    }
    static void swap(int[] arr,int a,int b){
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }
    static void swapArea(int[] arr,int a,int b){
        int[] newArea = Arrays.copyOfRange(arr,a,b+1);

        for(int i = a,c=newArea.length-1;c>=0;i++,c--)
            arr[i] = newArea[c];
    }
    
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        almostSorted(arr);

        scanner.close();
    }
}
