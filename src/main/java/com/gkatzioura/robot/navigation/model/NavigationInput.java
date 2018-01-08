package com.gkatzioura.robot.navigation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gkatzioura.robot.navigation.payload.NavigationRequest;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

@Entity
public class NavigationInput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column
    @Convert(converter = NavigationRequestConverter.class)
    private NavigationRequest navigationRequest;

    @OneToOne(mappedBy = "input", fetch = FetchType.LAZY, cascade = ALL)
    @JsonIgnore
    private NavigationOutput output;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NavigationRequest getNavigationRequest() {
        return navigationRequest;
    }

    public void setNavigationRequest(NavigationRequest navigationRequest) {
        this.navigationRequest = navigationRequest;
    }

    public NavigationOutput getOutput() {
        return output;
    }

    public void setOutput(NavigationOutput output) {
        this.output = output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NavigationInput that = (NavigationInput) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
