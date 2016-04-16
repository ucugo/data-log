package com.mahull.model.repositories;

import com.mahull.model.model.inventory.Item;
import com.mahull.model.query.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

import static com.mahull.model.query.UserQuery.FIND_ITEMS_WITH_PURCHASED_DATE;
import static com.mahull.model.query.UserQuery.FIND_ITEM_WITH_CRAFT_USER_AND_CATEGORY;
import static com.mahull.model.query.UserQuery.FIND_ITEM_WITH_CRAFT_USER_ID;
import static java.util.Objects.requireNonNull;

/**
 * Created by Ugo on 11/04/2016.
 */

@org.springframework.stereotype.Repository
@Transactional
public class ItemRepository extends Repository<Item> {

    @Autowired
    public ItemRepository(EntityManager entityManager) {
        super(entityManager);
    }

    /**
     *
     * @param craftUserId .
     * @return .
     */
    public List<Item> getItemsWithCraftUserId(long craftUserId) {
        return getEntityManager()
                .createQuery(FIND_ITEM_WITH_CRAFT_USER_ID, Item.class)
                .setParameter("craftUserId", craftUserId)
                .getResultList();
    }

    public void saveItem(Item item) throws Exception {
        requireNonNull(item);
        getEntityManager().persist(item);
    }

    /**
     *
     * @param craftUserId .
     * @param categoryId .
     * @return .
     */
    public List<Item> getItemWithCraftUserIdAndCategoryId(long craftUserId, long categoryId) {
        return getEntityManager()
                .createQuery(FIND_ITEM_WITH_CRAFT_USER_AND_CATEGORY, Item.class)
                .setParameter("craftUserId", craftUserId)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    /**
     *
     * @param purchasedDate .
     * @return .
     */
    public List<Item> findItemsWithPurchaseDates(Date purchasedDate) {
        return getEntityManager()
                .createQuery(FIND_ITEMS_WITH_PURCHASED_DATE, Item.class)
                .setParameter("purchasedDate", purchasedDate)
                .getResultList();
    }

    /**
     *
     * @param craftUserId .
     * @param purchasedDate .
     * @return .
     */
    public List<Item> findItemsWithPurchaseDatesAndCraftUserId(Long craftUserId, Date purchasedDate) {
        return getEntityManager()
                .createQuery(UserQuery.FIND_ITEMS_WITH_PURCHASED_DATE_AND_CRAFT_USER_ID, Item.class)
                .setParameter("craftUserId", craftUserId)
                .setParameter("purchasedDate", purchasedDate)
                .getResultList();
    }

    /**
     *
     * @param startDAte .
     * @param endDate .
     * @return .
     */
    public List<Item> getItemsPurchasedInGivenPeriod(Date startDAte, Date endDate) {
        return getEntityManager()
                .createQuery(UserQuery.FIND_ITEMS_PURCHASED_WITHIN_PERIOD)
                .setParameter("startDate", startDAte)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    /**
     *
     * @param startDAte .
     * @param endDate .
     * @param craftUserId .
     * @return .
     */
    public List<Item> getItemsPurchasedInGivenPeriodWithUserId(Date startDAte, Date endDate, Long craftUserId) {
        return getEntityManager()
                .createQuery(UserQuery.FIND_ITEMS_PURCHASED_WITHIN_PERIOD_FOR_A_USER)
                .setParameter("startDate", startDAte)
                .setParameter("endDate", endDate)
                .setParameter("craftUserId", craftUserId)
                .getResultList();
    }
}
