package com.gitlab.impervious.covid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.google.gson.annotations.Expose;

public class COVIDMain {

    public COVIDMain() throws Exception {
    }

    private static String readURL() throws Exception {
        BufferedReader reader = null;

        LocalDate date = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            URL url = new URL("https://api.opencovid.ca/timeseries?stat=cases&loc=ON&date=" + date.format(dateFormatter));
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder builder = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                builder.append(chars, 0, read);
            }
            return builder.toString();
        } finally {
            if(reader != null) {
                reader.close();
            }
        }
    }

    public String jsonURL = readURL();

    @Expose
    private List<Active> active;
    @Expose
    private List<Case> cases;
    @Expose
    private List<Mortality> mortality;
    @Expose
    private List<Recovered> recovered;
    @Expose
    private List<Testing> testing;

    public List<Active> getActive() {
        return active;
    }

    public void setActive(List<Active> active) {
        this.active = active;
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }

    public List<Mortality> getMortality() {
        return mortality;
    }

    public void setMortality(List<Mortality> mortality) {
        this.mortality = mortality;
    }

    public List<Recovered> getRecovered() {
        return recovered;
    }

    public void setRecovered(List<Recovered> recovered) {
        this.recovered = recovered;
    }

    public List<Testing> getTesting() {
        return testing;
    }

    public void setTesting(List<Testing> testing) {
        this.testing = testing;
    }
}