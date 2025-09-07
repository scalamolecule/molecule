package molecule.db.compliance.test.filterAttr.one

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders

case class FilterAttrRef(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Forward/backward" - refs {
    for {
      _ <- A.s.i.B.i.insert(
        ("a", 1, 0),
        ("b", 1, 1),
      ).transact

      // Pointing forwards to B
      _ <- A.s.i_(B.i_).B.i_.query.get.map(_ ==> List("b"))

      // Same as pointing backwards to A
      _ <- A.s.i_.B.i_(A.i_).query.get.map(_ ==> List("b"))
    } yield ()
  }

  "Ref-Ref" - refs {
    for {
      _ <- A.s.i.B.i.C.i.insert(
        ("a", 1, 1, 0),
        ("b", 1, 0, 1),
        ("c", 0, 1, 1),
      ).transact

      // Forwards
      _ <- A.s.i_(B.i_).B.i_.C.i_.query.get.map(_ ==> List("a"))
      _ <- A.s.i_(C.i_).B.i_.C.i_.query.get.map(_ ==> List("b"))
      _ <- A.s.i_.B.i_(C.i_).C.i_.query.get.map(_ ==> List("c"))

      // Backwards
      _ <- A.s.i_.B.i_(A.i_).C.i_.query.get.map(_ ==> List("a"))
      _ <- A.s.i_.B.i_.C.i_(A.i_).query.get.map(_ ==> List("b"))
      _ <- A.s.i_.B.i_.C.i_(B.i_).query.get.map(_ ==> List("c"))
    } yield ()
  }


  "Optional qualifying" - refs {
    for {
      _ <- A.s.i.B.i.insert(
        ("a", 1, 0),
        ("b", 1, 1),
      ).transact

      // We can qualify the full path to a filter attribute
      _ <- A.s.i_(A.B.i_).B.i_.query.get.map(_ ==> List("b"))

      // But since there is only one B to point to, we can omit qualifying B
      _ <- A.s.i_(B.i_).B.i_.query.get.map(_ ==> List("b"))

      // No need to qualify A
      _ <- A.s.i_.B.i_(A.i_).query.get.map(_ ==> List("b"))


      // Since the two A.B.i have the same unambiguous value we don't need to qualify here neither.
      _ <- A.s.i_(B.i_).B.i_.<(2).i_.not(0).query.get.map(_ ==> List("b"))
      // We can if we want
      _ <- A.s.i_(A.B.i_).B.i_.<(2).i_.not(0).query.get.map(_ ==> List("b"))

      // Backwards there's only one unambiguous value pointed to.
      _ <- A.s.i_.<(2).i_.not(0).B.i_(A.i_).query.get.map(_ ==> List("b"))
    } yield ()
  }


  "Qualifying, multiple branches" - refs {
    for {
      _ <- A.s.i
        .B.i._A
        .C.i
        .insert(
          ("a", 1, 1, 0),
          ("b", 1, 0, 1),
        ).transact

      // Filter attribute B.i needs qualifying
      _ <- A.s.i_(B.i_) // Ambiguous if B points to A.B or A.C
        .B.i_._A
        .C.i_
        .query.get.map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==>
            """Please qualify filter attribute B.i to an unambiguous path:
              |  A.B.i
              |  A.C.i""".stripMargin
        }

      // Ok when qualified with full unambiguous path

      _ <- A.s.i_(A.B.i_)
        .B.i_._A
        .C.i_
        .query.get.map(_ ==> List("a"))

      _ <- A.s.i_(A.C.i_)
        .B.i_._A
        .C.i_
        .query.get.map(_ ==> List("b"))
    } yield ()
  }


  "Branches, forward" - refs {
    for {
      _ <- A.s.i.B.i._A.C.i.insert(
        ("a", 1, 1, 0),
        ("b", 1, 0, 1),
        ("c", 0, 1, 1),
      ).transact

      // Unambiguous when pointing forwards to qualified branches

      _ <- A.s.i_(A.B.i_)
        .B.i_._A // <-- B.i is compared to A.i
        .C.i_
        .query.get.map(_ ==> List("a"))

      _ <- A.s.i_(A.C.i_)
        .B.i_._A
        .C.i_ // <-- C.i is compared to A.i
        .query.get.map(_ ==> List("b"))
    } yield ()
  }


  "Branches, backward" - refs {
    for {
      _ <- A.s.i.B.i._A.C.i.insert(
        ("a", 1, 1, 0),
        ("b", 1, 0, 1),
        ("c", 0, 1, 1),
      ).transact

      // Unambiguous when pointing backwards from branches

      _ <- A.s.i_
        .B.i_(A.i_)._A // point back to A from A.B branch
        .C.i_
        .query.get.map(_ ==> List("a"))

      _ <- A.s.i_
        .B.i_._A
        .C.i_(A.i_) // point back to A from A.C branch
        .query.get.map(_ ==> List("b"))
    } yield ()
  }


  "Branch to branch" - refs {
    for {
      _ <- A.s.B.i._A.C.i.insert(
        ("a", 1, 0),
        ("b", 1, 1),
      ).transact

      _ <- A.s
        .B.i_(A.C.i_)._A // point to A.C branch
        .C.i_
        .query.get.map(_ ==> List("b"))

      // Same as
      _ <- A.s
        .B.i_._A
        .C.i_(A.B.i_) // point to A.B branch
        .query.get.map(_ ==> List("b"))
    } yield ()
  }


  "2 branches" - refs {
    for {
      _ <- A.s.i
        .B.i.C.i._B._A
        .B1.i.C.i
        .insert(
          ("a", 0, 1, 0, 0, 0),
          ("b", 0, 0, 1, 0, 0),
          ("c", 0, 0, 0, 1, 0),
          ("d", 0, 0, 0, 0, 1),
        ).transact

      // Forwards --------------------------

      // A

      _ <- A.s.i_.<(A.B.i_)
        .B.i_.C.i_._B._A
        .B1.i_.C.i_
        .query.get.map(_ ==> List("a"))

      _ <- A.s.i_.<(A.B.C.i_)
        .B.i_.C.i_._B._A
        .B1.i_.C.i_
        .query.get.map(_ ==> List("b"))

      _ <- A.s.i_.<(A.B1.i_)
        .B.i_.C.i_._B._A
        .B1.i_.C.i_
        .query.get.map(_ ==> List("c"))

      _ <- A.s.i_.<(A.B1.C.i_)
        .B.i_.C.i_._B._A
        .B1.i_.C.i_
        .query.get.map(_ ==> List("d"))

      // A.B

      _ <- A.s.i_
        .B.i_.<(A.B.C.i_).C.i_._B._A
        .B1.i_.C.i_
        .query.get.map(_ ==> List("b"))

      _ <- A.s.i_
        .B.i_.<(A.B1.i_).C.i_._B._A
        .B1.i_.C.i_
        .query.get.map(_ ==> List("c"))

      _ <- A.s.i_
        .B.i_.<(A.B1.C.i_).C.i_._B._A
        .B1.i_.C.i_
        .query.get.map(_ ==> List("d"))

      // A.B.C

      _ <- A.s.i_
        .B.i_.C.i_.<(A.B1.i_)._B._A
        .B1.i_.C.i_
        .query.get.map(_ ==> List("c"))

      _ <- A.s.i_
        .B.i_.C.i_.<(A.B1.C.i_)._B._A
        .B1.i_.C.i_
        .query.get.map(_ ==> List("d"))

      // A.B1

      _ <- A.s.i_
        .B.i_.C.i_._B._A
        .B1.i_.<(A.B1.C.i_).C.i_
        .query.get.map(_ ==> List("d"))


      // Backwards ------------------------------

      // A
      _ <- A.s.i_
        .B.i_.>(A.i_).C.i_._B._A
        .B1.i_.C.i_
        .query.get.map(_ ==> List("a")) // 1 0

      _ <- A.s.i_
        .B.i_.C.i_.>(A.i_)._B._A
        .B1.i_.C.i_
        .query.get.map(_ ==> List("b")) // 1 1

      _ <- A.s.i_
        .B.i_.C.i_._B._A
        .B1.i_.>(A.i_).C.i_
        .query.get.map(_ ==> List("c")) // 1 1

      _ <- A.s.i_
        .B.i_.C.i_._B._A
        .B1.i_.C.i_.>(A.i_)
        .query.get.map(_ ==> List("d")) // 1 0

      // A.B
      _ <- A.s.i_
        .B.i_.C.i_.>(A.B.i_)._B._A
        .B1.i_.C.i_
        .query.get.map(_ ==> List("b")) // 0 1

      _ <- A.s.i_
        .B.i_.C.i_._B._A
        .B1.i_.>(A.B.i_).C.i_
        .query.get.map(_ ==> List("c")) // 0 1

      _ <- A.s.i_
        .B.i_.C.i_._B._A
        .B1.i_.C.i_.>(A.B.i_)
        .query.get.map(_ ==> List("d")) // 0 0

      // A.B.C
      _ <- A.s.i_
        .B.i_.C.i_._B._A
        .B1.i_.>(A.B.C.i_).C.i_
        .query.get.map(_ ==> List("c")) // 1 1

      _ <- A.s.i_
        .B.i_.C.i_._B._A
        .B1.i_.C.i_.>(A.B.C.i_)
        .query.get.map(_ ==> List("d"))

      // A.B1
      _ <- A.s.i_
        .B.i_.C.i_._B._A
        .B1.i_.C.i_.>(A.B1.i_)
        .query.get.map(_ ==> List("d"))
    } yield ()
  }
}
