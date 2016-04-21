package restaurant.utils;

public class ReflectionUtilsPlus {
    public static <T> T newInstance(Class<T> clazz) {
        T obj = null;

        if (clazz != null) {
            try {
                obj = clazz.newInstance();
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return obj;
    }
}
