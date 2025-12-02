package molecule.db.compliance.domains

import molecule.DomainStructure


/**
 * Layer 4: Attribute Update - Grant update permission at attribute level
 *
 * Demonstrates:
 * - `.updating[R]` - grant update permission to role(s) for this specific attribute
 *
 * Unlike entity-level grants, this applies only to the specific attribute.
 * Useful for fine-grained control where only some attributes are updatable.
 */
object SocialApp4_attr_update extends DomainStructure {

  trait Guest extends Role with query
  trait Member extends Role with read
  trait Moderator extends Role with read
  trait Admin extends Role with all


  // Grant update to single role on specific attribute
  // Admin provides all 6 actions (requirement: all actions must be available)
  trait Post extends Member with Admin {
    val content = oneString // Member can read (query + subscribe)

    // Member can also update this specific attribute
    val title = oneString.updating[Member]
  }


  // Grant update to multiple roles on specific attribute
  // Admin provides all 6 actions (requirement: all actions must be available)
  trait Article extends Member with Moderator with Admin {
    val preview = oneString

    // Both Member and Moderator can update tags
    val tags = oneString.updating[(Member, Moderator)]
  }


  // Different attributes with different update grants
  // Admin provides all 6 actions (requirement: all actions must be available)
  trait UserProfile extends Member with Moderator with Admin {
    // Member can only read these
    val username = oneString
    val email    = oneString

    // Member can update display name
    val displayName = oneString.updating[Member]

    // Both Member and Moderator can update bio
    val bio = oneString.updating[(Member, Moderator)]

    // Only Moderator can update verified status
    val verified = oneBoolean.updating[Moderator]
  }


  // Guest getting update permission on specific attribute
  // Admin provides all 6 actions (requirement: all actions must be available)
  trait Reaction extends Guest with Member with Admin {
    val emoji = oneString // Guest can query, Member can read

    // Guest can update count (special case - like incrementing likes)
    val count = oneInt.updating[Guest]
  }


  // Combining multiple attribute-level grants
  // Admin provides all 6 actions (requirement: all actions must be available)
  trait Comment extends Member with Moderator with Admin {
    val text = oneString

    // Member can update their own comment text
    val content = oneString.updating[Member]

    // Moderator can update status
    val status = oneString.updating[Moderator]

    // Both can update tags
    val tags = oneString.updating[(Member, Moderator)]
  }
}
