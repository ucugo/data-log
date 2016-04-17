package com.mahull.model.repositories;

import com.mahull.model.model.inventory.Category;
import com.mahull.model.model.inventory.InventoryType;
import com.mahull.model.model.inventory.Item;
import com.mahull.model.model.profile.CraftUser;
import com.mahull.model.util.TestCase;
import com.mahull.model.util.UnitTestsAnnotations;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ugo on 12/04/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@UnitTestsAnnotations
public class ItemRepositoryTest extends TestCase {

    @Test
    public void whenNewIteIsAddedThenItShouldBeStoredInTheDatabase() {
        final CraftUser craftUser = getCraftUser(USER_NAME);
        assertThat(addValidItemToDatabase(craftUser, getCategory(CATEGORY_NAME, craftUser), "item_name", new Date())).isNotNull();
    }

    @Test
    public void items_purchased_in_a_given_period() {

        final CraftUser craftUser = getCraftUser(USER_NAME);
        final Category category = getCategory(CATEGORY_NAME, craftUser);

        addValidItemToDatabase(craftUser, category, "item_name1", customDate("2000-01-01 12:30"));
        addValidItemToDatabase(craftUser, category, "item_name2", customDate("2000-01-01 12:30"));
        addValidItemToDatabase(craftUser, category, "item_name3", customDate("2001-01-01 12:00"));

        List<Item> items = itemRepository
                .getItemsPurchasedInGivenPeriod(customDate("1999-01-01 12:30"), customDate("2000-12-31 12:30"));

        assertThat(items.size()).isEqualTo(2);
    }

    @Test
    public void items_purchased_in_a_given_period_for_a_craft_user() {

        final CraftUser craftUser = getCraftUser(USER_NAME);
        final Category category = getCategory(CATEGORY_NAME, craftUser);

        addValidItemToDatabase(craftUser, category, "item_name1", customDate("2000-01-01 12:30"));
        addValidItemToDatabase(craftUser, category, "item_name2", customDate("2000-01-01 12:30"));
        addValidItemToDatabase(getCraftUser("another@cc.com"), category, "item_name3", customDate("2000-01-01 12:00"));

        List<Item> items = itemRepository
                .getItemsPurchasedInGivenPeriodWithUserId(customDate("1999-01-01 12:30"),
                        customDate("2000-12-31 12:30"), craftUser.getId());

        assertThat(items.size()).isEqualTo(2);
    }


    @Test
    public void items_are_returned_based_on_purchased_date_and_craftUser_id() {

        final String firstDate = "2000-01-01 12:30";
        final String secondDate = "2000-02-01 12:00";

        final CraftUser craftUser = getCraftUser(USER_NAME);
        final Category category = getCategory(CATEGORY_NAME, craftUser);

        addValidItemToDatabase(craftUser, category, "item_name1", customDate(firstDate));
        addValidItemToDatabase(craftUser, category, "item_name2", customDate(firstDate));

        addValidItemToDatabase(craftUser, category, "item_name3", customDate(secondDate));

        assertThat(itemRepository
                .findItemsWithPurchaseDatesAndCraftUserId(craftUser.getId(), customDate(firstDate)).size())
                .isEqualTo(2);

        assertThat(itemRepository
                .findItemsWithPurchaseDatesAndCraftUserId(craftUser.getId(), customDate(secondDate)).size())
                .isEqualTo(1);
    }

    @Test
    public void items_are_returned_based_on_purchased_date() {

        final String firstDate = "2000-01-01 19:30";
        final String secondDate = "2000-01-01 12:30";

        final CraftUser craftUser1 = getCraftUser(USER_NAME);
        addValidItemToDatabase(craftUser1, getCategory(CATEGORY_NAME, craftUser1), "item_name1", customDate(firstDate));

        final CraftUser craftUser2 = getCraftUser("another@bbb.com");
        addValidItemToDatabase(craftUser2, getCategory("another_name", craftUser2), "item_name2", customDate(secondDate));

        List<Item> items = itemRepository.findItemsWithPurchaseDates(customDate(secondDate));

        assertThat(items.size()).isEqualTo(2);
        assertThat(items).extractingResultOf("getPurchasedDate").contains(customDate(firstDate), customDate(secondDate));
    }

    @Test
    public void items_are_returned_for_a_craft_user_when_craft_user_id_and_category_id_is_passed() {

        final CraftUser craftUser1 = getCraftUser(USER_NAME);
        Item firstItem = addValidItemToDatabase(craftUser1, getCategory(CATEGORY_NAME, craftUser1), "item_name1", new Date());

        final CraftUser craftUser2 = getCraftUser("another@bbb.com");
        Item secondItem = addValidItemToDatabase(craftUser2, getCategory("another_category", craftUser2), "item_name2", new Date());

        List<Item> items = itemRepository
                .getItemWithCraftUserIdAndCategoryId(firstItem.getCraftUser().getId(), firstItem.getCategory().getId());

        assertThat(items.size()).isEqualTo(1);
        assertThat(firstItem.getId()).isNotEqualTo(secondItem.getId());
        assertThat(firstItem.getId()).isEqualTo(items.get(0).getId());
    }

    @Test
    public void items_are_returned_for_a_craft_user_when_craft_user_id_is_passed() {

        final CraftUser craftUser = getCraftUser(USER_NAME);
        Item item = addValidItemToDatabase(craftUser, getCategory(CATEGORY_NAME, craftUser), "item_name", new Date());
        List<Item> items = itemRepository.getItemsWithCraftUserId(item.getCraftUser().getId());
        assertThat(items.size()).isEqualTo(1);
    }

    private Item addValidItemToDatabase(CraftUser craftUser, Category category, String itemName, Date purchasedDate) {

        Item item = dummyItem(itemName, purchasedDate);
        item.setCraftUser(craftUser);
        item.setCategory(category);

        validateConstraint(item, 0);

        itemRepository.save(item);
        return itemRepository.get(Item.class, item.getId());
    }

    private Item dummyItem(String itemName, Date purchasedDate) {
        Item item = new Item();
        item.setName(itemName);
        item.setPurchasePricePerUnit(20.0);
        item.setInventoryType(InventoryType.QUANTITY_ITEM);
        item.setPurchasedDate(purchasedDate);
        item.setDefaultSellingPrice(2.0);
        return item;
    }

}