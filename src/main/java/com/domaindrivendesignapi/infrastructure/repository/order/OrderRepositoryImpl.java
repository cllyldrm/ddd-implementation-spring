package com.domaindrivendesignapi.infrastructure.repository.order;

import com.domaindrivendesignapi.domain.aggregate.order.Order;
import com.domaindrivendesignapi.domain.aggregate.order.OrderRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public OrderRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Retryable(value = {Exception.class}, maxAttempts = 5, backoff = @Backoff(200))
    public long save(Order order) {
        String query = "INSERT INTO Order([Name]) VALUES(:name)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", order.getName());

        jdbcTemplate.update(query, parameters, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        return orderId;
    }

    @Override
    public Order getById(long id) {
        String query = String.format("SELECT Id, Name FROM Order WITH(NOLOCK) WHERE Id = %d", id);
        List<Order> orders = jdbcTemplate.query(query, new MapSqlParameterSource(), new OrderRowMapper());
        Order order = orders.get(0);
        return order;
    }
}
