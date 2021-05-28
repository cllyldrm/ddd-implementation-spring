package com.domaindrivendesignapi.domain.aggregate.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class OrderTest {
    @Test
    public void constructor_WhenCreated_ShouldWorkProperly() {
        Order order = new Order(null, "name");
        order.setId(1);

        assertThat(order.getId()).isEqualTo(1);
    }
}
