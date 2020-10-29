package com.joe.concurrent.part4;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author ckh
 * @create 10/29/20 4:52 PM
 */
public class DelegatingVehicleTrackerTest {

    @Test
    public void getLocations() {
        HashMap<String, Point> pointHashMap = new HashMap<>();
        pointHashMap.put("t1",new Point(1,1));
        pointHashMap.put("t2",new Point(2,2));
        pointHashMap.put("t3",new Point(3,3));
        DelegatingVehicleTracker tracker = new DelegatingVehicleTracker(pointHashMap);

        // 该 locations 会随着点的变化而变化
        Map<String, Point> locations = tracker.getLocations();
        System.out.println("before locations: "+locations);

        Point point = tracker.getLocation("t1");
        System.out.println("point = " + point);
        tracker.setLocation("t1",6,6);
        System.out.println("after locations: "+locations);


    }
}