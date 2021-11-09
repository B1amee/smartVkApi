package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    @JsonSerialize(as = Address.class)
    private Address address;
    private String phone;
    private String website;
    @JsonSerialize(as = Company.class)
    private Company company;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    @JsonIgnore
    public String getCompanyName() {
        return company.getName();
    }
    @JsonIgnore
    public String getCompanyCatchPhrase() {
        return company.getCatchPhrase();
    }
    @JsonIgnore
    public String getCompanyBs() {
        return company.getBs();
    }
    @JsonIgnore
    public String getStreet() {
        return address.getStreet();
    }
    @JsonIgnore
    public String getSuite() {
        return address.getSuite();
    }
    @JsonIgnore
    public String getCity() {
        return address.getCity();
    }
    @JsonIgnore
    public String getZipcode() {
        return address.getZipcode();
    }
    @JsonIgnore
    public String getLat() {
        return address.getLat();
    }
    @JsonIgnore
    public String getLng() {
        return address.getLng();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", company=" + company +
                '}';
    }
}
