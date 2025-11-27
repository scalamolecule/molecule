package molecule.db.compliance.domains

import molecule.DomainStructure
import molecule.db.compliance.domains.JoinTable.A


object SocialApp extends DomainStructure {

  // Roles need to extend `Role` for the plugin to recognize a Role definition
  trait Admin extends Role with all
  trait Moderator extends Role with read with update with delete
  trait StandardUser extends Role with read with save
  trait Guest extends Role with query


  // Entity with default roles and comprehensive authorization examples
  trait Post extends StandardUser with Moderator { // default roles allowed for Post entity
    val title    = oneString
    val content  = oneString
    val authorId = oneString

    // === Single Role ===
    val published = oneBoolean.allowRoles[Guest]

    // === Multiple Roles (2 roles) ===
    val published2 = oneBoolean.allowRoles[(Guest, StandardUser)]

    // === Multiple Roles (3+ roles) ===
    val published3 = oneBoolean.allowRoles[(Guest, StandardUser, Moderator)]

    // === Single Action ===
    val flagged = oneBoolean.allowActions[update]

    // === Multiple Actions (2 actions) ===
    val flagged2 = oneBoolean.allowActions[(update, delete)]

    // === Multiple Actions (3+ actions) ===
    val flagged3 = oneBoolean.allowActions[(query, update, delete)]

    // === Composite Actions ===
    val readableContent  = oneString.allowActions[read]   // query + subscribe
    val writableContent  = oneString.allowActions[write]  // save + insertMany + update + delete
    val allAccessContent = oneString.allowActions[all]    // read + write

    // === Role + Action Combinations (all 4 variations) ===
    val views1 = oneLong.allowRoleActions[StandardUser, update]                       // Single role, single action
    val views2 = oneLong.allowRoleActions[StandardUser, (update, delete)]             // Single role, multiple actions
    val views3 = oneLong.allowRoleActions[(Guest, StandardUser), update]          // Multiple roles, single action
    val views4 = oneLong.allowRoleActions[(Guest, StandardUser), (update, delete)] // Multiple roles, multiple actions

    // === Composite Actions with Roles ===
    val readableByUser  = oneString.allowRoleActions[StandardUser, read]
    val writableByMod   = oneString.allowRoleActions[Moderator, write]
    val allAccessByAdmin = oneString.allowRoleActions[Admin, all]

    // === Chained allowRoleActions (permission matrix) ===
    val mixed = oneLong
      .allowRoleActions[StandardUser, insertMany]
      .allowRoleActions[Moderator, (save, update, insertMany)]
      .allowRoleActions[(Guest, Moderator), (subscribe, delete)]


    // === Authentication ===

    // Pure authentication (no other constraints)
    val authenticatedOnly = oneString.authenticated

    // Authentication mixed with other constraints
    val authenticatedEmail = oneString.email.authenticated


    // === Conditional Authorization (authorizeIf) ===

    val age         = oneInt
    val publishDate = oneInt
    val verified    = oneBoolean

    // Single authorizeIf
    val ageRestricted = oneString.authorizeIf(age.validate(_ >= 18))

    // Multiple authorizeIf (chained)
    val content1 = oneString
      .allowRoles[StandardUser]                       // Role constraint
      .authorizeIf(age.validate(_ >= 18))         // Age check
      .authenticated                              // Auth constraint
      .authorizeIf(verified.validate(_ == true))  // Verification check
      .authorizeIf(publishDate.validate(_ > 42))  // Publish check

    // Multiple authorizeIf (varargs - same as chained)
    val content2 = oneString
      .allowRoles[StandardUser]
      .authorizeIf(
        age.validate(_ >= 18),
        verified.validate(_ == true),
        publishDate.validate(_ > 42)
      )

    // Complex predicate in authorizeIf
    val complexRestriction = oneString.authorizeIf(
      age.validate(v => v >= 18 && v < 65 && v % 5 == 0)
    )

    // Cross-field validation with .value
    val minAge  = oneInt
    val userAge = oneInt
    val crossFieldRestriction = oneString.authorizeIf(
      userAge.validate(_ >= minAge.value)
    )

    // Boolean field authorization
    val isPublic = oneBoolean
    val publicOnlyContent = oneString.authorizeIf(
      isPublic.validate(identity)
    )
  }


  // Entity extending Role (demonstrates entity can be a role)
  trait User extends StandardUser {
    val name  = oneString
    val email = oneString.email.authenticated
  }


  // Entity with no authorization constraints (baseline/public)
  trait PublicData {
    val info        = oneString  // No constraints = accessible to all
    val description = oneString
  }


  // Entity with no default roles (explicit deny by default)
  trait AdminPanel {
    val revenue   = oneDouble.allowRoles[Admin]
    val userCount = oneLong.allowRoles[(Admin, Moderator)]
  }
}
