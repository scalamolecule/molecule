package molecule.db.compliance.test.authorization

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async

import molecule.db.common.spi.{Conn, Spi_async}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.SocialApp_raw_access.*
import molecule.db.compliance.setup.DbProviders
import java.time.LocalDate

case class Authorization_raw_access(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  // ============================================================================
  // Raw SQL Access Authorization
  // ============================================================================
  // Demonstrates:
  // - rawQuery action (read-only raw SQL)
  // - rawTransact action (dangerous raw SQL mutations)
  // - Use cases for raw SQL
  // - Dangers of bypassing authorization
  // ============================================================================


  // ============================================================================
  // Setup: Create test data
  // ============================================================================

//  def setupTestData()(using conn: JdbcConn_JVM) = {
  def setupTestData()(using conn: Conn) = {
    for {
      // Insert sales data in one go
      _ <- Sale.saleDate.customerId.amount.region.insert(List(
        (LocalDate.of(2024, 1, 1), 101L, 100.0, "North"),
        (LocalDate.of(2024, 1, 2), 102L, 150.0, "South"),
        (LocalDate.of(2024, 1, 3), 101L, 200.0, "North"),
        (LocalDate.of(2024, 1, 4), 103L, 75.0, "East"),
        (LocalDate.of(2024, 1, 5), 102L, 300.0, "South"),
      )).transact

      // Insert people
      _ <- Person.name.email.accessRole.insert(List(
        ("Alice", "alice@example.com", "Member"),
        ("Bob", "bob@example.com", "Member"),
        ("Charlie", "charlie@example.com", "Guest"),
      )).transact

      // Insert accounts with restricted attributes (need Admin role)
      adminConn <- conn.withAuth("admin", "Admin")
      _ <- Account.username.publicProfile.privateNotes.internalStatus.insert(List(
        ("alice123", "Public bio", "Admin only notes", "active"),
        ("bob456", "Another bio", "Secret info", "review"),
      )).transact(using adminConn)
    } yield ()
  }


  // ============================================================================
  // 1. No Raw Access - Denied
  // ============================================================================

  "Guest cannot use rawQuery" - rawAccess {
    val baseConn  = summon[Conn]
    for {
      _ <- setupTestData()(using baseConn)
      guestConn <- baseConn.withAuth("guest1", "Guest")

      _ <- fallback_rawQuery(
        "SELECT * FROM sale"
      )(using guestConn)
        .map(_ ==> "Should have failed")
        .recover { case ModelError(err) =>
          assert(err.contains("Access denied"))
          assert(err.contains("does not have rawQuery access"))
        }
    } yield ()
  }

  "Analyst cannot use rawTransact" - rawAccess {
    val baseConn    = summon[Conn]
    for {
      _ <- setupTestData()(using baseConn)
      analystConn <- baseConn.withAuth("analyst1", "Analyst")

      _ <- fallback_rawTransact(
        "UPDATE Sale SET region = 'West' WHERE id = 1"
      )(using analystConn)
        .map(_ ==> "Should have failed")
        .recover { case ModelError(err) =>
          assert(err.contains("Access denied"))
          assert(err.contains("does not have rawTransact access"))
        }
    } yield ()
  }

  "Unauthenticated cannot use rawQuery" - rawAccess {
    val baseConn = summon[Conn]
    for {
      _ <- setupTestData()(using baseConn)

      _ <- fallback_rawQuery(
        "SELECT * FROM Sale"
      )(using baseConn)
        .map(_ ==> "Should have failed")
        .recover { case ModelError(err) =>
          assert(err.contains("Access denied"))
          assert(err.contains("require authentication"))
        }
    } yield ()
  }


  // ============================================================================
  // 2. rawQuery Success - Analyst Role
  // ============================================================================

  "Analyst can use rawQuery - window function (running total)" - rawAccess {
    val baseConn    = summon[Conn]
    for {
      _ <- setupTestData()(using baseConn)
      analystConn <- baseConn.withAuth("analyst1", "Analyst")

      // Use window function to calculate running total
      // This is a legitimate use case where Molecule doesn't have built-in support
      results <- fallback_rawQuery(
        """
          |SELECT
          |  saleDate,
          |  amount,
          |  SUM(amount) OVER (ORDER BY saleDate) as running_total
          |FROM Sale
          |ORDER BY saleDate
        """.stripMargin
      )(using analystConn).map { results =>
        // Verify running totals are calculated correctly
        results.length ==> 5 // Should have 5 sales

        // First sale: 100
        results(0)(2).toString.toDouble ==> 100.0

        // Second sale: 100 + 150 = 250
        results(1)(2).toString.toDouble ==> 250.0

        // Third sale: 250 + 200 = 450
        results(2)(2).toString.toDouble ==> 450.0

        // Fourth sale: 450 + 75 = 525
        results(3)(2).toString.toDouble ==> 525.0

        // Fifth sale: 525 + 300 = 825
        results(4)(2).toString.toDouble ==> 825.0
      }
    } yield ()
  }


  // ============================================================================
  // 3. rawTransact - Admin Only (Dangerous!)
  // ============================================================================

  "Admin can use rawTransact - bulk operation with complex logic" - rawAccess {
    val baseConn  = summon[Conn]
    for {
      _ <- setupTestData()(using baseConn)
      adminConn <- baseConn.withAuth("admin1", "Admin")

      // Data before
      _ <- Sale.region.a1.amount.d2.query.get(using adminConn).map(_ ==> List(
        ("East", 75.0),
        ("North", 200.0),
        ("North", 100.0),
        ("South", 300.0),
        ("South", 150.0),
      ))

      // Legitimate use: Complex bulk update with CASE logic
      // Calculate and apply region-specific discounts based on multiple conditions
      // This level of conditional logic is difficult to express in Molecule
      _ <- fallback_rawTransact(
        """
          |UPDATE Sale
          |SET amount = CASE
          |  WHEN region = 'North' AND amount > 150 THEN amount * 0.9
          |  WHEN region = 'South' AND saleDate < '2024-01-03' THEN amount * 0.85
          |  WHEN region = 'East' THEN amount * 0.95
          |  ELSE amount
          |END
        """.stripMargin
      )(using adminConn)

      // Data after
      // Verify the complex update worked
      _ <- Sale.region.a1.amount.d2.query.get(using adminConn).map(_ ==> List(
        ("East", 71.25), // 75 * 0.95
        ("North", 180.0), // 200 * 0.9
        ("North", 100.0), // unchanged
        ("South", 300.0), // unchanged (after 2024-01-03)
        ("South", 127.5), // 150 * 0.85 (before 2024-01-03)
      ))
    } yield ()
  }

  "Admin rawTransact DANGER - bypasses attribute restrictions" - rawAccess {
    val baseConn   = summon[Conn]
    for {
      _ <- setupTestData()(using baseConn)
      adminConn  <- baseConn.withAuth("admin1", "Admin")
      memberConn <- baseConn.withAuth("member1", "Member")

      // Data before
      _ <- Account.username_("alice123").privateNotes.query.get(using adminConn)
        .map(_.head ==> "Admin only notes")

      // DANGER: Admin can use raw SQL to bypass attribute-level restrictions!
      // The Account entity has privateNotes.only[Admin], but raw SQL bypasses this:
      _ <- fallback_rawTransact(
        """
          |UPDATE Account
          |SET privateNotes = 'HACKED! Raw SQL bypasses authorization'
          |WHERE username = 'alice123'
        """.stripMargin
      )(using adminConn)

      // Data after
      // Verify the dangerous update worked (bypassed .only[Admin] restriction)
      _ <- Account.username_("alice123").privateNotes.query.get(using adminConn)
        .map(_.head ==> "HACKED! Raw SQL bypasses authorization")

      // Member still cannot access via normal Molecule API (authorization works)
      _ <- Account.username.privateNotes.query.get(using memberConn)
        .map(_ ==> "Should have failed")
        .recover { case ModelError(err) =>
          assert(err.contains("Access denied"))
        }
    } yield ()
  }

  "Admin rawTransact DANGER - can escalate privileges" - rawAccess {
    val baseConn  = summon[Conn]
    for {
      _ <- setupTestData()(using baseConn)
      adminConn <- baseConn.withAuth("admin1", "Admin")

      // Data before
      _ <- Person.name_("Charlie").accessRole.query.get(using adminConn)
        .map(_.head ==> "Guest")

      // EXTREME DANGER: Raw SQL can escalate user privileges!
      // This completely bypasses the authorization model
      _ <- fallback_rawTransact(
        """
          |UPDATE Person
          |SET accessRole = 'Admin'
          |WHERE name = 'Charlie'
        """.stripMargin
      )(using adminConn)

      // Data after - Charlie is now an Admin (extremely dangerous!)
      _ <- Person.name_("Charlie").accessRole.query.get(using adminConn)
        .map(_.head ==> "Admin")
    } yield ()
  }

  "Admin rawTransact DANGER - can modify any data regardless of entity roles" - rawAccess {
    val baseConn  = summon[Conn]
    for {
      _ <- setupTestData()(using baseConn)
      adminConn <- baseConn.withAuth("admin1", "Admin")

      // Data before
      _ <- Account.internalStatus.a1.query.get(using adminConn).map(_ ==> List("active", "review"))

      // Direct SQL update that bypasses all authorization checks
      // DANGER: Can directly manipulate data that should be restricted
      // Even if an entity has only Member access, raw SQL bypasses it
      _ <- fallback_rawTransact(
        """
          |UPDATE Account
          |SET internalStatus = 'suspended'
          |WHERE internalStatus = 'active'
        """.stripMargin
      )(using adminConn)

      // Data after - Raw SQL modified restricted data
      _ <- Account.internalStatus.a1.query.get(using adminConn).map(_ ==> List("review", "suspended"))
    } yield ()
  }

  "Admin rawTransact DANGER - can corrupt data across multiple tables" - rawAccess {
    val baseConn  = summon[Conn]
    for {
      _ <- setupTestData()(using baseConn)
      adminConn <- baseConn.withAuth("admin1", "Admin")

      // Data before
      _ <- Sale.customerId.a1.query.get(using adminConn)
        .map(_ ==> List(101L, 102L, 103L))

      // DANGER: Raw SQL can make changes that violate business logic
      // Set all customer IDs to the same value (corrupts relational integrity)
      _ <- fallback_rawTransact(
        """
          |UPDATE Sale
          |SET customerId = 999
        """.stripMargin
      )(using adminConn)

      // Data after - All sales now point to same customer!
      _ <- Sale.customerId.a1.query.get(using adminConn)
        .map(_ ==> List(999L))
    } yield ()
  }
}
