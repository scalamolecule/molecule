package molecule.coreTests.spi.filterAttr.one

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterAttrRef extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Forward/backward" - refs { implicit conn =>
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

    "Forward/backward owner" - refs { implicit conn =>
      for {
        _ <- A.s.i.OwnB.i.insert(
          ("a", 1, 0),
          ("b", 1, 1),
        ).transact

        _ <- A.s.i_(B.i_).OwnB.i_.query.get.map(_ ==> List("b"))

        _ <- A.s.i_.OwnB.i_(A.i_).query.get.map(_ ==> List("b"))
      } yield ()
    }

    "Ref-Ref" - refs { implicit conn =>
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

    "Ref-Own" - refs { implicit conn =>
      for {
        _ <- A.s.i.B.i.OwnC.i.insert(
          ("a", 1, 1, 0),
          ("b", 1, 0, 1),
          ("c", 0, 1, 1),
        ).transact

        // Forwards
        _ <- A.s.i_(B.i_).B.i_.OwnC.i_.query.get.map(_ ==> List("a"))
        _ <- A.s.i_(C.i_).B.i_.OwnC.i_.query.get.map(_ ==> List("b"))
        _ <- A.s.i_.B.i_(C.i_).OwnC.i_.query.get.map(_ ==> List("c"))

        // Backwards
        _ <- A.s.i_.B.i_(A.i_).OwnC.i_.query.get.map(_ ==> List("a"))
        _ <- A.s.i_.B.i_.OwnC.i_(A.i_).query.get.map(_ ==> List("b"))
        _ <- A.s.i_.B.i_.OwnC.i_(B.i_).query.get.map(_ ==> List("c"))
      } yield ()
    }

    "Own-Ref" - refs { implicit conn =>
      for {
        _ <- A.s.i.OwnB.i.C.i.insert(
          ("a", 1, 1, 0),
          ("b", 1, 0, 1),
          ("c", 0, 1, 1),
        ).transact

        // Forwards
        _ <- A.s.i_(B.i_).OwnB.i_.C.i_.query.get.map(_ ==> List("a"))
        _ <- A.s.i_(C.i_).OwnB.i_.C.i_.query.get.map(_ ==> List("b"))
        _ <- A.s.i_.OwnB.i_(C.i_).C.i_.query.get.map(_ ==> List("c"))

        // Backwards
        _ <- A.s.i_.OwnB.i_(A.i_).C.i_.query.get.map(_ ==> List("a"))
        _ <- A.s.i_.OwnB.i_.C.i_(A.i_).query.get.map(_ ==> List("b"))
        _ <- A.s.i_.OwnB.i_.C.i_(B.i_).query.get.map(_ ==> List("c"))
      } yield ()
    }

    "Own-Own" - refs { implicit conn =>
      for {
        _ <- A.s.i.OwnB.i.OwnC.i.insert(
          ("a", 1, 1, 0),
          ("b", 1, 0, 1),
          ("c", 0, 1, 1),
        ).transact

        // Forwards
        _ <- A.s.i_(B.i_).OwnB.i_.OwnC.i_.query.get.map(_ ==> List("a"))
        _ <- A.s.i_(C.i_).OwnB.i_.OwnC.i_.query.get.map(_ ==> List("b"))
        _ <- A.s.i_.OwnB.i_(C.i_).OwnC.i_.query.get.map(_ ==> List("c"))

        // Backwards
        _ <- A.s.i_.OwnB.i_(A.i_).OwnC.i_.query.get.map(_ ==> List("a"))
        _ <- A.s.i_.OwnB.i_.OwnC.i_(A.i_).query.get.map(_ ==> List("b"))
        _ <- A.s.i_.OwnB.i_.OwnC.i_(B.i_).query.get.map(_ ==> List("c"))
      } yield ()
    }


    "Optional qualifying" - refs { implicit conn =>
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


    "Optional qualifying, owned" - refs { implicit conn =>
      for {
        _ <- A.s.i.OwnB.i.insert(
          ("a", 1, 0),
          ("b", 1, 1),
        ).transact

        _ <- A.s.i_(A.OwnB.i_).OwnB.i_.query.get.map(_ ==> List("b"))
        _ <- A.s.i_(B.i_).OwnB.i_.query.get.map(_ ==> List("b"))

        _ <- A.s.i_(B.i_).OwnB.i_.<(2).i_.not(0).query.get.map(_ ==> List("b"))
        _ <- A.s.i_(A.OwnB.i_).OwnB.i_.<(2).i_.not(0).query.get.map(_ ==> List("b"))

        _ <- A.s.i_.<(2).i_.not(0).OwnB.i_(A.i_).query.get.map(_ ==> List("b"))
      } yield ()
    }


    "Qualifying, multiple branches" - refs { implicit conn =>
      for {
        _ <- A.s.i
          .B.i._A
          .OwnB.i
          .insert(
            ("a", 1, 1, 0),
            ("b", 1, 0, 1),
          ).transact

        // Filter attribute B.i needs qualifying
        _ <- A.s.i_(B.i_) // Ambiguous if B points to A.B or A.OwnB
          .B.i_._A
          .OwnB.i_
          .query.get.map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==>
              """Please qualify filter attribute B.i to an unambiguous path:
                |  A.B.i
                |  A.OwnB.i""".stripMargin
          }

        // Ok when qualified with full unambiguous path

        _ <- A.s.i_(A.B.i_)
          .B.i_._A
          .OwnB.i_
          .query.get.map(_ ==> List("a"))

        _ <- A.s.i_(A.OwnB.i_)
          .B.i_._A
          .OwnB.i_
          .query.get.map(_ ==> List("b"))
      } yield ()
    }


    "Branches, forward" - refs { implicit conn =>
      for {
        _ <- A.s.i.B.i._A.OwnB.i.insert(
          ("a", 1, 1, 0),
          ("b", 1, 0, 1),
          ("c", 0, 1, 1),
        ).transact

        // Unambiguous when pointing forwards to qualified branches

        _ <- A.s.i_(A.B.i_)
          .B.i_._A // <-- B.i is compared to A.i
          .OwnB.i_
          .query.get.map(_ ==> List("a"))

        _ <- A.s.i_(A.OwnB.i_)
          .B.i_._A
          .OwnB.i_ // <-- OwnB.i is compared to A.i
          .query.get.map(_ ==> List("b"))
      } yield ()
    }


    "Branches, backward" - refs { implicit conn =>
      for {
        _ <- A.s.i.B.i._A.OwnB.i.insert(
          ("a", 1, 1, 0),
          ("b", 1, 0, 1),
          ("c", 0, 1, 1),
        ).transact

        // Unambiguous when pointing backwards from branches

        _ <- A.s.i_
          .B.i_(A.i_)._A // point back to A from A.B branch
          .OwnB.i_
          .query.get.map(_ ==> List("a"))

        _ <- A.s.i_
          .B.i_._A
          .OwnB.i_(A.i_) // point back to A from A.OwnB branch
          .query.get.map(_ ==> List("b"))
      } yield ()
    }


    "Branch to branch" - refs { implicit conn =>
      for {
        _ <- A.s.B.i._A.OwnB.i.insert(
          ("a", 1, 0),
          ("b", 1, 1),
        ).transact

        _ <- A.s
          .B.i_(A.OwnB.i_)._A // point to A.OwnB branch
          .OwnB.i_
          .query.get.map(_ ==> List("b"))

        // Same as
        _ <- A.s
          .B.i_._A
          .OwnB.i_(A.B.i_) // point to A.B branch
          .query.get.map(_ ==> List("b"))
      } yield ()
    }


    "2 branches" - refs { implicit conn =>
      for {
        _ <- A.s.i
          .B.i.OwnC.i._B._A
          .OwnB.i.C.i
          .insert(
            ("a", 0, 1, 0, 0, 0),
            ("b", 0, 0, 1, 0, 0),
            ("c", 0, 0, 0, 1, 0),
            ("d", 0, 0, 0, 0, 1),
          ).transact

        // Forwards --------------------------

        // A

        _ <- A.s.i_.<(A.B.i_)
          .B.i_.OwnC.i_._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("a"))

        _ <- A.s.i_.<(A.B.OwnC.i_)
          .B.i_.OwnC.i_._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("b"))

        _ <- A.s.i_.<(A.OwnB.i_)
          .B.i_.OwnC.i_._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("c"))

        _ <- A.s.i_.<(A.OwnB.C.i_)
          .B.i_.OwnC.i_._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("d"))

        // A.B

        _ <- A.s.i_
          .B.i_.<(A.B.OwnC.i_).OwnC.i_._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("b"))

        _ <- A.s.i_
          .B.i_.<(A.OwnB.i_).OwnC.i_._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("c"))

        _ <- A.s.i_
          .B.i_.<(A.OwnB.C.i_).OwnC.i_._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("d"))

        // A.B.OwnC

        _ <- A.s.i_
          .B.i_.OwnC.i_.<(A.OwnB.i_)._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("c"))

        _ <- A.s.i_
          .B.i_.OwnC.i_.<(A.OwnB.C.i_)._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("d"))

        // A.OwnB

        _ <- A.s.i_
          .B.i_.OwnC.i_._B._A
          .OwnB.i_.<(A.OwnB.C.i_).C.i_
          .query.get.map(_ ==> List("d"))


        // Backwards ------------------------------

        // A
        _ <- A.s.i_
          .B.i_.>(A.i_).OwnC.i_._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("a")) // 1 0

        _ <- A.s.i_
          .B.i_.OwnC.i_.>(A.i_)._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("b")) // 1 1

        _ <- A.s.i_
          .B.i_.OwnC.i_._B._A
          .OwnB.i_.>(A.i_).C.i_
          .query.get.map(_ ==> List("c")) // 1 1

        _ <- A.s.i_
          .B.i_.OwnC.i_._B._A
          .OwnB.i_.C.i_.>(A.i_)
          .query.get.map(_ ==> List("d")) // 1 0

        // A.B
        _ <- A.s.i_
          .B.i_.OwnC.i_.>(A.B.i_)._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("b")) // 0 1

        _ <- A.s.i_
          .B.i_.OwnC.i_._B._A
          .OwnB.i_.>(A.B.i_).C.i_
          .query.get.map(_ ==> List("c")) // 0 1

        _ <- A.s.i_
          .B.i_.OwnC.i_._B._A
          .OwnB.i_.C.i_.>(A.B.i_)
          .query.get.map(_ ==> List("d")) // 0 0

        // A.B.OwnC
        _ <- A.s.i_
          .B.i_.OwnC.i_._B._A
          .OwnB.i_.>(A.B.OwnC.i_).C.i_
          .query.get.map(_ ==> List("c")) // 1 1

        _ <- A.s.i_
          .B.i_.OwnC.i_._B._A
          .OwnB.i_.C.i_.>(A.B.OwnC.i_)
          .query.get.map(_ ==> List("d"))

        // A.OwnB
        _ <- A.s.i_
          .B.i_.OwnC.i_._B._A
          .OwnB.i_.C.i_.>(A.OwnB.i_)
          .query.get.map(_ ==> List("d"))
      } yield ()
    }
  }
}
