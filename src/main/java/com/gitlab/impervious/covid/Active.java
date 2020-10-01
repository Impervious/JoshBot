package com.gitlab.impervious.covid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Active {

    @SerializedName("active_cases")
    private Long activeCases;
    @SerializedName("active_cases_change")
    private Long activeCasesChange;
    @SerializedName("cumulative_cases")
    private Long cumulativeCases;
    @SerializedName("cumulative_deaths")
    private Long cumulativeDeaths;
    @SerializedName("cumulative_recovered")
    private Long cumulativeRecovered;
    @SerializedName("date_active")
    private String dateActive;
    @Expose
    private String province;

    public Long getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(Long activeCases) {
        this.activeCases = activeCases;
    }

    public Long getActiveCasesChange() {
        return activeCasesChange;
    }

    public void setActiveCasesChange(Long activeCasesChange) {
        this.activeCasesChange = activeCasesChange;
    }

    public Long getCumulativeCases() {
        return cumulativeCases;
    }

    public void setCumulativeCases(Long cumulativeCases) {
        this.cumulativeCases = cumulativeCases;
    }

    public Long getCumulativeDeaths() {
        return cumulativeDeaths;
    }

    public void setCumulativeDeaths(Long cumulativeDeaths) {
        this.cumulativeDeaths = cumulativeDeaths;
    }

    public Long getCumulativeRecovered() {
        return cumulativeRecovered;
    }

    public void setCumulativeRecovered(Long cumulativeRecovered) {
        this.cumulativeRecovered = cumulativeRecovered;
    }

    public String getDateActive() {
        return dateActive;
    }

    public void setDateActive(String dateActive) {
        this.dateActive = dateActive;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}