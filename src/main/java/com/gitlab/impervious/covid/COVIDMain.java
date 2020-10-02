package com.gitlab.impervious.covid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import com.google.gson.annotations.Expose;

public class COVIDMain {

    public COVIDMain() throws Exception {
    }

    private static String readURL() throws Exception {
        BufferedReader reader = null;

        try {
            URL url = new URL("https://covid-19-report-api.now.sh/api/v1/cases/latest?iso=CA&province=Ontario");
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
    private Long count;
    @Expose
    private List<Datum> data;
    @Expose
    private String lastUpdate;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}