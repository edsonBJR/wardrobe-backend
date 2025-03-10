package com.wardrobe.model;

public class Vanity {

    private Long id;
    private Wardrobe wardrobe;

    public Vanity(Long id, Wardrobe wardrobe) {
        this.id = id;
        this.wardrobe = wardrobe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Wardrobe getWardrobe() {
        return wardrobe;
    }

    public void setWardrobe(Wardrobe wardrobe) {
        this.wardrobe = wardrobe;
    }
}
