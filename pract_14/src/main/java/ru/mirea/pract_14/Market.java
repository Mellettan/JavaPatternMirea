package ru.mirea.pract_14;

public class Market {
    private String name;
    private String address;

    public Market(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Market) obj).getName()) &&
                this.address.equals(((Market) obj).getAddress());
    }
}
