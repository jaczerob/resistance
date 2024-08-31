package dev.jaczerob.resistance.api.models.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.jaczerob.resistance.api.models.groups.GroupType;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateGroupRequest(
        @JsonProperty(required = true)
        UUID toonId,
        int minLaff,
        int minToonUp,
        int minTrap,
        int minLure,
        int minSound,
        int minThrow,
        int minSquirt,
        int minDrop,
        int maxSize,
        GroupType groupType,
        String location,
        String district
) {
}
