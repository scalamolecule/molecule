package molecule.db.compliance.test.authorization

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.{Api_async, AuthContext}
import molecule.db.common.spi.{Conn, Spi_async}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.SocialApp.*
import molecule.db.compliance.setup.DbProviders
import org.scalactic.Equality

case class Roles(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  // ============================================================================
  // 1. PUBLIC ENTITY TESTS (Article)
  // Tests: Public access, .authenticated, .allowOnlyRoles (narrowing from public)
  // ============================================================================

  "1.1 Public entity - no auth required for public attributes" - social {
    for {
      _ <- Article.title("Breaking News").preview("Summary...").save.transact

      // No authentication needed for public attributes
      _ <- Article.title.query.get.map(_ ==> List("Breaking News"))
      _ <- Article.preview.query.get.map(_ ==> List("Summary..."))
    } yield ()
  }

  "1.2 Public entity - .authenticated requires any role" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Article.title("Article").fullText("Full content...").save.transact(using baseConn)

      // Unauthenticated access to .authenticated attribute fails
      _ <- Article.fullText.query.get(using baseConn)
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: No authenticated role provided"
        }

      // Any authenticated role has access
      _ <- Article.fullText.query.get(using baseConn.withAuth("u1", "Guest")).map(_ ==> List("Full content..."))
      _ <- Article.fullText.query.get(using baseConn.withAuth("u2", "StandardUser")).map(_ ==> List("Full content..."))
      _ <- Article.fullText.query.get(using baseConn.withAuth("u3", "Admin")).map(_ ==> List("Full content..."))
    } yield ()
  }

  "1.3 Public entity - .allowOnlyRoles[Admin] narrows to Admin only" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Article.title("Article").adminNotes("Internal notes").save.transact(using baseConn)

      // Unauthenticated access denied
      _ <- Article.adminNotes.query.get(using baseConn)
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: No authenticated role provided"
        }

      // Non-Admin roles denied
      _ <- Article.adminNotes.query.get(using baseConn.withAuth("u1", "Guest"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query attribute 'Article.adminNotes'"
        }

      _ <- Article.adminNotes.query.get(using baseConn.withAuth("u2", "StandardUser"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'StandardUser' cannot query attribute 'Article.adminNotes'"
        }

      // Admin has access
      _ <- Article.adminNotes.query.get(using baseConn.withAuth("u3", "Admin"))
        .map(_ ==> List("Internal notes"))
    } yield ()
  }

  "1.4 Public entity - .allowMoreRoles adds specific roles to public" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Article.title("Article").subscriberContent("Subscriber content").save.transact(using baseConn)

      // StandardUser and Moderator have access (added via .allowMoreRoles)
      _ <- Article.subscriberContent.query.get(using baseConn.withAuth("u1", "StandardUser"))
        .map(_ ==> List("Subscriber content"))
      _ <- Article.subscriberContent.query.get(using baseConn.withAuth("u2", "Moderator"))
        .map(_ ==> List("Subscriber content"))

      // Guest and Admin not included
      _ <- Article.subscriberContent.query.get(using baseConn.withAuth("u3", "Guest"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query attribute 'Article.subscriberContent'"
        }

      _ <- Article.subscriberContent.query.get(using baseConn.withAuth("u4", "Admin"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Admin' cannot query attribute 'Article.subscriberContent'"
        }
    } yield ()
  }


  // ============================================================================
  // 2. SINGLE ROLE ENTITY TESTS (ModeratorLog)
  // Tests: Single role inheritance, .allowMoreRoles (additive), .allowOnlyRoles (replacement)
  // ============================================================================

  "2.1 Single role entity - entity role has access" - social {
    val baseConn = summon[Conn]
    for {
      _ <- ModeratorLog.action("Deleted spam").timestamp(123L).save.transact(using baseConn)

      // Moderator has access to entity
      _ <- ModeratorLog.action.query.get(using baseConn.withAuth("u1", "Moderator"))
        .map(_ ==> List("Deleted spam"))
      _ <- ModeratorLog.timestamp.query.get(using baseConn.withAuth("u1", "Moderator"))
        .map(_ ==> List(123L))
    } yield ()
  }

  "2.2 Single role entity - other roles denied at entity level" - social {
    val baseConn = summon[Conn]
    for {
      _ <- ModeratorLog.action("Action").save.transact(using baseConn)

      // Unauthenticated denied
      _ <- ModeratorLog.action.query.get(using baseConn)
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: No authenticated role provided"
        }

      // StandardUser cannot access Moderator-only entity
      _ <- ModeratorLog.action.query.get(using baseConn.withAuth("u1", "StandardUser"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'StandardUser' cannot query entity 'ModeratorLog'"
        }

      // Guest cannot access
      _ <- ModeratorLog.action.query.get(using baseConn.withAuth("u2", "Guest"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query entity 'ModeratorLog'"
        }

      // Admin cannot access action attribute (has entity access via details)
      _ <- ModeratorLog.action.query.get(using baseConn.withAuth("u3", "Admin"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Admin' cannot query attribute 'ModeratorLog.action'"
        }
    } yield ()
  }

  "2.3 Single role entity - .allowMoreRoles[Admin] adds Admin to Moderator" - social {
    val baseConn = summon[Conn]
    for {
      _ <- ModeratorLog.action("Action").details("Detailed info").save.transact(using baseConn)

      // Admin can access via .allowMoreRoles[Admin]
      _ <- ModeratorLog.details.query.get(using baseConn.withAuth("u1", "Admin"))
        .map(_ ==> List("Detailed info"))

      // Moderator still has access (entity role preserved)
      _ <- ModeratorLog.details.query.get(using baseConn.withAuth("u2", "Moderator"))
        .map(_ ==> List("Detailed info"))

      // Other roles cannot access (entity-level restriction)
      _ <- ModeratorLog.details.query.get(using baseConn.withAuth("u3", "StandardUser"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'StandardUser' cannot query entity 'ModeratorLog'"
        }

      _ <- ModeratorLog.details.query.get(using baseConn.withAuth("u4", "Guest"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query entity 'ModeratorLog'"
        }
    } yield ()
  }

  "2.4 Single role entity - .allowOnlyRoles[Admin] replaces Moderator with Admin" - social {
    val baseConn = summon[Conn]
    for {
      _ <- ModeratorLog.action("Action").auditTrail("Audit trail").save.transact(using baseConn)

      // Admin has access (replacement role)
      _ <- ModeratorLog.auditTrail.query.get(using baseConn.withAuth("u1", "Admin"))
        .map(_ ==> List("Audit trail"))

      // Moderator does NOT have access (entity role replaced)
      _ <- ModeratorLog.auditTrail.query.get(using baseConn.withAuth("u2", "Moderator"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Moderator' cannot query attribute 'ModeratorLog.auditTrail'"
        }

      // Other roles denied
      _ <- ModeratorLog.auditTrail.query.get(using baseConn.withAuth("u3", "StandardUser"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'StandardUser' cannot query entity 'ModeratorLog'"
        }
    } yield ()
  }


  // ============================================================================
  // 3. MULTIPLE ROLES ENTITY TESTS (Post) - Core Tests
  // ============================================================================

  // 3.1 Entity-level access
  "3.1.1 Multiple roles entity - first role has access" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Post.title("Post Title").content("Content").save.transact(using baseConn)

      _ <- Post.title.query.get(using baseConn.withAuth("u1", "StandardUser"))
        .map(_ ==> List("Post Title"))
    } yield ()
  }

  "3.1.2 Multiple roles entity - second role has access" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Post.title("Post Title").content("Content").save.transact(using baseConn)

      _ <- Post.title.query.get(using baseConn.withAuth("u1", "Moderator"))
        .map(_ ==> List("Post Title"))
    } yield ()
  }

  "3.1.3 Multiple roles entity - non-entity role denied" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Post.title("Post Title").save.transact(using baseConn)

      // Unauthenticated denied
      _ <- Post.title.query.get(using baseConn)
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: No authenticated role provided"
        }

      // Guest not in entity roles (StandardUser, Moderator)
      _ <- Post.title.query.get(using baseConn.withAuth("u1", "Guest"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query attribute 'Post.title'"
        }

      // Admin not in entity roles
      _ <- Post.title.query.get(using baseConn.withAuth("u2", "Admin"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Admin' cannot query attribute 'Post.title'"
        }
    } yield ()
  }

  // 3.2 Attribute inheritance
  "3.2.1 Attributes inherit entity roles" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Post.title("T").content("C").authorId("A").save.transact(using baseConn)

      // All default attributes inherit StandardUser + Moderator
      _ <- Post.title.query.get(using baseConn.withAuth("u1", "StandardUser")).map(_ ==> List("T"))
      _ <- Post.content.query.get(using baseConn.withAuth("u1", "StandardUser")).map(_ ==> List("C"))
      _ <- Post.authorId.query.get(using baseConn.withAuth("u1", "StandardUser")).map(_ ==> List("A"))

      _ <- Post.title.query.get(using baseConn.withAuth("u2", "Moderator")).map(_ ==> List("T"))
      _ <- Post.content.query.get(using baseConn.withAuth("u2", "Moderator")).map(_ ==> List("C"))
      _ <- Post.authorId.query.get(using baseConn.withAuth("u2", "Moderator")).map(_ ==> List("A"))
    } yield ()
  }

  // 3.3 .allowRoles with NEW role (additive)
  "3.3.1 allowRoles[NewRole] - new role has access" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Post.title("Post").viewCount(100L).save.transact(using baseConn)

      // Guest added via .allowRoles[Guest]
      _ <- Post.viewCount.query.get(using baseConn.withAuth("u1", "Guest")).map(_ ==> List(100L))
    } yield ()
  }

  "3.3.2 allowRoles[NewRole] - entity roles still have access" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Post.title("Post").viewCount(100L).save.transact(using baseConn)

      // StandardUser still has access (entity role preserved)
      _ <- Post.viewCount.query.get(using baseConn.withAuth("u1", "StandardUser")).map(_ ==> List(100L))

      // Moderator still has access (entity role preserved)
      _ <- Post.viewCount.query.get(using baseConn.withAuth("u2", "Moderator")).map(_ ==> List(100L))
    } yield ()
  }

  "3.3.3 allowRoles[NewRole] - non-allowed role denied" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Post.title("Post").viewCount(100L).save.transact(using baseConn)

      // Admin not in (Guest + StandardUser + Moderator)
      _ <- Post.viewCount.query.get(using baseConn.withAuth("u1", "Admin"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Admin' cannot query attribute 'Post.viewCount'"
        }
    } yield ()
  }

  // 3.4 .allowRoles with EXISTING role (idempotent)
  "3.4.1 allowRoles[ExistingRole] - no change in permissions" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Post.title("Post").likeCount(50L).save.transact(using baseConn)

      // StandardUser has access (redundantly specified in .allowRoles)
      _ <- Post.likeCount.query.get(using baseConn.withAuth("u1", "StandardUser")).map(_ ==> List(50L))

      // Moderator still has access (entity role)
      _ <- Post.likeCount.query.get(using baseConn.withAuth("u2", "Moderator")).map(_ ==> List(50L))

      // Guest still denied (not added)
      _ <- Post.likeCount.query.get(using baseConn.withAuth("u3", "Guest"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query attribute 'Post.likeCount'"
        }
    } yield ()
  }

  // 3.5 .allowRoles with MULTIPLE roles
  "3.5.1 allowRoles[(Role1, Role2)] - all new roles added" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Post.title("Post").published(true).save.transact(using baseConn)

      // Guest added
      _ <- Post.published.query.get(using baseConn.withAuth("u1", "Guest")).map(_ ==> List(true))

      // Admin added
      _ <- Post.published.query.get(using baseConn.withAuth("u2", "Admin")).map(_ ==> List(true))
    } yield ()
  }

  "3.5.2 allowRoles[(Role1, Role2)] - entity roles preserved" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Post.title("Post").published(true).save.transact(using baseConn)

      // StandardUser preserved
      _ <- Post.published.query.get(using baseConn.withAuth("u1", "StandardUser")).map(_ ==> List(true))

      // Moderator preserved
      _ <- Post.published.query.get(using baseConn.withAuth("u2", "Moderator")).map(_ ==> List(true))
    } yield ()
  }

  // 3.6 .allowRoles with MIX of new and existing
  "3.6.1 allowRoles[(NewRole, ExistingRole)] - combined correctly" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Post.title("Post").featured(true).save.transact(using baseConn)

      // Guest added (new)
      _ <- Post.featured.query.get(using baseConn.withAuth("u1", "Guest")).map(_ ==> List(true))

      // Moderator preserved (existing, redundantly specified)
      _ <- Post.featured.query.get(using baseConn.withAuth("u2", "Moderator")).map(_ ==> List(true))

      // StandardUser preserved (existing entity role)
      _ <- Post.featured.query.get(using baseConn.withAuth("u3", "StandardUser")).map(_ ==> List(true))

      // Admin not included
      _ <- Post.featured.query.get(using baseConn.withAuth("u4", "Admin"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Admin' cannot query attribute 'Post.featured'"
        }
    } yield ()
  }

  // 3.7 .allowOnlyRoles narrowing from multiple to single
  "3.7.1 allowOnlyRoles[Moderator] - narrows to Moderator only" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Post.title("Post").moderatorNotes("Moderator notes").save.transact(using baseConn)

      // Moderator has access (from narrowed role)
      _ <- Post.moderatorNotes.query.get(using baseConn.withAuth("u1", "Moderator"))
        .map(_ ==> List("Moderator notes"))

      // StandardUser does NOT have access (entity role replaced)
      _ <- Post.moderatorNotes.query.get(using baseConn.withAuth("u2", "StandardUser"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'StandardUser' cannot query attribute 'Post.moderatorNotes'"
        }

      // Other roles not included
      _ <- Post.moderatorNotes.query.get(using baseConn.withAuth("u3", "Guest"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query attribute 'Post.moderatorNotes'"
        }

      _ <- Post.moderatorNotes.query.get(using baseConn.withAuth("u4", "Admin"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Admin' cannot query attribute 'Post.moderatorNotes'"
        }
    } yield ()
  }

  // 3.8 .allowOnlyRoles narrowing from multiple to different multiple
  "3.8.1 allowOnlyRoles[(Moderator, Admin)] - replaces with new set" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Post.title("Post").internalFlags("Internal flags").save.transact(using baseConn)

      // Moderator has access (in new role set)
      _ <- Post.internalFlags.query.get(using baseConn.withAuth("u1", "Moderator"))
        .map(_ ==> List("Internal flags"))

      // Admin has access (in new role set)
      _ <- Post.internalFlags.query.get(using baseConn.withAuth("u2", "Admin"))
        .map(_ ==> List("Internal flags"))

      // StandardUser does NOT have access (entity role replaced)
      _ <- Post.internalFlags.query.get(using baseConn.withAuth("u3", "StandardUser"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'StandardUser' cannot query attribute 'Post.internalFlags'"
        }

      // Guest not included
      _ <- Post.internalFlags.query.get(using baseConn.withAuth("u4", "Guest"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query attribute 'Post.internalFlags'"
        }
    } yield ()
  }


  // ============================================================================
  // 4. AUTHENTICATED ENTITY TESTS (UserProfile)
  // ============================================================================

  "4.1 Authenticated entity - any role has access" - social {
    val baseConn = summon[Conn]
    for {
      _ <- UserProfile.displayName("John").bio("Developer").save.transact(using baseConn)

      // All roles have access to Authenticated entity
      _ <- UserProfile.displayName.query.get(using baseConn.withAuth("u1", "Guest")).map(_ ==> List("John"))
      _ <- UserProfile.bio.query.get(using baseConn.withAuth("u2", "StandardUser")).map(_ ==> List("Developer"))
      _ <- UserProfile.displayName.query.get(using baseConn.withAuth("u3", "Moderator")).map(_ ==> List("John"))
      _ <- UserProfile.bio.query.get(using baseConn.withAuth("u4", "Admin")).map(_ ==> List("Developer"))
    } yield ()
  }

  "4.2 Authenticated entity - unauthenticated denied" - social {
    val baseConn = summon[Conn]
    for {
      _ <- UserProfile.displayName("John").save.transact(using baseConn)

      // No authentication fails
      _ <- UserProfile.displayName.query.get(using baseConn)
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: No authenticated role provided"
        }
    } yield ()
  }

  "4.3 Authenticated entity with .allowRoles[Admin]" - social {
    val baseConn = summon[Conn]
    for {
      _ <- UserProfile.displayName("John").email("john@example.com").save.transact(using baseConn)

      // Only Admin can access
      _ <- UserProfile.email.query.get(using baseConn.withAuth("u1", "Admin"))
        .map(_ ==> List("john@example.com"))

      // Other roles denied
      _ <- UserProfile.email.query.get(using baseConn.withAuth("u2", "StandardUser"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'StandardUser' cannot query attribute 'UserProfile.email'"
        }
    } yield ()
  }

  "4.4 Authenticated entity with .allowRoles[subset]" - social {
    val baseConn = summon[Conn]
    for {
      _ <- UserProfile.displayName("John").karma(100L).save.transact(using baseConn)

      // StandardUser has access (in subset)
      _ <- UserProfile.karma.query.get(using baseConn.withAuth("u1", "StandardUser")).map(_ ==> List(100L))

      // Moderator has access (in subset)
      _ <- UserProfile.karma.query.get(using baseConn.withAuth("u2", "Moderator")).map(_ ==> List(100L))

      // Admin has access (in subset)
      _ <- UserProfile.karma.query.get(using baseConn.withAuth("u3", "Admin")).map(_ ==> List(100L))

      // Guest excluded from subset
      _ <- UserProfile.karma.query.get(using baseConn.withAuth("u4", "Guest"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query attribute 'UserProfile.karma'"
        }
    } yield ()
  }


  // ============================================================================
  // 5. ADMIN-ONLY ENTITY TESTS (SystemConfig)
  // ============================================================================

  "5.1 Admin entity - Admin has access" - social {
    val baseConn = summon[Conn]
    for {
      _ <- SystemConfig.apiKey("key123").secretKey("secret456").save.transact(using baseConn)

      _ <- SystemConfig.apiKey.query.get(using baseConn.withAuth("u1", "Admin")).map(_ ==> List("key123"))
      _ <- SystemConfig.secretKey.query.get(using baseConn.withAuth("u1", "Admin")).map(_ ==> List("secret456"))
    } yield ()
  }

  "5.2 Admin entity - other roles denied" - social {
    val baseConn = summon[Conn]
    for {
      _ <- SystemConfig.apiKey("key123").save.transact(using baseConn)

      _ <- SystemConfig.apiKey.query.get(using baseConn.withAuth("u1", "StandardUser"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'StandardUser' cannot query entity 'SystemConfig'"
        }

      _ <- SystemConfig.apiKey.query.get(using baseConn.withAuth("u2", "Moderator"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Moderator' cannot query attribute 'SystemConfig.apiKey'"
        }

      _ <- SystemConfig.apiKey.query.get(using baseConn.withAuth("u3", "Guest"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query entity 'SystemConfig'"
        }
    } yield ()
  }

  "5.3 Admin entity - .allowRoles expands access" - social {
    val baseConn = summon[Conn]
    for {
      _ <- SystemConfig.apiKey("key").publicKey("pub123").save.transact(using baseConn)

      // Admin still has access
      _ <- SystemConfig.publicKey.query.get(using baseConn.withAuth("u1", "Admin")).map(_ ==> List("pub123"))

      // Moderator added via .allowRoles
      _ <- SystemConfig.publicKey.query.get(using baseConn.withAuth("u2", "Moderator")).map(_ ==> List("pub123"))

      // Others still denied
      _ <- SystemConfig.publicKey.query.get(using baseConn.withAuth("u3", "StandardUser"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'StandardUser' cannot query entity 'SystemConfig'"
        }
    } yield ()
  }


  // ============================================================================
  // 6. CROSS-ENTITY TESTS
  // ============================================================================

  "6.1 Different roles access different entities" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Article.title("Article").save.transact(using baseConn)
      _ <- Post.title("Post").save.transact(using baseConn)
      _ <- ModeratorLog.action("Action").save.transact(using baseConn)

      // Guest can access Article (public) but not Post or ModeratorLog
      _ <- Article.title.query.get(using baseConn.withAuth("u1", "Guest"))
        .map(_ ==> List("Article"))

      _ <- Post.title.query.get(using baseConn.withAuth("u1", "Guest"))
        .map(_ ==> "Unexpected success").recover { case ModelError(_) => () }

      _ <- ModeratorLog.action.query.get(using baseConn.withAuth("u1", "Guest"))
        .map(_ ==> "Unexpected success").recover { case ModelError(_) => () }

      // StandardUser can access Article and Post but not ModeratorLog
      _ <- Article.title.query.get(using baseConn.withAuth("u2", "StandardUser")).map(_ ==> List("Article"))
      _ <- Post.title.query.get(using baseConn.withAuth("u2", "StandardUser")).map(_ ==> List("Post"))

      _ <- ModeratorLog.action.query.get(using baseConn.withAuth("u2", "StandardUser"))
        .map(_ ==> "Unexpected success").recover { case ModelError(_) => () }

      // Moderator can access all three
      _ <- Article.title.query.get(using baseConn.withAuth("u3", "Moderator")).map(_ ==> List("Article"))
      _ <- Post.title.query.get(using baseConn.withAuth("u3", "Moderator")).map(_ ==> List("Post"))
      _ <- ModeratorLog.action.query.get(using baseConn.withAuth("u3", "Moderator")).map(_ ==> List("Action"))
    } yield ()
  }

  "6.2 Role switching updates permissions" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Post.title("Post").save.transact(using baseConn)

      // Start as StandardUser
      _ <- Post.title.query.get(using baseConn.withAuth("u1", "StandardUser")).map(_ ==> List("Post"))

      // Switch to Guest (no access to Post.title)
      _ <- Post.title.query.get(using baseConn.withAuth("u1", "Guest"))
        .map(_ ==> "Unexpected success").recover { case ModelError(_) => () }

      // Switch to Moderator (has access again)
      _ <- Post.title.query.get(using baseConn.withAuth("u1", "Moderator")).map(_ ==> List("Post"))
    } yield ()
  }


  // ============================================================================
  // 7. ERROR MESSAGE TESTS
  // ============================================================================

  "7.1 No auth context - clear error message" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Post.title("Post").save.transact(using baseConn)

      _ <- Post.title.query.get(using baseConn)
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: No authenticated role provided"
        }
    } yield ()
  }

  "7.2 Wrong role - entity-level error" - social {
    val baseConn = summon[Conn]
    for {
      _ <- ModeratorLog.action("Action").save.transact(using baseConn)

      _ <- ModeratorLog.action.query.get(using baseConn.withAuth("u1", "Guest"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query entity 'ModeratorLog'"
        }
    } yield ()
  }

  "7.3 Wrong role - attribute-level error" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Post.title("Post").viewCount(100L).save.transact(using baseConn)

      // Admin can access entity but not viewCount attribute
      _ <- Post.viewCount.query.get(using baseConn.withAuth("u1", "Admin"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Admin' cannot query attribute 'Post.viewCount'"
        }
    } yield ()
  }

  "7.4 Unknown role - clear error message" - social {
    val baseConn = summon[Conn]
    for {
      _ <- Post.title("Post").save.transact(using baseConn)

      _ <- Post.title.query.get(using baseConn.withAuth("u1", "UnknownRole"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Unknown role: UnknownRole"
        }
    } yield ()
  }


  // ============================================================================
  // 8. SCOPE DIMENSION TESTS
  // ============================================================================
  // Demonstrates that actions and scope are orthogonal dimensions.
  // Two roles with IDENTICAL actions (read + delete):
  // - Moderator        -> ModeratorLog scope
  // - ContentModerator -> ContentQueue scope

  "8.1 Same actions, different scopes - Moderator only sees their scope" - social {
    val baseConn = summon[Conn]
    for {
      _ <- ModeratorLog.action("Moderation action").save.transact(using baseConn)
      _ <- ContentQueue.reportedContent("Spam post").save.transact(using baseConn)

      // Moderator can access ModeratorLog (their scope)
      _ <- ModeratorLog.action.query.get(using baseConn.withAuth("u1", "Moderator"))
        .map(_ ==> List("Moderation action"))

      // Moderator CANNOT access ContentQueue (ContentModerator's scope)
      _ <- ContentQueue.reportedContent.query.get(using baseConn.withAuth("u1", "Moderator"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Moderator' cannot query entity 'ContentQueue'"
        }
    } yield ()
  }

  "8.2 Same actions, different scopes - ContentModerator only sees their scope" - social {
    val baseConn = summon[Conn]
    for {
      _ <- ModeratorLog.action("Moderation action").save.transact(using baseConn)
      _ <- ContentQueue.reportedContent("Spam post").save.transact(using baseConn)

      // ContentModerator can access ContentQueue (their scope)
      _ <- ContentQueue.reportedContent.query.get(using baseConn.withAuth("u1", "ContentModerator"))
        .map(_ ==> List("Spam post"))

      // ContentModerator CANNOT access ModeratorLog (Moderator's scope)
      _ <- ModeratorLog.action.query.get(using baseConn.withAuth("u1", "ContentModerator"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'ContentModerator' cannot query entity 'ModeratorLog'"
        }
    } yield ()
  }

  "8.3 Scope isolation - each role sees only their own workspace" - social {
    val baseConn = summon[Conn]
    for {
      _ <- ModeratorLog.action("Moderator's private note").save.transact(using baseConn)
      _ <- ContentQueue.reportedContent("ContentModerator's queue item").save.transact(using baseConn)

      // Each role sees exactly 1 item in their scope
      _ <- ModeratorLog.action.query.get(using baseConn.withAuth("u1", "Moderator"))
        .map(_.size ==> 1)

      _ <- ContentQueue.reportedContent.query.get(using baseConn.withAuth("u2", "ContentModerator"))
        .map(_.size ==> 1)

      // StandardUser (different actions: read+save) cannot see any of these scopes
      _ <- ModeratorLog.action.query.get(using baseConn.withAuth("u3", "StandardUser"))
        .map(_ ==> "Unexpected success").recover { case ModelError(_) => () }

      _ <- ContentQueue.reportedContent.query.get(using baseConn.withAuth("u3", "StandardUser"))
        .map(_ ==> "Unexpected success").recover { case ModelError(_) => () }
    } yield ()
  }

  "8.4 Admin can access multiple scopes via explicit grants" - social {
    val baseConn = summon[Conn]
    for {
      _ <- ModeratorLog.action("Mod action").details("Details for admin").save.transact(using baseConn)
      _ <- ContentQueue.reportedContent("Spam").reviewNotes("Admin notes").save.transact(using baseConn)

      // Admin can access attributes with explicit grants (.allowMoreRoles[Admin])
      _ <- ModeratorLog.details.query.get(using baseConn.withAuth("u1", "Admin"))
        .map(_ ==> List("Details for admin"))

      _ <- ContentQueue.reviewNotes.query.get(using baseConn.withAuth("u1", "Admin"))
        .map(_ ==> List("Admin notes"))

      // But Admin CANNOT access non-granted attributes (scope is explicit, not automatic)
      _ <- ModeratorLog.action.query.get(using baseConn.withAuth("u1", "Admin"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Admin' cannot query attribute 'ModeratorLog.action'"
        }

      _ <- ContentQueue.reportedContent.query.get(using baseConn.withAuth("u1", "Admin"))
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Admin' cannot query attribute 'ContentQueue.reportedContent'"
        }
    } yield ()
  }
}
