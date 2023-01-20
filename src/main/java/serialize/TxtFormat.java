package serialize;

import exception.ValidationException;

public interface TxtFormat<T> {
    String toStringSerialize();
    T toObject(String string) throws ValidationException;
}
