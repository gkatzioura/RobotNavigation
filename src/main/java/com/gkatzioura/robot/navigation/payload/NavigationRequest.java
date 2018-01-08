package com.gkatzioura.robot.navigation.payload;

public class NavigationRequest {

    private Integer[] roomSize;
    private Integer[] coords;
    private Integer[][] patches;
    private String instructions;

    public Integer[] getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(Integer[] roomSize) {
        this.roomSize = roomSize;
    }

    public Integer[] getCoords() {
        return coords;
    }

    public void setCoords(Integer[] coords) {
        this.coords = coords;
    }

    public Integer[][] getPatches() {
        return patches;
    }

    public void setPatches(Integer[][] patches) {
        this.patches = patches;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
