package molecule.db.compliance.domains

import molecule.DomainStructure


/**
 * Authorization Overview - Minimal Examples
 *
 * This file provides the absolute minimal examples for understanding
 * the 4 authorization layers. Each example demonstrates one concept only.
 *
 * After understanding each layer individually, see the composed example
 * at the end showing how they all work together.
 */
object SocialApp0_overview extends DomainStructure {

  // Role definitions
  trait Guest extends Role with query
  trait Member extends Role with read
  trait Admin extends Role with all


  // ============================================================================
  // Layer 1: Roles - Entity-level role access
  // ============================================================================

  // Public entity - anyone can access
  trait Article {
    val title = oneString
  }

  // Single role entity (Admin provides all 6 actions)
  trait Post extends Member with Admin {
    val content = oneString
  }


  // ============================================================================
  // Layer 2: Entity Action Grants
  // ============================================================================

  // Entity-level update grant (Admin provides all 6 actions)
  trait Comment extends Member with Admin
    with updating[Member] {
    val text = oneString
  }

  // Entity-level delete grant
  trait Log extends Admin with Member
    with deleting[Admin] {
    val entry = oneString
  }


  // ============================================================================
  // Layer 3: Attribute Role Restrictions
  // ============================================================================

  // Using .only[R]
  trait Settings extends Member with Admin {
    val theme = oneString

    val apiKey = oneString.only[Admin]
  }

  // Using .exclude[R] (Admin provides all 6 actions)
  trait Profile extends Guest with Member with Admin {
    val username = oneString

    val email = oneString.exclude[Guest]
  }


  // ============================================================================
  // Layer 4: Attribute Update Grant
  // ============================================================================

  // Admin provides all 6 actions
  trait Draft extends Member with Admin {
    val content = oneString

    val title = oneString.updating[Member]
  }


  // ============================================================================
  // Composed Example - All layers working together
  // ============================================================================

  trait BlogPost extends Guest with Member with Admin
    with updating[Member]           // Layer 2: Entity grant
    with deleting[Admin] {          // Layer 2: Entity grant

    val title = oneString           // All roles

    val content = oneString         // All roles, Member gets entity update
      .exclude[Guest]               // Layer 3: No Guest

    val viewCount = oneLong         // All roles
      .updating[Guest]              // Layer 4: Guest can update

    val internal = oneString        // Only Admin, gets entity update + delete
      .only[Admin]                  // Layer 3
  }
}
