package com.mahull.model.repositories;

import com.mahull.model.model.inventory.Attribute;
import com.mahull.model.model.profile.CraftUser;
import com.mahull.model.util.TestCase;
import com.mahull.model.util.UnitTestsAnnotations;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Ugo on 19/04/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@UnitTestsAnnotations
public class AttributeRepositoryTest extends TestCase {

    @Test
    public void get_attributes_with_craft_user() {

        CraftUser craftUser = getCraftUser(USER_NAME);
        Attribute attribute = dummyAttribute("attributeName", craftUser);

        attributeRepository.save(attribute);

        List<Attribute> fromDatabase = attributeRepository.findWithCraftUserId(craftUser.getId());

        Assertions.assertThat(fromDatabase.get(0).getCraftUser().getId()).isEqualTo(craftUser.getId());
    }

    public Attribute dummyAttribute(String attributeName, CraftUser craftUser ) {
        Attribute attribute = new Attribute();
        attribute.setName(attributeName);
        attribute.setCraftUser(craftUser);
        attribute.setDisplayName(attributeName);
        attribute.setPublishToDocuments(true);
        return attribute;
    }
}