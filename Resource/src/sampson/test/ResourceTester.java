package sampson.test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
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

    @Value("${tempDirectory}")
    private String tempDirectory;

    private UrlResource urlResource;

    private FileSystemResource fileSystemResource;

    private ClassPathResource classPathResource;

    public void prepareTest() {
        /*
         * 1) Create file with read content. 2) Create the
         * resources accordingly.
         */
        logger.debug("ResourceTester::prepareTest");

        Path path = GenFileManager.createNewFile(tempDirectory + testFileName);
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

        logger.debug("ResourceTester::executeTest");
        // Test file system resource
        logger.info("***FileSystemResources test {}.***", testResourceInternal(fileSystemResource) ? "PASSED" : "FAILED");
        
        // Test class pat resource
        logger.info("***ClassPathResources test {}.***", testResourceInternal(classPathResource) ? "PASSED" : "FAILED");

    }

    public void clearTest() {
        /*
         * Clear all the created file from above resources
         */
        GenFileManager.deleteFile(tempDirectory + testFileName);
        GenFileManager.deleteFile(fileSystemResource.getPath());
        GenFileManager.deleteFile(classPathResource.getPath());

    }

    private void createUrlResource(Path path) {
        logger.debug("Creating URL resource '{}'", path.toString());

    }

    private void createFileSystemResource(Path path) {
        logger.debug("Creating file system resource '{}'", path.toString());

        /*
         * 1) Find directory of the the class 2) Create a
         * file in the directory 2) Create
         * filesystemresource in the directory
         */
        String parentPath = getParentOfTargetClass(this.getClass());
        if (parentPath == null) {
            logger.error("Null parent path was found.");
        }

        Path filePath = GenFileManager.createNewFile(parentPath + "/fileSystemResource.txt");
        GenFileManager.truncateStringToFile(filePath, testFileContent);
        fileSystemResource = new FileSystemResource(filePath.toFile());
        logger.debug("FileSystemResource was created successfully.");
    }

    private void createClassPathResource(Path path) {
        logger.debug("Creating classpath resource '{}'", path.toString());
        
        // 1) Create class path resource
        String parentPath = getParentOfTargetClass(this.getClass());
        if (parentPath == null) {
            logger.error("Null parent path was found.");
        }
        Path filePath = GenFileManager.createNewFile(parentPath + "/classPathResource.txt");
        GenFileManager.truncateStringToFile(filePath, testFileContent);
        classPathResource = new ClassPathResource("sampson/test/classPathResource.txt");
    }

    private String getParentOfTargetClass(Class<?> clazz) {
        String result = null;

        if (clazz != null) {
            String classPath = clazz.getName().replace('.', '/').concat(".class");
            String filePath = clazz.getClassLoader().getResource(classPath).getPath();
            result = filePath.substring(0, filePath.lastIndexOf('/'));
        }

        return result;
    }
    
    private boolean testResourceInternal(Resource resource) {
        boolean result = false;
        
        if (resource.exists()) {
            try (InputStream input = resource.getInputStream()) {
                byte[] readBytes = new byte[testFileContent.length()];
                input.read(readBytes);
                result =  testFileContent.equals(new String(readBytes, Charset.forName("US-ASCII")));
            } catch (IOException e) {
                logger.error("Can not read content for resource");
                e.getMessage();
            }
        }
        
        return result;
    }

}
