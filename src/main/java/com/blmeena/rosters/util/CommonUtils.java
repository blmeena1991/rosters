package com.blmeena.rosters.util;

import org.apache.commons.text.CaseUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import java.util.*;

public class CommonUtils {

    public static String convertToCamelCase(String str){
        return CaseUtils.toCamelCase(str, false, new char[]{'_'});
    }

    public static String removeSpecialCharacter(String str){
        return str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static String base64Encode(String token) {
        Base64.Encoder encoder = Base64.getEncoder();
        String str = encoder.encodeToString(token.getBytes());
        return str;
    }

    public static String base64Decode(String token) {
        Base64.Decoder decoder = Base64.getDecoder();
        String str = new String(decoder.decode(token));
        return str;
    }
}
