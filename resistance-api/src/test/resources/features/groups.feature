Feature: groups are functional
  Scenario Outline: toon joins a group
    When a toon is created
    Then the toon can create a group with minLaff <minLaff>, minToonUp <minToonUp>, minTrap <minTrap>, minLure <minLure>, minSound <minSound>, minThrow <minThrow>, minSquirt <minSquirt>, minDrop <minDrop>, maxSize <maxSize>
    Then a toon can join the group with laff <laff>, toonUp <toonUp>, trap <trap>, lure <lure>, sound <sound>, throw <throw>, squirt <squirt>, drop <drop>
    Then the leader can remove the toon
    And the leader can delete the group
    Examples:
      | laff | toonUp | trap | lure | sound | throw | squirt | drop | minLaff | minToonUp | minTrap | minLure | minSound | minThrow | minSquirt | minDrop | maxSize |
      | 140  | 7      | 7    | 7    | 7     | 7     | 7      | 7    | 0       | 0         | 0       | 0       | 0        | 0        | 0         | 0       | 2       |
      | 140  | 7      | 7    | 7    | 7     | 7     | 7      | 7    | 1       | 1         | 1       | 1       | 1        | 1        | 1         | 1       | 2       |
      | 140  | 7      | 7    | 7    | 7     | 7     | 7      | 7    | 140     | 7         | 7       | 7       | 7        | 7        | 7         | 7       | 2       |

  Scenario Outline: toon fails to join a group that is full
    When a toon is created
    Then the toon can create a group with minLaff <minLaff>, minToonUp <minToonUp>, minTrap <minTrap>, minLure <minLure>, minSound <minSound>, minThrow <minThrow>, minSquirt <minSquirt>, minDrop <minDrop>, maxSize <maxSize>
    Then a toon fails to join the group with laff <laff>, toonUp <toonUp>, trap <trap>, lure <lure>, sound <sound>, throw <throw>, squirt <squirt>, drop <drop>
    Examples:
      | laff | toonUp | trap | lure | sound | throw | squirt | drop | minLaff | minToonUp | minTrap | minLure | minSound | minThrow | minSquirt | minDrop | maxSize |
      | 140  | 7      | 7    | 7    | 7     | 7     | 7      | 7    | 0       | 0         | 0       | 0       | 0        | 0        | 0         | 0       | 1       |
      | 1    | 7      | 7    | 7    | 7     | 7     | 7      | 7    | 2       | 0         | 0       | 0       | 0        | 0        | 0         | 0       | 2       |
      | 140  | 0      | 7    | 7    | 7     | 7     | 7      | 7    | 0       | 1         | 0       | 0       | 0        | 0        | 0         | 0       | 2       |
      | 140  | 7      | 0    | 7    | 7     | 7     | 7      | 7    | 0       | 0         | 1       | 0       | 0        | 0        | 0         | 0       | 2       |
      | 140  | 7      | 7    | 0    | 7     | 7     | 7      | 7    | 0       | 0         | 0       | 1       | 0        | 0        | 0         | 0       | 2       |
      | 140  | 7      | 7    | 7    | 0     | 7     | 7      | 7    | 0       | 0         | 0       | 0       | 1        | 0        | 0         | 0       | 2       |
      | 140  | 7      | 7    | 7    | 7     | 0     | 7      | 7    | 0       | 0         | 0       | 0       | 0        | 1        | 0         | 0       | 2       |
      | 140  | 7      | 7    | 7    | 7     | 7     | 0      | 7    | 0       | 0         | 0       | 0       | 0        | 0        | 1         | 0       | 2       |
      | 140  | 7      | 7    | 7    | 7     | 7     | 7      | 0    | 0       | 0         | 0       | 0       | 0        | 0        | 0         | 1       | 2       |

  Scenario: toon fails to join their own group
    When a toon is created
    Then that toon cannot join their own group
