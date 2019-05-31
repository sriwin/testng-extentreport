package com.sriwin.automation.utils;

import com.sriwin.automation.constants.DateConstants;
import com.sriwin.automation.exceptions.CoreException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtil implements DateConstants {

    public static String getFormattedDate(String fromFormat, String toFormat, String inputDate) {
        try {
            DateFormat fromDateFormat = new SimpleDateFormat(fromFormat);
            DateFormat toDateFormat = new SimpleDateFormat(toFormat);
            Date fromDate = fromDateFormat.parse(inputDate);
            String outputDate = toDateFormat.format(fromDate);
            return outputDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getNewDateByDateInd(String dateFormat, int field, int value) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.set(field, value);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        String currentTime = simpleDateFormat.format(rightNow.getTime());
        return currentTime.toUpperCase();
    }


    public static String getDate(String dateFormat) {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        String currentTime = simpleDateFormat.format(rightNow.getTime());
        //System.out.println("getDate :: " + currentTime.toUpperCase());
        return currentTime.toUpperCase();
    }

    public static String addDays(String dateFormat, int addDays) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.add(Calendar.DATE, addDays);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        String currentTime = simpleDateFormat.format(rightNow.getTime());
        return currentTime.toUpperCase();
    }

    public static String addMonths(String dateFormat, int addMonths) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.add(Calendar.MONTH, addMonths);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        String currentTime = simpleDateFormat.format(rightNow.getTime());
        return currentTime.toUpperCase();
    }

    public static String addYears(String dateFormat, int addYears) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.add(Calendar.YEAR, addYears);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        String currentTime = simpleDateFormat.format(rightNow.getTime());
        return currentTime.toUpperCase();
    }

    public static LocalDate addDays(int addDays) {
        LocalDate nowDate = LocalDate.now();
        LocalDate futureDate = (addDays < 0) ?
                nowDate.minusDays(addDays) :
                nowDate.plusDays(addDays);
        return futureDate;
    }

    public static LocalDate addMonths(int addMonths) {
        LocalDate nowDate = LocalDate.now();
        LocalDate futureDate = (addMonths < 0) ?
                nowDate.minusMonths(addMonths) :
                nowDate.plusMonths(addMonths);
        return futureDate;
    }

    public static LocalDate addYears(int addYears) {
        LocalDate nowDate = LocalDate.now();
        LocalDate futureDate = (addYears < 0) ?
                nowDate.minusYears(addYears) :
                nowDate.minusYears(addYears);
        return futureDate;
    }

    public static String format(LocalDate localDate, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        String dateStr = localDate.format(dateTimeFormatter);
        return dateStr;
    }


    /**
     * This method will add three hours
     *
     * @param dateFormat
     * @return
     */
    public static String getLclEtdTm(String dateFormat, int addHours) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.add(Calendar.HOUR, addHours);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        String currentTime = simpleDateFormat.format(rightNow.getTime());
        return currentTime.toUpperCase();
    }


    public static String getDateExpFormat(String originalDate, String strExpFormat) {
        try {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format2 = new SimpleDateFormat(strExpFormat);
            Date date = format1.parse(originalDate);
            return format2.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public String getDate(String args, int min) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateInString = args;
        Date date = df.parse(dateInString);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, min + 60480);
        String newTime = df.format(cal.getTime());
        return newTime;
    }

    public String usingDateFormatterWithTimeZone(long input) {
        {
            Date date = new Date(input);
            Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
            sdf.setCalendar(cal);
            cal.setTime(date);
            return sdf.format(date);
        }
    }

    /**
     * @param inputFormat  = MMM/yyyy
     * @param outputFormat = MM/dd/yyyy
     * @param inputValue   = FEB/2020
     * @return = 02/29/2020
     * @throws CoreException
     */
    public static String getTotalDaysInMonthDate(String inputFormat, String outputFormat, String inputValue)
            throws CoreException, CoreException {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(inputFormat);
            Date date = simpleDateFormat.parse(inputValue);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);

            //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM").withLocale(Locale.ENGLISH);
            //TemporalAccessor temporalAccessor = dateTimeFormatter.parse("APR");
            //int month = temporalAccessor.get(ChronoField.MONTH_OF_YEAR);

            LocalDate localDate = LocalDate.of(year, month, 1);
            int totalDays = localDate.lengthOfMonth();

            localDate = LocalDate.of(year, month, totalDays);
            String formattedDate = localDate.format(DateTimeFormatter.ofPattern(outputFormat));
            return formattedDate;

        } catch (ParseException e) {
            throw ExceptionUtil.handleCoreException(e);
        }
    }

    public static Calendar parseDate(String inputFormat, String inputValue) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(inputFormat);
            Date date = simpleDateFormat.parse(inputValue);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            return null;
        }
    }

}