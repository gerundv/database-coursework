package com.example.demo;

public class OrganizationTable {

    private String org_id;
    private String address;
    private String fam_boss;
    private String city_id;
    private String ph_number;


    public OrganizationTable(String org_id, String address, String fam_boss, String city_id, String ph_number) {
        this.org_id = org_id;
        this.address = address;
        this.fam_boss = fam_boss;
        this.city_id = city_id;
        this.ph_number = ph_number;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFam_boss() {
        return fam_boss;
    }

    public void setFam_boss(String fam_boss) {
        this.fam_boss = fam_boss;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getPh_number() {
        return ph_number;
    }

    public void setPh_number(String ph_number) {
        this.ph_number = ph_number;
    }


}
