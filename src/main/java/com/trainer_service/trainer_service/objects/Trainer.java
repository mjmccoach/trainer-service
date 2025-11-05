package com.trainer_service.trainer_service.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Trainer {

    private int id;
    private String name;

    public Trainer(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
