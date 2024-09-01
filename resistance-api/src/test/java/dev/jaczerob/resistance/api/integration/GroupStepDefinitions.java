package dev.jaczerob.resistance.api.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jaczerob.resistance.api.models.groups.Group;
import dev.jaczerob.resistance.api.models.toons.Toon;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@CucumberContextConfiguration
@SpringBootTest
public class GroupStepDefinitions {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    private Toon leaderToon;
    private Group group;
    private Toon newToon;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @When("a toon is created")
    public void aToonIsCreated() throws Exception {
        this.leaderToon = this.createToon(140, 7, 7, 7, 7, 7, 7, 7);
    }

    @Then("the toon can create a group with minLaff {int}, minToonUp {int}, minTrap {int}, minLure {int}, minSound {int}, minThrow {int}, minSquirt {int}, minDrop {int}, maxSize {int}")
    public void theToonCanCreateAGroupWithMinLaffMinLaffMinToonUpMinToonUpMinTrapMinTrapMinLureMinLureMinSoundMinSoundMinThrowMinThrowMinSquirtMinSquirtMinDropMinDropMaxSizeMaxSize(final int minLaff, final int minToonUp, final int minTrap, final int minLure, final int minSound, final int minThrow, final int minSquirt, final int minDrop, final int maxSize) throws Exception {
        this.group = this.createGroup(this.leaderToon, minLaff, minToonUp, minTrap, minLure, minSound, minThrow, minSquirt, minDrop, maxSize);
    }

    @Then("a toon fails to join the group with laff {int}, toonUp {int}, trap {int}, lure {int}, sound {int}, throw {int}, squirt {int}, drop {int}")
    public void aToonFailsToJoinTheGroupWithLaffLaffToonUpToonUpTrapTrapLureLureSoundSoundThrowThrowSquirtSquirtDropDrop(final int laff, final int toonUpLevel, final int trapLevel, final int lureLevel, final int soundLevel, final int throwLevel, final int squirtLevel, final int dropLevel) throws Exception {
        this.newToon = this.createToon(laff, toonUpLevel, trapLevel, lureLevel, soundLevel, throwLevel, squirtLevel, dropLevel);
        Assertions.assertThrows(Throwable.class, () -> this.joinGroup(newToon, group));
    }

    @Then("a toon can join the group with laff {int}, toonUp {int}, trap {int}, lure {int}, sound {int}, throw {int}, squirt {int}, drop {int}")
    public void aToonCanJoinTheGroupWithLaffToonUpTrapLureSoundThrowSquirtDrop(final int laff, final int toonUpLevel, final int trapLevel, final int lureLevel, final int soundLevel, final int throwLevel, final int squirtLevel, final int dropLevel) throws Exception {
        this.newToon = this.createToon(laff, toonUpLevel, trapLevel, lureLevel, soundLevel, throwLevel, squirtLevel, dropLevel);
        Assertions.assertDoesNotThrow(() -> this.joinGroup(newToon, group));
    }

    @Then("that toon cannot join their own group")
    public void thatToonCannotJoinTheirOwnGroup() {
        Assertions.assertThrows(Throwable.class, () -> this.joinGroup(leaderToon, group));
    }

    @Then("the leader can remove the toon")
    public void theLeaderCanRemoveTheToon() {

    }

    @And("the leader can delete the group")
    public void theLeaderCanDeleteTheGroup() {

    }

    private void joinGroup(final Toon toon, final Group group) throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/groups/join/%s/%s".formatted(group.id(), toon.id()));
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    private Group createGroup(final Toon toon, final int minLaff, final int minToonUp, final int minTrap, final int minLure, final int minSound, final int minThrow, final int minSquirt, final int minDrop, final int maxSize) throws Exception {
        final String body = """
                {
                    "toonId": "%s",
                    "minLaff": %d,
                    "minToonUp": %d,
                    "minTrap": %d,
                    "minLure": %d,
                    "minSound": %d,
                    "minThrow": %d,
                    "minSquirt": %d,
                    "minDrop": %d,
                    "maxSize": %d,
                    "groupType": "BUILDING"
                }
                """.formatted(toon.id(), minLaff, minToonUp, minTrap, minLure, minSound, minThrow, minSquirt, minDrop, maxSize);

        final RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/groups/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body);

        final String response = this.mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse()
                .getContentAsString();

        return OBJECT_MAPPER.readValue(response, Group.class);
    }

    private Toon createToon(final int laff, final int toonUpLevel, final int trapLevel, final int lureLevel, final int soundLevel, final int throwLevel, final int squirtLevel, final int dropLevel) throws Exception {
        final String body = """
                {
                    "name": "jaczerobTest",
                    "laff": %d,
                    "species": "CAT",
                    "toonUpGag": {
                        "gagType": "TOON_UP",
                        "level": %d,
                        "isOrganic": false
                    },
                    "trapGag": {
                        "gagType": "TRAP",
                        "level": %d,
                        "isOrganic": false
                    },
                    "lureGag": {
                        "gagType": "LURE",
                        "level": %d,
                        "isOrganic": false
                    },
                    "soundGag": {
                        "gagType": "SOUND",
                        "level": %d,
                        "isOrganic": false
                    },
                    "throwGag": {
                        "gagType": "THROW",
                        "level": %d,
                        "isOrganic": false
                    },
                    "squirtGag": {
                        "gagType": "SQUIRT",
                        "level": %d,
                        "isOrganic": false
                    },
                    "dropGag": {
                        "gagType": "DROP",
                        "level": %d,
                        "isOrganic": false
                    }
                }
                """.formatted(laff, toonUpLevel, trapLevel, lureLevel, soundLevel, throwLevel, squirtLevel, dropLevel);

        final RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/toons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body);

        final String response = this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return OBJECT_MAPPER.readValue(response, Toon.class);
    }
}
