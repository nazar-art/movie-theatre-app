package net.lelyak.edu.tests.util;


import net.lelyak.edu.entity.Event;

import java.util.HashMap;

public class AspectTestUtil {

    public void printCounter(HashMap<Event, Long> counter) {
        System.out.println("\n printCounter: ");

        for (Event e : counter.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Event '")
                    .append(e.getName())
                    .append("', count: ")
                    .append(counter.get(e).toString());
            System.out.println(sb);
        }
        System.out.println("=========================================================");
    }

    public boolean compareEventCounts(HashMap<Event, Long> first, HashMap<Event, Long> second) {
        for (Event event : first.keySet()) {
            System.out.println(" Event '" + event.getName() + "', fisrt=" + first.get(event) + ", second=" + second.get(event));
            if (first.get(event).compareTo(second.get(event)) != 0) {
                System.out.println("Fail: " + first.get(event) + " != " + second.get(event));
                return false;
            }
        }
        System.out.println(" fisrt.size=" + first.size() + " second.size=" + second.size());
        return (first.size() == second.size());
    }
}
