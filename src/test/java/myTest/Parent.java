package myTest;

public class Parent {
    private String name;
    private Integer age;

    public Parent() {
        System.out.println("父类无参构造");
    }

    public Parent(String name, Integer age) {
        this.name = name;
        this.age = age;
        System.out.println("父类有参构造");
    }

    static {
        System.out.println("父类的静态代码块");
    }

    {
        System.out.println("父类代码块");
    }

    public void fun() {
        System.out.println("父类fun()方法");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
