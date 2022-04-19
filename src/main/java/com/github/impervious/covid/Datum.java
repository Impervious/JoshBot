package com.github.impervious.covid;

import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("change_cases")
    private Long changeCases;

    public Long getChangeCases() {
        return changeCases;
    }

    public static class Builder {

        private Long changeCases;

        public Datum.Builder withChangeCases(Long changeCases) {
            this.changeCases = changeCases;
            return this;
        }

        public Datum build() {
            Datum datum = new Datum();
            datum.changeCases = changeCases;
            return datum;
        }

    }

}