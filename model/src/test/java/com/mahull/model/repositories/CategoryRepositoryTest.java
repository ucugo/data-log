package com.mahull.model.repositories;

import com.mahull.model.model.inventory.Category;
import com.mahull.model.model.profile.CraftUser;
import com.mahull.model.util.TestCase;
import com.mahull.model.util.UnitTestsAnnotations;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ugo on 17/04/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@UnitTestsAnnotations
public class CategoryRepositoryTest extends TestCase {
    
    @Test
    public void get_categories_for_a_craft_user() {

        CraftUser craftUser = getCraftUser(USER_NAME);
        Category category = getCategory(CATEGORY_NAME, craftUser);

        List<Category> categories = categoryRepository.getWithCraftUserId(craftUser.getId());

        assertThat(categories.size()).isEqualTo(1);
        assertThat(categories.get(0).getId()).isEqualTo(category.getId());
        assertThat(categories.get(0).getCraftUser().getId()).isEqualTo(craftUser.getId());
    }

    @Test
    public void update_categories_for_a_craft_user() {

        CraftUser craftUser = getCraftUser(USER_NAME);
        Category category = getCategory(CATEGORY_NAME, craftUser);

        category.setName("new_name");

        categoryRepository.save(category);

        List<Category> categories = categoryRepository.getWithCraftUserId(craftUser.getId());

        assertThat(categories.size()).isEqualTo(1);
        assertThat(categories.get(0).getId()).isEqualTo(category.getId());
        assertThat(categories.get(0).getCraftUser().getId()).isEqualTo(craftUser.getId());
        assertThat(categories.get(0).getName()).isEqualTo("new_name");
    }

    @Test
    public void get_categories_with_for_craft_user_with_category_name() {

        CraftUser craftUser = getCraftUser(USER_NAME);
        Category category = getCategory(CATEGORY_NAME, craftUser);
        Category category2 = getCategory("new_category", craftUser);

        Category fromDatabase = categoryRepository.getWithCraftUserIdAndCategoryName(craftUser.getId(), CATEGORY_NAME);

        assertThat(fromDatabase.getName()).isEqualTo(category.getName());
        assertThat(fromDatabase.getName()).isNotEqualTo(category2.getName());
    }
}