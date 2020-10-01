package com.gitlab.impervious.covid;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recovered {

    @SerializedName("cumulative_recovered")
    private Long cumulativeRecovered;
    @SerializedName("date_recovered")
    private String dateRecovered;
    @Expose
    private String province;
    @Expose
    private Long recovered;

    public Long getCumulativeRecovered() {
        return cumulativeRecovered;
    }

    public void setCumulativeRecovered(Long cumulativeRecovered) {
        this.cumulativeRecovered = cumulativeRecovered;
    }

    public String getDateRecovered() {
        return dateRecovered;
    }

    public void setDateRecovered(String dateRecovered) {
        this.dateRecovered = dateRecovered;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Long getRecovered() {
        return recovered;
    }

    public void setRecovered(Long recovered) {
        this.recovered = recovered;
    }

}