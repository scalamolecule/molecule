package molecule.coreTests.spi.filterAttr

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


    def ambiguous(attr: String): String =
      s"""Ambiguous filter attribute path: $attr
         |Please qualify the filter attribute by appending the full path of namespaces.
         |Or make sure that the target attribute is not appearing multiple times.""".stripMargin


    "Qualifying" - refs { implicit conn =>
      for {
        _ <- A.s.i.B.i.insert(
          ("a", 1, 0),
          ("b", 1, 1),
          ("c", 0, 1),
        ).transact

        _ <- A.s.i(B.i_) // Ambiguous if B points to A.B or A.OwnB
          .B.i._A
          .OwnB.i
          .query.get.map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> ambiguous("B.i")
          }

        // We can qualify the full path to a filter attribute
        _ <- A.s.i_(A.B.i_).B.i_.query.get.map(_ ==> List("b"))

        // But in this case `B` is enough
        _ <- A.s.i_(B.i_).B.i_.query.get.map(_ ==> List("b"))


        // No need to qualify
        // B.i can unambiguously point to A.B.i since A.B.i has the same
        // unambiguous value even though it participates in multiple expressions.
        _ <- A.s.i_(B.i_).B.i_.<(2).i_.not(0).query.get.map(_ ==> List("b"))
        // Backwards
        _ <- A.s.i_.<(2).i_.not(0).B.i_(A.i_).query.get.map(_ ==> List("b"))



        // Same with owned relationships
        _ <- A.s.i.OwnB.i.insert(
          ("a", 1, 0),
          ("b", 1, 1),
        ).transact

        _ <- A.s.i_(A.OwnB.i_).OwnB.i_.query.get.map(_ ==> List("b"))
        // It is unambiguous that `B` points to the `OwnB` branch
        _ <- A.s.i_(B.i_).OwnB.i_.query.get.map(_ ==> List("b"))

        // No need to qualify
        _ <- A.s.i_(B.i_).OwnB.i_.<(2).i_.not(0).query.get.map(_ ==> List("b"))
        // Backwards
        _ <- A.s.i_.<(2).i_.not(0).OwnB.i_(A.i_).query.get.map(_ ==> List("b"))
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
            ("a", 1, 1, 0, 0, 0),
            ("b", 1, 0, 1, 0, 0),
            ("c", 1, 0, 0, 1, 0),
            ("d", 1, 0, 0, 0, 1),
          ).transact

        // A  ---------------------------

        // Forwards
        _ <- A.s.i_(A.B.i_)
          .B.i_.OwnC.i_._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("a"))

        _ <- A.s.i_(A.B.OwnC.i_)
          .B.i_.OwnC.i_._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("b"))

        _ <- A.s.i_(A.OwnB.i_)
          .B.i_.OwnC.i_._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("c"))

        _ <- A.s.i_(A.OwnB.C.i_)
          .B.i_.OwnC.i_._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("d"))

        // Backwards
        _ <- A.s.i_
          .B.i_(A.i_).OwnC.i_._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("a"))

        _ <- A.s.i_
          .B.i_.OwnC.i_(A.i_)._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("b"))

        _ <- A.s.i_
          .B.i_.OwnC.i_._B._A
          .OwnB.i_(A.i_).C.i_
          .query.get.map(_ ==> List("c"))

        _ <- A.s.i_
          .B.i_.OwnC.i_._B._A
          .OwnB.i_.C.i_(A.i_)
          .query.get.map(_ ==> List("d"))


        // A.B  ---------------------------

        // Forwards
        _ <- A.s.i_
          .B.i_(A.B.OwnC.i_).OwnC.i_._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("c", "d"))

        _ <- A.s.i_
          .B.i_(A.OwnB.i_).OwnC.i_._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("b", "d"))

        _ <- A.s.i_
          .B.i_(A.OwnB.C.i_).OwnC.i_._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("b", "c"))

        // Backwards
        _ <- A.s.i_
          .B.i_.OwnC.i_(A.B.i_)._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("c", "d"))

        _ <- A.s.i_
          .B.i_.OwnC.i_._B._A
          .OwnB.i_(A.B.i_).C.i_
          .query.get.map(_ ==> List("b", "d"))

        _ <- A.s.i_
          .B.i_(A.OwnB.C.i_).OwnC.i_._B._A
          .OwnB.i_.C.i_(A.B.i_)
          .query.get.map(_ ==> List("b", "c"))


        // A.B.OwnC  ---------------------------

        // Forwards
        _ <- A.s.i_
          .B.i_.OwnC.i_(A.OwnB.i_)._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("a", "d"))

        _ <- A.s.i_
          .B.i_.OwnC.i_(A.OwnB.C.i_)._B._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("a", "c"))

        // Backwards
        _ <- A.s.i_
          .B.i_.OwnC.i_._B._A
          .OwnB.i_(A.B.OwnC.i_).C.i_
          .query.get.map(_ ==> List("a", "d"))

        _ <- A.s.i_
          .B.i_.OwnC.i_._B._A
          .OwnB.i_.C.i_(A.B.OwnC.i_)
          .query.get.map(_ ==> List("a", "c"))


        // A.OwnB  ---------------------------

        // Forwards
        _ <- A.s.i_
          .B.i_.OwnC.i_._B._A
          .OwnB.i_(A.OwnB.C.i_).C.i_
          .query.get.map(_ ==> List("a", "b"))

        // Backwards
        _ <- A.s.i_
          .B.i_.OwnC.i_._B._A
          .OwnB.i_.C.i_(A.OwnB.i_)
          .query.get.map(_ ==> List("a", "b"))
      } yield ()
    }
  }
}
