package com.trainer_service.trainer_service.objects.payload;

import lombok.ToString;

@ToString
public class UpdateTrainerPayload {
    private int id;
    private String name;

    public UpdateTrainerPayload(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
