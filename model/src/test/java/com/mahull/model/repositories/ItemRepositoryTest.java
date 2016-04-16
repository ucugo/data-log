package com.mahull.model.repositories;

import com.mahull.model.model.inventory.Category;
import com.mahull.model.model.inventory.InventoryType;
import com.mahull.model.model.inventory.Item;
import com.mahull.model.model.profile.CraftUser;
import com.mahull.model.util.TestCase;
import com.mahull.model.util.UnitTestsAnnotations;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ugo on 12/04/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@UnitTestsAnnotations
@Transactional
public class ItemRepositoryTest extends TestCase {

    private Date PURCHASED_DATE;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CraftUserRepository craftUserRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Before
    public void init() {
        PURCHASED_DATE = new Date();
    }
    @Test
    public void whenNewIteIsAddedThenItShouldBeStoredInTheDatabase() throws Exception{
        assertThat(addValidItemToDatabase(getCraftUser(), getCategory())).isNotNull();
    }

    @Test
    public void item_is_returned_based_on_purchased_date_and_craftUser_id() {

        final String firstDate = "2000-01-01 12:30";
        final String secondDate = "2000-02-01 12:00";

        final CraftUser craftUser = getCraftUser();
        final Category category = getCategory();

        PURCHASED_DATE = customDate(firstDate);
        addValidItemToDatabase(craftUser, category);
        addValidItemToDatabase(craftUser, category);

        PURCHASED_DATE = customDate(secondDate);
        addValidItemToDatabase(craftUser, category);

        assertThat(itemRepository
                .findItemsWithPurchaseDatesAndCraftUserId(craftUser.getId(), customDate(firstDate)).size())
                .isEqualTo(2);

        assertThat(itemRepository
                .findItemsWithPurchaseDatesAndCraftUserId(craftUser.getId(), customDate(secondDate)).size())
                .isEqualTo(1);
    }

    @Test
    public void item_is_returned_based_on_purchased_date() {

        final String firstDate = "2000-01-01 12:30";
        final String secondDate = "2000-01-01 12:30";

        PURCHASED_DATE = customDate(firstDate);
        addValidItemToDatabase(getCraftUser(), getCategory());

        PURCHASED_DATE = customDate(secondDate);
        addValidItemToDatabase(getCraftUser(), getCategory());

        List<Item> items = itemRepository.findItemsWithPurchaseDates(PURCHASED_DATE);

        assertThat(items.size()).isEqualTo(2);
        assertThat(items).extractingResultOf("getPurchasedDate").contains(customDate(firstDate),customDate(secondDate));
    }

    @Test
    public void item_is_returned_with_craft_user_id_and_category_id() {
        Item firstItem = addValidItemToDatabase(getCraftUser(), getCategory());
        Item secondItem = addValidItemToDatabase(getCraftUser(), getCategory());

        List<Item> items = itemRepository
                .getItemWithCraftUserIdAndCategoryId(firstItem.getCraftUser().getId(), firstItem.getCategory().getId());

        assertThat(items.size()).isEqualTo(1);
        assertThat(firstItem.getId()).isNotEqualTo(secondItem.getId());
        assertThat(firstItem.getId()).isEqualTo(items.get(0).getId());
    }

    @Test
    public void item_is_returned_for_a_craft_user_when_craft_user_id_is_passed() {
        Item item = addValidItemToDatabase(getCraftUser(), getCategory());
        List<Item> items = itemRepository.getItemsWithCraftUserId(item.getCraftUser().getId());
        assertThat(items.size()).isEqualTo(1);
    }

    private Item addValidItemToDatabase(CraftUser craftUser, Category category) {

        Item item = dummyItem();
        item.setCraftUser(craftUser);
        item.setCategory(category);

        validateConstraint(item, 0);

        itemRepository.saveItem(item);
        return itemRepository.get(Item.class, item.getId());
    }

    private Category getCategory() {
        Category category = new Category();
        category.setName("category_name");
        categoryRepository.save(category);
        return category;
    }

    private CraftUser getCraftUser() {
        CraftUser craftUser = dummyUser();
        craftUserRepository.save(craftUser);
        return craftUser;
    }

    private Item dummyItem() {
        Item item = new Item();
        item.setName("some_name");
        item.setPurchasePricePerUnit(20.0);
        item.setInventoryType(InventoryType.QUANTITY_ITEM);
        item.setPurchasedDate(PURCHASED_DATE);
        item.setDefaultSeelingPrice(2.0);
        return item;
    }

}