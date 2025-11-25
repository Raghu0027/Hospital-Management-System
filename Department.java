public class Department {
    private String name;
    private String head;
    private int numberOfStaff;

    public Department(String name, String head, int numberOfStaff) {
        this.name = name;
        this.head = head;
        this.numberOfStaff = numberOfStaff;
    }

    public String getName() {
        return name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public int getNumberOfStaff() {
        return numberOfStaff;
    }
}
