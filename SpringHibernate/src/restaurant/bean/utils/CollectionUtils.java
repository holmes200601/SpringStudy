package restaurant.bean.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CollectionUtils {
    public static class MapKeyValueArray {
        List<String> keys = new ArrayList<String>();
        List<Object> values = new ArrayList<Object>();

        public MapKeyValueArray(final Map<String, Object> map) {
            if (map == null) {
                return;
            }

            for (Entry<String, Object> entry : map.entrySet()) {
                keys.add(entry.getKey());
                values.add(entry.getValue());
            }
        }

        public List<String> getKeys() {
            return this.keys;
        }

        public List<Object> getValues() {
            return this.values;
        }
    }

}
