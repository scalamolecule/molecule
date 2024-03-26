package molecule.coreTests.spi.filter.map.types

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs.{A, B}
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_Int extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Map("a" -> int1, "b" -> int2))
        val b = (2, Map("a" -> int2, "b" -> int3, "c" -> int4))
        for {
          _ <- Ns.i.intMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.intMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val int: Int = someMapOfInts("someKey")`

        val a = (1, Map("a" -> int1, "b" -> int2))
        val b = (2, Map("a" -> int2, "b" -> int3, "c" -> int4))
        for {
          _ <- Ns.i.intMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.intMap("-").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.intMap("a").query.get.map(_ ==> List((1, int1), (2, int2)))
          _ <- Ns.i.a1.intMap("b").query.get.map(_ ==> List((1, int2), (2, int3)))
          _ <- Ns.i.a1.intMap("c").query.get.map(_ ==> List((2, int4)))
        } yield ()
      }


      "Matching entire map not supported" - types { implicit conn =>
        for {
          _ <- Ns.intMap(Map(pint1)).query.get
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Matching/applying a map for map attribute `Ns.intMap` is not supported in queries."
            }
        } yield ()
      }


      //      "not key(s)" - types { implicit conn =>
      //        val a = (1, Map("a" -> int1, "b" -> int2))
      //        val b = (2, Map("a" -> int2, "b" -> int3, "c" -> int4))
      //        for {
      //          _ <- Ns.i.intMap.insert(List(a, b)).transact
      //
      //          // "Not (exactly this AND that)"
      //          _ <- Ns.i.a1.intMap.not("a").query.get.map(_ ==> List(a, b))
      //          _ <- Ns.i.a1.intMap.not("a", "b").query.get.map(_ ==> List(b)) // exclude exact match
      //          _ <- Ns.i.a1.intMap.not("a", "b", "c").query.get.map(_ ==> List(a, b))
      //          // Same as
      //          _ <- Ns.i.a1.intMap.not(List("a")).query.get.map(_ ==> List(a, b))
      //          _ <- Ns.i.a1.intMap.not(List("a", "b")).query.get.map(_ ==> List(b)) // exclude exact match
      //          _ <- Ns.i.a1.intMap.not(List("a", "b", "c")).query.get.map(_ ==> List(a, b))
      //
      //          // Empty Seq of keys matches all
      //          _ <- Ns.i.a1.intMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
      //        } yield ()
      //      }
      //
      //
      //      "get maps which has value(s)" - types { implicit conn =>
      //        val a = (1, Map("a" -> int1, "b" -> int2))
      //        val b = (2, Map("a" -> int2, "b" -> int3, "c" -> int4))
      //        for {
      //          _ <- Ns.i.intMap.insert(List(a, b)).transact
      //
      //          // Sets with one or more values matching
      //
      //          // "Has this"
      //          _ <- Ns.i.a1.intMap.has(int0).query.get.map(_ ==> List())
      //          _ <- Ns.i.a1.intMap.has(int1).query.get.map(_ ==> List(a))
      //          _ <- Ns.i.a1.intMap.has(int2).query.get.map(_ ==> List(a, b))
      //          _ <- Ns.i.a1.intMap.has(int3).query.get.map(_ ==> List(b))
      //          // Same as
      //          _ <- Ns.i.a1.intMap.has(List(int0)).query.get.map(_ ==> List())
      //          _ <- Ns.i.a1.intMap.has(List(int1)).query.get.map(_ ==> List(a))
      //          _ <- Ns.i.a1.intMap.has(List(int2)).query.get.map(_ ==> List(a, b))
      //          _ <- Ns.i.a1.intMap.has(List(int3)).query.get.map(_ ==> List(b))
      //
      //
      //          // OR semantics when multiple values
      //
      //          // "Has this OR that"
      //          _ <- Ns.i.a1.intMap.has(int0, int1).query.get.map(_ ==> List(a))
      //          _ <- Ns.i.a1.intMap.has(int1, int2).query.get.map(_ ==> List(a, b))
      //          _ <- Ns.i.a1.intMap.has(int2, int3).query.get.map(_ ==> List(a, b))
      //          _ <- Ns.i.a1.intMap.has(int3, int4).query.get.map(_ ==> List(b))
      //          // Same as
      //          _ <- Ns.i.a1.intMap.has(List(int0, int1)).query.get.map(_ ==> List(a))
      //          _ <- Ns.i.a1.intMap.has(List(int1, int2)).query.get.map(_ ==> List(a, b))
      //          _ <- Ns.i.a1.intMap.has(List(int2, int3)).query.get.map(_ ==> List(a, b))
      //          _ <- Ns.i.a1.intMap.has(List(int2, int4)).query.get.map(_ ==> List(b))
      //
      //          // Empty Seq of values matches nothing
      //          _ <- Ns.i.a1.intMap.has(List.empty[Int]).query.get.map(_ ==> List())
      //        } yield ()
      //      }
      //
      //
      //      "get maps which hasNo" - types { implicit conn =>
      //        val a = (1, Map("a" -> int1, "b" -> int2))
      //        val b = (2, Map("a" -> int2, "b" -> int3, "c" -> int4))
      //        for {
      //          _ <- Ns.i.intMap.insert(List(a, b)).transact
      //
      //          // Sets without one or more values matching
      //
      //          // "Doesn't have this"
      //          _ <- Ns.i.a1.intMap.hasNo(int0).query.get.map(_ ==> List(a, b))
      //          _ <- Ns.i.a1.intMap.hasNo(int1).query.get.map(_ ==> List(b))
      //          _ <- Ns.i.a1.intMap.hasNo(int2).query.get.map(_ ==> List())
      //          _ <- Ns.i.a1.intMap.hasNo(int3).query.get.map(_ ==> List(a))
      //          _ <- Ns.i.a1.intMap.hasNo(int4).query.get.map(_ ==> List(a))
      //          _ <- Ns.i.a1.intMap.hasNo(int5).query.get.map(_ ==> List(a, b))
      //          // Same as
      //          _ <- Ns.i.a1.intMap.hasNo(List(int0)).query.get.map(_ ==> List(a, b))
      //          _ <- Ns.i.a1.intMap.hasNo(List(int1)).query.get.map(_ ==> List(b))
      //          _ <- Ns.i.a1.intMap.hasNo(List(int2)).query.get.map(_ ==> List())
      //          _ <- Ns.i.a1.intMap.hasNo(List(int3)).query.get.map(_ ==> List(a))
      //          _ <- Ns.i.a1.intMap.hasNo(List(int4)).query.get.map(_ ==> List(a))
      //          _ <- Ns.i.a1.intMap.hasNo(List(int5)).query.get.map(_ ==> List(a, b))
      //
      //
      //          // OR semantics when multiple values
      //
      //          // "Has neither this OR that"
      //          _ <- Ns.i.a1.intMap.hasNo(int0, int1).query.get.map(_ ==> List(b))
      //          _ <- Ns.i.a1.intMap.hasNo(int1, int2).query.get.map(_ ==> List())
      //          _ <- Ns.i.a1.intMap.hasNo(int2, int3).query.get.map(_ ==> List())
      //          _ <- Ns.i.a1.intMap.hasNo(int3, int4).query.get.map(_ ==> List(a))
      //          // Same as
      //          _ <- Ns.i.a1.intMap.hasNo(List(int0, int1)).query.get.map(_ ==> List(b))
      //          _ <- Ns.i.a1.intMap.hasNo(List(int1, int2)).query.get.map(_ ==> List())
      //          _ <- Ns.i.a1.intMap.hasNo(List(int2, int3)).query.get.map(_ ==> List())
      //          _ <- Ns.i.a1.intMap.hasNo(List(int3, int4)).query.get.map(_ ==> List(a))
      //
      //
      //          // Negating empty Seq of values matches all
      //          _ <- Ns.i.a1.intMap.hasNo(List.empty[Int]).query.get.map(_ ==> List(a, b))
      //        } yield ()
      //      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Map("a" -> int1, "b" -> int2)))
        val b = (2, Some(Map("a" -> int2, "b" -> int3, "c" -> int4)))
        val c = (3, None)
        for {
          _ <- Ns.i.intMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.intMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      //      "apply (equal)" - types { implicit conn =>
      //        val a = (1, Some(Map("a" -> int1, "b" -> int2)))
      //        val b = (2, Some(Map("a" -> int2, "b" -> int3, "c" -> int4)))
      //        val c = (3, None)
      //        for {
      //          _ <- Ns.i.intMap_?.insert(a, b, c).transact
      //
      //          // Exact Set matches
      //
      //          // AND semantics
      //          // "Is exactly this AND that"
      //          _ <- Ns.i.a1.intMap_?(Some(Map(pint1))).query.get.map(_ ==> List())
      //          _ <- Ns.i.a1.intMap_?(Some(Map(pint1, pint2))).query.get.map(_ ==> List(a)) // include exact match
      //          _ <- Ns.i.a1.intMap_?(Some(Map(pint1, pint2, pint3))).query.get.map(_ ==> List())
      //
      //          // None matches non-asserted values
      //          _ <- Ns.i.a1.intMap_?(Option.empty[Map[String, Int]]).query.get.map(_ ==> List(c))
      //        } yield ()
      //      }


      "optional value by key" - types { implicit conn =>
        // Applying a String key to an optional attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[Int] = someMapOfInts.get("someKey")`

        val a = (1, Some(Map("a" -> int1, "b" -> int2)))
        val b = (2, Some(Map("a" -> int2, "b" -> int3, "c" -> int4)))
        val c = (3, None)
        for {
          _ <- Ns.i.intMap_?.insert(a, b, c).transact

          //          _ <- Ns.i.a1.intMap_?("-").query.get.map(_ ==> List((1, None), (2, None)))
          _ <- Ns.i.a1.intMap_?("-").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.intMap_?("a").query.get.map(_ ==> List((1, Some(int1)), (2, Some(int2))))
          _ <- Ns.i.a1.intMap_?("b").query.get.map(_ ==> List((1, Some(int2)), (2, Some(int3))))
          _ <- Ns.i.a1.intMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(int4))))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.intMap.insert(List(
            (1, Map("a" -> int1, "b" -> int2)),
            (2, Map("a" -> int2, "b" -> int3, "c" -> int4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // intMap not asserted for i = 0
          _ <- Ns.i.a1.intMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      //      "equal to map" - types { implicit conn =>
      //        for {
      //          _ <- Ns.i.intMap_?.insert(List(
      //            (0, None),
      //            (1, Some(Map("a" -> int1, "b" -> int2))),
      //            (2, Some(Map("a" -> int2, "b" -> int3, "c" -> int4))),
      //          )).transact
      //
      //          // AND semantics
      //          // "Is exactly this AND that"
      //          _ <- Ns.i.a1.intMap_(Map(pint1)).query.get.map(_ ==> List())
      //          _ <- Ns.i.a1.intMap_(Map(pint1, pint2)).query.get.map(_ ==> List(1)) // include exact match
      //          _ <- Ns.i.a1.intMap_(Map(pint1, pint2, pint3)).query.get.map(_ ==> List())
      //
      //          // Empty Seq/Sets match nothing
      //          _ <- Ns.i.a1.intMap_(Map.empty[String, Int]).query.get.map(_ ==> List())
      //
      //          // Match non-asserted attribute (null)
      //          _ <- Ns.i.a1.intMap_().query.get.map(_ ==> List(0))
      //        } yield ()
      //      }


      "match key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.intMap_?.insert(List(
            (0, None),
            (1, Some(Map("a" -> int1, "b" -> int2))),
            (2, Some(Map("a" -> int2, "b" -> int3, "c" -> int4))),
          )).transact

          // Match maps having exactly applied keys

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.intMap_("a").query.get.map(_ ==> List())
          _ <- Ns.i.a1.intMap_("a", "b").query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.intMap_("a", "b", "c").query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.intMap_(List("a")).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intMap_(List("a", "b")).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.intMap_(List("a", "b", "c")).query.get.map(_ ==> List())

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.intMap_(List.empty[String]).query.get.map(_ ==> List())
        } yield ()
      }


      "not key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.intMap.insert(List(
            (1, Map("a" -> int1, "b" -> int2)),
            (2, Map("a" -> int2, "b" -> int3, "c" -> int4)),
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.intMap_.not("a").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intMap_.not("a", "b").query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.intMap_.not("a", "b", "c").query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.intMap_.not(List("a")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intMap_.not(List("a", "b")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.not(List("a", "b", "c")).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.intMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.intMap.insert(List(
            (1, Map("a" -> int1, "b" -> int2)),
            (2, Map("a" -> int2, "b" -> int3, "c" -> int4)),
          )).transact

          // Maps with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.intMap_.has(int0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intMap_.has(int1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.has(int2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intMap_.has(int3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.intMap_.has(List(int0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intMap_.has(List(int1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.has(List(int2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intMap_.has(List(int3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.intMap_.has(int0, int1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.has(int1, int2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intMap_.has(int2, int3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intMap_.has(int3, int4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.intMap_.has(List(int0, int1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.has(List(int1, int2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intMap_.has(List(int2, int3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intMap_.has(List(int3, int4)).query.get.map(_ ==> List(2))


          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.intMap_.has(List.empty[Int]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.intMap.insert(List(
            (1, Map("a" -> int1, "b" -> int2)),
            (2, Map("a" -> int2, "b" -> int3, "c" -> int4)),
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.intMap_.hasNo(int0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intMap_.hasNo(int1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.hasNo(int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intMap_.hasNo(int3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.hasNo(int4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.hasNo(int5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.intMap_.hasNo(List(int0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intMap_.hasNo(List(int1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.hasNo(List(int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intMap_.hasNo(List(int3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.hasNo(List(int4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.hasNo(List(int5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.intMap_.hasNo(int0, int1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.hasNo(int1, int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intMap_.hasNo(int1, int3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intMap_.hasNo(int1, int4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intMap_.hasNo(int1, int5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.intMap_.hasNo(List(int0, int1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.hasNo(List(int1, int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intMap_.hasNo(List(int1, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intMap_.hasNo(List(int1, int4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intMap_.hasNo(List(int1, int5)).query.get.map(_ ==> List(2))


          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.intMap_.hasNo(List.empty[Int]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }
  }
}