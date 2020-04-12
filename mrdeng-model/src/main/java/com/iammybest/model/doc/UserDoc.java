package com.iammybest.model.doc;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * Created by MrDeng on 2017/2/27.
 */

@SolrDocument(solrCoreName = FieldDef.COLLECTION_USER)
public class UserDoc implements FieldDef {
    private
    @Id
    @Indexed
    Long id;
    private
    @Indexed(USER_DISPLAY_NAME)
    String displayName;
    private
    @Indexed(USER_EMAIL)
    String email;
    private
    @Indexed(USER_MOBILE)
    String mobile;

    //    @Indexed
    public UserDoc() {
    }

    public UserDoc(Long id, String displayName, String email, String mobile) {
        this.id = id;
        this.displayName = displayName;
        this.email = email;
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
