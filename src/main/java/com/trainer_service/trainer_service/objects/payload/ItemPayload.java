package com.trainer_service.trainer_service.objects.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ItemPayload {
    private String name;
    private int baseCharges;
}
