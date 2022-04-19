package com.github.impervious.reddit;

import com.google.gson.annotations.Expose;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RedditMain {

    public RedditMain() throws Exception {
    }

    private static String readURL() throws Exception {
        HttpURLConnection conn;
        BufferedReader reader = null;

        try {
            URL url = new URL("https://www.reddit.com/r/gamedeals/search.json?q=site:steampowered.com&sort=top&restrict_sr=1&t=day&include_over_18=on");
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestProperty("User-Agent", "JoshBot, operated by /u/Impervioos");
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
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
    private Data data;
    @Expose
    private String kind;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

}