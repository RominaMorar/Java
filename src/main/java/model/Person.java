package model;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.Set;

public class Person {
    @Size(min = 3, max = 12, message = "3<=firstName<=12")
    protected String firstName;
    @Size(min = 5, max = 12, message = "3<=firstName<=12")
    protected String lastName;
    @Max(value = 2022, message = "{Max.yearOfBirth}")
    protected int yearOfBirth;

    public Person() {
    }

    public Person(String firstName, String lastName, int yearOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
    }

    public static class Builder<T extends Builder<T>> {
        private String firstName;
        private String lastName;
        private int yearOfBirth;

        public Builder() {}

        public T firstName(String firstName){
            this.firstName = firstName;
            return (T) this;
        }

        public T lastName(String lastName){
            this.lastName = lastName;
            return (T) this;
        }

        public T yearOfBirth(int yearOfBirth){
            this.yearOfBirth = yearOfBirth;
            return (T) this;
        }

        public Person build() {
            return new Person(this);
        }

        public Person validate(Person obj) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Person>> violations = validator.validate(obj);
            if (violations.isEmpty()) {
                return obj;
            } else {
                StringBuilder sb = new StringBuilder();
                for (var violation : violations) {
                    sb.append(violation.getInvalidValue()).append(" : ").append(violation.getMessage());
                }
                return null;
            }
        }
    }

    protected Person(Builder<?> builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.yearOfBirth = builder.yearOfBirth;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    @Override
    public String toString() {
        return " Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;

        if (yearOfBirth != person.yearOfBirth) return false;
        if (!firstName.equals(person.firstName)) return false;
        return lastName.equals(person.lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + yearOfBirth;
        return result;
    }
}
