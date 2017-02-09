package com.nojsoft.constants;

/**
 * Created by alan on 1/19/17.
 */
public class DataBaseConstants {

    public static String OWNER_ID_FIELD = "ownerId";
    public static String USER_ID_FIELD = "userId";
    public static String GROUP_ID_FIELD = "groupId";
    public static String ID_FIELD = "id";
    public static String USER_ACCESS_KEY_FIELD = "accessKey";
    public static String USER_TOKEN_FIELD = "token";

    public static String GROUP_PARTICIPANT_QUERY = "SELECT g.* from groups g " +
            "JOIN group_participants gp ON g.id = gp.group_id WHERE gp.status = 1 AND gp.user_id =:userId";

    public static int USER_REQUESTER = 0;
    public static int USER_ACCEPTED = 1;
    public static int USER_REJECTED = 2;
}

