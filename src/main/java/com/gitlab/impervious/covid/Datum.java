package com.gitlab.impervious.covid;

import com.google.gson.annotations.Expose;

public class Datum {

    @Expose
    private Long confirmed;
    @Expose
    private Countrycode countrycode;
    @Expose
    private String countryregion;
    @Expose
    private Long deaths;
    @Expose
    private String lastupdate;
    @Expose
    private Location location;
    @Expose
    private String provincestate;

    public Long getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Long confirmed) {
        this.confirmed = confirmed;
    }

    public Countrycode getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(Countrycode countrycode) {
        this.countrycode = countrycode;
    }

    public String getCountryregion() {
        return countryregion;
    }

    public void setCountryregion(String countryregion) {
        this.countryregion = countryregion;
    }

    public Long getDeaths() {
        return deaths;
    }

    public void setDeaths(Long deaths) {
        this.deaths = deaths;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getProvincestate() {
        return provincestate;
    }

    public void setProvincestate(String provincestate) {
        this.provincestate = provincestate;
    }

}