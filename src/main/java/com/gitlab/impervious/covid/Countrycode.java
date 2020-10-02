package com.gitlab.impervious.covid;

import com.google.gson.annotations.Expose;

public class Countrycode {

    @Expose
    private String iso2;
    @Expose
    private String iso3;

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

}