
package myTest;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Ppp {

	public static void main(String[] args) {

		Long[] result = max(1200L);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + ",");
		}

	}

	public static Long[] max(Long maxNum) {

		// 结果放在MAP中
		Map<Long, Integer> map = new HashMap<>();
		// 结果集
		List<Long> result = new ArrayList<>();
		result.add(maxNum);

		// 倒数第二个数
		// Long penult = maxNum-1;
		for (Long penult = maxNum - 1; penult > 0; penult--) {
			// 倒数第三个数
			Long antepenultimate = maxNum - penult;
			// 数列长度
			int size = 3;
			size = foo(penult, antepenultimate, size);
			map.put(penult, size);
		}

		// 输出map
		for (Entry<Long, Integer> set : map.entrySet()) {
			System.out.println(set.getKey() + "----" + set.getValue());
		}

		// 从map中取最优解
		int maxSize = 0;
		// 最优的倒数第二个数
		Long secend = 0L;
		for (Long init : map.keySet()) {
			int size = map.get(init);
			if (size > maxSize) {
				secend = init;
				maxSize = size;
			}
		}

		// 得到结果集
		Long[] result2 = getResult(maxNum, secend, result);

		return result2;
	}

	public static int foo(Long penult, Long antepenultimate, int size) {
		// Long initValue = penult;

		if (antepenultimate >= penult || antepenultimate <= 0) {
			return size;
		} else {
			size++;
			Long flag = penult;
			penult = antepenultimate;
			antepenultimate = flag - antepenultimate;
			return foo(penult, antepenultimate, size);
		}

	}

	public static Long[] getResult(Long maxNum, Long secend, List<Long> result) {

		if (secend >= maxNum) {
			result.add(secend);
			Long[] s = new Long[result.size()];
			result.toArray(s);
			return s;
		} else {
			result.add(secend);
			maxNum = secend;
			secend = maxNum - secend;
			return getResult(maxNum, secend, result);
		}

	}

}
