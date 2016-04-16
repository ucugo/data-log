package com.mahull.model.util;

import com.mahull.model.model.ModelObject;
import com.mahull.model.model.profile.CraftUser;
import com.mahull.model.security.Role;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

import static com.mahull.model.security.Role.USER_ROLE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ugo on 27/03/2016.
 */
public class TestCase {

    protected static final String FIRST_NAME = "firstname";
    protected static final String LAST_NAME = "lastname";
    protected static final String USER_NAME = "username@test.com";
    protected static final String SOME_PASSWORD = "password";
    protected static final Role User_ROLE = USER_ROLE;

    protected void validateConstraint(ModelObject modelObject, int violationSize) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        Set<ConstraintViolation<ModelObject>> constraintViolations =
                factory.getValidator().validate(modelObject);

        assertThat(constraintViolations.size()).isEqualTo(violationSize);
    }

    protected Date customDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    protected CraftUser dummyUser() {
        CraftUser craftUser = new CraftUser();
        craftUser.setFirstName(FIRST_NAME);
        craftUser.setLastName(LAST_NAME);
        craftUser.setUserName(USER_NAME);
        craftUser.setPassword(SOME_PASSWORD);
        craftUser.setRole(User_ROLE);
        craftUser.setUpdatedAt(new Date());
        return craftUser;
    }
}
