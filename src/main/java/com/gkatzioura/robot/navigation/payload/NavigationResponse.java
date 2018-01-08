package com.gkatzioura.robot.navigation.payload;

public class NavigationResponse {

    private Integer[] coords;
    private Integer patches;

    public Integer[] getCoords() {
        return coords;
    }

    public void setCoords(Integer[] coords) {
        this.coords = coords;
    }

    public Integer getPatches() {
        return patches;
    }

    public void setPatches(Integer patches) {
        this.patches = patches;
    }
}
