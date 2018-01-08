package com.gkatzioura.robot.navigation.repository;

import com.gkatzioura.robot.navigation.model.NavigationInput;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputRepository extends JpaRepository<NavigationInput,Long> {
}
