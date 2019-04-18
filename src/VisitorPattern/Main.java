package VisitorPattern;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String,String> map = new LinkedHashMap<>();
        map.put("Lundi","01");
        map.put("Mardi","02");
        System.out.println(map);
    }
}
