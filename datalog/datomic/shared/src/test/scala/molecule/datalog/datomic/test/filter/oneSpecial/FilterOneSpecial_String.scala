package molecule.datalog.datomic.test.filter.oneSpecial

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._

object FilterOneSpecial_String extends DatomicTestSuite {

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      for {
        _ <- Ns.i.string.insert(
          (1, "Hello"),
          (2, "friends"),
        ).transact

        // Case sensitive
        _ <- Ns.string.startsWith("He").query.get.map(_ ==> List("Hello"))
        _ <- Ns.string.startsWith("he").query.get.map(_ ==> List())

        _ <- Ns.string.endsWith("lo").query.get.map(_ ==> List("Hello"))

        _ <- Ns.string.contains("ll").query.get.map(_ ==> List("Hello"))
        _ <- Ns.string.contains("e").a1.query.get.map(_ ==> List("Hello", "friends"))

        // Regex matches
        _ <- Ns.string.matches("^[A-Z].*").query.get.map(_ ==> List("Hello"))
        _ <- Ns.string.matches("^[a-z].*").query.get.map(_ ==> List("friends"))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      for {
        _ <- Ns.i.string.insert(
          (1, "Hello"),
          (2, "friends"),
        ).transact

        _ <- Ns.i.string_.startsWith("He").query.get.map(_ ==> List(1))
        _ <- Ns.i.string_.startsWith("he").query.get.map(_ ==> List())

        _ <- Ns.i.string_.endsWith("lo").query.get.map(_ ==> List(1))

        _ <- Ns.i.string_.contains("ll").query.get.map(_ ==> List(1))
        _ <- Ns.i.string_.contains("e").query.get.map(_ ==> List(1, 2))

        _ <- Ns.i.string_.matches("^[A-Z].*").query.get.map(_ ==> List(1))
        _ <- Ns.i.string_.matches("^[a-z].*").query.get.map(_ ==> List(2))
      } yield ()
    }


    "Modified return string" - types { implicit conn =>
      for {
        _ <- Ns.i.string.insert(
          (1, "Hello"),
          (2, "friends"),
        ).transact

        _ <- Ns.string.take(3).a1.query.get.map(_ ==> List("Hel", "fri"))
        _ <- Ns.string.take(6).a1.query.get.map(_ ==> List("Hello", "friend"))
        _ <- Ns.string.take(0).a1.query.get.map(_ ==> List())
        _ <- Ns.string.take(-2).a1.query.get.map(_ ==> List())

        _ <- Ns.string.takeRight(3).a1.query.get.map(_ ==> List("llo", "nds"))
        _ <- Ns.string.takeRight(5).a1.query.get.map(_ ==> List("Hello", "iends"))
        _ <- Ns.string.takeRight(9).a1.query.get.map(_ ==> List("Hello", "friends"))
        _ <- Ns.string.takeRight(0).a1.query.get.map(_ ==> List())
        _ <- Ns.string.takeRight(-2).a1.query.get.map(_ ==> List())

        _ <- Ns.string.drop(1).a1.query.get.map(_ ==> List("ello", "riends"))
        _ <- Ns.string.drop(5).a1.query.get.map(_ ==> List("ds"))
        _ <- Ns.string.drop(9).a1.query.get.map(_ ==> List())
        _ <- Ns.string.drop(0).a1.query.get.map(_ ==> List("Hello", "friends"))
        _ <- Ns.string.drop(-2).a1.query.get.map(_ ==> List("Hello", "friends"))

        _ <- Ns.string.dropRight(3).a1.query.get.map(_ ==> List("He", "frie"))
        _ <- Ns.string.dropRight(5).a1.query.get.map(_ ==> List("fr"))
        _ <- Ns.string.dropRight(9).a1.query.get.map(_ ==> List())
        _ <- Ns.string.dropRight(0).a1.query.get.map(_ ==> List("Hello", "friends"))
        _ <- Ns.string.dropRight(-2).a1.query.get.map(_ ==> List("Hello", "friends"))

        _ <- Ns.string.slice(1, 4).a1.query.get.map(_ ==> List("ell", "rie"))
        _ <- Ns.string.slice(5, 7).a1.query.get.map(_ ==> List("ds"))
        _ <- Ns.string.slice(0, 3).a1.query.get.map(_ ==> List("Hel", "fri"))
        _ <- Ns.string.slice(-2, 9).a1.query.get.map(_ ==> List("Hello", "friends"))
        _ <- Ns.string.slice(3, 3).a1.query.get.map(_ ==> List())
        _ <- Ns.string.slice(5, 3).a1.query.get.map(_ ==> List())

        // Same forgiving semantics as slice
        _ <- Ns.string.substring(1, 4).a1.query.get.map(_ ==> List("ell", "rie"))
        _ <- Ns.string.substring(5, 7).a1.query.get.map(_ ==> List("ds"))
        _ <- Ns.string.substring(0, 3).a1.query.get.map(_ ==> List("Hel", "fri"))
        _ <- Ns.string.substring(-2, 9).a1.query.get.map(_ ==> List("Hello", "friends"))
        _ <- Ns.string.substring(3, 3).a1.query.get.map(_ ==> List())
        _ <- Ns.string.substring(5, 3).a1.query.get.map(_ ==> List())
      } yield ()
    }


    "Empty string" - types { implicit conn =>
      for {
        _ <- Ns.i.string.insert(
          (0, ""),
          (1, "Hello"),
          (2, "friends"),
        ).transact

        _ <- Ns.string.apply("").a1.query.get.map(_ ==> List(""))
        _ <- Ns.string.startsWith("").a1.query.get.map(_ ==> List("", "Hello", "friends"))
        _ <- Ns.string.endsWith("").a1.query.get.map(_ ==> List("", "Hello", "friends"))
        _ <- Ns.string.contains("").a1.query.get.map(_ ==> List("", "Hello", "friends"))
        _ <- Ns.string.matches("").a1.query.get.map(_ ==> List("", "Hello", "friends"))

        _ <- Ns.i.a1.string_.apply("").query.get.map(_ ==> List(0))
        _ <- Ns.i.a1.string_.startsWith("").query.get.map(_ ==> List(0, 1, 2))
        _ <- Ns.i.a1.string_.endsWith("").query.get.map(_ ==> List(0, 1, 2))
        _ <- Ns.i.a1.string_.contains("").query.get.map(_ ==> List(0, 1, 2))
        _ <- Ns.i.a1.string_.matches("").query.get.map(_ ==> List(0, 1, 2))

        _ <- Ns.string.take(3).a1.query.get.map(_ ==> List("", "Hel", "fri"))
        _ <- Ns.string.takeRight(3).a1.query.get.map(_ ==> List("", "llo", "nds"))
        _ <- Ns.string.drop(1).a1.query.get.map(_ ==> List("ello", "riends"))
        _ <- Ns.string.dropRight(3).a1.query.get.map(_ ==> List("He", "frie"))
        _ <- Ns.string.slice(1, 4).a1.query.get.map(_ ==> List("ell", "rie"))
        _ <- Ns.string.substring(1, 4).a1.query.get.map(_ ==> List("ell", "rie"))
      } yield ()
    }


    "Filters + modifier" - types { implicit conn =>
      for {
        _ <- Ns.i.string.insert(
          (1, "Hello"),
          (2, "friends"),
        ).transact

        _ <- Ns.string.startsWith("fri").query.get.map(_ ==> List("friends"))
        _ <- Ns.string_.startsWith("fri").string.take(2).query.get.map(_ ==> List("fr"))
        _ <- Ns.string_.startsWith("fri").string.takeRight(2).query.get.map(_ ==> List("ds"))
        _ <- Ns.string_.startsWith("fri").string.drop(2).query.get.map(_ ==> List("iends"))
        _ <- Ns.string_.startsWith("fri").string.dropRight(2).query.get.map(_ ==> List("frien"))
        _ <- Ns.string_.startsWith("fri").string.substring(1, 4).query.get.map(_ ==> List("rie"))
        _ <- Ns.string_.startsWith("fri").string.slice(1, 4).query.get.map(_ ==> List("rie"))

        // Multiple filters
        _ <- Ns.string.startsWith("fri").string_.endsWith("nds").query.get.map(_ ==> List("friends"))
        _ <- Ns.string_.startsWith("fri").string_.endsWith("nds").string.take(2).query.get.map(_ ==> List("fr"))
        _ <- Ns.string_.startsWith("fri").string_.endsWith("nds").string.takeRight(2).query.get.map(_ ==> List("ds"))
        _ <- Ns.string_.startsWith("fri").string_.endsWith("nds").string.drop(2).query.get.map(_ ==> List("iends"))
        _ <- Ns.string_.startsWith("fri").string_.endsWith("nds").string.dropRight(2).query.get.map(_ ==> List("frien"))
        _ <- Ns.string_.startsWith("fri").string_.endsWith("nds").string.substring(1, 4).query.get.map(_ ==> List("rie"))
        _ <- Ns.string_.startsWith("fri").string_.endsWith("nds").string.slice(1, 4).query.get.map(_ ==> List("rie"))

        // etc...
      } yield ()
    }
  }
}
