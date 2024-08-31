package dev.jaczerob.resistance.api.controllers;

import dev.jaczerob.resistance.api.exceptions.ResistanceException;
import dev.jaczerob.resistance.api.exceptions.ToonNotExistsException;
import dev.jaczerob.resistance.api.models.gags.GagType;
import dev.jaczerob.resistance.api.models.groups.GagFilter;
import dev.jaczerob.resistance.api.models.groups.Group;
import dev.jaczerob.resistance.api.models.groups.GroupFilter;
import dev.jaczerob.resistance.api.models.requests.CreateGroupRequest;
import dev.jaczerob.resistance.api.models.toons.Toon;
import dev.jaczerob.resistance.api.services.GroupService;
import dev.jaczerob.resistance.api.services.ToonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final ToonService toonService;
    private final GroupService groupService;

    public GroupController(final ToonService toonService, final GroupService groupService) {
        this.toonService = toonService;
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<Group> createGroup(final @RequestBody CreateGroupRequest createGroupRequest) throws ResistanceException {
        final Toon toon = this.toonService.getToon(createGroupRequest.toonId()).orElseThrow(() -> new ToonNotExistsException(createGroupRequest.toonId()));

        final GroupFilter laffFilter = GroupFilter.laff(createGroupRequest.minLaff());
        final GroupFilter gagsFilter = GroupFilter.gags(
                new GagFilter(GagType.TOON_UP, createGroupRequest.minToonUp()),
                new GagFilter(GagType.TRAP, createGroupRequest.minTrap()),
                new GagFilter(GagType.LURE, createGroupRequest.minLure()),
                new GagFilter(GagType.SOUND, createGroupRequest.minSound()),
                new GagFilter(GagType.THROW, createGroupRequest.minThrow()),
                new GagFilter(GagType.SQUIRT, createGroupRequest.minSquirt()),
                new GagFilter(GagType.DROP, createGroupRequest.minDrop())
        );

        final Group group = this.groupService.createGroup(toon, createGroupRequest.groupType(), createGroupRequest.maxSize(), createGroupRequest.location(), createGroupRequest.district(), laffFilter, gagsFilter);
        return ResponseEntity.status(HttpStatus.CREATED).body(group);
    }
}
