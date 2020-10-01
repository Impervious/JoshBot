package com.gitlab.impervious.covid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mortality {

    @SerializedName("cumulative_deaths")
    private Long cumulativeDeaths;
    @SerializedName("date_death_report")
    private String dateDeathReport;
    @Expose
    private Long deaths;
    @Expose
    private String province;

    public Long getCumulativeDeaths() {
        return cumulativeDeaths;
    }

    public void setCumulativeDeaths(Long cumulativeDeaths) {
        this.cumulativeDeaths = cumulativeDeaths;
    }

    public String getDateDeathReport() {
        return dateDeathReport;
    }

    public void setDateDeathReport(String dateDeathReport) {
        this.dateDeathReport = dateDeathReport;
    }

    public Long getDeaths() {
        return deaths;
    }

    public void setDeaths(Long deaths) {
        this.deaths = deaths;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}