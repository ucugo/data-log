package com.mahull.model.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by Ugo on 05/03/2016.
 */
@Component
public class PropertiesAccessor {

    private final Environment environment;

    @Autowired
    public PropertiesAccessor(Environment environment) {
        this.environment = environment;
    }


}
