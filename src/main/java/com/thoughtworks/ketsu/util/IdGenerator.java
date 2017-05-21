package com.thoughtworks.ketsu.util;

import java.util.UUID;

/**
 * Created by pzzheng on 5/21/17.
 */
public class IdGenerator {
    public static String next() {
        return UUID.randomUUID().toString();
    }
}
