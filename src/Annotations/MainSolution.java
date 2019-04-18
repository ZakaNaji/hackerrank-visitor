package Annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class MainSolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nTests = Integer.parseInt(scanner.nextLine());
        while(nTests > 0){
            String role = scanner.next();
            int spent = scanner.nextInt();
            Method[] methods = Family.class.getMethods();
            for (Method method : methods){
                boolean condition = method.isAnnotationPresent(roleBudgetAnnotation.class);
                if(condition){
                    roleBudgetAnnotation familyRoleBudgetAnnotation = method.getAnnotation(roleBudgetAnnotation.class);
                    if(familyRoleBudgetAnnotation.role().equals(role)){
                        if(familyRoleBudgetAnnotation.budget()>= spent){
                            try {
                                method.invoke(Family.class.newInstance(),spent,familyRoleBudgetAnnotation.budget());
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            }
                        }else {
                            System.out.println("Spent Over Budget Limit !!");
                        }
                    }
                }
            }
            nTests--;
        }

    }

}

@interface roleBudgetAnnotation{
    String role();
    int budget();
}

class Family{
    @roleBudgetAnnotation(role = "senior", budget = 1000)
    public void senior(int spent, int budget){
        System.out.println("SENIOR MEMBER");
        System.out.println("MONEY SPENT: " + spent);
        System.out.println("REMAIN OF BUDGET: " + (budget - spent));
    }
    @roleBudgetAnnotation(role = "junior", budget = 500)
    public void junior(int spent, int budget){
        System.out.println("JUNIOR MEMBER");
        System.out.println("MONEY SPENT: " + spent);
        System.out.println("REMAIN OF BUDGET: " + (budget - spent));
    }
}