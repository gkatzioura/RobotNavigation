package com.gkatzioura.robot.navigation.repository;

import com.gkatzioura.robot.navigation.model.NavigationOutput;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutputRepository extends JpaRepository<NavigationOutput,Long> {
}
