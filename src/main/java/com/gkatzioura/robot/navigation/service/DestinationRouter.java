package com.gkatzioura.robot.navigation.service;

import com.gkatzioura.robot.navigation.model.Point;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class DestinationRouter {

    private Integer xAxes;
    private Integer yAxes;

    public DestinationRouter(Integer xAxes,Integer yAxes) {
        this.xAxes = xAxes;
        this.yAxes = yAxes;
    }

    public Pair<Point,Integer> route(List<String> directions,List<Point> patches,Point initialPoint) {

        Set<Point> patchSet = new HashSet<>(patches);
        Integer matches = 0;

        /**
         * The hoover is always on, thus we must check the initial location
         */
        if(patchSet.remove(initialPoint)) matches++;

        return move(directions,patchSet,initialPoint,matches);
    }

    private Pair<Point,Integer> move(List<String> directions, Set<Point> patchesLeft, Point point, Integer matches) {

        if(directions.size()==0) {

            return new ImmutablePair<>(point,matches);
        } else {

            String head = directions.get(0);
            List<String> tail = directions.size()==1? Collections.EMPTY_LIST: directions.subList(1,directions.size());
            Point newPoint;

            switch (head) {
                case "N":
                    newPoint = moveNorth(point);
                    break;
                case "W":
                    newPoint = moveWest(point);
                    break;
                case "E":
                    newPoint = moveEast(point);
                    break;
                case "S":
                    newPoint = moveSouth(point);
                    break;
                default:
                    throw new IllegalArgumentException("No such direction");
            }

            if(patchesLeft.remove(newPoint)) matches++;
            return move(tail, patchesLeft, newPoint,matches);
        }
    }

    private Point moveNorth(Point point) {

        int y;

        /**
         * When driving on a wall the robot should skid in place,
         * Thus once the north side wall is reached the y coordinate
         * should be the same
         */
        if(point.getY()+1>=yAxes) {
            y = point.getY();
        } else {
            y = point.getY()+1;
        }

        return new Point(point.getX(),y);
    }

    private Point moveWest(Point point) {

        int x;

        /**
         * When driving on a wall the robot should skid in place,
         * Thus once the west side wall is reached the x coordinate
         * should remain the same
         */
        if(point.getX()-1<0) {
            x = 0;
        } else {
            x = point.getX()-1;
        }

        return new Point(x,point.getY());
    }

    private Point moveEast(Point point) {

        int x;

        /**
         * When driving on a wall the robot should skid in place,
         * Thus once the east side wall is reached the x coordinate
         * should remain the same
         */
        if(point.getX()+1>=xAxes) {
            x = point.getX();
        } else {
            x = point.getX()+1;
        }

        return new Point(x,point.getY());
    }

    private Point moveSouth(Point point) {

        int y;

        /**
         * When driving on a wall the robot should skid in place,
         * Thus once the south side wall is reached the x coordinate
         * should remain the same
         */
        if(point.getY()-1<0) {
            y = 0;
        } else {
            y = point.getY()-1;
        }

        return new Point(point.getX(),y);
    }

}
