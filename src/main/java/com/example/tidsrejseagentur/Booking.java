package com.example.tidsrejseagentur;

public class Booking {
    private int id;
    private Timemachine timemachine;
    private Customer customer;
    private TimePeriod timePeriod;
    private Guide guide;

    public Booking(int id, Timemachine timemachine, Customer customer, TimePeriod timePeriod, Guide guide) {
        this.id = id;
        this.timemachine = timemachine;
        this.customer = customer;
        this.timePeriod = timePeriod;
        this.guide = guide;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timemachine getTimemachine() {
        return timemachine;
    }

    public void setTimemachine(Timemachine timemachine) {
        this.timemachine = timemachine;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public Guide getGuide() {
        return guide;
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
    }

    @Override
    public String toString() {
        return customer.getName() + " has booked " + timemachine.getName() + " with " + guide.getName() + " for " + timePeriod.getName();
    }
}
