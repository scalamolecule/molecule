package molecule.db.compliance.test.authorization

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async

import molecule.db.common.spi.{Conn, Spi_async}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.SocialApp_overview.*
import molecule.db.compliance.setup.DbProviders

case class Authorization_overview(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  // ============================================================================
  // AUTHORIZATION OVERVIEW - The 4 Layers + Composition
  // ============================================================================
  // This test suite provides minimal examples demonstrating each authorization
  // layer in isolation, followed by a composed example showing how they work
  // together.
  //
  // These examples align with AUTHORIZATION_MODEL.md and SocialApp_overview.scala
  // ============================================================================


  // ============================================================================
  // Layer 1: Roles - Entity-level role access
  // ============================================================================

  "Layer 1 - Public entity (anyone can access)" - social0 {
    val conn = summon[Conn]
    for {
      _ <- Article.title("Public Article").save.transact(using conn)
      _ <- Article.title.query.get(using conn).map(_ ==> List("Public Article"))
    } yield ()
  }

  "Layer 1 - Single role entity (only Member)" - social0 {
    val baseConn   = summon[Conn]
    for {
      adminConn  <- baseConn.withAuth("u1", "Admin")
      memberConn <- baseConn.withAuth("u2", "Member")
      _ <- Post.content("Member content").save.transact(using adminConn)
      _ <- Post.content.query.get(using memberConn).map(_ ==> List("Member content"))

      // Guest cannot access
      guestConn <- baseConn.withAuth("u3", "Guest")
      _ <- Post.content.query.get(using guestConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query entity 'Post'"
        }
    } yield ()
  }


  // ============================================================================
  // Layer 2: Role Actions - Action grants at entity level
  // ============================================================================

  "Layer 2 - Entity-level update grant" - social0 {
    val baseConn   = summon[Conn]
    for {
      adminConn  <- baseConn.withAuth("u1", "Admin")
      memberConn <- baseConn.withAuth("u2", "Member")
      id <- Comment.text("Original").save.transact(using adminConn).map(_.id)

      // Member can update (action grant)
      _ <- Comment(id).text("Updated").update.transact(using memberConn)
      _ <- Comment.text.query.get(using memberConn).map(_ ==> List("Updated"))
    } yield ()
  }

  "Layer 2 - Entity-level delete grant" - social0 {
    val baseConn   = summon[Conn]
    for {
      memberConn <- baseConn.withAuth("u1", "Member")
      adminConn  <- baseConn.withAuth("u2", "Admin")
      id <- Log.entry("Log entry").save.transact(using adminConn).map(_.id)

      // Member cannot delete (no grant)
      _ <- Log(id).delete.transact(using memberConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Member' cannot delete entity 'Log'"
        }

      // Admin can delete (action grant)
      _ <- Log(id).delete.transact(using adminConn)
    } yield ()
  }


  // ============================================================================
  // Layer 3: Attribute Roles - Attribute role restrictions
  // ============================================================================

  "Layer 3 - Using .only[R]" - social0 {
    val baseConn   = summon[Conn]
    for {
      memberConn <- baseConn.withAuth("u1", "Member")
      adminConn  <- baseConn.withAuth("u2", "Admin")
      _ <- Settings.theme("dark").apiKey("secret").save.transact(using adminConn)

      // Member can access theme
      _ <- Settings.theme.query.get(using memberConn).map(_ ==> List("dark"))

      // Member cannot access apiKey (only Admin)
      _ <- Settings.apiKey.query.get(using memberConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Member' cannot query attribute 'Settings.apiKey'"
        }

      // Admin can access both
      _ <- Settings.apiKey.query.get(using adminConn).map(_ ==> List("secret"))
    } yield ()
  }

  "Layer 3 - Using .exclude[R]" - social0 {
    val baseConn   = summon[Conn]
    for {
      guestConn  <- baseConn.withAuth("u1", "Guest")
      memberConn <- baseConn.withAuth("u2", "Member")
      adminConn  <- baseConn.withAuth("u3", "Admin")
      _ <- Profile.username("alice").email("alice@example.com").save.transact(using adminConn)

      // Guest can access username
      _ <- Profile.username.query.get(using guestConn).map(_ ==> List("alice"))

      // Guest cannot access email (excluded)
      _ <- Profile.email.query.get(using guestConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query attribute 'Profile.email'"
        }

      // Member can access both
      _ <- Profile.email.query.get(using memberConn).map(_ ==> List("alice@example.com"))
    } yield ()
  }


  // ============================================================================
  // Layer 4: Attribute Update - Attribute update grants
  // ============================================================================

  "Layer 4 - Attribute-level update grant" - social0 {
    val baseConn   = summon[Conn]
    for {
      adminConn  <- baseConn.withAuth("u1", "Admin")
      memberConn <- baseConn.withAuth("u2", "Member")
      id <- Draft.content("Draft content").title("Draft title").save.transact(using adminConn).map(_.id)

      // Member can update title (attribute update grant)
      _ <- Draft(id).title("Updated title").update.transact(using memberConn)
      _ <- Draft.title.query.get(using memberConn).map(_ ==> List("Updated title"))

      // Member cannot update content (no grant)
      _ <- Draft(id).content("Updated content").update.transact(using memberConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Member' cannot update attribute 'Draft.content'"
        }
    } yield ()
  }


  // ============================================================================
  // Composed Example - All 4 layers working together
  // ============================================================================

  "Composed - All layers together" - social0 {
    val baseConn   = summon[Conn]
    for {
      guestConn  <- baseConn.withAuth("u1", "Guest")
      memberConn <- baseConn.withAuth("u2", "Member")
      adminConn  <- baseConn.withAuth("u3", "Admin")
      // Setup: Admin creates a blog post
      id <- BlogPost.title("Blog Title").content("Blog Content")
        .viewCount(0L).internal("Internal notes")
        .save.transact(using adminConn).map(_.id)

      // Guest can query title
      _ <- BlogPost.title.query.get(using guestConn).map(_ ==> List("Blog Title"))

      // Guest cannot query content (excluded)
      _ <- BlogPost.content.query.get(using guestConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query attribute 'BlogPost.content'"
        }

      // Guest can update viewCount (attribute update grant)
      _ <- BlogPost(id).viewCount(1L).update.transact(using guestConn)
      _ <- BlogPost.viewCount.query.get(using adminConn).map(_ ==> List(1L))

      // Guest cannot query internal (only Admin)
      _ <- BlogPost.internal.query.get(using guestConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot query attribute 'BlogPost.internal'"
        }

      // Member can query and update content (action grant)
      _ <- BlogPost.content.query.get(using memberConn).map(_ ==> List("Blog Content"))
      _ <- BlogPost(id).content("Updated Content").update.transact(using memberConn)
      _ <- BlogPost.content.query.get(using memberConn).map(_ ==> List("Updated Content"))

      // Member cannot query internal (only Admin)
      _ <- BlogPost.internal.query.get(using memberConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Member' cannot query attribute 'BlogPost.internal'"
        }

      // Member cannot delete (only Admin has action grant for delete)
      _ <- BlogPost(id).delete.transact(using memberConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Member' cannot delete entity 'BlogPost'"
        }

      // Admin can access everything and delete
      _ <- BlogPost.internal.query.get(using adminConn).map(_ ==> List("Internal notes"))
      _ <- BlogPost(id).delete.transact(using adminConn)
    } yield ()
  }
}
