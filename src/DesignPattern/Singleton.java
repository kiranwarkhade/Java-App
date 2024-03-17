package DesignPattern;

public class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    
    public static void main(String[] arg)
    {
    	Singleton s=new Singleton();
    	s.getInstance();
    	System.out.println(s.getInstance());
    	
    }
}

