package com.example.junit;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;


public class DummyMethodsImplTest {

    @Test
    public void myFirstTest(){
        assertTrue(1>0);
        assertThat("abc").isNotEqualToIgnoringCase("Abcd");
        assertThat(0).isEqualTo(0);
    }
}
