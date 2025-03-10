package com.wardrobe.model;

public class Cloth {

    private Long id;
    private String color;
    private String size;
    private String style;

    public Cloth(Long id, String color, String size, String style) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.style = style;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
