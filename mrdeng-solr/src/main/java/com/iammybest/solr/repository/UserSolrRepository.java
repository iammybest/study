package com.iammybest.solr.repository;

import com.iammybest.model.doc.UserDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 * Created by MrDeng on 2017/2/13.
 */
public interface UserSolrRepository extends SolrCrudRepository<UserDoc, Long> {

    @Query(value = "displayName:*?0* or email:?0 or mobile:?0",fields = "id")
    public Page<UserDoc> findByDisplayName(String displayName, Pageable page);

    public Long countByDisplayName(SimpleQuery query);

}
