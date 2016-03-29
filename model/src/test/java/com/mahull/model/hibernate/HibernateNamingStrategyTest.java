package com.mahull.model.hibernate;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ugo on 27/03/2016.
 */
public class HibernateNamingStrategyTest {

    private HibernateNamingStrategy hibernateNamingStrategy;

    @Before
    public void init() throws Exception {
        this.hibernateNamingStrategy = new HibernateNamingStrategy();
    }

    @Test
    public void foreignKeyNameShouldHaveAnUnderscoreAddedToIt() throws Exception {
        String propertyName = "propertyname";
        String propertyEntityName = "propertyEntityName";
        String propertyTableName = "propertyTableName";
        String referencedColumnName = "referencedColumnName";

        String foreignKeyColumnName = hibernateNamingStrategy.foreignKeyColumnName(propertyName, propertyEntityName, propertyTableName, referencedColumnName);

        assertThat(foreignKeyColumnName).isEqualTo(propertyName.concat("_").concat(referencedColumnName));
    }

    @Test
    public void whenClassNameEndsWithSThenTableNameWillEndWithES() {
        String className = "class";
        String formatedName = hibernateNamingStrategy.classToTableName(className);

        assertThat(formatedName).isEqualTo(className.concat("es").toLowerCase());
    }

    @Test
    public void whenClassNameEndsWithYThenTableNameWillEndWithIES() {
        String className = "Story";
        String formatedName = hibernateNamingStrategy.classToTableName(className);

        assertThat(formatedName).isEqualTo("stories");
    }

    @Test
    public void whenClassNameResultsToDefault() throws Exception {
        String className = "User";
        String formatedName = hibernateNamingStrategy.classToTableName(className);

        assertThat(formatedName).isEqualTo(className.concat("s").toLowerCase());
    }

}