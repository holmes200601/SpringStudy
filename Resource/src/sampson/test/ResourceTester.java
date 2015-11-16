package sampson.test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
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
		 * 1) Create file with read content. 2) Create the resources
		 * accordingly.
		 */
		logger.debug("ResourceTester::prepareTest");

		try {
			Path path = GenFileManager.createNewFile((new URI("../temp/" + "hello")).getPath());
			path = GenFileManager.truncateStringToFile(path, testFileContent);

			createUrlResource(path);

			createFileSystemResource(path);

			createClassPathResource(path);
		} catch (URISyntaxException e) {
			logger.error("Failed to create temp file.");
			e.printStackTrace();
		}

	}

	public void executeTest() {
		/*
		 * 1) Test FileSystemResource by reading content from that resource 2)
		 * Test ClassPathResource by reading content from that resource 3) Test
		 * URLResource by reading content from that resource
		 */

		logger.debug("ResourceTester::executeTest");

		if (fileSystemResource.getFile().exists()) {
			try {
				String readContent = new String(Files.readAllBytes(fileSystemResource.getFile().toPath()));
				logger.info("***FileSystemResources test {}.***",
						testFileContent.equals(readContent) ? "PASSED" : "FAILED");
			} catch (IOException ex) {
				logger.error("Can not read content of fileSystemResource");
				ex.printStackTrace();
			}
		}

	}

	public void clearTest() {
		/*
		 * Clear all the created file from above resources
		 */
		GenFileManager.deleteFile("C:\\temp\\" + testFileName);
		GenFileManager.deleteFile(fileSystemResource.getPath());

	}

	private void createUrlResource(Path path) {
		logger.debug("Creating URL resource '{}'", path.toString());

	}

	private void createFileSystemResource(Path path) {
		logger.debug("Creating file system resource '{}'", path.toString());

		/*
		 * 1) Find directory of the the class 2) Create a file in the directory
		 * 2) Create filesystemresource in the directory
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

}
