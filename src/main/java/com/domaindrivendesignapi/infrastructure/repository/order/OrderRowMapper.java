package com.domaindrivendesignapi.infrastructure.repository.order;

import com.domaindrivendesignapi.domain.aggregate.order.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Order order = new Order(resultSet.getLong("Id"), resultSet.getNString("Name"));
        return order;
    }
}
