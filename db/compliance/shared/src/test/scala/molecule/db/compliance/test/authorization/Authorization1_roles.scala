package molecule.db.compliance.test.authorization

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.{Conn, Spi_async}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.SocialApp1_roles.*
import molecule.db.compliance.setup.DbProviders

case class Authorization1_roles(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  // ============================================================================
  // Layer 1: Roles - Which roles have access to entities
  // ============================================================================
  // Demonstrates:
  // - Role definitions with action permissions (query, save, insert, update, delete)
  // - Public entities (no roles)
  // - Single role entities
  // - Multiple role entities
  // ============================================================================


  // ============================================================================
  // Public Entities
  // ============================================================================

  "Public entity - unauthenticated can access" - social1 {
    val conn = summon[Conn]
    for {
      _ <- Article.title("Public").preview("Preview").save.transact(using conn)
      _ <- Article.title.query.get(using conn).map(_ ==> List("Public"))
      _ <- Article.preview.query.get(using conn).map(_ ==> List("Preview"))
    } yield ()
  }

  "Public entity - all roles can access" - social1 {
    val baseConn = summon[Conn]
    for {
      _ <- Article.title("Article").preview("Preview").save.transact(using baseConn)

      guestConn <- baseConn.withAuth("u1", "Guest")
      _ <- Article.title.query.get(using guestConn).map(_ ==> List("Article"))
      memberConn <- baseConn.withAuth("u2", "Member")
      _ <- Article.title.query.get(using memberConn).map(_ ==> List("Article"))
      moderatorConn <- baseConn.withAuth("u3", "Moderator")
      _ <- Article.title.query.get(using moderatorConn).map(_ ==> List("Article"))
      adminConn <- baseConn.withAuth("u4", "Admin")
      _ <- Article.title.query.get(using adminConn).map(_ ==> List("Article"))
    } yield ()
  }


  // ============================================================================
  // Single Role Entities
  // ============================================================================

  "Single role entity - Member can query" - social1 {
    val baseConn = summon[Conn]
    for {
      adminConn <- baseConn.withAuth("u1", "Admin")
      _ <- UserProfile.displayName("Alice").bio("Bio").save.transact(using adminConn)
      memberConn <- baseConn.withAuth("u2", "Member")
      _ <- UserProfile.displayName.query.get(using memberConn).map(_ ==> List("Alice"))
    } yield ()
  }

  "Single role entity - Admin can save" - social1 {
    val baseConn = summon[Conn]
    for {
      adminConn <- baseConn.withAuth("u1", "Admin")
      _ <- UserProfile.displayName("Bob").bio("Bio").save.transact(using adminConn)
      _ <- UserProfile.displayName.query.get(using adminConn).map(_ ==> List("Bob"))
    } yield ()
  }

  "Single role entity - other roles denied" - social1 {
    val baseConn = summon[Conn]
    for {
      adminConn <- baseConn.withAuth("u1", "Admin")
      _ <- UserProfile.displayName("Alice").bio("Bio").save.transact(using adminConn)

      // Unauthenticated denied
      _ <- UserProfile.displayName.query.get(using baseConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: No authenticated role provided"
        }

      // Guest denied
      guestConn <- baseConn.withAuth("u2", "Guest")
      _ <- UserProfile.displayName.query.get(using guestConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query entity 'UserProfile'"
        }

      // Moderator denied
      moderatorConn <- baseConn.withAuth("u3", "Moderator")
      _ <- UserProfile.displayName.query.get(using moderatorConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Moderator' cannot query entity 'UserProfile'"
        }
    } yield ()
  }


  // ============================================================================
  // Multiple Role Entities
  // ============================================================================

  "Multiple role entity - Member can access" - social1 {
    val baseConn = summon[Conn]
    for {
      adminConn <- baseConn.withAuth("u1", "Admin")
      _ <- Post.content("Post content").author("Alice").save.transact(using adminConn)
      memberConn <- baseConn.withAuth("u2", "Member")
      _ <- Post.content.query.get(using memberConn).map(_ ==> List("Post content"))
    } yield ()
  }

  "Multiple role entity - Moderator can access" - social1 {
    val baseConn = summon[Conn]
    for {
      adminConn <- baseConn.withAuth("u1", "Admin")
      _ <- Post.content("Post content").author("Alice").save.transact(using adminConn)
      moderatorConn <- baseConn.withAuth("u2", "Moderator")
      _ <- Post.content.query.get(using moderatorConn).map(_ ==> List("Post content"))
    } yield ()
  }

  "Multiple role entity - other roles denied" - social1 {
    val baseConn = summon[Conn]
    for {
      adminConn <- baseConn.withAuth("u1", "Admin")
      _ <- Post.content("Post content").author("Alice").save.transact(using adminConn)

      // Unauthenticated denied
      _ <- Post.content.query.get(using baseConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: No authenticated role provided"
        }

      // Guest denied
      guestConn <- baseConn.withAuth("u2", "Guest")
      _ <- Post.content.query.get(using guestConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query entity 'Post'"
        }
    } yield ()
  }


  // ============================================================================
  // Role Actions
  // ============================================================================

  "Role with query - can query only" - social1 {
    val baseConn = summon[Conn]
    for {
      guestConn <- baseConn.withAuth("u1", "Guest")
      // Setup by unauthenticated (Article is public)
      _ <- Article.title("Title").preview("Preview").save.transact(using baseConn)

      // Guest can query (has query action)
      _ <- Article.title.query.get(using guestConn).map(_ ==> List("Title"))

      // Guest cannot save (lacks save action)
      _ <- Article.title("New").preview("Preview").save.transact(using guestConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot save entity 'Article'"
        }
    } yield ()
  }

  "Role with query - can query" - social1 {
    val baseConn = summon[Conn]
    for {
      adminConn <- baseConn.withAuth("u1", "Admin")
      _ <- UserProfile.displayName("Alice").bio("Bio").save.transact(using adminConn)

      memberConn <- baseConn.withAuth("u2", "Member")
      // Member can query (has query action)
      _ <- UserProfile.displayName.query.get(using memberConn).map(_ ==> List("Alice"))
    } yield ()
  }

  "Role with all 5 actions - can do everything" - social1 {
    val baseConn = summon[Conn]
    for {
      adminConn <- baseConn.withAuth("u1", "Admin")
      // Admin can save
      id <- UserProfile.displayName("Admin User").bio("Bio").save.transact(using adminConn).map(_.id)

      // Admin can query
      _ <- UserProfile.displayName.query.get(using adminConn).map(_ ==> List("Admin User"))

      // Admin can update
      _ <- UserProfile(id).displayName("Updated Admin").update.transact(using adminConn)
      _ <- UserProfile.displayName.query.get(using adminConn).map(_ ==> List("Updated Admin"))

      // Admin can delete
      _ <- UserProfile(id).delete.transact(using adminConn)
      _ <- UserProfile.displayName.query.get(using adminConn).map(_ ==> List())
    } yield ()
  }
}
