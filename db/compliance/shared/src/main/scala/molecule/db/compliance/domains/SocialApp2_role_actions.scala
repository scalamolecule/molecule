package molecule.db.compliance.domains

import molecule.DomainStructure


/**
 * Layer 2: Role Actions - Grant additional actions to roles at entity level
 *
 * Demonstrates:
 * - `with updating[Role]` - grants update permission to role(s) at entity level
 * - `with deleting[Role]` - grants delete permission to role(s) at entity level
 *
 * These grants extend the base permissions defined by role actions.
 * All attributes in the entity inherit these grants.
 */
object SocialApp2_role_actions extends DomainStructure {

  trait Guest extends Role with query
  trait Member extends Role with query
  trait Moderator extends Role with query
  trait Admin extends Role with query with save with insert with update with delete


  // Entity-level update grant to single role
  // Base: Member has query, Admin has all (all 5 actions covered)
  // Grant: Member can also update all attributes
  trait Post extends Member with Admin
    with updating[Member] {
    val content = oneString
    val title   = oneString
  }


  // Entity-level delete grant to single role
  // Base: Moderator has query, Admin has all (all 5 actions covered)
  // Grant: Moderator can also delete
  trait Comment extends Moderator with Admin
    with deleting[Moderator] {
    val text = oneString
  }


  // Multiple role grants at entity level
  // Both Member and Moderator can update, Admin provides all actions
  trait Article extends Member with Moderator with Admin
    with updating[(Member, Moderator)] {
    val title   = oneString
    val content = oneString
  }


  // Combining updating and deleting grants
  // Moderator can update, both Moderator and Admin can delete
  trait ModLog extends Admin with Moderator
    with updating[Moderator]
    with deleting[(Moderator, Admin)] {
    val action    = oneString
    val timestamp = oneLong
  }


  // Different roles with different grants
  trait UserProfile extends Member with Admin
    with updating[Member]
    with deleting[Admin] {
    val displayName = oneString
    val bio         = oneString
  }
}
