package com.mahull.admin.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Created by Ugo on 17/03/2016.
 */
@Service
public class PropertiesFetcher {

    private final Environment environment;

    @Autowired
    public PropertiesFetcher(Environment environment) {
        this.environment = environment;
    }

    public String getEncryptedHashSalt() {
        return getPropertyAsString("com.mahull.encrypted.hash.salt");
    }

    private String getPropertyAsString(String propertyName) {
        return this.environment.getProperty(propertyName);
    }
}
