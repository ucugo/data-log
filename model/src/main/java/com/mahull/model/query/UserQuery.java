package com.mahull.model.query;

/**
 * Created by Ugo on 13/03/2016.
 */
public interface UserQuery {

    String FIND_USER_WITH_USERNAME = "From CraftUser as u where u.userName = :userName";

    String FIND_ITEM_WITH_CRAFT_USER_ID = "From Item as i where i.craftUser.id = :craftUserId";

    String FIND_ITEM_WITH_CRAFT_USER_AND_CATEGORY =
            "From Item as i where i.craftUser.id = :craftUserId AND i.category.id = :categoryId";

    String FIND_ITEMS_WITH_PURCHASED_DATE = "From Item as i where i.purchasedDate = :purchasedDate";

    String FIND_ITEMS_WITH_PURCHASED_DATE_AND_CRAFT_USER_ID =
            "From Item as i where i.purchasedDate = :purchasedDate AND i.craftUser.id = :craftUserId";

    String FIND_ITEMS_PURCHASED_WITHIN_PERIOD = "From Item as i where i.purchasedDate BETWEEN :startDate AND :endDate";

    String FIND_ITEMS_PURCHASED_WITHIN_PERIOD_FOR_A_USER =
            "From Item as i where i.craftUser.id = :craftUserId AND i.purchasedDate BETWEEN :startDate AND :endDate";
    String FIND_CATEGORIES_WITH_CRAFT_USER_ID = "From Category as c WHERE c.craftUser.id = :craftUserId";
    String FIND_CATEGORIES_WITH_CRAFT_USER_ID_AND_CATEGORY_NAME =
            "From Category as c WHERE c.craftUser.id = :craftUserId AND name = :categoryName";
    String FIND_ATTRIBUTES_WITH_USER_ID = "From Attribute a WHERE a.craftUser.id = :craftUserId";
}
