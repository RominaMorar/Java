package serialize;


public interface ISerialize<T> {
    void writeObject(String fileName, T object);

    T readObject(String fileName, Class<T> tClass);
}
