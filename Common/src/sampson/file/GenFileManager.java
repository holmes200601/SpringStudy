package sampson.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import restaurant.utils.StringUtil;

public class GenFileManager {
    public static final Logger logger = LoggerFactory.getLogger(GenFileManager.class);

    /*
     * If the file already existed, this function simply
     * return it
     */
    public static Path createNewFile(String absoluteFilePath) {
        logger.debug("Creating file '{}'", absoluteFilePath);
        
        Path result = null;

        if (!StringUtil.isNullString(absoluteFilePath)) {
            File file = new File(absoluteFilePath);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    logger.error("Failed to create file '{}'", absoluteFilePath);
                    e.printStackTrace();
                }
            }
            result = file.toPath();
        }
        return result;
    }

    public static Path truncateStringToFile(Path path, String contentString) {
        logger.debug("Truncating string into file '{}'",  path.toString());
        
        try {
            Files.write(path, contentString.getBytes(), StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            logger.error("Failed to writing file '{}'", path.toString());
            e.printStackTrace();
        }

        return path;
    }
    
    public static void deleteFile(String absoluteFilePath) {
        logger.debug("Deleting file '{}'", absoluteFilePath);
        
        try {
            Files.deleteIfExists(new File(absoluteFilePath).toPath());
        } catch (IOException e) {
            logger.error("Failed to delete file '{}'", absoluteFilePath);
            e.printStackTrace();
        }
    }
}
