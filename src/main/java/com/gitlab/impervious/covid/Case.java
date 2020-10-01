package com.gitlab.impervious.covid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Case {

    @Expose
    private Long cases;
    @SerializedName("cumulative_cases")
    private Long cumulativeCases;
    @SerializedName("date_report")
    private String dateReport;
    @Expose
    private String province;

    public Long getCases() {
        return cases;
    }

    public void setCases(Long cases) {
        this.cases = cases;
    }

    public Long getCumulativeCases() {
        return cumulativeCases;
    }

    public void setCumulativeCases(Long cumulativeCases) {
        this.cumulativeCases = cumulativeCases;
    }

    public String getDateReport() {
        return dateReport;
    }

    public void setDateReport(String dateReport) {
        this.dateReport = dateReport;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}