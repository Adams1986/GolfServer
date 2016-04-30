package controller;

import database.DatabaseWrapper;
import model.Member;

/**
 * Created by ADI on 30-04-2016.
 */
public class Logic {

    private static DatabaseWrapper dbWrapper = new DatabaseWrapper();

    public static boolean authenticateMember(Member member){

        return dbWrapper.authenticateMember(member);
    }

    public static Member getMember(String id) {

        return dbWrapper.getMember(id);
    }
}
