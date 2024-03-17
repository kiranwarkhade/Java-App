package DesignPattern;

interface Product {
    void create();
}

class ConcreteProduct implements Product {
    public void create() {
        System.out.println("ConcreteProduct created");
    }
}

abstract class Creator {
    public abstract Product factoryMethod();
}

class ConcreteCreator extends Creator {
	 public static void main(String[] arg)
     {  	
		 ConcreteProduct pro=new ConcreteProduct();
		 pro.create();
		
     }
	
    public Product factoryMethod() {
        return new ConcreteProduct();
       
    }
}
