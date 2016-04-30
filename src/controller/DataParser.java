package controller;

import com.google.gson.Gson;
import model.Club;
import model.Config;
import model.Member;

import java.util.ArrayList;
import java.util.HashMap;


public class DataParser {

    private static Gson gson = new Gson();

    public static Member getMember(String jsonData){

        HashMap<String, String>dataHashMap = gson.fromJson(jsonData, HashMap.class);

        Member member = new Gson().fromJson(dataHashMap.get("message"), Member.class);
        member.setPassword(Security.hashing(member.getPassword()));

        return member;
    }

    public static String hashMapToJson(HashMap<String, String> dataMap) {

        return new Gson().toJson(dataMap);
    }

    public static String memberToJson(Member member) {

        return new Gson().toJson(member);
    }
}
