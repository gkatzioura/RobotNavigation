package com.gkatzioura.robot.navigation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gkatzioura.robot.navigation.payload.NavigationResponse;

import javax.persistence.*;

@Entity
public class NavigationOutput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column
    @Convert(converter = NavigationResponseConverter.class)
    private NavigationResponse navigationResponse;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private NavigationInput input;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NavigationResponse getNavigationResponse() {
        return navigationResponse;
    }

    public void setNavigationResponse(NavigationResponse navigationResponse) {
        this.navigationResponse = navigationResponse;
    }

    public NavigationInput getInput() {
        return input;
    }

    public void setInput(NavigationInput input) {
        this.input = input;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NavigationOutput that = (NavigationOutput) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
