public class Employee {
    private String fullName;
    private String position;
    private int age;
    private int salary;

    /**
     * Setter methods
     */
    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String firstname, String lastname) {
        this.fullName = firstname + " " + lastname;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * Getter methods
     */
    public int getAge() {
        return age;
    }

    public String getName() {
        return fullName;
    }

    public int getSalary() {
        return salary;
    }

    public String getPosition() {
        return position;
    }

    /**
     * Print employee information
     */
    public void printEmployee() {
        System.out.printf("%-17s %4d %17s %,10d\n", getName(), getAge(), getPosition(), getSalary());
    }
}
