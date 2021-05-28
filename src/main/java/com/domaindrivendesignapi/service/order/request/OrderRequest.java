package com.domaindrivendesignapi.service.order.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OrderRequest {
    @NotBlank(message = "NameEmptyError")
    private String name;
}
