package com.compay.service;

import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TruncateDataBase {

    private EntityManager entityManager;

    @Autowired
    public TruncateDataBase(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void truncate() {
        List<String> tableNames = new ArrayList<>();
        Session session = entityManager.unwrap(Session.class);
        Map<String, ClassMetadata> hibernateMetadata = session.getSessionFactory().getAllClassMetadata();

        for (ClassMetadata classMetadata : hibernateMetadata.values()) {
            AbstractEntityPersister aep = (AbstractEntityPersister) classMetadata;
            tableNames.add(aep.getTableName());
        }

        entityManager.flush();
        tableNames.forEach(tableName -> entityManager.createNativeQuery("DELETE FROM " + tableName).executeUpdate());
    }


}
