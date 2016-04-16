package com.mahull.model.query;

/**
 * Created by Ugo on 13/03/2016.
 */
public interface UserQuery {

    String FIND_USER_WITH_USERNAME = "From CraftUser as u where u.userName = :userName";

    String FIND_ITEM_WITH_CRAFT_USER_ID = "From Item as i where i.craftUser.id = :craftUser_id";
    String FIND_ITEM_WITH_CRAFT_USER_AND_CATEGORY = "From Item as i where i.craftUser.id = :craftUser_id AND "
            + "i.category.id = :category_id";
    String FIND_ITEMS_WITH_PURCHASED_DATE = "From Item as i where i.purchasedDate = :purchasedDate";
    String FIND_ITEMS_WITH_PURCHASED_DATE_AND_CRAFT_USER_ID = "From Item as i where i.purchasedDate = :purchasedDate "
            + "AND i.craftUser.id = :craftUserId";
}
