
package com.github.impervious.covid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.google.gson.annotations.Expose;
import lombok.SneakyThrows;

public class COVIDMain {

    public COVIDMain() throws Exception {
    }

    private static String readURL() throws Exception {
        BufferedReader reader = null;

        LocalDate date = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            URL url = new URL("https://api.covid19tracker.ca/reports/province/on?stat=cases&date=" + date.format(dateFormatter));
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
    private List<Datum> data;
    @Expose
    private String province;

    public List<Datum> getData() {
        return data;
    }

    public String getProvince() {
        return province;
    }

    public static class Builder {

        private List<Datum> data;
        private String province;

        public COVIDMain.Builder withData(List<Datum> data) {
            this.data = data;
            return this;
        }

        public COVIDMain.Builder withProvince(String province) {
            this.province = province;
            return this;
        }

        @SneakyThrows
        public COVIDMain build() {
            COVIDMain cOVIDMain = new COVIDMain();
            cOVIDMain.data = data;
            cOVIDMain.province = province;
            return cOVIDMain;
        }

    }

}