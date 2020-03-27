package myTest;

public class Sequence {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long[] result = function(1200L);
        System.out.println("最优数列长度："+result.length);
        for (int i = 0; i <result.length ; i++) {
            System.out.print(result[i]+",");
        }
        long end = System.currentTimeMillis();
        System.out.println("\n执行时间："+(end-start)+"毫秒");
    }


    public static long[] function(long number) {
        int size = 2;//数列初始长度
        int maxSize = 0;//最优的数列长度
        int currentSize = 0;//根据第二个数算出来的长度
        long bestSecend = 0;//最优的第二个数
        for (long i = 1; i < number / 2; i++) {
            long second = number - i;
            currentSize = recursion(number, second, size);

            if (currentSize > maxSize) {
                maxSize = currentSize;
                bestSecend = second;
            }
        }

        long[] result = getResult(number, bestSecend, maxSize);
        return result;
    }

    public static int recursion(long number, long second, int size) {
        if (second >= number) {
            return size;
        }

        long third = number - second;//数列第三个数
        size++;
        return recursion(second, third, size);
    }

    public static long[] getResult(long number, long bestSecond, int maxSize) {
        long[] series = new long[maxSize];
        series[0] = number;
        series[1] = bestSecond;
        for (int i = 2; i < maxSize; i++) {
            series[i] = number - bestSecond;
            long temp = number;
            number = bestSecond;
            bestSecond = temp - bestSecond;
        }

        return series;
    }
}
