package molecule.db.compliance.test.authorization

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.{Conn, Spi_async}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.SocialApp4_attr_update.*
import molecule.db.compliance.setup.DbProviders

case class Authorization4_attrUpdate(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  // ============================================================================
  // Layer 4: Attribute Update - Which roles can update specific attributes
  // ============================================================================
  // Demonstrates:
  // - `.updating[R]` - grant update permission at attribute level
  // - Fine-grained control per attribute
  // - Grants to tuple of roles
  // - Special cases like Guest updating specific fields
  // ============================================================================


  // ============================================================================
  // Single Role Attribute Update
  // ============================================================================

  "Attribute update grant - Member can update specific attribute" - social4 {
    val baseConn   = summon[Conn]
    val adminConn  = baseConn.withAuth("u1", "Admin")
    val memberConn = baseConn.withAuth("u2", "Member")
    for {
      id <- Post.content("Content").title("Title").save.transact(using adminConn).map(_.id)

      // Member can update title (.updating[Member])
      _ <- Post(id).title("Updated Title").update.transact(using memberConn)
      _ <- Post.title.query.get(using memberConn).map(_ ==> List("Updated Title"))

      // Member cannot update content (no update grant)
      _ <- Post(id).content("Updated Content").update.transact(using memberConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Member' cannot update attribute 'Post.content'"
        }
    } yield ()
  }

  "Attribute update grant - role without update action" - social4 {
    val baseConn   = summon[Conn]
    val adminConn  = baseConn.withAuth("u1", "Admin")
    val memberConn = baseConn.withAuth("u2", "Member")
    for {
      // Member has read (query + subscribe), not update action
      id <- Post.content("Content").title("Title").save.transact(using adminConn).map(_.id)

      // But Member gets update on title via attribute grant
      _ <- Post(id).title("Updated").update.transact(using memberConn)
      _ <- Post.title.query.get(using memberConn).map(_ ==> List("Updated"))
    } yield ()
  }


  // ============================================================================
  // Multiple Roles with Attribute Update
  // ============================================================================

  "Attribute update grant - tuple of roles" - social4 {
    val baseConn      = summon[Conn]
    val adminConn     = baseConn.withAuth("u1", "Admin")
    val memberConn    = baseConn.withAuth("u2", "Member")
    val moderatorConn = baseConn.withAuth("u3", "Moderator")
    for {
      id <- Article.preview("Preview").tags("tag1").save.transact(using adminConn).map(_.id)

      // Both Member and Moderator can update tags (.updating[(Member, Moderator)])
      _ <- Article(id).tags("tag2").update.transact(using memberConn)
      _ <- Article.tags.query.get(using memberConn).map(_ ==> List("tag2"))

      _ <- Article(id).tags("tag3").update.transact(using moderatorConn)
      _ <- Article.tags.query.get(using moderatorConn).map(_ ==> List("tag3"))
    } yield ()
  }


  // ============================================================================
  // Different Attributes with Different Grants
  // ============================================================================

  "Multiple attributes with different update grants" - social4 {
    val baseConn      = summon[Conn]
    val adminConn     = baseConn.withAuth("u1", "Admin")
    val memberConn    = baseConn.withAuth("u2", "Member")
    val moderatorConn = baseConn.withAuth("u3", "Moderator")
    for {
      id <- UserProfile.username("alice").email("alice@example.com")
        .displayName("Alice").bio("Bio").verified(false)
        .save.transact(using adminConn).map(_.id)

      // Member can update displayName (.updating[Member])
      _ <- UserProfile(id).displayName("Alice Updated").update.transact(using memberConn)

      // Member can update bio (.updating[Member])
      _ <- UserProfile(id).bio("Updated Bio").update.transact(using memberConn)

      // Member cannot update email (no grant)
      _ <- UserProfile(id).email("new@example.com").update.transact(using memberConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Member' cannot update attribute 'UserProfile.email'"
        }

      // Member cannot update verified (.updating[Moderator])
      _ <- UserProfile(id).verified(true).update.transact(using memberConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Member' cannot update attribute 'UserProfile.verified'"
        }

      // Moderator can update verified (.updating[Moderator])
      _ <- UserProfile(id).verified(true).update.transact(using moderatorConn)
      _ <- UserProfile.verified.query.get(using moderatorConn).map(_ ==> List(true))
    } yield ()
  }


  // ============================================================================
  // Guest with Special Update Permission
  // ============================================================================

  "Guest can update specific attribute" - social4 {
    val baseConn   = summon[Conn]
    val adminConn  = baseConn.withAuth("u1", "Admin")
    val guestConn  = baseConn.withAuth("u2", "Guest")
    val memberConn = baseConn.withAuth("u3", "Member")
    for {
      id <- Reaction.emoji("👍").count(0).save.transact(using adminConn).map(_.id)

      // Guest can query
      _ <- Reaction.count.query.get(using guestConn).map(_ ==> List(0))

      // Guest can update count (.updating[Guest]) - special case for incrementing
      _ <- Reaction(id).count(1).update.transact(using guestConn)
      _ <- Reaction.count.query.get(using guestConn).map(_ ==> List(1))

      // Guest cannot update emoji (no grant)
      _ <- Reaction(id).emoji("👎").update.transact(using guestConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Guest' cannot update attribute 'Reaction.emoji'"
        }
    } yield ()
  }


  // ============================================================================
  // Combining Multiple Update Grants
  // ============================================================================

  "Multiple roles with different attribute grants" - social4 {
    val baseConn      = summon[Conn]
    val adminConn     = baseConn.withAuth("u1", "Admin")
    val memberConn    = baseConn.withAuth("u2", "Member")
    val moderatorConn = baseConn.withAuth("u3", "Moderator")
    for {
      id <- Comment.text("Text").content("Content").status("draft").tags("tag1")
        .save.transact(using adminConn).map(_.id)

      // Member can update content (.updating[Member])
      _ <- Comment(id).content("Updated Content").update.transact(using memberConn)

      // Moderator can update status (.updating[Moderator])
      _ <- Comment(id).status("published").update.transact(using moderatorConn)

      // Both can update tags (.updating[(Member, Moderator)])
      _ <- Comment(id).tags("tag2").update.transact(using memberConn)
      _ <- Comment(id).tags("tag3").update.transact(using moderatorConn)

      _ <- Comment.content.status.tags.query.get(using moderatorConn)
        .map(_ ==> List(("Updated Content", "published", "tag3")))
    } yield ()
  }


  // ============================================================================
  // Attribute Update vs Entity Update
  // ============================================================================

  "Attribute grant is independent of entity grant" - social4 {
    val baseConn   = summon[Conn]
    val adminConn  = baseConn.withAuth("u1", "Admin")
    val memberConn = baseConn.withAuth("u2", "Member")
    for {
      // Post entity has no entity-level update grant
      id <- Post.content("Content").title("Title").save.transact(using adminConn).map(_.id)

      // Member still gets update on title via attribute grant
      _ <- Post(id).title("Updated").update.transact(using memberConn)

      // But not on content (no grant at all)
      _ <- Post(id).content("Updated Content").update.transact(using memberConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Member' cannot update attribute 'Post.content'"
        }
    } yield ()
  }


  // ============================================================================
  // Combining with Restrictions
  // ============================================================================

  "Attribute update with attribute restriction" - social4 {
    val baseConn   = summon[Conn]
    val memberConn = baseConn.withAuth("u1", "Member")
    val adminConn  = baseConn.withAuth("u2", "Admin")
    for {
      id <- UserProfile.username("alice").email("alice@example.com")
        .displayName("Alice").bio("Bio").verified(false)
        .save.transact(using adminConn).map(_.id)

      // Member can update attributes they have grants for
      _ <- UserProfile(id).displayName("Updated").update.transact(using memberConn)

      // Admin can update verified (has update grant)
      _ <- UserProfile(id).verified(true).update.transact(using adminConn)
      _ <- UserProfile.verified.query.get(using adminConn).map(_ ==> List(true))
    } yield ()
  }


  // ============================================================================
  // Query vs Update Permissions
  // ============================================================================

  "Can query but not update without grant" - social4 {
    val baseConn   = summon[Conn]
    val adminConn  = baseConn.withAuth("u1", "Admin")
    val memberConn = baseConn.withAuth("u2", "Member")
    for {
      id <- Post.content("Content").title("Title").save.transact(using adminConn).map(_.id)

      // Member can query content
      _ <- Post.content.query.get(using memberConn).map(_ ==> List("Content"))

      // But cannot update content (no grant)
      _ <- Post(id).content("Updated").update.transact(using memberConn)
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Access denied: Role 'Member' cannot update attribute 'Post.content'"
        }
    } yield ()
  }

  "Can update only with grant" - social4 {
    val baseConn   = summon[Conn]
    val adminConn  = baseConn.withAuth("u1", "Admin")
    val memberConn = baseConn.withAuth("u2", "Member")
    for {
      id <- Post.content("Content").title("Title").save.transact(using adminConn).map(_.id)

      // Member can query title
      _ <- Post.title.query.get(using memberConn).map(_ ==> List("Title"))

      // Member can update title (has attribute grant)
      _ <- Post(id).title("Updated Title").update.transact(using memberConn)
      _ <- Post.title.query.get(using memberConn).map(_ ==> List("Updated Title"))
    } yield ()
  }
}
