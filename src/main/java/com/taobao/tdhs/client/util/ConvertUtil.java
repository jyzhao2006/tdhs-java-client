/*
 * Copyright(C) 2011-2012 Alibaba Group Holding Limited
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License version 2 as
 *  published by the Free Software Foundation.
 *
 *  Authors:
 *    wentong <wentong@taobao.com>
 */

package com.taobao.tdhs.client.util;

import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author <a href="mailto:wentong@taobao.com">文通</a>
 * @since 12-1-13 下午2:42
 */
public final class ConvertUtil {

	public static ThreadLocal<DateFormat> dateFormatTheadLocal = new ThreadLocal<DateFormat>();
    public static String toHex(byte b) {
        return ("" + "0123456789ABCDEF".charAt(0xf & b >> 4) + "0123456789ABCDEF"
                .charAt(b & 0xf));
    }

    public static boolean getBooleanFromString(String v) {
        if (StringUtils.isBlank(v)) {
            return false;
        }
        int c = Character.toLowerCase(v.charAt(0));
        return !(c == 'f' || c == 'n' || c == '0');
    }


    public static byte getByteFromString(String stringVal) throws SQLException {

        if (StringUtils.isBlank(stringVal)) {
            return (byte) 0;
        }
        stringVal = stringVal.trim();

        try {
            int decimalIndex = stringVal.indexOf(".");

            if (decimalIndex != -1) {
                double valueAsDouble = Double.parseDouble(stringVal);
                return (byte) valueAsDouble;
            }

            long valueAsLong = Long.parseLong(stringVal);

            return (byte) valueAsLong;
        } catch (NumberFormatException NFE) {
            throw new SQLException("Parse byte value error:" + stringVal);
        }
    }


    public static short getShortFromString(String stringVal) throws SQLException {
        if (StringUtils.isBlank(stringVal)) {
            return 0;
        }
        try {
            int decimalIndex = stringVal.indexOf(".");

            if (decimalIndex != -1) {
                double valueAsDouble = Double.parseDouble(stringVal);
                return (short) valueAsDouble;
            }

            return Short.parseShort(stringVal);
        } catch (NumberFormatException e) {
            throw new SQLException("Parse integer error:" + stringVal);
        }
    }

    public static int getIntFromString(String stringVal) throws SQLException {
        if (StringUtils.isBlank(stringVal)) {
            return 0;
        }
        try {
            int decimalIndex = stringVal.indexOf(".");

            if (decimalIndex != -1) {
                double valueAsDouble = Double.parseDouble(stringVal);
                return (int) valueAsDouble;
            }

            return Integer.parseInt(stringVal);
        } catch (NumberFormatException e) {
            throw new SQLException("Parse integer error:" + stringVal);
        }
    }

    public static long getLongFromString(String stringVal) throws SQLException {
        if (StringUtils.isBlank(stringVal)) {
            return 0;
        }
        try {
            int decimalIndex = stringVal.indexOf(".");

            if (decimalIndex != -1) {
                double valueAsDouble = Double.parseDouble(stringVal);
                return (long) valueAsDouble;
            }

            return Long.parseLong(stringVal);
        } catch (NumberFormatException e) {
            throw new SQLException("Parse integer error:" + stringVal);
        }
    }

    public static float getFloatFromString(String stringVal) throws SQLException {
        if (StringUtils.isBlank(stringVal)) {
            return 0;
        }
        try {
            return Float.parseFloat(stringVal);
        } catch (NumberFormatException e) {
            throw new SQLException("Parse integer error:" + stringVal);
        }
    }

    public static double getDoubleFromString(String stringVal) throws SQLException {
        if (StringUtils.isBlank(stringVal)) {
            return 0;
        }
        try {
            return Double.parseDouble(stringVal);
        } catch (NumberFormatException e) {
            throw new SQLException("Parse integer error:" + stringVal);
        }
    }

    public static BigDecimal getBigDecimalFromString(String stringVal,
                                                     int scale) throws SQLException {
        BigDecimal bdVal;

        if (stringVal != null) {
            if (stringVal.length() == 0) {
                bdVal = new BigDecimal("0");
                try {
                    return bdVal.setScale(scale);
                } catch (ArithmeticException ex) {
                    try {
                        return bdVal.setScale(scale, BigDecimal.ROUND_HALF_UP);
                    } catch (ArithmeticException arEx) {
                        throw new SQLException(
                                "ResultSet.Bad_format_for_BigDecimal: value="
                                        + stringVal + ",scale=" + scale);
                    }
                }
            }
            try {
                try {
                    return new BigDecimal(stringVal).setScale(scale);
                } catch (ArithmeticException ex) {
                    try {
                        return new BigDecimal(stringVal).setScale(scale,
                                BigDecimal.ROUND_HALF_UP);
                    } catch (ArithmeticException arEx) {
                        throw new SQLException(
                                "ResultSet.Bad_format_for_BigDecimal: value="
                                        + stringVal + ",scale=" + scale);
                    }
                }
            } catch (NumberFormatException ex) {
                throw new SQLException(
                        "ResultSet.Bad_format_for_BigDecimal: value="
                                + stringVal + ",scale=" + scale);
            }
        }

        return null;
    }

    public static BigDecimal getBigDecimalFromString(String stringVal) throws SQLException {
        BigDecimal val;
        if (stringVal != null) {
            if (stringVal.length() == 0) {
                val = new BigDecimal("0");
                return val;
            }
            try {
                val = new BigDecimal(stringVal);
                return val;
            } catch (NumberFormatException ex) {
                throw new SQLException(
                        "ResultSet.Bad_format_for_BigDecimal: value="
                                + stringVal);
            }
        }
        return null;
    }

    public static Long getTimeFromString(String stringVal, @Nullable Calendar cal)
            throws SQLException {
        if (stringVal == null) {
            return null;
        }
        String val = stringVal.trim();
        if (val.length() == 0) {
            return null;
        }
        if (val.equals("0") || val.equals("0000-00-00")
                || val.equals("0000-00-00 00:00:00")
                || val.equals("00000000000000") || val.equals("0")) {
            Calendar calendar = null;
            if (cal != null) {
                calendar = Calendar.getInstance(cal.getTimeZone());
            } else {
                calendar = Calendar.getInstance();
            }
            calendar.set(Calendar.YEAR, 1);
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            return calendar.getTimeInMillis();
        }
        
        DateFormat dateFormat;
        
        dateFormat = dateFormatTheadLocal.get();
        if(dateFormat == null)
        {
            if (val.length() == "yyyy-MM-dd".length()) {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            } else {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
            dateFormatTheadLocal.set(dateFormat);
        }

        if (cal != null) {
            TimeZone timeZone = cal.getTimeZone();
            dateFormat.setTimeZone(timeZone);
        }
        try {
            return dateFormat.parse(val).getTime();
        } catch (ParseException e) {
            throw new SQLException("Parse date failure:" + val);
        }
    }


    /**
     * Method getStringFromByte ...
     *
     * @param f           of type byte[]
     * @param charsetName of type String
     *
     * @return String
     *
     * @throws UnsupportedEncodingException when
     */
    public static String getStringFromByte(byte[] f, String charsetName) throws UnsupportedEncodingException {
        if (f.length == 0) {
            return null;
        } else if (f.length == 1 && f[0] == 0) {
            return "";
        } else {
            return StringUtils.isNotBlank(charsetName) ? new String(f, charsetName) :
                    new String(f);
        }
    }

}
