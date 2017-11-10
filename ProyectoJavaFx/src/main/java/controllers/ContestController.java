package controllers;

import db.MySQLDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ContestController {

    MySQLDriver dbDriver;

    public ContestController() {
        dbDriver = new MySQLDriver();
    }

    public HashMap<String, Double> getContestEntryStats(String fromDate, String toDate) {
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
        double classicSum = 0;
        double h2hSum = 0;
        for (Double d : classicList) {
            classicSum += d;
        }
        for (Double d : h2hList) {
            h2hSum += d;
        }
        double totalSum = classicSum + h2hSum;
        HashMap<String, Double> data = new HashMap<>();
        data.put("Classic", classicSum);
        data.put("Head-to-head", h2hSum);
        return data;
    }

    public HashMap<String, HashMap<String, Double>> getLastWeekContestStats() {
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

        HashMap<String, Double> classicData = new HashMap<>();
        classicData.put("Monday", classicMonday);
        classicData.put("Tuesday", classicTuesday);
        classicData.put("Wednesday", classicWednesday);
        classicData.put("Thursday", classicThursday);
        classicData.put("Friday", classicFriday);
        classicData.put("Saturday", classicSaturday);
        classicData.put("Sunday", classicSunday);

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
        HashMap<String, Double> h2hData = new HashMap<>();
        h2hData.put("Monday", h2hMonday);
        h2hData.put("Tuesday", h2hTuesday);
        h2hData.put("Wednesday", h2hWednesday);
        h2hData.put("Thursday", h2hThursday);
        h2hData.put("Friday", h2hFriday);
        h2hData.put("Saturday", h2hSaturday);
        h2hData.put("Sunday", h2hSunday);
        HashMap<String, HashMap<String, Double>> resultData = new HashMap<>();
        resultData.put("Classic", classicData);
        resultData.put("Head-to-head", h2hData);
        return resultData;
    }

}
