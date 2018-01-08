package com.gkatzioura.robot.navigation.service;

import com.gkatzioura.robot.navigation.model.NavigationInput;
import com.gkatzioura.robot.navigation.model.NavigationOutput;
import com.gkatzioura.robot.navigation.model.Point;
import com.gkatzioura.robot.navigation.payload.NavigationRequest;
import com.gkatzioura.robot.navigation.payload.NavigationResponse;
import com.gkatzioura.robot.navigation.repository.InputRepository;
import com.gkatzioura.robot.navigation.repository.OutputRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NavigationService {

    @Autowired
    private InputRepository navigationEntryRepository;

    @Autowired
    private OutputRepository navigationResultRepository;

    @Transactional
    public NavigationResponse navigate(NavigationRequest navigationRequest) {

        NavigationResponse navigationResponse = calculateResult(navigationRequest);
        saveIO(navigationRequest,navigationResponse);
        return navigationResponse;
    }

    private NavigationResponse calculateResult(NavigationRequest navigationRequest) {

        List<String> directions = extractDirections(navigationRequest);
        List<Point> patches = extractPatches(navigationRequest);
        Point initialPoint = extractInitialPoint(navigationRequest);

        Integer xAxes = navigationRequest.getRoomSize()[0];
        Integer yAxes = navigationRequest.getRoomSize()[1];

        DestinationRouter destinationRouter = new DestinationRouter(xAxes,yAxes);
        Pair<Point,Integer> result = destinationRouter.route(directions,patches,initialPoint);

        NavigationResponse navigationResponse = new NavigationResponse();
        navigationResponse.setCoords(new Integer[]{result.getLeft().getX(),result.getLeft().getY()});
        navigationResponse.setPatches(result.getRight());

        return navigationResponse;
    }

    private void saveIO(NavigationRequest navigationRequest,NavigationResponse navigationResponse) {

        NavigationInput input = new NavigationInput();
        input.setNavigationRequest(navigationRequest);
        navigationEntryRepository.save(input);

        NavigationOutput output = new NavigationOutput();
        output.setNavigationResponse(navigationResponse);
        output.setInput(input);
        navigationResultRepository.save(output);
    }

    private List<String> extractDirections(NavigationRequest navigationRequest) {

        return Arrays.asList(navigationRequest.getInstructions().split(""));
    }

    private List<Point> extractPatches(NavigationRequest navigationRequest) {

        return Arrays.asList(navigationRequest.getPatches())
                .stream().map(p->new Point(p[0],p[1]))
                .collect(Collectors.toList());
    }

    private Point extractInitialPoint(NavigationRequest navigationRequest) {

        return new Point(navigationRequest.getCoords());
    }

}
