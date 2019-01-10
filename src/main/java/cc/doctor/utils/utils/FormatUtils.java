package cc.doctor.utils.utils;

import cc.doctor.utils.annotation.Nullable;

import java.math.BigDecimal;

public class FormatUtils {

    private FormatUtils() {
    }

    @Nullable
    public static Object parseValue(String string, Class aClass) {
        if (aClass.equals(String.class)) {
            return string;
        } else if (aClass.equals(Integer.class)) {
            return Integer.parseInt(string);
        } else if (aClass.equals(Long.class)) {
            return Long.parseLong(string);
        } else if (aClass.equals(Short.class)) {
            return Short.parseShort(string);
        } else if (aClass.equals(Float.class)) {
            return Float.parseFloat(string);
        } else if (aClass.equals(Double.class)) {
            return Double.parseDouble(string);
        } else if (aClass.equals(BigDecimal.class)) {
            return BigDecimal.valueOf(Double.parseDouble(string));
        } else {
            throw new IllegalArgumentException(String.format("Format[%s] not support to inject.", aClass.toString()));
        }
    }
}
