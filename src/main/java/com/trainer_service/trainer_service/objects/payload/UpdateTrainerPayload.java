package com.trainer_service.trainer_service.objects.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateTrainerPayload {
    private int id;
    private String name;

    public UpdateTrainerPayload(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
