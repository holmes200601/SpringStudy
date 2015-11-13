package sampson.test;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;

import sampson.file.GenFileManager;

public class ResourceTester implements Tester {
    private static final Logger logger = LoggerFactory.getLogger(ResourceTester.class);

    @Autowired
    private ApplicationContext ctx;

    @Value("${name}")
    private String testFileName;

    @Value("${content}")
    private String testFileContent;

    private UrlResource urlResource;

    private FileSystemResource fileSystemResource;

    private ClassPathResource classPathResource;

    public void prepareTest() {
        /*
         * 1) Create file with read content. 2) Create the
         * resources accordingly.
         */
        logger.info("ResourceTester::prepareTest");

        Path path = GenFileManager.createNewFile("C:\\temp\\" + testFileName);
        path = GenFileManager.truncateStringToFile(path, testFileContent);
        
        createUrlResource(path);
        
        createFileSystemResource(path);
        
        createClassPathResource(path);
    }

    public void executeTest() {
        /*
         * 1) Test FileSystemResource by reading content
         * from that resource 2) Test ClassPathResource by
         * reading content from that resource 3) Test
         * URLResource by reading content from that resource
         */

        logger.info("ResourceTester::executeTest");
    }

    public void clearTest() {
        /*
         * Clear all the created file from above resources
         */
        GenFileManager.deleteFile("C:\\temp\\" + testFileName);

    }
    
    private void createUrlResource(Path path) {
        logger.debug("Creating URL resource '{}'", path.toString());
        
    }

    private void createFileSystemResource(Path path) {
        logger.debug("Creating file system resource '{}'", path.toString());
        
    }
    
    private void createClassPathResource(Path path) {
        logger.debug("Creating classpath resource '{}'", path.toString());
        
    }
    
}
