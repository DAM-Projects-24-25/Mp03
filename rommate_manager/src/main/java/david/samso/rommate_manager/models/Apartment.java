package david.samso.rommate_manager.models;

public class Apartment {
    private int id;
    private String address;
    private double rent;

    public Apartment(int id, String address, double rent) {
        this.id = id;
        this.address = address;
        this.rent = rent;
    }

    // Getters i Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public double getRent() { return rent; }
    public void setRent(double rent) { this.rent = rent; }
}
