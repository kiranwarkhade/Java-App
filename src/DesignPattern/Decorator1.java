package DesignPattern;

interface Component {
    void operation();
}

class ConcreteComponent implements Component {
    public void operation() {
        System.out.println("ConcreteComponent operation");
    }
}

abstract class Decorator1 implements Component {
    private Component component;

  
    public Decorator1(Component component) {
        this.component = component;
    }

    public void operation() {
        component.operation();
    }
}

class ConcreteDecorator extends Decorator1 {
    public ConcreteDecorator(Component component) {
        super(component);
    }

    public void additionalOperation() {
        System.out.println("Additional operation");
    }

    public void operation() {
        super.operation();
        additionalOperation();
    }
       
}


