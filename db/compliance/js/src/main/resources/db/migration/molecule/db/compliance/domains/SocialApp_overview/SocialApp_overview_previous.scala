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
trait SocialApp_overview extends DomainStructure {

  // Role definitions
  trait Guest extends Role with query
  trait Member extends Role with query
  trait Admin extends Role with query with save with insert with update with delete


  // ============================================================================
  // Layer 1: Roles - Entity-level role access
  // ============================================================================

  // Public entity - anyone can access
  trait Article {
    val title = oneString
  }

  // Single role entity (Admin provides all 5 actions)
  trait Post extends Member with Admin {
    val content = oneString
  }


  // ============================================================================
  // Layer 2: Entity Action Grants
  // ============================================================================

  // Entity-level update grant (Admin provides all 5 actions)
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

  // Using .exclude[R] (Admin provides all 5 actions)
  trait Profile extends Guest with Member with Admin {
    val username = oneString

    val email = oneString.exclude[Guest]
  }


  // ============================================================================
  // Layer 4: Attribute Update Grant
  // ============================================================================

  // Admin provides all 5 actions
  trait Draft extends Member with Admin {
    val content = oneString

    val title = oneString.updating[Member]
  }


  // ============================================================================
  // Composed Example - All layers working together
  // ============================================================================
  // Demonstrates how attribute restrictions (Layer 3) can prevent entity-level
  // grants (Layer 2) from being applied to certain roles, requiring attribute-level
  // grants (Layer 4) for fine-grained control.

  trait BlogPost extends Guest with Member with Admin
    with updating[Admin]            // Layer 2: Entity grant (redundant for Admin who has update, but shows intent)
    with deleting[Admin] {          // Layer 2: Entity grant (redundant for Admin who has delete, but shows intent)
                                    // Note: Can't grant updating/deleting to Member due to internal.only[Admin]

    val title = oneString           // All roles can access

    val content = oneString         // Member and Admin can access
      .exclude[Guest]               // Layer 3: Guest excluded
      .updating[Member]             // Layer 4: Member gets update via attribute grant (since no entity grant)

    val viewCount = oneLong         // All roles can access
      .updating[Guest]              // Layer 4: Guest gets update via attribute grant

    val internal = oneString        // Only Admin can access (blocks entity grants to other roles)
      .only[Admin]                  // Layer 3: Restricts to Admin only
  }
}
