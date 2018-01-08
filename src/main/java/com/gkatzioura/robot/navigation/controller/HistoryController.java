package com.gkatzioura.robot.navigation.controller;

import com.gkatzioura.robot.navigation.model.NavigationInput;
import com.gkatzioura.robot.navigation.model.NavigationOutput;
import com.gkatzioura.robot.navigation.repository.InputRepository;
import com.gkatzioura.robot.navigation.repository.OutputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("history")
public class HistoryController {

    @Autowired
    private InputRepository entryRepository;

    @Autowired
    private OutputRepository resultRepository;

    @GetMapping("input")
    public List<NavigationInput> input(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size) {

        return entryRepository.findAll(PageRequest.of(page,size)).getContent();
    }

    @GetMapping("input/{inputId}/output")
    @Transactional
    public NavigationOutput getOutputForInput(@PathVariable("inputId") Long inputId) {

        return entryRepository.findById(inputId).get().getOutput();
    }

    @GetMapping("output")
    public List<NavigationOutput> output(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size) {

        return resultRepository.findAll(PageRequest.of(page,size)).getContent();
    }

    @GetMapping("output/{outputId}/input")
    @Transactional
    public NavigationInput getInputForOutput(@PathVariable("outputId") Long outputId) {

        return resultRepository.findById(outputId).get().getInput();
    }

}
