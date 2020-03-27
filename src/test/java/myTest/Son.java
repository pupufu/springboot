package myTest;

public class Son extends Parent {

    public Son() {
        System.out.println("子类无参构造");
    }

    public Son(String name, Integer age) {
        super(name, age);
        System.out.println("子类有参构造");
    }

    static {
        System.out.println("子类静态代码块");
    }

    {
        System.out.println("子类代码块");
    }

    @Override
    public void fun() {
        System.out.println("子类fun()方法");
    }

    public void eat() {
        System.out.println("子类eat()方法");
    }
}
//456