import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class MatrixRotation {

    // Complete the matrixRotation function below.
    static void matrixRotation(List<List<Integer>> matrix, int r) {
        int m = matrix.size();
        int n = matrix.get(0).size();

        int dimentions = Math.min(m,n)/2;        
        
        while(dimentions>0)        
            rotateSurface(matrix,--dimentions,r);
            
        print(matrix);
    }

    public static void print(List<List<Integer>> matrix){
        for(List<Integer> line:matrix){
            for(Integer col:line)
                System.out.printf("%s ",col);
            System.out.printf("\n");            
        }
    }
    public static void rotateSurface(List<List<Integer>> matrix,int layer,int rotate_n){
        int m = matrix.size() - 2 * layer;
        int n = matrix.get(0).size() - 2 * layer;
        int dim = m * 2+ (n-2)*2;
        int len_m = matrix.size()-layer;
        int len_n = matrix.get(0).size()-layer;
        
        //System.out.printf("\n m(%s),n(%s),dim(%s),len_m(%s),len_n(%s),layer(%s)\n ",m,n,dim,len_m,len_n,layer);
        
        if(dim < 1)
            return;
        
        int[] line = new int[dim];
        int c = 0;
        
        //horizontal
        for(int x = layer,t=0;x<len_n;x++,c++,t++){
            line[c] = matrix.get(layer).get(x);
            line[line.length-t-m+1] = matrix.get(m-1+layer).get(x);
        }
        
        //vertical
        for(int y = layer+1,t=0;y<len_m-1;y++,c++,t++){
            line[c] = matrix.get(y).get(len_n-1);
            line[line.length-1-t] = matrix.get(y).get(layer);            
        }

        c = 0;
        //horizontal l-r
        for(int x = layer;x<len_n;x++,c++){            
            matrix.get(layer).set(x,line[(rotate_n+c)%line.length]);
            //System.out.println(String.format(".%-3s,%s,%s",c,layer,x));
        }
        //vertical u-d
        for(int y = layer+1;y<len_m-1;y++,c++){            
            matrix.get(y).set(len_n-1,line[(rotate_n+c)%line.length]);
            //System.out.println(String.format(".%-3s,%s,%s",c,y,len_n-1));
        }
        //horizontal r-l
        for(int x = len_n-1;x>=layer;x--,c++){
            matrix.get(len_m-1).set(x,line[(rotate_n+c)%line.length]);
            //System.out.println(String.format(".%-3s,%s,%s",c,len_m-1,x));
        }
        //vertical d-u
        for(int y = len_m-2;y>layer;y--,c++){
            matrix.get(y).set(layer,line[(rotate_n+c)%line.length]);
            //System.out.println(String.format("*%-3s,%s,%s",c,y,layer));
        }                
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] mnr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(mnr[0]);

        int n = Integer.parseInt(mnr[1]);

        int r = Integer.parseInt(mnr[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        matrixRotation(matrix, r);

        bufferedReader.close();
    }
}
