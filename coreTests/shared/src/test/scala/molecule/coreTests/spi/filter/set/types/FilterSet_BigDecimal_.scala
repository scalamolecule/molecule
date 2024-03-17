// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_BigDecimal_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(bigDecimal1, bigDecimal2))
        val b = (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        for {
          _ <- Ns.i.bigDecimals.insert(List(a, b)).transact

          _ <- Ns.i.a1.bigDecimals.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(bigDecimal1, bigDecimal2))
        val b = (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        for {
          _ <- Ns.i.bigDecimals.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.bigDecimals(Set(bigDecimal1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals(Set(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.bigDecimals(Set(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigDecimals(Seq(Set(bigDecimal1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals(Seq(Set(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimals(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimals(Seq(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigDecimals(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals(Set.empty[BigDecimal], Set(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals(Set.empty[BigDecimal], Set.empty[BigDecimal]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals(Set.empty[BigDecimal]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals(Seq.empty[Set[BigDecimal]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals(Seq(Set.empty[BigDecimal])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.bigDecimals().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(bigDecimal1, bigDecimal2))
        val b = (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        for {
          _ <- Ns.i.bigDecimals.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimals.not(Set(bigDecimal1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.not(Set(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.bigDecimals.not(Set(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimals.not(Seq(Set(bigDecimal1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.not(Seq(Set(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.not(Seq(Set(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.not(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimals.not(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.not(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.not(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigDecimals.not(Seq(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.not(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.not(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.bigDecimals.not(Seq(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.not(Set.empty[BigDecimal]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.not(Seq.empty[Set[BigDecimal]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(bigDecimal1, bigDecimal2))
        val b = (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        for {
          _ <- Ns.i.bigDecimals.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.bigDecimals.has(bigDecimal0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.has(bigDecimal1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals.has(bigDecimal2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.has(bigDecimal3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigDecimals.has(Seq(bigDecimal0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.has(Seq(bigDecimal1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals.has(Seq(bigDecimal2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.has(Seq(bigDecimal3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigDecimals.has(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.has(bigDecimal1, bigDecimal3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.has(bigDecimal2, bigDecimal3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.has(bigDecimal1, bigDecimal2, bigDecimal3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimals.has(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.has(Seq(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.has(Seq(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.has(Seq(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigDecimals.has(Seq.empty[BigDecimal]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(bigDecimal1, bigDecimal2))
        val b = (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        for {
          _ <- Ns.i.bigDecimals.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.bigDecimals.hasNo(bigDecimal0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.hasNo(bigDecimal1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.hasNo(bigDecimal2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.hasNo(bigDecimal3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals.hasNo(bigDecimal4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals.hasNo(bigDecimal5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimals.hasNo(Seq(bigDecimal0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.hasNo(Seq(bigDecimal1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.hasNo(Seq(bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.hasNo(Seq(bigDecimal3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals.hasNo(Seq(bigDecimal4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals.hasNo(Seq(bigDecimal5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.bigDecimals.hasNo(bigDecimal1, bigDecimal2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.hasNo(bigDecimal1, bigDecimal3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.hasNo(bigDecimal1, bigDecimal4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.hasNo(bigDecimal1, bigDecimal5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigDecimals.hasNo(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.hasNo(Seq(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.hasNo(Seq(bigDecimal1, bigDecimal4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.hasNo(Seq(bigDecimal1, bigDecimal5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.bigDecimals.hasNo(Seq.empty[BigDecimal]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigDecimals.insert(List(
            (1, Set(bigDecimal1, bigDecimal2)),
            (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // bigDecimals not asserted for i = 0
          _ <- Ns.i.a1.bigDecimals_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.bigDecimals_?.insert(List(
            (0, None),
            (1, Some(Set(bigDecimal1, bigDecimal2))),
            (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.bigDecimals_().query.get.map(_ ==> List(0))

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.bigDecimals_(Set(bigDecimal1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_(Set(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.bigDecimals_(Set(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigDecimals_(Seq(Set(bigDecimal1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_(Seq(Set(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimals_(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimals_(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimals_(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigDecimals_(Seq(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimals_(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigDecimals_(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimals_(Set.empty[BigDecimal]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_(Seq.empty[Set[BigDecimal]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_(Seq(Set.empty[BigDecimal])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigDecimals.insert(List(
            (1, Set(bigDecimal1, bigDecimal2)),
            (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimals_.not(Set(bigDecimal1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimals_.not(Set(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.bigDecimals_.not(Set(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigDecimals_.not(Seq(Set(bigDecimal1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimals_.not(Seq(Set(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimals_.not(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimals_.not(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimals_.not(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimals_.not(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigDecimals_.not(Seq(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimals_.not(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimals_.not(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.bigDecimals_.not(Seq(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimals_.not(Set.empty[BigDecimal]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimals_.not(Seq.empty[Set[BigDecimal]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigDecimals.insert(List(
            (1, Set(bigDecimal1, bigDecimal2)),
            (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.bigDecimals_.has(bigDecimal0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.has(bigDecimal1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimals_.has(bigDecimal2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimals_.has(bigDecimal3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.bigDecimals_.has(Seq(bigDecimal0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.has(Seq(bigDecimal1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimals_.has(Seq(bigDecimal2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimals_.has(Seq(bigDecimal3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigDecimals_.has(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimals_.has(bigDecimal1, bigDecimal3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimals_.has(bigDecimal2, bigDecimal3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimals_.has(bigDecimal1, bigDecimal2, bigDecimal3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigDecimals_.has(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimals_.has(Seq(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimals_.has(Seq(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimals_.has(Seq(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigDecimals_.has(Seq.empty[BigDecimal]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigDecimals.insert(List(
            (1, Set(bigDecimal1, bigDecimal2)),
            (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.bigDecimals_.hasNo(bigDecimal0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimals_.hasNo(bigDecimal1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimals_.hasNo(bigDecimal2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.hasNo(bigDecimal3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimals_.hasNo(bigDecimal4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimals_.hasNo(bigDecimal5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigDecimals_.hasNo(Seq(bigDecimal0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimals_.hasNo(Seq(bigDecimal1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimals_.hasNo(Seq(bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.hasNo(Seq(bigDecimal3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimals_.hasNo(Seq(bigDecimal4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimals_.hasNo(Seq(bigDecimal5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.bigDecimals_.hasNo(bigDecimal1, bigDecimal2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.hasNo(bigDecimal1, bigDecimal3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.hasNo(bigDecimal1, bigDecimal4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.hasNo(bigDecimal1, bigDecimal5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.bigDecimals_.hasNo(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.hasNo(Seq(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.hasNo(Seq(bigDecimal1, bigDecimal4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.hasNo(Seq(bigDecimal1, bigDecimal5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.bigDecimals_.hasNo(Seq.empty[BigDecimal]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(bigDecimal1, bigDecimal2)))
        val b = (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigDecimals_?.insert(a, b, c).transact

          _ <- Ns.i.a1.bigDecimals_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no bigDecimal value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.bigDecimals_?.query.get.map(_ ==> List(
            (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(bigDecimal1, bigDecimal2)))
        val b = (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigDecimals_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.bigDecimals_?(Some(Set(bigDecimal1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?(Some(Set(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.bigDecimals_?(Some(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(Set(bigDecimal1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(Set(bigDecimal1, bigDecimal2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigDecimals_?(Some(Set.empty[BigDecimal])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq.empty[Set[BigDecimal]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(Set.empty[BigDecimal]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.bigDecimals_?(Option.empty[Set[BigDecimal]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigDecimals_?(Option.empty[Seq[Set[BigDecimal]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(bigDecimal1, bigDecimal2)))
        val b = (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigDecimals_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Set(bigDecimal1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Set(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1, bigDecimal2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)))).query.get.map(_ ==> List())

          // Empty Sets are ignored
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Set.empty[BigDecimal])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq.empty[Set[BigDecimal]])).query.get.map(_ ==> List(a, b))

          // Negation of None matches all asserted
          _ <- Ns.i.a1.bigDecimals_?.not(Option.empty[Set[BigDecimal]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.not(Option.empty[Seq[Set[BigDecimal]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(bigDecimal1, bigDecimal2)))
        val b = (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigDecimals_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.bigDecimals_?.has(Some(bigDecimal0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.has(Some(bigDecimal1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?.has(Some(bigDecimal2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.has(Some(bigDecimal3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigDecimals_?.has(Some(Seq(bigDecimal0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.has(Some(Seq(bigDecimal1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?.has(Some(Seq(bigDecimal2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.has(Some(Seq(bigDecimal3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigDecimals_?.has(Some(Seq(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.has(Some(Seq(bigDecimal1, bigDecimal3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.has(Some(Seq(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.has(Some(Seq(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigDecimals_?.has(Some(Seq.empty[BigDecimal])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.bigDecimals_?.has(Option.empty[BigDecimal]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigDecimals_?.has(Option.empty[Seq[BigDecimal]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(bigDecimal1, bigDecimal2)))
        val b = (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigDecimals_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.bigDecimals_?.hasNo(Some(bigDecimal0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.hasNo(Some(bigDecimal1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_?.hasNo(Some(bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.hasNo(Some(bigDecimal3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?.hasNo(Some(bigDecimal4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?.hasNo(Some(bigDecimal5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimals_?.hasNo(Some(Seq(bigDecimal0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.hasNo(Some(Seq(bigDecimal1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_?.hasNo(Some(Seq(bigDecimal2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.hasNo(Some(Seq(bigDecimal3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?.hasNo(Some(Seq(bigDecimal4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?.hasNo(Some(Seq(bigDecimal5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.bigDecimals_?.hasNo(Some(Seq(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.hasNo(Some(Seq(bigDecimal1, bigDecimal3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.hasNo(Some(Seq(bigDecimal1, bigDecimal4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.hasNo(Some(Seq(bigDecimal1, bigDecimal5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.bigDecimals_?.hasNo(Some(Seq.empty[BigDecimal])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.bigDecimals_?.hasNo(Option.empty[BigDecimal]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.hasNo(Option.empty[Seq[BigDecimal]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}