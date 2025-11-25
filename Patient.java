public class Patient {
    private String patientId;
    private String name;
    private int age;
    private String illness;

    public Patient(String patientId, String name, int age, String illness) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.illness = illness;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }
}
