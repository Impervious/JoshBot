package com.gitlab.impervious.covid;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Testing {

    @SerializedName("cumulative_testing")
    private Long cumulativeTesting;
    @SerializedName("date_testing")
    private String dateTesting;
    @Expose
    private String province;
    @Expose
    private Long testing;
    @SerializedName("testing_info")
    private String testingInfo;

    public Long getCumulativeTesting() {
        return cumulativeTesting;
    }

    public void setCumulativeTesting(Long cumulativeTesting) {
        this.cumulativeTesting = cumulativeTesting;
    }

    public String getDateTesting() {
        return dateTesting;
    }

    public void setDateTesting(String dateTesting) {
        this.dateTesting = dateTesting;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Long getTesting() {
        return testing;
    }

    public void setTesting(Long testing) {
        this.testing = testing;
    }

    public String getTestingInfo() {
        return testingInfo;
    }

    public void setTestingInfo(String testingInfo) {
        this.testingInfo = testingInfo;
    }

}