package model;

import exception.ValidationException;
import serialize.TxtFormat;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class Aspirant extends Student implements TxtFormat<Aspirant>, Serializable {
     @NotNull
     @Size(min = 5, max = 25, message = "5<=work<=25")
    private String work;

     public Aspirant(){

     }

    public Aspirant(String firstName, String lastName, int yearOfBirth, int group, double avgMark, boolean working, String work) {
        super(firstName, lastName, yearOfBirth, group, avgMark, working);
        this.work = work;
    }

    public static class Builder{
        private String firstName;
        private String lastName;
        private int yearOfBirth;
        int group;
        double avgMark;
        boolean working;

        String work;

        public Builder() {
        }

        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder yearOfBirth(int yearOfBirth){
            this.yearOfBirth = yearOfBirth;
            return this;
        }
        public Builder group(int group){
            this.group = group;
            return this;
        }

        public Builder avgMark(double avgMark){
            this.avgMark = avgMark;
            return this;
        }

        public Builder working(boolean working){
            this.working = working;
            return this;
        }
        public Builder Work(String work) {
            this.work = work;
            return this;
        }

        public Aspirant build() {
            return new Aspirant(this);
        }

        public Aspirant validate(Aspirant obj) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Aspirant>> violations = validator.validate(obj);
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

    protected Aspirant(Builder builder){
        super();
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.yearOfBirth = builder.yearOfBirth;
        this.group = builder.group;
        this.avgMark = builder.avgMark;
        this.working = builder.working;
        this.work = builder.work;
    }

    @Override
    public double getScholarship() {
        return (avgMark >= 90) ? 4000 : 3000;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    @Override
    public String toString() {
        return "Aspirant{" +
                "work='" + work + '\'' +
                '}' + super.toString() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aspirant aspirant)) return false;
        if (!super.equals(o)) return false;

        return work.equals(aspirant.work);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + work.hashCode();
        return result;
    }

    @Override
    public String toStringSerialize() {
        return "firstName=" + this.firstName +
                ",lastName=" + this.lastName +
                ",yearOfBirth=" + this.yearOfBirth +
                ",group=" + this.group +
                ",avgMark=" + this.avgMark +
                ",working=" + this.working +
                ",work=" + this.work;
    }

    @Override
    public Aspirant toObject(String string) throws ValidationException {
        String[] str = string.split(",");
        var values = new ArrayList<String>();
        for (String item : str) {
            String[] innerItem = item.split("=");
            if (innerItem.length == 2) {
                values.add(innerItem[1]);
            }
        }
        for (var i :
                values) {
            i.trim();
        }

        Aspirant aspirant = new Builder()
                .firstName(values.get(0))
                .lastName(values.get(1))
                .yearOfBirth(Integer.parseInt(values.get(2)))
                .group(Integer.parseInt(values.get(3)))
                .avgMark(Double.parseDouble(values.get(4)))
                .working(Boolean.parseBoolean(values.get(5)))
                .Work(values.get(6))
                .build();

        return aspirant;
    }
}
