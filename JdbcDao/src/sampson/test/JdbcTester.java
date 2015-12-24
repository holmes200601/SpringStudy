package sampson.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import sampson.jdbcdao.Actor;
import sampson.jdbcdao.JdbcDao;

public class JdbcTester implements Tester {
    private static final Logger logger = LoggerFactory.getLogger(JdbcTester.class);
    
    @Autowired
    private JdbcDao jdbcDao;
    
    @Override
    public void prepareTest() {
        // Nothing to do

    }

    @Override
    public void executeTest() {
        String queryStr = "select * from actor a where a.actor_id = :id";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", 1L);
        
        List<Actor> resultList = jdbcDao.queryForObject(queryStr, paramMap, new RowMapper<Actor>() {
            @Override
            public Actor mapRow(ResultSet rs, int idx) throws SQLException {
                Actor actor = new Actor();
                actor.setId(rs.getLong("actor_id"));
                actor.setFirstName(rs.getString("first_name"));
                actor.setLastName(rs.getString("last_name"));
                
                return actor;
            }
            
        });

        logger.info("Test result for JdbcTester is ****{}****", (resultList.size() == 1) ? "Success" : "Failed");
    }

    @Override
    public void clearTest() {
        

    }

}
