package molecule.document.mongodb

import molecule.core.util.AggrUtils
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.language.implicitConversions


object AdhocJVM_mongodb extends TestSuite_mongodb with AggrUtils {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      //      implicit val tolerant = tolerantIntEquality(toleranceInt)
      //      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      val a = (1, Some(Set(short1, short2)))
      val b = (2, Some(Set(short2, short3, short4)))
      val c = (3, None)
      for {
        _ <- Ns.i.shorts_?.insert(a, b, c).transact

        // Sets with one or more values matching

        // "Has this value"
        _ <- Ns.i.a1.shorts_?.has(Some(short0)).query.i.get.map(_ ==> List())
        _ <- Ns.i.a1.shorts_?.has(Some(short1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.shorts_?.has(Some(short2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_?.has(Some(short3)).query.get.map(_ ==> List(b))
        // Same as
        _ <- Ns.i.a1.shorts_?.has(Some(Seq(short0))).query.get.map(_ ==> List())
        _ <- Ns.i.a1.shorts_?.has(Some(Seq(short1))).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.shorts_?.has(Some(Seq(short2))).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_?.has(Some(Seq(short3))).query.get.map(_ ==> List(b))


        // OR semantics when multiple values

        // "Has this OR that"
        _ <- Ns.i.a1.shorts_?.has(Some(Seq(short1, short2))).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_?.has(Some(Seq(short1, short3))).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_?.has(Some(Seq(short2, short3))).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_?.has(Some(Seq(short1, short2, short3))).query.get.map(_ ==> List(a, b))


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        _ <- Ns.i.a1.shorts_?.has(Some(Set(short1))).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.shorts_?.has(Some(Set(short1, short2))).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.shorts_?.has(Some(Set(short1, short2, short3))).query.get.map(_ ==> List())
        _ <- Ns.i.a1.shorts_?.has(Some(Set(short2))).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_?.has(Some(Set(short2, short3))).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.shorts_?.has(Some(Set(short2, short3, short4))).query.get.map(_ ==> List(b))
        // Same as
        _ <- Ns.i.a1.shorts_?.has(Some(Seq(Set(short1)))).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.shorts_?.has(Some(Seq(Set(short1, short2)))).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.shorts_?.has(Some(Seq(Set(short1, short2, short3)))).query.get.map(_ ==> List())
        _ <- Ns.i.a1.shorts_?.has(Some(Seq(Set(short2)))).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_?.has(Some(Seq(Set(short2, short3)))).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.shorts_?.has(Some(Seq(Set(short2, short3, short4)))).query.get.map(_ ==> List(b))


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        _ <- Ns.i.a1.shorts_?.has(Some(Seq(Set(short1, short2), Set(short0)))).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.shorts_?.has(Some(Seq(Set(short1, short2), Set(short0, short3)))).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.shorts_?.has(Some(Seq(Set(short1, short2), Set(short2, short3)))).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_?.has(Some(Seq(Set(short1, short2), Set(short2, short3, short4)))).query.get.map(_ ==> List(a, b))


        // Empty Seq/Sets match nothing
        _ <- Ns.i.a1.shorts_?.has(Some(Seq.empty[Short])).query.get.map(_ ==> List())
        _ <- Ns.i.a1.shorts_?.has(Some(Set.empty[Short])).query.get.map(_ ==> List())
        _ <- Ns.i.a1.shorts_?.has(Some(Seq.empty[Set[Short]])).query.get.map(_ ==> List())


        // None matches non-asserted values
        _ <- Ns.i.a1.shorts_?.has(Option.empty[Short]).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.shorts_?.has(Option.empty[Seq[Short]]).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.shorts_?.has(Option.empty[Set[Short]]).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.shorts_?.has(Option.empty[Seq[Set[Short]]]).query.get.map(_ ==> List(c))


        //        _ <- Ns.i(1).save.transact
        //        _ <- Ns.i.query.get.map(_ ==> List(1))
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._

      //      implicit val tolerant = tolerantIntEquality(toleranceInt)
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      val (median_2_3, median_1_2) = if (database == "MongoDB") {
        (2, 1)
      } else {
        (
          (2 + 3).toDouble / 2.0,
          (1 + 2).toDouble / 2.0
        )
      }

      for {
        _ <- A.i.B.ii.insert(List(
          (1, Set(1, 2)),
          (2, Set(2, 3)),
          (2, Set(3, 4)),
          (2, Set(3, 4)),
        )).transact

        //          _ <- A.B.ii.query.i.get.map(_ ==> List(Set(1, 2, 3, 4)))
        _ <- A.B.ii(median).query.i.get.map(_.head ==~ median_2_3)

        //          _ <- A.i.a1.B.ii.query.get.map(_ ==> List(
        //            (1, Set(1, 2)),
        //            (2, Set(2, 3, 4)),
        //          ))
        //          _ <- A.i.B.ii(median).query.get.map(_.map {
        //            case (1, median) => median ==~ median_1_2
        //            case (2, median) => median ==~ 3.0
        //          })


      } yield ()
    }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //        _ <- Uniques.i(1).save.transact
    //
    //      } yield ()
    //    }
    //
    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //      } yield ()
    //    }
  }
}
