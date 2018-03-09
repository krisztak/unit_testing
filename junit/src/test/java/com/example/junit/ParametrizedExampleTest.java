package com.example.junit;

import com.example.junit.impl.DummyMethodsImpl;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class ParametrizedExampleTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                 { 1000, 20 }, { 10000, 400 }
        });
    }

    private double salesTotal;

    private double commision;

    public ParametrizedExampleTest(int input, int expected) {
        salesTotal= input;
        commision= expected;
    }

    @Test
    public void testCommissions() {
        DummyMethodsImpl impl = new DummyMethodsImpl();
        assertThat(commision).isEqualTo(impl.calculateAnnualCommusion(1,salesTotal));
    }
}
