package api.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class FileUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T readJsonFile(String path, Class<T> tClass){
        try {
            return objectMapper.readValue(FileUtil.class.getClassLoader().getResourceAsStream(path), tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
