package sampson.jdbcdao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class JdbcDao {
    private NamedParameterJdbcTemplate jt;
    
    public void setDataSource(DataSource ds) {
        jt = new NamedParameterJdbcTemplate(ds);
    }
    
    public <T> List<T> queryForObject(String sql, Map<String, ?> paramMap, RowMapper<T> rowMapper) {
        return jt.query(sql, paramMap, rowMapper);
    }
    
}
