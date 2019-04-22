package PrimeChecker;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.reflect.*;

import com.sun.deploy.util.StringUtils;

class Prime{
    private List<String> primes = new ArrayList<>();

    public void checkPrime(int ... args ){
        for (int arg : args){
            if(isPrime(arg)){
                primes.add(String.valueOf(arg));
            }
        }
        if(!primes.isEmpty()) {
            String string = StringUtils.join(primes, " ");
            System.out.println(string);
        }
    }

    private boolean isPrime(int arg) {
        int counter =0;
        for(int i = 1; i <= arg; i++){
            if(arg%i ==0) counter++;
        }
        if (counter > 2) return false;
        return true;
    }
}

public class Solution {

    public static void main(String[] args) {
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            int n1=Integer.parseInt(br.readLine());
            int n2=Integer.parseInt(br.readLine());
            int n3=Integer.parseInt(br.readLine());
            int n4=Integer.parseInt(br.readLine());
            int n5=Integer.parseInt(br.readLine());
            Prime ob=new Prime();
            ob.checkPrime(n1);
            ob.checkPrime(n1,n2);
            ob.checkPrime(n1,n2,n3);
            ob.checkPrime(n1,n2,n3,n4,n5);
            Method[] methods=Prime.class.getDeclaredMethods();
            Set<String> set=new HashSet<>();
            boolean overload=false;
            for(int i=0;i<methods.length;i++)
            {
                if(set.contains(methods[i].getName()))
                {
                    overload=true;
                    break;
                }
                set.add(methods[i].getName());

            }
            if(overload)
            {
                throw new Exception("Overloading not allowed");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

}