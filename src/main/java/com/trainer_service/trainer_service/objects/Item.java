package com.trainer_service.trainer_service.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Item {
    private int id;
    private String name;
    private int baseCharges;
}
