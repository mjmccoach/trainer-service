package com.trainer_service.trainer_service.objects;

import com.trainer_service.trainer_service.enums.BadgeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GymBadge {
    private int id;
    private BadgeType badgeType;
}
