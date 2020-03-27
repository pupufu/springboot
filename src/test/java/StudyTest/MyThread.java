package StudyTest;

public class MyThread {

    public static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException, CloneNotSupportedException {


        Runnable t1 = ()->{
            try {
                synchronized (obj) {
                    System.out.println("111111111111");
                    obj.wait();
                    System.out.println("333333333333");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable t2 = ()->{
            synchronized (obj) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("22222222222");
                obj.notify();
            }
        };
        new Thread(t1).start();
        new Thread(t2).start();

    }


}
