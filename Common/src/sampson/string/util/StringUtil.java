package sampson.string.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {
    public static boolean isNullString(String str) {
        String tmpStr = new String(str);
        return (tmpStr == null || tmpStr.trim().isEmpty());
    }
    
    public static List<String> split(String srcStr, String token) {
        List<String> result = null;
        if (srcStr == null) {
            return Arrays.asList(srcStr);
        }
        
        String[] tmpResult = srcStr.split(token);
        result = new ArrayList<String>();
        for (String splitedStr : tmpResult) {
            result.add(splitedStr);
        }
        
        return result.stream().map(String::trim).collect(Collectors.toCollection(ArrayList<String>::new));
    }
}
