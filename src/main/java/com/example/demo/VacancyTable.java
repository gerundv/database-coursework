package com.example.demo;

public class VacancyTable {
    private String vac_id;
    private String start_salary;
    private String title;
    private String org_id;

    public String getVac_id() {
        return vac_id;
    }

    public void setVac_id(String vac_id) {
        this.vac_id = vac_id;
    }

    public String getStart_salary() {
        return start_salary;
    }

    public void setStart_salary(String start_salary) {
        this.start_salary = start_salary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public VacancyTable(String vac_id, String start_salary, String title, String org_id) {
        this.vac_id = vac_id;
        this.start_salary = start_salary;
        this.title = title;
        this.org_id = org_id;
    }
}
