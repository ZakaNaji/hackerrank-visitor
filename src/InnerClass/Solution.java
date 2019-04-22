package InnerClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Solution {

    public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int num = Integer.parseInt(br.readLine().trim());
            Object o;// Must be used to hold the reference of the instance of the class Solution.Inner.Private
            Solution.Inner solutionInner = new Solution.Inner();
            Class<?> innerPrivateClass = Inner.class.getDeclaredClasses()[0];
            Constructor<?> innerPrivateConstructor = innerPrivateClass.getDeclaredConstructors()[0];
            innerPrivateConstructor.setAccessible(true);
        try {
            o = innerPrivateConstructor.newInstance(solutionInner);
        } catch (InstantiationException e) {
            e.printStackTrace();
            o = null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            o = null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            o = null;
        }
        System.out.println("An instance of class: " + o.getClass().getCanonicalName() + " has been created");

    }//end of main
    static class Inner{
        private class Private{
            private String powerof2(int num){
                return ((num&num-1)==0)?"power of 2":"not a power of 2";
            }
        }
    }//end of Inner

}