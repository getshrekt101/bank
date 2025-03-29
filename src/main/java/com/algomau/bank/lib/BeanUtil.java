package com.algomau.bank.lib;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

public class BeanUtil {

    public static void copyNonNullProperties(Object source, Object target, String... ignoreProperties) {
        String[] nullPropertyNames = getNullPropertyNames(source);

        // Combine null properties and explicitly ignored properties
        Set<String> ignoreList = new HashSet<>(Set.of(nullPropertyNames));
        if (ignoreProperties != null) {
            ignoreList.addAll(Set.of(ignoreProperties));
        }

        BeanUtils.copyProperties(source, target, ignoreList.toArray(new String[0]));
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Set.of(src.getPropertyDescriptors()).stream()
                .map(pd -> pd.getName())
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }
}