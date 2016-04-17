package com.mahull.model.util;

import com.mahull.model.config.JpaConfig;
import com.mahull.model.config.JpaConfigTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Ugo on 12/04/2016.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Transactional
@ContextConfiguration(classes = {JpaConfigTest.class, JpaConfig.class})
@TestPropertySource(locations = {"/application-test.properties"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public @interface UnitTestsAnnotations {
}
