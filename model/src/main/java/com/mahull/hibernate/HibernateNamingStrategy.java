package com.mahull.hibernate;

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
        return super.foreignKeyColumnName(propertyName, propertyEntityName, propertyTableName, referencedColumnName) + "_" + referencedColumnName;
    }

    @Override
    public String classToTableName(String className) {
        String n = super.classToTableName(className);
        if (n.endsWith("s")) {
            return n + "es";
        }
        if (n.endsWith("y")) {
            return n.substring(0, n.length() - 1) + "ies";
        }
        return n + "s";
    }
}
