package controllers;

import db.MySQLDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ContestController {

    MySQLDriver dbDriver;

    public ContestController() {
        dbDriver = new MySQLDriver();
    }

    public void getContestEntryStats(String fromDate, String toDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date initialDate = null;
        Date finalDate = new Date();
        String query2;
        if (fromDate != null) {
            try {
                initialDate = sdf.parse(fromDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (toDate != null) {
                try {
                    finalDate = sdf.parse(toDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else {
                finalDate = new Date();
            }
            query2 = "SELECT * FROM panenka_db.contests_contest WHERE created_date BETWEEN '" + sdf.format(initialDate) + "' AND '" + sdf.format(finalDate) + "'";
        }
        else {
            if (toDate != null) {
                try {
                    finalDate = sdf.parse(toDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                query2 = "SELECT * FROM panenka_db.contests_contest WHERE created_date < '" + sdf.format(finalDate) + "'";
            }
            else {
                finalDate = new Date();
            }
            query2 = "SELECT * FROM panenka_db.contests_contest";
        }
        ResultSet result2 = dbDriver.runQuery(query2);
        ArrayList<Double> classicList = new ArrayList<>();
        ArrayList<Double> h2hList = new ArrayList<>();
        try {
            while (result2.next())
            {
                if (result2.getInt("game_mode_id") == 1) {
                    classicList.add(result2.getDouble("entry_fee"));
                }
                else if (result2.getInt("game_mode_id") == 2) {
                    h2hList.add(result2.getDouble("entry_fee"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("CONTESTS");
        double classicSum = 0;
        double h2hSum = 0;
        for (Double d : classicList) {
            classicSum += d;
        }
        for (Double d : h2hList) {
            h2hSum += d;
        }
        double totalSum = classicSum + h2hSum;
        System.out.println("   Classic: " + (classicSum * 0.1) + " (" + ((classicSum / totalSum) * 100) + "%)");
        System.out.println("   H2H: " + (h2hSum * 0.1) + " (" + ((h2hSum / totalSum) * 100) + "%)");
        System.out.println("   Total: " + (totalSum * 0.1));
    }

    public void getLastWeekContestStats() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -(calendar.get(Calendar.DAY_OF_WEEK) - 2));
        String initialDate = calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + " 00:00:00";
        String query = "SELECT * FROM panenka_db.contests_contest WHERE created_date BETWEEN '" + initialDate + "' AND '" + sdf.format(calendar.getTime()) + "'";
        ResultSet result = dbDriver.runQuery(query);
        ArrayList<Double>[] classicArray = new ArrayList[7];
        for (int i = 0 ; i < classicArray.length; i++) {
            classicArray[i] = new ArrayList<Double>();
        }
        ArrayList<Double>[] h2hArray = new ArrayList[7];
        for (int i = 0 ; i < h2hArray.length; i++) {
            h2hArray[i] = new ArrayList<Double>();
        }
        try {
            while (result.next())
            {
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(result.getDate("created_date"));
                if (result.getInt("game_mode_id") == 1) {
                    classicArray[calendar2.get(Calendar.DAY_OF_WEEK) - 1].add(result.getDouble("entry_fee"));
                }
                else if (result.getInt("game_mode_id") == 2) {
                    h2hArray[calendar2.get(Calendar.DAY_OF_WEEK) - 1].add(result.getDouble("entry_fee"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Double classicSunday = 0.0;
        for (Double b : classicArray[0]) {
            classicSunday += b;
        }
        Double classicMonday = 0.0;
        for (Double b : classicArray[1]) {
            classicMonday += b;
        }
        Double classicTuesday = 0.0;
        for (Double b : classicArray[2]) {
            classicTuesday += b;
        }
        Double classicWednesday = 0.0;
        for (Double b : classicArray[3]) {
            classicWednesday += b;
        }
        Double classicThursday = 0.0;
        for (Double b : classicArray[4]) {
            classicThursday += b;
        }
        Double classicFriday = 0.0;
        for (Double b : classicArray[5]) {
            classicFriday += b;
        }
        Double classicSaturday = 0.0;
        for (Double b : classicArray[6]) {
            classicSaturday += b;
        }

        Double h2hSunday = 0.0;
        for (Double b : h2hArray[0]) {
            h2hSunday += b;
        }
        Double h2hMonday = 0.0;
        for (Double b : h2hArray[1]) {
            h2hMonday += b;
        }
        Double h2hTuesday = 0.0;
        for (Double b : h2hArray[2]) {
            h2hTuesday += b;
        }
        Double h2hWednesday = 0.0;
        for (Double b : h2hArray[3]) {
            h2hWednesday += b;
        }
        Double h2hThursday = 0.0;
        for (Double b : h2hArray[4]) {
            h2hThursday += b;
        }
        Double h2hFriday = 0.0;
        for (Double b : h2hArray[5]) {
            h2hFriday += b;
        }
        Double h2hSaturday = 0.0;
        for (Double b : h2hArray[6]) {
            h2hSaturday += b;
        }
        Double totalMonday = classicMonday + h2hMonday;
        Double totalTuesday = classicTuesday + h2hTuesday;
        Double totalWednesday = classicWednesday + h2hWednesday;
        Double totalThursday = classicThursday + h2hThursday;
        Double totalFriday = classicFriday + h2hFriday;
        Double totalSaturday = classicSaturday + h2hSaturday;
        Double totalSunday = classicSunday + h2hSunday;
        System.out.println("CLASSICS | HEAD-TO-HEAD");
        System.out.println("   Monday: " + classicMonday + " (" + ((classicMonday / totalMonday) * 100) + "%) | " + h2hMonday + " (" + ((h2hMonday / totalMonday) * 100) + "%)");
        System.out.println("   Tuesday: " + classicTuesday + " (" + ((classicTuesday / totalTuesday) * 100) + "%) | " + h2hTuesday + " (" + ((h2hTuesday / totalTuesday) * 100) + "%)");
        System.out.println("   Wednesday: " + classicWednesday + " (" + ((classicWednesday / totalWednesday) * 100) + "%) | " + h2hWednesday + " (" + ((h2hWednesday / totalWednesday) * 100) + "%)");
        System.out.println("   Thursday: " + classicThursday + " (" + ((classicThursday / totalThursday) * 100) + "%) | " + h2hThursday + " (" + ((h2hThursday / totalThursday) * 100) + "%)");
        System.out.println("   Friday: " + classicFriday + " (" + ((classicFriday / totalFriday) * 100) + "%) | " + h2hFriday + " (" + ((h2hFriday / totalFriday) * 100) + "%)");
        System.out.println("   Saturday: " + classicSaturday + " (" + ((classicSaturday / totalSaturday) * 100) + "%) | " + h2hSaturday + " (" + ((h2hSaturday / totalSaturday) * 100) + "%)");
        System.out.println("   Sunday: " + classicSunday + " (" + ((classicSunday / totalSunday) * 100) + "%) | " + h2hSunday + " (" + ((h2hSunday / totalSunday) * 100) + "%)");
    }

}
