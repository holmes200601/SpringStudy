package sampson.jdbcdao;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.ReflectionUtils;

public class TableGenerator {
    private NamedParameterJdbcTemplate jt;
    private List<Class<?>> tableClasses;
    private Map<String, String> simpleMapper;

    public TableGenerator(NamedParameterJdbcTemplate jt, List<Class<?>> tableClasses) {
        this.jt = jt;
        this.tableClasses = tableClasses;
        initSimpleMapper();
    }

    public void createTable() {
        for (Class<?> clazz : tableClasses) {
            /* Extract table name */
            String tableName = clazz.getSimpleName();
            
            /* Resemble the create sql string */
            final StringBuilder[] sqlBuilder = {new StringBuilder("CREATE TABLE " + tableName + " (")};
            
            ReflectionUtils.doWithFields(clazz, 
                    new ReflectionUtils.FieldCallback() {
                        @Override
                        public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                            String sqlStr = getSqlStringForProperty(field, field.getDeclaredAnnotation(Property.class));
                            sqlBuilder[0].append(sqlStr).append(", ");                            
                        }
                    }, 
                    new ReflectionUtils.FieldFilter() {
                        @Override
                        public boolean matches(Field field) {
                            return field.getDeclaredAnnotation(Property.class) != null;                            
                        }
                    });
           
            /* Erase the last token */
            sqlBuilder[0] = sqlBuilder[0].delete(sqlBuilder[0].length() - 2, sqlBuilder[0].length());
            /* Add the close ')' */
            sqlBuilder[0] = sqlBuilder[0].append(");");
            
            jt.update(sqlBuilder[0].toString(), new HashMap<String, Object>());
        }
    }

    private String getSqlStringForProperty(Field field, Property propType) {
        StringBuilder result = new StringBuilder(field.getName() + " ");

        String sqlType = JavaType2SqlType(field.getType(), propType.type());
        result = result.append(sqlType);
        if (propType.primary()) {
            result = result.append(" auto_increment, primary key(" + field.getName() + ")");
        }

        return result.toString();
    }

    private String JavaType2SqlType(Class<?> javaType, String propType) {
        String result = "VARCHAR(127)";

        if (propType.isEmpty()) {
            result = simpleMapper.get(javaType.getName());
        } else {
            result = propType;
        }

        return result;
    }

    private void initSimpleMapper() {
        simpleMapper = new HashMap<String, String>();

        simpleMapper.put(Long.class.getName(), "BIGINT");
        simpleMapper.put(Integer.class.getName(), "INTEGER");
        simpleMapper.put(String.class.getName(), "VARCHAR(255)");
        simpleMapper.put(Enum.class.getName(), "CHAR(127)");
        simpleMapper.put(Double.class.getName(), "DOUBLE");
        simpleMapper.put(BigDecimal.class.getName(), "DECIMAL");
    }
}
