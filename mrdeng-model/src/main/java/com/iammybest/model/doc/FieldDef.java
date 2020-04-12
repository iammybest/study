package com.iammybest.model.doc;

/**
 * Created by MrDeng on 2017/2/27.
 */
public interface FieldDef {
    public final static String COL_ID = "id";
    ////////////////////Definition of solr collection and fields fo user ///////////////////////////
    public final static String COLLECTION_USER = "zb-user";
    public final static String USER_DISPLAY_NAME="displayName";
    public final static String USER_EMAIL = "email";
    public final static String USER_MOBILE="mobile";

    ////////////////////Definition of solr collection and fields fo group ///////////////////////////
    public final static String COLLECTION_GROUP="zb-group";
    public final static String GROUP_NAME="groupName";
}
