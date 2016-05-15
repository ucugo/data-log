package com.mahull.admin.config;

import com.mahull.admin.util.EnvironmentProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by Ugo on 17/03/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class EnvironmentPropertiesTest {


    private EnvironmentProperties environmentProperties;

    @Mock
    private Environment environment;

    @Before
    public void init() {
        environmentProperties = new EnvironmentProperties(environment);
    }

    @Test
    public void whenEncryptedHashSaltIsRequestedThenValidValueIsReturned() throws Exception {
        testProperty("com.mahull.encrypted.hash.salt", "value", environmentProperties::getEncryptedHashSalt, String.class);
    }

     private <T> void testProperty(String propertyKey, T propertyValue, Supplier<T> getterToTest,  Class<T> propertyType) {
         when(environment.getRequiredProperty(propertyKey, propertyType)).thenReturn(propertyValue);
         when(environment.getProperty(eq(propertyKey), eq(propertyType))).thenReturn(propertyValue);
         assertThat(getterToTest.get()).isEqualTo(propertyValue);
     }

}