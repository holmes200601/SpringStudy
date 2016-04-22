package restaurant.utils;

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

    public static class Pair {
        private Object key;
        private Object value;

        public Pair(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return key;
        }

        public void setKey(Object key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

}
