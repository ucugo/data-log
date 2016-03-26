package com.mahull.admin.config;

import com.mahull.admin.util.PropertiesFetcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ugo on 17/03/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
@ContextConfiguration(classes = {PropertiesFetcher.class})
public class PropertiesFetcherTest {

    @Autowired
    private PropertiesFetcher propertiesFetcher;

    @Test
    public void whenEncryptedHashSaltIsRequestedThenValidValueIsReturned() throws Exception {
        String encryptedHashSalt = propertiesFetcher.getEncryptedHashSalt();
        assertThat(encryptedHashSalt).isNotNull();
    }

}