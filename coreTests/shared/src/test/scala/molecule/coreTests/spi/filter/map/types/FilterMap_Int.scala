package molecule.coreTests.spi.filter.map.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_Int extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> int1, "b" -> int2))
        val b = (2, Map("a" -> int2, "b" -> int3, "c" -> int4))
        for {
          _ <- Ns.i.intMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.intMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val int: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> int1, "b" -> int2))
        val b = (2, Map("a" -> int2, "b" -> int3, "c" -> int4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.intMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.intMap("_").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.intMap("a").query.get.map(_ ==> List((1, int1), (2, int2)))
          _ <- Ns.i.a1.intMap("b").query.get.map(_ ==> List((1, int2), (2, int3)))
          _ <- Ns.i.a1.intMap("c").query.get.map(_ ==> List((2, int4)))
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> int1, "b" -> int2)))
        val b = (2, Some(Map("a" -> int2, "b" -> int3, "c" -> int4)))
        val c = (3, None)
        for {
          _ <- Ns.i.intMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.intMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[Int] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> int1, "b" -> int2)))
        val b = (2, Some(Map("a" -> int2, "b" -> int3, "c" -> int4)))
        val c = (3, None)
        for {
          _ <- Ns.i.intMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.intMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.intMap_?("a").query.get.map(_ ==> List((1, Some(int1)), (2, Some(int2)), (3, None)))
          _ <- Ns.i.a1.intMap_?("b").query.get.map(_ ==> List((1, Some(int2)), (2, Some(int3)), (3, None)))
          _ <- Ns.i.a1.intMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(int4)), (3, None)))
        } yield ()
      }
    }


    "Tacit" - {
      // Use tacit map attribute to filter by keys/values of Maps
      // Use mandatory/optional map attributes to retrieve Maps/keys/values
      // Combine multiple map attributes if both retrieval and filtering is required

      // OBS: Note have tacit map attribute methods `apply` and `not` have OR semantics! 
      // (contrary to Set and Seq that have AND semantics)
      // This is to have the same semantics for searching by key (apply/not) and value (has/hasNo)
      // Also to avoid adding further restricted molecule keywords. 

      val (a1, b2) = ("a" -> int1, "b" -> int2)
      val (b3, c4) = ("b" -> int3, "c" -> int4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.intMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // intMap not asserted for i = 0
          _ <- Ns.i.a1.intMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.intMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.intMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.intMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.intMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.intMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intMap_(List("a", "_")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.intMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.intMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.intMap.intMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.intMap.intMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.intMap.intMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.intMap.intMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.intMap.intMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.intMap.intMap_("a", "_").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.intMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.intMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.intMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.intMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.intMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.intMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.intMap_.not(List("a", "_")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.intMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.intMap.intMap_.not("_").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.intMap.intMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.intMap.intMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.intMap.intMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.intMap.intMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.intMap.intMap_.not("a", "_").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.intMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.intMap_.has(int0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.intMap_.has(int1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.has(int2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.has(int3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.has(int4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.has(int0, int1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.has(int1, int2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.has(int2, int3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intMap_.has(int3, int4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.has(int4, int5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.has(int5, int6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.intMap_.has(List(int0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.intMap_.has(List(int1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.has(List(int2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.has(List(int3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.has(List(int4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.has(List(int0, int1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.has(List(int1, int2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.has(List(int2, int3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intMap_.has(List(int3, int4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.has(List(int4, int5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.has(List(int5, int6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.intMap_.has(List.empty[Int]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.intMap.intMap_.has(int0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.intMap.intMap_.has(int1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.intMap.intMap_.has(int2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.intMap.intMap_.has(int3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.intMap.intMap_.has(int4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.intMap.intMap_.has(int0, int1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.intMap.intMap_.has(int1, int2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.intMap.intMap_.has(int2, int3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.intMap.intMap_.has(int3, int4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.intMap.intMap_.has(int4, int5).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.intMap.intMap_.has(int5, int6).query.get.map(_ ==> List())
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.intMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.intMap_.hasNo(int0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intMap_.hasNo(int1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.hasNo(int2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.hasNo(int3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.hasNo(int4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.hasNo(int0, int1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.hasNo(int1, int2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.hasNo(int2, int3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intMap_.hasNo(int3, int4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.hasNo(int4, int5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.hasNo(int5, int6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.intMap_.hasNo(List(int0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intMap_.hasNo(List(int1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.hasNo(List(int2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.hasNo(List(int3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.hasNo(List(int4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.hasNo(List(int0, int1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.hasNo(List(int1, int2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intMap_.hasNo(List(int2, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intMap_.hasNo(List(int3, int4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.hasNo(List(int4, int5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intMap_.hasNo(List(int5, int6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.intMap_.hasNo(List.empty[Int]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.intMap.intMap_.hasNo(int0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.intMap.intMap_.hasNo(int1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.intMap.intMap_.hasNo(int2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.intMap.intMap_.hasNo(int3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.intMap.intMap_.hasNo(int4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.intMap.intMap_.hasNo(int0, int1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.intMap.intMap_.hasNo(int1, int2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.intMap.intMap_.hasNo(int2, int3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.intMap.intMap_.hasNo(int3, int4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.intMap.intMap_.hasNo(int4, int5).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.intMap.intMap_.hasNo(int5, int6).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
        } yield ()
      }
    }
  }
}