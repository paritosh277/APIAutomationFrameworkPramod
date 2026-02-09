package com.thetestingacademy.pojos;

public class Booking {

    //Use this website for cretaing pojo class - https://www.jsonschema2pojo.org/
    //Just copy the response and see the magic

    private String additionalneeds;
    private Bookingdates bookingdates;
    private Boolean depositpaid;
    private String firstname;
    private String lastname;
    private Integer totalprice;

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    public Bookingdates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(Bookingdates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public Boolean getDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "additionalneeds='" + additionalneeds + '\'' +
                ", bookingdates=" + bookingdates +
                ", depositpaid=" + depositpaid +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                '}';
    }
}
