package molecule.db.compliance.domains

import molecule.DomainStructure


/**
 * Layer 3: Attribute Roles - Restrict attribute access to specific roles
 *
 * Demonstrates:
 * - `.only[R]` - restrict attribute to ONLY specific role(s)
 * - `.exclude[R]` - exclude specific role(s) from attribute access
 *
 * These restrictions override entity-level permissions for specific attributes.
 */
object SocialApp3_attr_roles extends DomainStructure {

  trait Guest extends Role with query
  trait Member extends Role with query
  trait Moderator extends Role with query
  trait Admin extends Role with query with save with insert with update with delete


  // Using .only[R] to restrict to specific role(s)
  trait Post extends Guest with Member with Admin {
    val title = oneString // All entity roles can access (Guest, Member, Admin)

    // Only Member and Admin can access this attribute
    val content = oneString.only[(Member, Admin)]

    // Only Admin can access this attribute
    val secret = oneString.only[Admin]
  }


  // Using .exclude[R] to exclude specific role(s)
  trait Article extends Guest with Member with Moderator with Admin {
    val title = oneString // All entity roles can access

    // Exclude Guest - only Member, Moderator, Admin can access
    val fullText = oneString.exclude[Guest]

    // Exclude multiple roles - only Admin can access
    val editHistory = oneString.exclude[(Guest, Member, Moderator)]
  }


  // Combining .only and .exclude on different attributes
  trait ModerationLog extends Guest with Member with Moderator with Admin {
    val action = oneString // All entity roles can access

    // Only Moderator and Admin can access details
    val details = oneString.only[(Moderator, Admin)]

    // Exclude Member and Guest from audit
    val audit = oneString.exclude[(Guest, Member)]

    // Only Admin can access sensitive data
    val sensitiveData = oneString.only[Admin]
  }


  // Practical example: Progressive disclosure of information
  trait UserProfile extends Member with Moderator with Admin {
    val displayName = oneString // All roles (Member, Moderator, Admin)

    val email = oneString.only[(Moderator, Admin)] // Moderators and Admins only

    val ipAddress = oneString.only[Admin] // Admins only
  }
}
