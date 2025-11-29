//package molecule.db.compliance.domains
//
//import molecule.DomainStructure
//import molecule.db.compliance.domains.JoinTable.A
//
//
//object SocialApp1 extends DomainStructure {
//
//  // Roles need to extend `Role` for the plugin to recognize a Role definition
//  trait Admin extends Role with all
//  trait Moderator extends Role with read with update with delete
//  trait StandardUser extends Role with read with save
//  trait Guest extends Role with query
//
//
//  // Entity with default roles and comprehensive authorization examples
//  trait Post extends StandardUser with Moderator { // default roles allowed for Post entity
//    val title    = oneString
//    val content  = oneString
//    val authorId = oneString
//
//    // === Single Role ===
//    val published = oneBoolean.allowRoles[Guest]
//
//    // === Multiple Roles (2 roles) ===
//    val published2 = oneBoolean.allowRoles[(Guest, StandardUser)]
//
//    // === Multiple Roles (3+ roles) ===
//    val published3 = oneBoolean.allowRoles[(Guest, StandardUser, Moderator)]
//
//    // === Single Action ===
//    val flagged = oneBoolean.allowActions[update]
//
//    // === Multiple Actions (2 actions) ===
//    val flagged2 = oneBoolean.allowActions[(update, delete)]
//
//    // === Multiple Actions (3+ actions) ===
//    val flagged3 = oneBoolean.allowActions[(query, update, delete)]
//
//    // === Composite Actions ===
//    val readableContent  = oneString.allowActions[read]   // query + subscribe
//    val writableContent  = oneString.allowActions[write]  // save + insertMany + update + delete
//    val allAccessContent = oneString.allowActions[all]    // read + write
//
//    // === Role + Action Combinations (all 4 variations) ===
//    val views1 = oneLong.allowScope[StandardUser, update]                       // Single role, single action
//    val views2 = oneLong.allowScope[StandardUser, (update, delete)]             // Single role, multiple actions
//    val views3 = oneLong.allowScope[(Guest, StandardUser), update]              // Multiple roles, single action
//    val views4 = oneLong.allowScope[(Guest, StandardUser), (update, delete)]    // Multiple roles, multiple actions
//
//    // === Composite Actions with Roles ===
//    val readableByUser   = oneString.allowScope[StandardUser, read]
//    val writableByMod    = oneString.allowScope[Moderator, write]
//    val allAccessByAdmin = oneString.allowScope[Admin, all]
//
//    // === Chained allowRoleActions (permission matrix) ===
//    val mixed = oneLong
//      .allowScope[StandardUser, insertMany]
//      .allowScope[Moderator, (save, update, insertMany)]
//      .allowScope[(Guest, Moderator), (subscribe, delete)]
//
//
////    // === Conditional Authorization (authorizeIf) ===
////
////    val age         = oneInt
////    val publishDate = oneInt
////    val verified    = oneBoolean
////
////    // Single authorizeIf
////    val ageRestricted = oneString.authorizeIf(age.validate(_ >= 18))
////
////    // Multiple authorizeIf (chained)
////    val content1 = oneString
////      .allowRoles[StandardUser]                       // Role constraint
////      .authorizeIf(age.validate(_ >= 18))             // Age check
////      .authorizeIf(verified.validate(_ == true))      // Verification check
////      .authorizeIf(publishDate.validate(_ > 42))      // Publish check
////
////    // Multiple authorizeIf (varargs - same as chained)
////    val content2 = oneString
////      .allowRoles[StandardUser]
////      .authorizeIf(
////        age.validate(_ >= 18),
////        verified.validate(_ == true),
////        publishDate.validate(_ > 42)
////      )
////
////    // Complex predicate in authorizeIf
////    val complexRestriction = oneString.authorizeIf(
////      age.validate(v => v >= 18 && v < 65 && v % 5 == 0)
////    )
////
////    // Cross-field validation with .value
////    val minAge  = oneInt
////    val userAge = oneInt
////    val crossFieldRestriction = oneString.authorizeIf(
////      userAge.validate(_ >= minAge.value)
////    )
////
////    // Boolean field authorization
////    val isPublic = oneBoolean
////    val publicOnlyContent = oneString.authorizeIf(
////      isPublic.validate(identity)
////    )
//  }
//
//
//  // Entity extending Role (demonstrates entity can be a role)
//  trait User extends StandardUser {
//    val name  = oneString
//    val email = oneString.email
//  }
//
//
//  // Entity extending Authenticated (all fields require any authenticated user)
//  trait UserProfile extends Authenticated {
//    val bio         = oneString  // Any authenticated user
//    val location    = oneString  // Any authenticated user
//    val website     = oneString  // Any authenticated user
//    val phoneNumber = oneString.allowRoles[Admin]  // Override: only Admin
//  }
//
//
//  // Public entity (no role extension = public by default, like GraphQL)
//  trait PublicData {
//    val info        = oneString  // Public (no role required)
//    val description = oneString  // Public
//    val preview     = oneString  // Public
//
//    // Require login but accept any authenticated user (regardless of role)
//    val fullArticle = oneString.authenticated  // Any logged-in user
//
//    // Or restrict to specific role(s)
//    val adminNotes  = oneString.allowRoles[Admin]  // Only Admin
//  }
//
//
//  // Private entity (use role extension to restrict access)
//  trait AdminPanel extends Admin {
//    val revenue   = oneDouble  // Only Admin can access (entity default)
//    val userCount = oneLong.allowRoles[(Admin, Moderator)]  // Expand to Admin OR Moderator
//  }
//}
