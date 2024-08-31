package dev.jaczerob.resistance.api.controllers;

import dev.jaczerob.resistance.api.models.groups.Group;
import dev.jaczerob.resistance.api.services.GroupService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Controller
public class GroupQueryController {
    private final GroupService groupService;

    public GroupQueryController(final GroupService groupService) {
        this.groupService = groupService;
    }

    @QueryMapping
    public Set<Group> groups() {
        return this.groupService.getGroups();
    }

    @QueryMapping
    public Optional<Group> groupById(final @Argument UUID id) {
        return this.groupService.getGroup(id);
    }
}
