package serialize;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class JsonSerialize<T> implements ISerialize<T> {
    private ObjectMapper objectMapper;

    public JsonSerialize() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public void writeObject(String fileName, T object) {
        try {
            objectMapper.writeValue(new File(fileName), object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public T readObject(String fileName, Class<T> tClass) {
        try {
            var res = objectMapper.readValue(Paths.get(fileName).toFile(), tClass);
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
