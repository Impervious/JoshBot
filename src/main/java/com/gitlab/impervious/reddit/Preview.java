package com.gitlab.impervious.reddit;

import java.util.List;

import com.google.gson.annotations.Expose;

public class Preview {

    @Expose
    private Boolean enabled;
    @Expose
    private List<Image> images;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

}