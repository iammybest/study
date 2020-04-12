package com.iammybest.common.solr;

/**
 * Created by MrDeng on 2017/2/16.
 */
public interface SolrConstants {
    public final static String DOC_ID = "id";
    ////////////////////Definition of solr collection and fields fo user ///////////////////////////
    public final static String COLLECTION_USER = "deng-user";
    public final static String USER_DISPLAY_NAME="name";
    public final static String USER_EMAIL = "email";
    public final static String USER_MOBILE="mobile";
    public final static String USER_AGE="age";
    public final static String USER_SEX="sex";
    public final static String USER_FRIENDS="friends";
    public final static String USER_MARKS="marks";

    ////////////////////Definition of solr collection and fields fo group ///////////////////////////
    public final static String COLLECTION_GROUP="zb-group";
    public final static String GROUP_NAME="groupName";


}
