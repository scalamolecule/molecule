package molecule.db.compliance.test.authorization

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.{Conn, Spi_async}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.SocialApp2_role_actions.*
import molecule.db.compliance.setup.DbProviders

case class Authorization2_roleActions(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  // ============================================================================
  // Layer 2: Role Actions - What actions roles can perform at entity level
  // ============================================================================
  // Demonstrates:
  // - `with updating[Role]` - grants update permission at entity level
  // - `with deleting[Role]` - grants delete permission at entity level
  // - Combining multiple grants
  // - Grants to tuple of roles
  // ============================================================================


  // ============================================================================
  // Entity-level Update Grants
  // ============================================================================

  "Entity update grant - Member can update" - social2 {
    val baseConn   = summon[Conn]
    val adminConn  = baseConn.withAuth("u1", "Admin")
    val memberConn = baseConn.withAuth("u2", "Member")
    for {
      // Member has read (query + subscribe) from role definition
      id <- Post.content("Original").title("Title").save.transact(using adminConn).map(_.id)

      // Member can update (entity grant)
      _ <- Post(id).content("Updated").update.transact(using memberConn)
      _ <- Post.content.query.get(using memberConn).map(_ ==> List("Updated"))
    } yield ()
  }

  "Entity update grant - applies to all attributes" - social2 {
    val baseConn   = summon[Conn]
    val adminConn  = baseConn.withAuth("u1", "Admin")
    val memberConn = baseConn.withAuth("u2", "Member")
    for {
      id <- Post.content("Content").title("Title").save.transact(using adminConn).map(_.id)

      // Entity grant applies to all attributes
      _ <- Post(id).content("Updated Content").title("Updated Title").update.transact(using memberConn)
      _ <- Post.content.title.query.get(using memberConn).map(_ ==> List(("Updated Content", "Updated Title")))
    } yield ()
  }

  "Entity update grant - other roles lack access" - social2 {
    val baseConn  = summon[Conn]
    val adminConn = baseConn.withAuth("u1", "Admin")
    val guestConn = baseConn.withAuth("u2", "Guest")
    for {
      id <- Post.content("Content").title("Title").save.transact(using adminConn).map(_.id)

      // Guest cannot access entity
      _ <- Post(id).content("Updated").update.transact(using guestConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot update entity 'Post'"
        }
    } yield ()
  }


  // ============================================================================
  // Entity-level Delete Grants
  // ============================================================================

  "Entity delete grant - Moderator can delete" - social2 {
    val baseConn      = summon[Conn]
    val adminConn     = baseConn.withAuth("u1", "Admin")
    val moderatorConn = baseConn.withAuth("u2", "Moderator")
    for {
      id <- Comment.text("Comment").save.transact(using adminConn).map(_.id)

      // Moderator can delete (entity grant)
      _ <- Comment(id).delete.transact(using moderatorConn)
      _ <- Comment.text.query.get(using moderatorConn).map(_ ==> List())
    } yield ()
  }

  "Entity delete grant - other roles cannot delete" - social2 {
    val baseConn      = summon[Conn]
    val adminConn     = baseConn.withAuth("u1", "Admin")
    val moderatorConn = baseConn.withAuth("u2", "Moderator")
    val guestConn     = baseConn.withAuth("u3", "Guest")
    for {
      id <- Comment.text("Comment").save.transact(using adminConn).map(_.id)

      // Guest cannot access entity
      _ <- Comment(id).delete.transact(using guestConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot delete entity 'Comment'"
        }
    } yield ()
  }


  // ============================================================================
  // Multiple Roles with Entity Grants
  // ============================================================================

  "Entity grant - tuple of roles (Member, Moderator)" - social2 {
    val baseConn      = summon[Conn]
    val adminConn     = baseConn.withAuth("u1", "Admin")
    val memberConn    = baseConn.withAuth("u2", "Member")
    val moderatorConn = baseConn.withAuth("u3", "Moderator")
    for {
      id1 <- Article.title("Article 1").content("Content 1").save.transact(using adminConn).map(_.id)
      id2 <- Article.title("Article 2").content("Content 2").save.transact(using adminConn).map(_.id)

      // Both Member and Moderator can update (entity grant with tuple)
      _ <- Article(id1).title("Updated by Member").update.transact(using memberConn)
      _ <- Article(id2).title("Updated by Moderator").update.transact(using moderatorConn)

      _ <- Article.title.query.get(using memberConn).map(_.toSet ==> Set("Updated by Member", "Updated by Moderator"))
    } yield ()
  }


  // ============================================================================
  // Combining Entity Grants
  // ============================================================================

  "Combining updating and deleting grants" - social2 {
    val baseConn      = summon[Conn]
    val moderatorConn = baseConn.withAuth("u1", "Moderator")
    val adminConn     = baseConn.withAuth("u2", "Admin")
    for {
      id <- ModLog.action("Action").timestamp(123L).save.transact(using adminConn).map(_.id)

      // Moderator can update (entity grant)
      _ <- ModLog(id).action("Updated Action").update.transact(using moderatorConn)

      // Moderator can delete (entity grant)
      _ <- ModLog(id).delete.transact(using moderatorConn)
      _ <- ModLog.action.query.get(using moderatorConn).map(_ ==> List())

      // Admin can also delete (entity grant with tuple)
      id2 <- ModLog.action("Another Action").timestamp(456L).save.transact(using adminConn).map(_.id)
      _ <- ModLog(id2).delete.transact(using adminConn)
    } yield ()
  }

  "Different roles with different grants" - social2 {
    val baseConn   = summon[Conn]
    val memberConn = baseConn.withAuth("u1", "Member")
    val adminConn  = baseConn.withAuth("u2", "Admin")
    for {
      id <- UserProfile.displayName("User").bio("Bio").save.transact(using adminConn).map(_.id)

      // Member can update (entity grant)
      _ <- UserProfile(id).displayName("Updated User").update.transact(using memberConn)

      // Member cannot delete (only Admin has delete grant)
      _ <- UserProfile(id).delete.transact(using memberConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Member' cannot delete entity 'UserProfile'"
        }

      // Admin can delete (entity grant)
      _ <- UserProfile(id).delete.transact(using adminConn)
    } yield ()
  }


  // ============================================================================
  // Role Actions vs Entity Grants
  // ============================================================================

  "Role with all actions - no grant needed" - social2 {
    val adminConn = summon[Conn].withAuth("u1", "Admin")
    for {
      // Admin has `all` action - includes update
      id <- UserProfile.displayName("Admin User").bio("Bio").save.transact(using adminConn).map(_.id)

      // Admin can update (has update action, doesn't need entity grant)
      _ <- UserProfile(id).displayName("Updated").update.transact(using adminConn)

      // Admin can delete (entity grant is redundant for Admin who has all actions)
      _ <- UserProfile(id).delete.transact(using adminConn)
    } yield ()
  }

  "Role without action - needs grant" - social2 {
    val baseConn   = summon[Conn]
    val adminConn  = baseConn.withAuth("u1", "Admin")
    val memberConn = baseConn.withAuth("u2", "Member")
    for {
      // Member has read (query + subscribe), not update
      id <- Post.content("Content").title("Title").save.transact(using adminConn).map(_.id)

      // Without entity grant, Member cannot update
      // But Post has `with updating[Member]`, so Member can update
      _ <- Post(id).content("Updated").update.transact(using memberConn)
      _ <- Post.content.query.get(using memberConn).map(_ ==> List("Updated"))
    } yield ()
  }
}
