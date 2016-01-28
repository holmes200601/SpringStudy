package sampson.test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.xml.sax.SAXException;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import sampson.file.XmlFileParser;
import sampson.jdbcdao.Customer;
import sampson.jdbcdao.Eshop;
import sampson.jdbcdao.TableGenerator;

public class JdbcTester implements Tester {
    private static final Logger logger = LoggerFactory.getLogger(JdbcTester.class);

    @Autowired
    Environment env;

    @Autowired
    private NamedParameterJdbcTemplate npds;

    @Autowired
    private MysqlConnectionPoolDataSource dataSource;

    @Override
    public void prepareTest() {
        /* Read classes from configuration file */
        List<Class<?>> tableClasses = readBeanClasses();

        /* Create corresponding table for each class */
        TableGenerator tableCreator = new TableGenerator(npds, tableClasses);
        tableCreator.createTable();
    }

    @Override
    public void executeTest() {

        LobHandler lobHandler = new DefaultLobHandler();

        /* Test Eshop */
        logger.info("Test Eshop ***{}***", testEshop(lobHandler) ? "success" : "failed");
        /* Test Customer */
        logger.info("Test Eshop ***{}***", testCustomer(lobHandler) ? "success" : "failed");
        /* Test Employee */
        //logger.info("Test Eshop ***{}***", testEmployee(lobHandler) ? "success" : "failed");
        /* Test Product */
        //logger.info("Test Eshop ***{}***", testProduct(lobHandler) ? "success" : "failed");
        /* Test SalesOrder */
        //logger.info("Test Eshop ***{}***", testSalesOrder(lobHandler) ? "success" : "failed");
    }

    @Override
    public void clearTest() {

    }

    private boolean testEshop(final LobHandler lobHandler) {
        String image = env.getProperty("eshop-image");

        /* Create eshop */
        final Eshop eshop = new Eshop();
        eshop.setLocation("Shanghai");
        eshop.setName("Business");
        eshop.setLogoImage(Image2Byte(image));

        /* Insert into database */
        final String insertSql = "INSERT INTO eshop (location, name, logoImage) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        npds.getJdbcOperations().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(insertSql, new String[] { "id" });
                ps.setString(1, eshop.getLocation());
                ps.setString(2, eshop.getName());
                lobHandler.getLobCreator().setBlobAsBytes(ps, 3, eshop.getLogoImage());

                return ps;
            }

        }, keyHolder);
        eshop.setId(keyHolder.getKey().longValue());

        /* Read from database */
        String readSql = "SELECT * from eshop where id = :id";
        Eshop readEshop = npds.queryForObject(readSql,
                (new MapSqlParameterSource()).addValue("id", keyHolder.getKey().longValue(), Types.BIGINT),
                new RowMapper<Eshop>() {

                    @Override
                    public Eshop mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Eshop result = new Eshop();
                        result.setId(rs.getLong("id"));
                        result.setName(rs.getString("name"));
                        result.setLocation(rs.getString("location"));
                        result.setLogoImage(lobHandler.getBlobAsBytes(rs, "logoImage"));

                        return result;
                    }
                });

        /* Compare read object with created object */
        boolean result = true;
        result = result && (eshop.getId().compareTo(readEshop.getId()) == 0);
        result = result && (eshop.getName().equals(readEshop.getName()));
        result = result && (eshop.getLocation().equals(readEshop.getLocation()));
        result = result && printImage(env.getProperty("eshop-image-output"), readEshop.getLogoImage());
        
        
        return result;
    }
    
    private boolean testCustomer(final LobHandler lobHandler) {
        String image = env.getProperty("customer-image");
        
        /* Create customer */
        final Customer customer = new Customer();
        customer.setName("Sampson");
        customer.setCustomerCode("SMP");
        customer.setBalance(BigDecimal.ZERO);
        customer.setImage(Image2Byte(image));
        
        /* Insert into database */
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String insertSql = "INSERT INTO customer (name, customerCode, balance, image) VALUES (?, ?, ?, ?)";
        npds.getJdbcOperations().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(insertSql, new String[] {"id"});
                ps.setString(1, customer.getName());
                ps.setString(2, customer.getCustomerCode());
                ps.setBigDecimal(3, customer.getBalance());
                lobHandler.getLobCreator().setBlobAsBytes(ps, 4, customer.getImage());
                
                return ps;
            }
            
        }, keyHolder);
        customer.setId(keyHolder.getKey().longValue());
        
        /* Read from database */
        Customer readCustomer = npds.queryForObject(
                "SELECT * FROM customer cs where cs.id = :id",
                new BeanPropertySqlParameterSource(customer),
                new RowMapper<Customer>() {
                    @Override
                    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Customer result = new Customer();
                        result.setId(rs.getLong("id"));
                        result.setName(rs.getString("name"));
                        result.setCustomerCode(rs.getString("customerCode"));
                        result.setBalance(rs.getBigDecimal("balance"));
                        result.setImage(lobHandler.getBlobAsBytes(rs, "image"));
                        
                        return result;
                    }
                    
                });
        
        /* Compare read object with created object */
        boolean result = true;
        result = result && (customer.getId().compareTo(readCustomer.getId()) == 0);
        result = result && (customer.getName().equals(readCustomer.getName()));
        result = result && (customer.getCustomerCode().equals(readCustomer.getCustomerCode()));
        result = result && (customer.getBalance().compareTo(readCustomer.getBalance()) == 0);
        result = result && (printImage(env.getProperty("customer-image-output"), readCustomer.getImage()));
    
        return result;
    }    

    private boolean printImage(String location, byte[] imageData) {
        final File file = new File(location);
        if (file.exists()) {
            try (FileWriter fw = new FileWriter(file)) {
                fw.write("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        try (InputStream is = new ByteArrayInputStream(imageData)){
            BufferedImage bi = ImageIO.read(is);
            ImageIO.write(bi, "PNG", file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return true;
    }
    
    private byte[] Image2Byte(String path) {
        File iFile = new File(path);
        byte[] result = null;
        
        try (InputStream is = new FileInputStream(iFile)) {
            result = FileUtils.readFileToByteArray(iFile);
            int readByteNum = is.read(result, 0, (int)iFile.length());
            logger.info("Read byte number from file is: {}", readByteNum);
        } catch (IOException e) {
            logger.error("No file was found with the path '{}'", path);
            e.printStackTrace();
        }

        return result;
    }

    private List<Class<?>> readBeanClasses() {
        List<Class<?>> result = new ArrayList<Class<?>>();

        String tableClassTag = env.getProperty("bean-class-tag");
        String tableClassFile = env.getProperty("bean-class-register");
        List<String> tableClasses = readXmlTagValue(tableClassFile, tableClassTag);
        for (String clazz : tableClasses) {
            try {
                result.add(Class.forName(clazz));
            } catch (ClassNotFoundException e) {
                logger.error("Failed to convert to class from '{}'", clazz);
                e.printStackTrace();
            }
        }

        return result;
    }

    private List<String> readXmlTagValue(String file, String tag) {
        List<String> result = null;

        try {
            XmlFileParser fileReader = new XmlFileParser(file);
            result = fileReader.getElementsValueByTagName(tag);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.error("Failed to read tag '{}' from file '{}'", tag, file);
            e.printStackTrace();
        }

        return result;
    }

}
