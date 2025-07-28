package cpe121.karlvince.lab7_1;

import static kvx.cli.Std.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

interface Comparison<T> {
    boolean isGreater(T a, T b);
    boolean isLess(T a, T b);
    boolean isEqual(T a, T b);
}

class Time {
    private int hour, minute, second;
    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    public int toSeconds() {
        return hour * 3600 + minute * 60 + second;
    }
    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}

class TimeComparison implements Comparison<Time> {
    public boolean isGreater(Time a, Time b) { return a.toSeconds() > b.toSeconds(); }
    public boolean isLess(Time a, Time b) { return a.toSeconds() < b.toSeconds(); }
    public boolean isEqual(Time a, Time b) { return a.toSeconds() == b.toSeconds(); }
}

public class REYES_LE_7_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        clear();
        print("Enter first time (hh mm ss): ");
        String[] t1 = in.readLine().split(" ");
        Time time1 = new Time(Integer.parseInt(t1[0]), Integer.parseInt(t1[1]), Integer.parseInt(t1[2]));
        print("Enter second time (hh mm ss): ");
        String[] t2 = in.readLine().split(" ");
        Time time2 = new Time(Integer.parseInt(t2[0]), Integer.parseInt(t2[1]), Integer.parseInt(t2[2]));
        TimeComparison cmp = new TimeComparison();
        print("\nComparing %s and %s\n", time1, time2);
        if (cmp.isGreater(time1, time2)) {
            print("First time is greater.\n");
        } else if (cmp.isLess(time1, time2)) {
            print("Second time is greater.\n");
        } else {
            print("Both times are equal.\n");
        }
    }
}
