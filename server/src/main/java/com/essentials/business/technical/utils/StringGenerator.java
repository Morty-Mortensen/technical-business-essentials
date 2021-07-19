package com.essentials.business.technical.utils;

import java.util.UUID;

public class StringGenerator {

    public static String generator() {
        return UUID.randomUUID().toString();
    }
}
