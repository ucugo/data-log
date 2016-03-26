package com.mahull.model.hibernate;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * Created by Ugo on 26/04/2015.
 */
public class HibernateNamingStrategy extends ImprovedNamingStrategy {

    private static final long serialVersionUID = 1L;

    @Override
    public String foreignKeyColumnName(String propertyName,
                                       String propertyEntityName, String propertyTableName,
                                       String referencedColumnName) {
        return super.foreignKeyColumnName(propertyName, propertyEntityName,
                propertyTableName, referencedColumnName) + "_" + referencedColumnName;
    }

    @Override
    public String classToTableName(String className) {
        String name = super.classToTableName(className);
        if (name.endsWith("s")) {
            return name + "es";
        }
        if (name.endsWith("y")) {
            return name.substring(0, name.length() - 1) + "ies";
        }
        return name + "s";
    }
}
