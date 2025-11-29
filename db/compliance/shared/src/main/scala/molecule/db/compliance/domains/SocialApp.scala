package molecule.db.compliance.domains

import molecule.DomainStructure
import molecule.db.compliance.domains.JoinTable.A


object SocialApp extends DomainStructure {

  // ============================================================================
  // ROLES (sorted alphabetically)
  // ============================================================================

  trait Admin extends Role with all // All actions
  trait ContentModerator extends Role with read with delete // Same actions as Moderator, different scope
  trait Guest extends Role with query // Read-only, minimal
  trait Moderator extends Role with read with delete // Can read and clean up
  trait StandardUser extends Role with read with save // Can read and create


  // ============================================================================
  // ENTITY 1: PUBLIC ENTITY
  // Tests: Public access, .authenticated, .allowOnlyRoles, .allowMoreRoles on public entity
  // ============================================================================

  trait Article {
    val title             = oneString // Public (no auth required)
    val preview           = oneString // Public
    val fullText          = oneString.authenticated // Any authenticated role
    val adminNotes        = oneString.allowOnlyRoles[Admin] // Admin only (replace: public -> Admin)
    val subscriberContent = oneString.allowMoreRoles[(StandardUser, Moderator)] // Add specific roles to public
  }


  // ============================================================================
  // ENTITY 2: SINGLE ROLE ENTITY
  // Tests: Single role inheritance, .allowMoreRoles adding roles, .allowOnlyRoles replacing role
  // ============================================================================

  trait ModeratorLog extends Moderator {
    val action     = oneString // Inherits: Moderator only
    val timestamp  = oneLong // Inherits: Moderator only
    val details    = oneString.allowMoreRoles[Admin] // Moderator + Admin (additive)
    val auditTrail = oneString.allowOnlyRoles[Admin] // Admin only (replace: Moderator -> Admin)
  }


  // ============================================================================
  // ENTITY 3: MULTIPLE ROLES ENTITY (Main test entity)
  // Tests: Multiple role inheritance, .allowMoreRoles adding new roles, .allowOnlyRoles narrowing
  // ============================================================================

  trait Post extends StandardUser with Moderator {
    // === Attributes inheriting entity defaults ===
    val title    = oneString // StandardUser, Moderator
    val content  = oneString // StandardUser, Moderator
    val authorId = oneString // StandardUser, Moderator

    // === .allowMoreRoles with NEW role (adds Guest) ===
    val viewCount = oneLong.allowMoreRoles[Guest]
    // Expected: Guest + StandardUser + Moderator

    // === .allowMoreRoles with EXISTING role (should be compile error, but testing codegen) ===
    val likeCount = oneLong.allowMoreRoles[StandardUser]
    // Expected: StandardUser + Moderator (redundant - should error in production)

    // === .allowMoreRoles with MULTIPLE NEW roles ===
    val published = oneBoolean.allowMoreRoles[(Guest, Admin)]
    // Expected: Guest + Admin + StandardUser + Moderator

    // === .allowMoreRoles with MIX of new and existing roles ===
    val featured = oneBoolean.allowMoreRoles[(Guest, Moderator)]
    // Expected: Guest + StandardUser + Moderator (Moderator redundant)

    // === .allowOnlyRoles narrowing from multiple to single ===
    val moderatorNotes = oneString.allowOnlyRoles[Moderator]
    // Expected: Moderator only (replace: StandardUser + Moderator -> Moderator)

    // === .allowOnlyRoles narrowing from multiple to different multiple ===
    val internalFlags = oneString.allowOnlyRoles[(Moderator, Admin)]
    // Expected: Moderator + Admin (replace: StandardUser + Moderator -> Moderator + Admin)
  }


  // ============================================================================
  // ENTITY 4: AUTHENTICATED ENTITY
  // Tests: Authenticated requirement, .allowOnlyRoles override on Authenticated
  // ============================================================================

  trait UserProfile extends Authenticated {
    val displayName = oneString // Any authenticated role
    val bio         = oneString // Any authenticated role
    val email       = oneString.allowOnlyRoles[Admin] // Admin only (replace)
    val karma       = oneLong.allowOnlyRoles[(StandardUser, Moderator, Admin)] // Subset (excludes Guest)
  }


  // ============================================================================
  // ENTITY 5: ADMIN-ONLY ENTITY
  // Tests: Admin-only access, .allowMoreRoles expanding access
  // ============================================================================

  trait SystemConfig extends Admin {
    val apiKey    = oneString // Admin only
    val secretKey = oneString // Admin only
    val publicKey = oneString.allowMoreRoles[Moderator] // Admin + Moderator (additive)
  }


  // ============================================================================
  // ENTITY 6: SCOPE DIMENSION DEMONSTRATION
  // Tests: Same actions (read+delete), different scopes
  // Demonstrates that actions and scope are orthogonal dimensions
  // ============================================================================

  // ContentModerator scope - reviews user-generated content
  trait ContentQueue extends ContentModerator {
    val reportedContent = oneString // ContentModerator only
    val reportReason    = oneString // ContentModerator only
    val reviewNotes     = oneString.allowMoreRoles[Admin] // ContentModerator + Admin
  }

  // Note: ContentModerator and Moderator have identical actions (read+delete)
  // but different scopes (ContentQueue and ModeratorLog respectively)
}
