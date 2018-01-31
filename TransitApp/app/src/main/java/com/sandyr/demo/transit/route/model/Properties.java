package com.sandyr.demo.transit.route.model;



import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//TODO: replace serializable with pracelable
public class Properties implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("available_bikes")
    @Expose
    private Integer availableBikes;
    @SerializedName("companies")
    @Expose
    private List<Company> companies = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAvailableBikes() {
        return availableBikes;
    }

    public void setAvailableBikes(Integer availableBikes) {
        this.availableBikes = availableBikes;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

}
