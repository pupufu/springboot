package myTest;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

public class Coming {

    public static void main(String[] args) {
        foo(19999999);
    }


    public static void foo(Integer number) {
        System.out.println("最优的数列为：");
        long startTime = System.currentTimeMillis();

        int maxSize = 0;
        int bestSecend = 0;

        for (int i = 1; i <= number / 2; i++) {
            int secend = number - i;
            int thrid = number - secend;
            int minSize = 3;
            int currentSize = getSize(secend, thrid, minSize);
            if (currentSize > maxSize) {
                maxSize = currentSize;
                bestSecend = secend;
            }
        }
        print(number, bestSecend, maxSize);
        long endTime = System.currentTimeMillis();
        System.out.println("执行时间为：" + (endTime - startTime)+"毫秒");
    }

    private static int getSize(int secend, int thrid, int minSize) {
        if (thrid >= secend) {
            return minSize;
        }
        minSize++;

        return getSize(thrid, secend - thrid, minSize);
    }

    public static void print(int number, int secend, int maxSize) {

        int[] arr = new int[maxSize];
        arr[0] = number;
        arr[1] = secend;
        for (int i = 2; i < maxSize; i++) {
            arr[i] = arr[i - 2] - arr[i - 1];
        }
        for (int i = 0; i < maxSize; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println();
    }


}
