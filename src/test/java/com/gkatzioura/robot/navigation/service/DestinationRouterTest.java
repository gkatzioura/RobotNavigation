package com.gkatzioura.robot.navigation.service;

import com.gkatzioura.robot.navigation.model.Point;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DestinationRouterTest {

    @Test
    public void testSimpleRoute() {

        Point initialPoint = new Point(1,2);
        List<Point> patches = Arrays.asList(new Point(1,0),new Point(2,2),new Point(2,3));
        List<String> directions = Arrays.asList("NNESEESWNWW".split(""));

        DestinationRouter destinationRouter = new DestinationRouter(5,5);
        Pair<Point,Integer> result = destinationRouter.route(directions,patches,initialPoint);

        Assert.assertEquals(new Point(1,3),result.getLeft());
        Assert.assertEquals(new Integer(1),result.getRight());
    }

    @Test
    public void testCleanAllRoute() {

        Point initialPoint = new Point(1,2);
        List<Point> patches = Arrays.asList(new Point(1,0),new Point(2,2),new Point(2,3));
        List<String> directions = Arrays.asList("NNESEESWNWWSESSSSSW".split(""));

        DestinationRouter destinationRouter = new DestinationRouter(5,5);
        Pair<Point,Integer> result = destinationRouter.route(directions,patches,initialPoint);

        Assert.assertEquals(new Point(1,0),result.getLeft());
        Assert.assertEquals(new Integer(3),result.getRight());
    }

    @Test
    public void testComplexRoute() {

        Point initialPoint = new Point(3,3);
        List<Point> patches = Arrays.asList(new Point(1,6),new Point(3,3),new Point(1,1),new Point(3,5),new Point(7,7));
        List<String> directions = Arrays.asList("NNNNNNNWWWWWWWSESESESEEEEEESSSESESESESWNWNWWWSWSW".split(""));

        DestinationRouter destinationRouter = new DestinationRouter(7,7);
        Pair<Point,Integer> result = destinationRouter.route(directions,patches,initialPoint);

        Assert.assertEquals(new Point(0,0),result.getLeft());
        Assert.assertEquals(new Integer(4),result.getRight());
    }

}
