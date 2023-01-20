package serialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;

import java.io.File;
import java.io.IOException;

public class XmlSerialize<T> implements ISerialize<T> {
    private ObjectMapper objectMapper;

    public XmlSerialize() {
        JacksonXmlModule xmlModule = new JacksonXmlModule();
        xmlModule.setDefaultUseWrapper(false);
        objectMapper = new com.fasterxml.jackson.dataformat.xml.XmlMapper(xmlModule);
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
            var res = objectMapper.readValue(new File(fileName), tClass);
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
