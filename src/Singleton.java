public class Singleton {

    private static Singleton instance;

    public String str;

    private Singleton(){}

    public static Singleton getSingletonInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}
class Main1{
    public static void main(String[] args){
        Singleton singleton = Singleton.getSingletonInstance();
        singleton.str = "hello world";
        Singleton singleton1 = Singleton.getSingletonInstance();
        System.out.println(singleton.str);
    }
}

