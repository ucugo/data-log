package com.mahull.model.query;

/**
 * Created by Ugo on 13/03/2016.
 */
public interface UserQuery {

    String FIND_USER_WITH_USERNAME = "From CraftUser as u where u.userName = :userName";
}
