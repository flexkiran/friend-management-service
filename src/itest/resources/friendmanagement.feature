Feature: Friends Management Service
  Scenario: 1. User should be able to create a friend connection between two email addresses.
    Given Below email addresses exists
      | email             |
      | andy@example.com  |
      | john@example.com  |
      | kane@example.com  |
      | rose@example.com  |
      | frank@example.com |
      | grace@example.com |
      | kate@example.com  |

     When User creates connection between
      | email1           | email2           |
      | andy@example.com | john@example.com |

     Then User should receive success message

  Scenario: 2. User should be able to retrieve friends list for an email address`
    Given Below email addresses exists
      | email             |
      | andy@example.com  |
      | john@example.com  |
      | kane@example.com  |
      | rose@example.com  |
      | frank@example.com |
      | grace@example.com |
      | kate@example.com  |

     When User creates connection between
      | email1           | email2           |
      | andy@example.com | john@example.com |

     Then User should receive success message

     When User retrieves friend list for andy@example.com

     Then User should receive below email
      | email            |
      | john@example.com |

  Scenario: 3 : User should be able to retrieve the common friends list between two email addresses.
    Given Below email addresses exists
      | email             |
      | andy@example.com  |
      | john@example.com  |
      | kane@example.com  |
      | rose@example.com  |
      | frank@example.com |
      | grace@example.com |
      | kate@example.com  |

    Given below email addresses are friend
      | email1           | email2           |
      | andy@example.com | john@example.com |
      | rose@example.com | john@example.com |

     When User retrieves common friend list between
      | email1           | email2           |
      | andy@example.com | rose@example.com |

     Then User should receive following details
      | emails           | count | success |
      | john@example.com | 1     | true    |

  Scenario: 4 : User should be able to subscribe for updates from one email address to another email address
    Given Below email addresses exists
      | email             |
      | andy@example.com  |
      | john@example.com  |
      | kane@example.com  |
      | rose@example.com  |
      | frank@example.com |

     When User subscribes to updates for
      | requestor        | target            |
      | rose@example.com | frank@example.com |

     Then User should receive success message
  #
  Scenario: 5.a : User should be able to block updates from a friends email address.
    Given Below email addresses exists
      | email             |
      | andy@example.com  |
      | john@example.com  |
      | kane@example.com  |
      | rose@example.com  |
      | frank@example.com |
      | grace@example.com |
      | kate@example.com  |

    Given below email addresses are friend
      | email1           | email2           |
      | andy@example.com | john@example.com |

     When User blocks updates for
      | requestor        | target           |
      | andy@example.com | john@example.com |

     Then User should receive success message
      And rose@example.com should not receive notifications from john@example.com

  Scenario: 5.b : User should be able to block updates from a non-friend email address.
    Given Below email addresses exists
      | email             |
      | andy@example.com  |
      | john@example.com  |
      | kane@example.com  |
      | rose@example.com  |
      | frank@example.com |
      | grace@example.com |
      | kate@example.com  |

    Given below email addresses are not friend
      | email1            | email2           |
      | frank@example.com | kane@example.com |

     When User blocks updates for
      | requestor         | target           |
      | frank@example.com | kane@example.com |

     Then User should receive success message

      And frank@example.com should not receive notifications from kane@example.com

     When User creates connection between
      | email1            | email2           |
      | frank@example.com | kane@example.com |

     Then User should receive failure message
      | errorCode | message                                        |
      | 3003      | One email have blocked another email's updates |

  Scenario: 6.a  User should be able to retrieve all email addresses that can receive updates from an email address.
    Given Below email addresses exists
      | email             |
      | andy@example.com  |
      | john@example.com  |
      | kane@example.com  |
      | rose@example.com  |
      | frank@example.com |
      | grace@example.com |
      | kate@example.com  |

    Given below email addresses are friend
      | email1           | email2           |
      | andy@example.com | john@example.com |

     When User subscribes to updates for
      | requestor         | target           |
      | grace@example.com | andy@example.com |

     Then User should receive success message

     When message is sent with
      | text                          | sender           |
      | Hello World! kate@example.com | andy@example.com |

     Then User receives following subscribers
      | emails                                              | success |
      | john@example.com;grace@example.com;kate@example.com | true    |

Scenario: 6.b  User should be able to retrieve all email addresses that can receive updates from an email address.
    Given Below email addresses exists
      | email             |
      | andy@example.com  |
      | john@example.com  |
      | kane@example.com  |
      | rose@example.com  |
      | frank@example.com |
      | grace@example.com |
      | kate@example.com  |

    Given below email addresses are friend
      | email1           | email2           |
      | andy@example.com | john@example.com |
      | andy@example.com | frank@example.com |

     When User subscribes to updates for
      | requestor         | target           |
      | grace@example.com | andy@example.com |
      | kane@example.com  | andy@example.com |

     Then User should receive success message

     When message is sent with
      | text                          | sender           |
      | Hello World! nobody is here   | andy@example.com |

     Then User receives following subscribers
      | emails                                                                  | success |
      | john@example.com;frank@example.com;grace@example.com;kane@example.com   | true    |

     When message is sent with
      | text                               | sender           |
      | Hello World! I dont have friends   | kate@example.com |

     Then User receives following subscribers
      | emails     | success |
      |            | true    |

     When message is sent with
      | text                               | sender           |
      | Hello World! I am not registered   | unregisterd@example.com |

     Then User should receive failure message
      | errorCode | message                         |
      | 2002      | Email address is not registered |
