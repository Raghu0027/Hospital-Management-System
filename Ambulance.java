public class Ambulance {
    private String id;
    private String driverName;
    private boolean isAvailable;

    public Ambulance(String id, String driverName) {
        this.id = id;
        this.driverName = driverName;
        this.isAvailable = true; // By default, ambulance is available
    }

    public String getId() {
        return id;
    }

    public String getDriverName() {
        return driverName;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
