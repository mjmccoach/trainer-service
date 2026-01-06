package com.trainer_service.trainer_service.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Trainer {

    private int id;
    private String name;
    private List<GymBadge> badges;
}
