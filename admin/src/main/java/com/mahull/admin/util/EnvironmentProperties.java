package com.mahull.admin.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Created by Ugo on 17/03/2016.
 */
@Service
public class EnvironmentProperties {

    private final Environment environment;

    @Autowired
    public EnvironmentProperties(Environment environment) {
        this.environment = environment;
    }

    public String getEncryptedHashSalt() {
        return environment.getRequiredProperty("com.mahull.encrypted.hash.salt", String.class);
    }

}
