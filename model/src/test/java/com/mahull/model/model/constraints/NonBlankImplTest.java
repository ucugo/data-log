package com.mahull.model.model.constraints;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ugo on 09/04/2016.
 */
public class NonBlankImplTest {

    private NonBlankImpl nonBlank = new NonBlankImpl();

    @Test
    public void shouldValidateStringValue() throws Exception {

        assertThat(nonBlank.isValid("Hello", null)).isTrue();
        assertThat(nonBlank.isValid("", null)).isFalse();
    }
}