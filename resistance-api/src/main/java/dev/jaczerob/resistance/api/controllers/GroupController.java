package dev.jaczerob.resistance.api.controllers;

import dev.jaczerob.resistance.api.exceptions.GroupNotExistsException;
import dev.jaczerob.resistance.api.exceptions.ResistanceException;
import dev.jaczerob.resistance.api.exceptions.ToonNotExistsException;
import dev.jaczerob.resistance.api.models.gags.GagType;
import dev.jaczerob.resistance.api.models.groups.Group;
import dev.jaczerob.resistance.api.models.groups.GroupFilter;
import dev.jaczerob.resistance.api.models.requests.CreateGroupRequest;
import dev.jaczerob.resistance.api.models.toons.Toon;
import dev.jaczerob.resistance.api.services.GroupService;
import dev.jaczerob.resistance.api.services.ToonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final ToonService toonService;
    private final GroupService groupService;

    public GroupController(final ToonService toonService, final GroupService groupService) {
        this.toonService = toonService;
        this.groupService = groupService;
    }

    @DeleteMapping("/remove/{groupId}")
    public ResponseEntity<Void> deleteGroup(final @PathVariable("groupId") UUID groupId) throws ResistanceException {
        this.groupService.deleteGroup(groupId);
        return ResponseEntity.accepted().body(null);
    }

    @DeleteMapping("/remove/{groupId}/{toonId}")
    public ResponseEntity<Void> removeFromGroup(final @PathVariable("groupId") UUID groupId, final @PathVariable("toonId") UUID toonId) throws ResistanceException {
        final Group group = this.groupService.getGroup(groupId).orElseThrow(() -> new GroupNotExistsException(groupId));
        final Toon toon = this.toonService.getToon(toonId).orElseThrow(() -> new ToonNotExistsException(toonId));
        this.groupService.removeFromGroup(group, toon);
        return ResponseEntity.accepted().body(null);
    }

    @PostMapping("/join/{groupId}/{toonId}")
    public ResponseEntity<Void> joinGroup(final @PathVariable("groupId") UUID groupId, final @PathVariable("toonId") UUID toonId) throws ResistanceException {
        final Group group = this.groupService.getGroup(groupId).orElseThrow(() -> new GroupNotExistsException(groupId));
        final Toon toon = this.toonService.getToon(toonId).orElseThrow(() -> new ToonNotExistsException(toonId));
        this.groupService.addToGroup(group, toon);
        return ResponseEntity.accepted().body(null);
    }

    @PostMapping("/create")
    public ResponseEntity<Group> createGroup(final @RequestBody CreateGroupRequest createGroupRequest) throws ResistanceException {
        final Toon toon = this.toonService.getToon(createGroupRequest.toonId()).orElseThrow(() -> new ToonNotExistsException(createGroupRequest.toonId()));

        final List<GroupFilter> groupFilters = new ArrayList<>();
        if (createGroupRequest.minLaff() > 0)
            groupFilters.add(GroupFilter.laff(createGroupRequest.minLaff()));

        if (createGroupRequest.maxSize() > 0)
            groupFilters.add(GroupFilter.size(createGroupRequest.maxSize()));

        if (createGroupRequest.minToonUp() > 0)
            groupFilters.add(GroupFilter.gag(GagType.TOON_UP, createGroupRequest.minToonUp()));

        if (createGroupRequest.minTrap() > 0)
            groupFilters.add(GroupFilter.gag(GagType.TRAP, createGroupRequest.minTrap()));

        if (createGroupRequest.minLure() > 0)
            groupFilters.add(GroupFilter.gag(GagType.LURE, createGroupRequest.minLure()));

        if (createGroupRequest.minSound() > 0)
            groupFilters.add(GroupFilter.gag(GagType.SOUND, createGroupRequest.minSound()));

        if (createGroupRequest.minThrow() > 0)
            groupFilters.add(GroupFilter.gag(GagType.THROW, createGroupRequest.minThrow()));

        if (createGroupRequest.minSquirt() > 0)
            groupFilters.add(GroupFilter.gag(GagType.SQUIRT, createGroupRequest.minSquirt()));

        if (createGroupRequest.minDrop() > 0)
            groupFilters.add(GroupFilter.gag(GagType.DROP, createGroupRequest.minDrop()));

        final Group group = this.groupService.createGroup(toon, createGroupRequest.groupType(), createGroupRequest.location(), createGroupRequest.district(), List.copyOf(groupFilters).toArray(new GroupFilter[0]));
        return ResponseEntity.status(HttpStatus.CREATED).body(group);
    }
}
