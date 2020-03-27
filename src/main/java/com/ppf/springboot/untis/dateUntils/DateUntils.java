package com.ppf.springboot.untis.dateUntils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUntils {

    public static String getPattern(String target) {
        String result = null;

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("[0-9]{4}年[0-9]{1,2}月", DatePattern.date11);
        hashMap.put("[0-9]{4}/[0-9]{1,2}", DatePattern.date12);
        hashMap.put("[0-9]{4}.[0-9]{1,2}", DatePattern.date13);
        hashMap.put("[0-9]{4}-[0-9]{1,2}", DatePattern.date14);

        hashMap.put("[0-9]{4}年[0-9]{1,2}月[0-9]{1,2}日", DatePattern.date21);
        hashMap.put("[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}", DatePattern.date22);
        hashMap.put("[0-9]{4}.[0-9]{1,2}.[0-9]{1,2}", DatePattern.date23);
        hashMap.put("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}", DatePattern.date24);

        hashMap.put("[0-9]{4}年[0-9]{1,2}月[0-9]{1,2}日[ ]{0,5}[0-9]{2}:[0-9]{2}", DatePattern.date31);
        hashMap.put("[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}[ ]{0,5}[0-9]{2}:[0-9]{2}", DatePattern.date32);
        hashMap.put("[0-9]{4}.[0-9]{1,2}.[0-9]{1,2}[ ]{0,5}[0-9]{2}:[0-9]{2}", DatePattern.date33);
        hashMap.put("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}[ ]{0,5}[0-9]{2}:[0-9]{2}", DatePattern.date34);

        hashMap.put("[0-9]{4}年[0-9]{1,2}月[0-9]{1,2}日[ ]{0,5}[0-9]{2}:[0-9]{2}:[0-9]{2}", DatePattern.date41);
        hashMap.put("[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}[ ]{0,5}[0-9]{2}:[0-9]{2}:[0-9]{2}", DatePattern.date42);
        hashMap.put("[0-9]{4}.[0-9]{1,2}.[0-9]{1,2}[ ]{0,5}[0-9]{2}:[0-9]{2}:[0-9]{2}", DatePattern.date43);
        hashMap.put("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}[ ]{0,5}[0-9]{2}:[0-9]{2}:[0-9]{2}", DatePattern.date44);


        Set<String> keySet = hashMap.keySet();
        for (String key : keySet) {
            if (target.matches(key)) {
                result = hashMap.get(key);
                return result;
            }
        }

        return result;
    }


}
