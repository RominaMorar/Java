package model;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class Student extends Person {
    @NotNull
    @Min(value = 100, message = "{Min.group}")
    @Max(value = 700, message = "{Max.group}")
    protected int group;

    @Max(value = 100, message = "{Max.avgMark}")
    @Min(value = 35, message = "{Min.avgMark}")
    protected double avgMark;
    @AssertTrue
    protected boolean working;

    public Student(String firstName, String lastName, int yearOfBirth, int group, double avgMark, boolean working) {
        super(firstName, lastName, yearOfBirth);
        this.group = group;
        this.avgMark = avgMark;
        this.working = working;
    }

    public Student() {

    }

    public static class Builder extends Person.Builder<Builder> {
        int group;
        double avgMark;
        boolean working;

        public Builder() {
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

        public Student build() {
            return new Student(this);
        }

        private Student validate() throws IllegalArgumentException {

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Student student = new Student(this);

            Set<ConstraintViolation<Student>> violations = validator.validate(student);

            StringBuilder mb = new StringBuilder();

            for (ConstraintViolation<Student> violation : violations) {
                mb.append("Error for field " + violation.getPropertyPath() + ": '"+ violation.getInvalidValue() + " " + violation.getMessage()).append("\n");
            }

            if (mb.length() > 0) {
                throw new IllegalArgumentException(mb.toString());
            }
            return student;
        }
    }

    protected Student(Builder builder){
        super(builder);
        this.group = builder.group;
        this.avgMark = builder.avgMark;
        this.working = builder.working;
    }

    public int getGroup() {
        return group;
    }

    public double getAvgMark() {
        return avgMark;
    }

    public boolean isWorking() {
        return working;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void setAvgMark(double avgMark) {
        this.avgMark = avgMark;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public boolean isBachelor()
    {
        return (group / 100) <= 4;
    }


    public boolean isMaster()
    {
        return (group / 100) >= 4;
    }

    public double getScholarship()
    {
        return (avgMark >= 90) ? 2000 : 1000;
    }

    @Override
    public String toString() {
        return " Student{" +
                "group=" + group +
                ", avgMark=" + avgMark +
                ", working=" + working +
                '}' + super.toString() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        if (!super.equals(o)) return false;

        if (group != student.group) return false;
        if (Double.compare(student.avgMark, avgMark) != 0) return false;
        return working == student.working;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + group;
        temp = Double.doubleToLongBits(avgMark);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (working ? 1 : 0);
        return result;
    }
}
