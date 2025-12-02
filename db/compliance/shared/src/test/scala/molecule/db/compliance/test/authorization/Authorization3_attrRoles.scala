package molecule.db.compliance.test.authorization

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.{Conn, Spi_async}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.SocialApp3_attr_roles.*
import molecule.db.compliance.setup.DbProviders

case class Authorization3_attrRoles(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  // ============================================================================
  // Layer 3: Attribute Roles - Which roles can access specific attributes
  // ============================================================================
  // Demonstrates:
  // - `.only[R]` - restrict attribute to ONLY specific role(s)
  // - `.exclude[R]` - exclude specific role(s) from attribute access
  // - Restrictions override entity-level permissions
  // ============================================================================


  // ============================================================================
  // Using .only[R]
  // ============================================================================

  "Attribute .only[R] - single role" - social3 {
    val baseConn   = summon[Conn]
    val memberConn = baseConn.withAuth("u1", "Member")
    val adminConn  = baseConn.withAuth("u2", "Admin")
    for {
      _ <- Post.title("Title").content("Content").secret("Secret").save.transact(using adminConn)

      // Member can access title (all entity roles)
      _ <- Post.title.query.get(using memberConn).map(_ ==> List("Title"))

      // Member can access content (.only[(Member, Admin)])
      _ <- Post.content.query.get(using memberConn).map(_ ==> List("Content"))

      // Member cannot access secret (.only[Admin])
      _ <- Post.secret.query.get(using memberConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Member' cannot query attribute 'Post.secret'"
        }

      // Admin can access everything
      _ <- Post.secret.query.get(using adminConn).map(_ ==> List("Secret"))
    } yield ()
  }

  "Attribute .only[R] - tuple of roles" - social3 {
    val baseConn   = summon[Conn]
    val guestConn  = baseConn.withAuth("u1", "Guest")
    val memberConn = baseConn.withAuth("u2", "Member")
    val adminConn  = baseConn.withAuth("u3", "Admin")
    for {
      _ <- Post.title("Title").content("Content").secret("Secret").save.transact(using adminConn)

      // content is .only[(Member, Admin)]
      _ <- Post.content.query.get(using memberConn).map(_ ==> List("Content"))
      _ <- Post.content.query.get(using adminConn).map(_ ==> List("Content"))

      // Guest cannot access content
      _ <- Post.content.query.get(using guestConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query attribute 'Post.content'"
        }
    } yield ()
  }


  // ============================================================================
  // Using .exclude[R]
  // ============================================================================

  "Attribute .exclude[R] - single role" - social3 {
    val baseConn      = summon[Conn]
    val guestConn     = baseConn.withAuth("u1", "Guest")
    val memberConn    = baseConn.withAuth("u2", "Member")
    val moderatorConn = baseConn.withAuth("u3", "Moderator")
    val adminConn     = baseConn.withAuth("u4", "Admin")
    for {
      _ <- Article.title("Title").fullText("Full text").editHistory("History")
        .save.transact(using adminConn)

      // fullText is .exclude[Guest]
      _ <- Article.fullText.query.get(using memberConn).map(_ ==> List("Full text"))
      _ <- Article.fullText.query.get(using moderatorConn).map(_ ==> List("Full text"))
      _ <- Article.fullText.query.get(using adminConn).map(_ ==> List("Full text"))

      // Guest cannot access fullText
      _ <- Article.fullText.query.get(using guestConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query attribute 'Article.fullText'"
        }
    } yield ()
  }

  "Attribute .exclude[R] - tuple of roles" - social3 {
    val baseConn      = summon[Conn]
    val guestConn     = baseConn.withAuth("u1", "Guest")
    val memberConn    = baseConn.withAuth("u2", "Member")
    val moderatorConn = baseConn.withAuth("u3", "Moderator")
    val adminConn     = baseConn.withAuth("u4", "Admin")
    for {
      _ <- Article.title("Title").fullText("Full text").editHistory("History")
        .save.transact(using adminConn)

      // editHistory is .exclude[(Guest, Member, Moderator)]
      _ <- Article.editHistory.query.get(using adminConn).map(_ ==> List("History"))

      // Guest, Member, Moderator cannot access editHistory
      _ <- Article.editHistory.query.get(using guestConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query attribute 'Article.editHistory'"
        }

      _ <- Article.editHistory.query.get(using memberConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Member' cannot query attribute 'Article.editHistory'"
        }

      _ <- Article.editHistory.query.get(using moderatorConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Moderator' cannot query attribute 'Article.editHistory'"
        }
    } yield ()
  }


  // ============================================================================
  // Combining Restrictions
  // ============================================================================

  "Multiple attributes with different restrictions" - social3 {
    val baseConn      = summon[Conn]
    val guestConn     = baseConn.withAuth("u1", "Guest")
    val memberConn    = baseConn.withAuth("u2", "Member")
    val moderatorConn = baseConn.withAuth("u3", "Moderator")
    val adminConn     = baseConn.withAuth("u4", "Admin")
    for {
      _ <- ModerationLog.action("Action").details("Details").audit("Audit")
        .sensitiveData("Sensitive").save.transact(using adminConn)

      // action - all entity roles
      _ <- ModerationLog.action.query.get(using guestConn).map(_ ==> List("Action"))

      // details - .only[(Moderator, Admin)]
      _ <- ModerationLog.details.query.get(using moderatorConn).map(_ ==> List("Details"))
      _ <- ModerationLog.details.query.get(using adminConn).map(_ ==> List("Details"))

      _ <- ModerationLog.details.query.get(using guestConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query attribute 'ModerationLog.details'"
        }

      // audit - .exclude[(Guest, Member)]
      _ <- ModerationLog.audit.query.get(using moderatorConn).map(_ ==> List("Audit"))
      _ <- ModerationLog.audit.query.get(using adminConn).map(_ ==> List("Audit"))

      _ <- ModerationLog.audit.query.get(using memberConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Member' cannot query attribute 'ModerationLog.audit'"
        }

      // sensitiveData - .only[Admin]
      _ <- ModerationLog.sensitiveData.query.get(using adminConn).map(_ ==> List("Sensitive"))

      _ <- ModerationLog.sensitiveData.query.get(using moderatorConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Moderator' cannot query attribute 'ModerationLog.sensitiveData'"
        }
    } yield ()
  }


  // ============================================================================
  // Progressive Disclosure
  // ============================================================================

  "Progressive disclosure example" - social3 {
    val baseConn      = summon[Conn]
    val memberConn    = baseConn.withAuth("u1", "Member")
    val moderatorConn = baseConn.withAuth("u2", "Moderator")
    val adminConn     = baseConn.withAuth("u3", "Admin")
    for {
      _ <- UserProfile.displayName("Alice").email("alice@example.com")
        .ipAddress("192.168.1.1").save.transact(using adminConn)

      // displayName - all roles (Member, Moderator, Admin)
      _ <- UserProfile.displayName.query.get(using memberConn).map(_ ==> List("Alice"))

      // email - .only[(Moderator, Admin)]
      _ <- UserProfile.email.query.get(using moderatorConn).map(_ ==> List("alice@example.com"))
      _ <- UserProfile.email.query.get(using adminConn).map(_ ==> List("alice@example.com"))

      _ <- UserProfile.email.query.get(using memberConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Member' cannot query attribute 'UserProfile.email'"
        }

      // ipAddress - .only[Admin]
      _ <- UserProfile.ipAddress.query.get(using adminConn).map(_ ==> List("192.168.1.1"))

      _ <- UserProfile.ipAddress.query.get(using moderatorConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Moderator' cannot query attribute 'UserProfile.ipAddress'"
        }
    } yield ()
  }


  // ============================================================================
  // Restrictions Affect All Operations
  // ============================================================================

  "Restrictions affect save" - social3 {
    val baseConn   = summon[Conn]
    val memberConn = baseConn.withAuth("u1", "Member")
    val adminConn  = baseConn.withAuth("u2", "Admin")
    for {
      // Member cannot save with secret attribute (.only[Admin])
      _ <- Post.title("Title").content("Content").secret("Secret").save.transact(using memberConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Member' cannot save entity 'Post'"
        }

      // Admin can save without secret
      _ <- Post.title("Title").content("Content").save.transact(using adminConn)

      // Admin can save with secret
      _ <- Post.title("Title 2").content("Content 2").secret("Secret 2").save.transact(using adminConn)
    } yield ()
  }

  "Restrictions affect update" - social3 {
    val baseConn   = summon[Conn]
    val memberConn = baseConn.withAuth("u1", "Member")
    val adminConn  = baseConn.withAuth("u2", "Admin")
    for {
      id <- Post.title("Title").content("Content").secret("Secret").save.transact(using adminConn).map(_.id)

      // Member cannot update secret (.only[Admin])
      _ <- Post(id).secret("Updated Secret").update.transact(using memberConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Member' cannot update entity 'Post'"
        }

      // Admin can update secret
      _ <- Post(id).secret("Updated Secret").update.transact(using adminConn)
      _ <- Post.secret.query.get(using adminConn).map(_ ==> List("Updated Secret"))
    } yield ()
  }
}
