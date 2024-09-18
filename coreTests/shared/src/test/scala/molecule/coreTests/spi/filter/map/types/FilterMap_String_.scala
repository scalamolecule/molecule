// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.map.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterMap_String_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "Get map" - types { implicit conn =>
        val a = (1, Map("a" -> string1, "b" -> string2))
        val b = (2, Map("a" -> string2, "b" -> string3, "c" -> string4))
        for {
          _ <- Ns.i.stringMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.stringMap.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "Get value by key" - types { implicit conn =>
        // Applying a String key to a mandatory map attribute is a special case that corresponds to
        // calling `apply` on a Scala Map, like `val string: Int = someMapOfInt("someKey")`

        val a = (1, Map("a" -> string1, "b" -> string2))
        val b = (2, Map("a" -> string2, "b" -> string3, "c" -> string4))
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.stringMap.insert(List(a, b)).transact

          _ <- Ns.i.a1.stringMap("_").query.get.map(_ ==> Nil) // When no map is saved
          _ <- Ns.i.a1.stringMap("a").query.get.map(_ ==> List((1, string1), (2, string2)))
          _ <- Ns.i.a1.stringMap("b").query.get.map(_ ==> List((1, string2), (2, string3)))
          _ <- Ns.i.a1.stringMap("c").query.get.map(_ ==> List((2, string4)))
        } yield ()
      }
    }


    "Optional" - {

      "Get optional map" - types { implicit conn =>
        val a = (1, Some(Map("a" -> string1, "b" -> string2)))
        val b = (2, Some(Map("a" -> string2, "b" -> string3, "c" -> string4)))
        val c = (3, None)
        for {
          _ <- Ns.i.stringMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.stringMap_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "Get optional value by key" - types { implicit conn =>
        // Applying a String key to an optional map attribute is a special case that corresponds to
        // calling `get` on a Scala Map, like `val optInt: Option[String] = someMapOfInt.get("someKey")`

        val a = (1, Some(Map("a" -> string1, "b" -> string2)))
        val b = (2, Some(Map("a" -> string2, "b" -> string3, "c" -> string4)))
        val c = (3, None)
        for {
          _ <- Ns.i.stringMap_?.insert(a, b, c).transact

          _ <- Ns.i.a1.stringMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
          _ <- Ns.i.a1.stringMap_?("a").query.get.map(_ ==> List((1, Some(string1)), (2, Some(string2)), (3, None)))
          _ <- Ns.i.a1.stringMap_?("b").query.get.map(_ ==> List((1, Some(string2)), (2, Some(string3)), (3, None)))
          _ <- Ns.i.a1.stringMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(string4)), (3, None)))
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

      val (a1, b2) = ("a" -> string1, "b" -> string2)
      val (b3, c4) = ("b" -> string3, "c" -> string4)

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.stringMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // stringMap not asserted for i = 0
          _ <- Ns.i.a1.stringMap_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "contains key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.insert(0).transact // Entity without map attribute
          _ <- Ns.i.stringMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains this OR that key"
          _ <- Ns.i.a1.stringMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.stringMap_("a").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringMap_("b").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringMap_("c").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringMap_("a", "c").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringMap_("a", "_").query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.stringMap_(List("_")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.stringMap_(List("a")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringMap_(List("b")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringMap_(List("c")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringMap_(List("a", "_")).query.get.map(_ ==> List(1))

          // Empty Seq of keys matches nothing
          _ <- Ns.i.a1.stringMap_(List.empty[String]).query.get.map(_ ==> Nil)

          // Match entities without map attribute
          _ <- Ns.i.a1.stringMap_().query.get.map(_ ==> List(0))

          // Combine with retrieval
          _ <- Ns.i.a1.stringMap.stringMap_("_").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.stringMap.stringMap_("a").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.stringMap.stringMap_("b").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.stringMap.stringMap_("c").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.stringMap.stringMap_("a", "c").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.stringMap.stringMap_("a", "_").query.get.map(_ ==> List((1, Map(a1, b2))))
        } yield ()
      }


      "doesn't contain key(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.stringMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // OR semantics! (different from Set and Seq)

          // "Map contains neither this OR that key"
          _ <- Ns.i.a1.stringMap_.not("_").query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringMap_.not("a").query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.stringMap_.not("c").query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.stringMap_.not("a", "_").query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.stringMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringMap_.not(List("a")).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringMap_.not(List("b")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.stringMap_.not(List("c")).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringMap_.not(List("a", "c")).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.stringMap_.not(List("a", "_")).query.get.map(_ ==> List(2))

          // Negating empty Seq of keys matches all
          _ <- Ns.i.a1.stringMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.stringMap.stringMap_.not("_").query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.stringMap.stringMap_.not("a").query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.stringMap.stringMap_.not("b").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.stringMap.stringMap_.not("c").query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.stringMap.stringMap_.not("a", "c").query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.stringMap.stringMap_.not("a", "_").query.get.map(_ ==> List((2, Map(b3, c4))))
        } yield ()
      }


      "has value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.stringMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains this OR that value"
          _ <- Ns.i.a1.stringMap_.has(string0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.stringMap_.has(string1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringMap_.has(string2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringMap_.has(string3).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringMap_.has(string4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringMap_.has(string0, string1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringMap_.has(string1, string2).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringMap_.has(string2, string3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringMap_.has(string3, string4).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringMap_.has(string4, string5).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringMap_.has(string5, string6).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.stringMap_.has(List(string0)).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.stringMap_.has(List(string1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringMap_.has(List(string2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringMap_.has(List(string3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringMap_.has(List(string4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringMap_.has(List(string0, string1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringMap_.has(List(string1, string2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringMap_.has(List(string2, string3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringMap_.has(List(string3, string4)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringMap_.has(List(string4, string5)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringMap_.has(List(string5, string6)).query.get.map(_ ==> List())

          // Empty Seq of values matches nothing
          _ <- Ns.i.a1.stringMap_.has(List.empty[String]).query.get.map(_ ==> Nil)

          // Combine with retrieval
          _ <- Ns.i.a1.stringMap.stringMap_.has(string0).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.stringMap.stringMap_.has(string1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.stringMap.stringMap_.has(string2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.stringMap.stringMap_.has(string3).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.stringMap.stringMap_.has(string4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.stringMap.stringMap_.has(string0, string1).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.stringMap.stringMap_.has(string1, string2).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.stringMap.stringMap_.has(string2, string3).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.stringMap.stringMap_.has(string3, string4).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.stringMap.stringMap_.has(string4, string5).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.stringMap.stringMap_.has(string5, string6).query.get.map(_ ==> List())
        } yield ()
      }


      "doesn't have value(s)" - types { implicit conn =>
        for {
          _ <- Ns.i.stringMap.insert(List(
            (1, Map(a1, b2)),
            (2, Map(b3, c4)),
          )).transact

          // "Map contains neither this OR that value"
          _ <- Ns.i.a1.stringMap_.hasNo(string0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringMap_.hasNo(string1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringMap_.hasNo(string2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringMap_.hasNo(string3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringMap_.hasNo(string4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringMap_.hasNo(string0, string1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringMap_.hasNo(string1, string2).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringMap_.hasNo(string2, string3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringMap_.hasNo(string3, string4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringMap_.hasNo(string4, string5).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringMap_.hasNo(string5, string6).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.stringMap_.hasNo(List(string0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringMap_.hasNo(List(string1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringMap_.hasNo(List(string2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringMap_.hasNo(List(string3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringMap_.hasNo(List(string4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringMap_.hasNo(List(string0, string1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringMap_.hasNo(List(string1, string2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringMap_.hasNo(List(string2, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringMap_.hasNo(List(string3, string4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringMap_.hasNo(List(string4, string5)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringMap_.hasNo(List(string5, string6)).query.get.map(_ ==> List(1, 2))

          // Negating empty Seq of values matches all
          _ <- Ns.i.a1.stringMap_.hasNo(List.empty[String]).query.get.map(_ ==> List(1, 2))

          // Combine with retrieval
          _ <- Ns.i.a1.stringMap.stringMap_.hasNo(string0).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
          _ <- Ns.i.a1.stringMap.stringMap_.hasNo(string1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.stringMap.stringMap_.hasNo(string2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.stringMap.stringMap_.hasNo(string3).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.stringMap.stringMap_.hasNo(string4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.stringMap.stringMap_.hasNo(string0, string1).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.stringMap.stringMap_.hasNo(string1, string2).query.get.map(_ ==> List((2, Map(b3, c4))))
          _ <- Ns.i.a1.stringMap.stringMap_.hasNo(string2, string3).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.stringMap.stringMap_.hasNo(string3, string4).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.stringMap.stringMap_.hasNo(string4, string5).query.get.map(_ ==> List((1, Map(a1, b2))))
          _ <- Ns.i.a1.stringMap.stringMap_.hasNo(string5, string6).query.get.map(_ ==> List((1, Map(a1, b2)), (2, Map(b3, c4))))
        } yield ()
      }
    }
  }
}