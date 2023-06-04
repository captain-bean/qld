package com.marshall.benjy.qld.core.engine.datatype;


import java.util.UUID;

public class UUIDGenerator {

    public static String generateString(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static UUID generate(){
        return UUID.randomUUID();
    }

    public static String generateStringfromInput(String input){
        UUID uuid = UUID.nameUUIDFromBytes(input.getBytes());
        return uuid.toString();
    }

    public static UUID generateUUIDfromInput(String input){
        UUID uuid = UUID.nameUUIDFromBytes(input.getBytes());
        return uuid;
    }

    public static UUID read(String input){
        return UUID.fromString(input);
    }
}
