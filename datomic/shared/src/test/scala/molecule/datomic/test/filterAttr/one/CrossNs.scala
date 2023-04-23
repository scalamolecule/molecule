package molecule.datomic.test.filterAttr.one

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.test.filterAttr.one.Adjacent.types
import molecule.datomic.test.filterAttr.set.CrossNs.{a, b, c, types}
import utest._

object CrossNs extends DatomicTestSuite {

  override lazy val tests = Tests {

    "equal (apply)" - types { implicit conn =>
      for {
        _ <- Ns.s.i.Ref.int.insert(
          ("a", 1, 2),
          ("b", 3, 3),
          ("c", 5, 4),
        ).transact

        _ <- Ns.s.i(Ref.int_).Ref.int.query.get.map(_ ==> List(("b", 3, 3)))
        _ <- Ns.s.i(Ref.int_).Ref.int_.query.get.map(_ ==> List(("b", 3))) // Ns.i
        _ <- Ns.s.i_(Ref.int_).Ref.int.query.get.map(_ ==> List(("b", 3))) // Ns.int
        _ <- Ns.s.i_(Ref.int_).Ref.int_.query.get.map(_ ==> List("b"))

        // Filter compare attribute itself
        _ <- Ns.s.i(Ref.int_).Ref.int(3).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- Ns.s.i(Ref.int_).Ref.int.not(3).query.get.map(_ ==> List())
        _ <- Ns.s.i(Ref.int_).Ref.int.>(3).query.get.map(_ ==> List())
        _ <- Ns.s.i(Ref.int_).Ref.int.>=(3).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- Ns.s.i(Ref.int_).Ref.int.<(3).query.get.map(_ ==> List())
        _ <- Ns.s.i(Ref.int_).Ref.int.<=(3).query.get.map(_ ==> List(("b", 3, 3)))

        _ <- Ns.s.i(Ref.int_).Ref.int_(3).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i(Ref.int_).Ref.int_.not(3).query.get.map(_ ==> List())
        _ <- Ns.s.i(Ref.int_).Ref.int_.>(3).query.get.map(_ ==> List())
        _ <- Ns.s.i(Ref.int_).Ref.int_.>=(3).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i(Ref.int_).Ref.int_.<(3).query.get.map(_ ==> List())
        _ <- Ns.s.i(Ref.int_).Ref.int_.<=(3).query.get.map(_ ==> List(("b", 3)))

        _ <- Ns.s.i_(Ref.int_).Ref.int(3).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i_(Ref.int_).Ref.int.not(3).query.get.map(_ ==> List())
        _ <- Ns.s.i_(Ref.int_).Ref.int.>(3).query.get.map(_ ==> List())
        _ <- Ns.s.i_(Ref.int_).Ref.int.>=(3).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i_(Ref.int_).Ref.int.<(3).query.get.map(_ ==> List())
        _ <- Ns.s.i_(Ref.int_).Ref.int.<=(3).query.get.map(_ ==> List(("b", 3)))

        _ <- Ns.s.i_(Ref.int_).Ref.int_(3).query.get.map(_ ==> List("b"))
        _ <- Ns.s.i_(Ref.int_).Ref.int_.not(3).query.get.map(_ ==> List())
        _ <- Ns.s.i_(Ref.int_).Ref.int_.>(3).query.get.map(_ ==> List())
        _ <- Ns.s.i_(Ref.int_).Ref.int_.>=(3).query.get.map(_ ==> List("b"))
        _ <- Ns.s.i_(Ref.int_).Ref.int_.<(3).query.get.map(_ ==> List())
        _ <- Ns.s.i_(Ref.int_).Ref.int_.<=(3).query.get.map(_ ==> List("b"))


        // Pointing backwards

        _ <- Ns.s.i.Ref.int(Ns.i_).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- Ns.s.i.Ref.int_(Ns.i_).query.get.map(_ ==> List(("b", 3))) // Ns.i
        _ <- Ns.s.i_.Ref.int(Ns.i_).query.get.map(_ ==> List(("b", 3))) // Ns.int
        _ <- Ns.s.i_.Ref.int_(Ns.i_).query.get.map(_ ==> List("b"))

        // Filter compare attribute itself
        _ <- Ns.s.i(3).Ref.int(Ns.i_).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- Ns.s.i.not(3).Ref.int(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.>(3).Ref.int(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.>=(3).Ref.int(Ns.i_).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- Ns.s.i.<(3).Ref.int(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.<=(3).Ref.int(Ns.i_).query.get.map(_ ==> List(("b", 3, 3)))

        _ <- Ns.s.i(3).Ref.int_(Ns.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i.not(3).Ref.int_(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.>(3).Ref.int_(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.>=(3).Ref.int_(Ns.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i.<(3).Ref.int_(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.<=(3).Ref.int_(Ns.i_).query.get.map(_ ==> List(("b", 3)))

        _ <- Ns.s.i_(3).Ref.int(Ns.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i_.not(3).Ref.int(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>(3).Ref.int(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>=(3).Ref.int(Ns.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i_.<(3).Ref.int(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<=(3).Ref.int(Ns.i_).query.get.map(_ ==> List(("b", 3)))

        _ <- Ns.s.i_(3).Ref.int_(Ns.i_).query.get.map(_ ==> List("b"))
        _ <- Ns.s.i_.not(3).Ref.int_(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>(3).Ref.int_(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>=(3).Ref.int_(Ns.i_).query.get.map(_ ==> List("b"))
        _ <- Ns.s.i_.<(3).Ref.int_(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<=(3).Ref.int_(Ns.i_).query.get.map(_ ==> List("b"))
      } yield ()
    }


    "not equal" - types { implicit conn =>
      for {
        _ <- Ns.s.i.Ref.int.insert(
          ("a", 1, 2),
          ("b", 3, 3),
          ("c", 5, 4),
        ).transact

        _ <- Ns.s.a1.i.not(Ref.int_).Ref.int.query.get.map(_ ==> List(("a", 1, 2), ("c", 5, 4)))
        _ <- Ns.s.a1.i.not(Ref.int_).Ref.int_.query.get.map(_ ==> List(("a", 1), ("c", 5)))
        _ <- Ns.s.a1.i_.not(Ref.int_).Ref.int.query.get.map(_ ==> List(("a", 2), ("c", 4)))
        _ <- Ns.s.a1.i_.not(Ref.int_).Ref.int_.query.get.map(_ ==> List("a", "c"))

        // Filter compare attribute itself
        _ <- Ns.s.i.not(Ref.int_).Ref.int(2).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- Ns.s.i.not(Ref.int_).Ref.int.not(2).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- Ns.s.i.not(Ref.int_).Ref.int.>(4).query.get.map(_ ==> List())
        _ <- Ns.s.i.not(Ref.int_).Ref.int.>=(4).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- Ns.s.i.not(Ref.int_).Ref.int.<(2).query.get.map(_ ==> List())
        _ <- Ns.s.i.not(Ref.int_).Ref.int.<=(2).query.get.map(_ ==> List(("a", 1, 2)))

        _ <- Ns.s.i.not(Ref.int_).Ref.int_(2).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.i.not(Ref.int_).Ref.int_.not(2).query.get.map(_ ==> List(("c", 5)))
        _ <- Ns.s.i.not(Ref.int_).Ref.int_.>(4).query.get.map(_ ==> List())
        _ <- Ns.s.i.not(Ref.int_).Ref.int_.>=(4).query.get.map(_ ==> List(("c", 5)))
        _ <- Ns.s.i.not(Ref.int_).Ref.int_.<(2).query.get.map(_ ==> List())
        _ <- Ns.s.i.not(Ref.int_).Ref.int_.<=(2).query.get.map(_ ==> List(("a", 1)))

        _ <- Ns.s.i_.not(Ref.int_).Ref.int(2).query.get.map(_ ==> List(("a", 2)))
        _ <- Ns.s.i_.not(Ref.int_).Ref.int.not(2).query.get.map(_ ==> List(("c", 4)))
        _ <- Ns.s.i_.not(Ref.int_).Ref.int.>(4).query.get.map(_ ==> List())
        _ <- Ns.s.i_.not(Ref.int_).Ref.int.>=(4).query.get.map(_ ==> List(("c", 4)))
        _ <- Ns.s.i_.not(Ref.int_).Ref.int.<(2).query.get.map(_ ==> List())
        _ <- Ns.s.i_.not(Ref.int_).Ref.int.<=(2).query.get.map(_ ==> List(("a", 2)))

        _ <- Ns.s.i_.not(Ref.int_).Ref.int_(2).query.get.map(_ ==> List("a"))
        _ <- Ns.s.i_.not(Ref.int_).Ref.int_.not(2).query.get.map(_ ==> List("c"))
        _ <- Ns.s.i_.not(Ref.int_).Ref.int_.>(4).query.get.map(_ ==> List())
        _ <- Ns.s.i_.not(Ref.int_).Ref.int_.>=(4).query.get.map(_ ==> List("c"))
        _ <- Ns.s.i_.not(Ref.int_).Ref.int_.<(2).query.get.map(_ ==> List())
        _ <- Ns.s.i_.not(Ref.int_).Ref.int_.<=(2).query.get.map(_ ==> List("a"))


        // Pointing backwards

        _ <- Ns.s.a1.i.Ref.int.not(Ns.i_).query.get.map(_ ==> List(("a", 1, 2), ("c", 5, 4)))
        _ <- Ns.s.a1.i.Ref.int_.not(Ns.i_).query.get.map(_ ==> List(("a", 1), ("c", 5)))
        _ <- Ns.s.a1.i_.Ref.int.not(Ns.i_).query.get.map(_ ==> List(("a", 2), ("c", 4)))
        _ <- Ns.s.a1.i_.Ref.int_.not(Ns.i_).query.get.map(_ ==> List("a", "c"))

        // Filter compare attribute itself
        _ <- Ns.s.i(1).Ref.int.not(Ns.i_).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- Ns.s.i.not(1).Ref.int.not(Ns.i_).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- Ns.s.i.>(5).Ref.int.not(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.>=(5).Ref.int.not(Ns.i_).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- Ns.s.i.<(1).Ref.int.not(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.<=(1).Ref.int.not(Ns.i_).query.get.map(_ ==> List(("a", 1, 2)))

        _ <- Ns.s.i(1).Ref.int_.not(Ns.i_).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.i.not(1).Ref.int_.not(Ns.i_).query.get.map(_ ==> List(("c", 5)))
        _ <- Ns.s.i.>(5).Ref.int_.not(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.>=(5).Ref.int_.not(Ns.i_).query.get.map(_ ==> List(("c", 5)))
        _ <- Ns.s.i.<(1).Ref.int_.not(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.<=(1).Ref.int_.not(Ns.i_).query.get.map(_ ==> List(("a", 1)))

        _ <- Ns.s.i_(1).Ref.int.not(Ns.i_).query.get.map(_ ==> List(("a", 2)))
        _ <- Ns.s.i_.not(1).Ref.int.not(Ns.i_).query.get.map(_ ==> List(("c", 4)))
        _ <- Ns.s.i_.>(5).Ref.int.not(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>=(5).Ref.int.not(Ns.i_).query.get.map(_ ==> List(("c", 4)))
        _ <- Ns.s.i_.<(1).Ref.int.not(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<=(1).Ref.int.not(Ns.i_).query.get.map(_ ==> List(("a", 2)))

        _ <- Ns.s.i_(1).Ref.int_.not(Ns.i_).query.get.map(_ ==> List("a"))
        _ <- Ns.s.i_.not(1).Ref.int_.not(Ns.i_).query.get.map(_ ==> List("c"))
        _ <- Ns.s.i_.>(5).Ref.int_.not(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>=(5).Ref.int_.not(Ns.i_).query.get.map(_ ==> List("c"))
        _ <- Ns.s.i_.<(1).Ref.int_.not(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<=(1).Ref.int_.not(Ns.i_).query.get.map(_ ==> List("a"))
      } yield ()
    }


    "<" - types { implicit conn =>
      for {
        _ <- Ns.s.i.Ref.int.insert(
          ("a", 1, 2),
          ("b", 3, 3),
          ("c", 5, 4),
        ).transact

        _ <- Ns.s.i.<(Ref.int_).Ref.int.query.get.map(_ ==> List(("a", 1, 2)))
        _ <- Ns.s.i.<(Ref.int_).Ref.int_.query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.i_.<(Ref.int_).Ref.int.query.get.map(_ ==> List(("a", 2)))
        _ <- Ns.s.i_.<(Ref.int_).Ref.int_.query.get.map(_ ==> List("a"))

        // Filter compare attribute itself
        _ <- Ns.s.i.<(Ref.int_).Ref.int(2).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- Ns.s.i.<(Ref.int_).Ref.int.not(2).query.get.map(_ ==> List())
        _ <- Ns.s.i.<(Ref.int_).Ref.int.>(2).query.get.map(_ ==> List())
        _ <- Ns.s.i.<(Ref.int_).Ref.int.>=(2).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- Ns.s.i.<(Ref.int_).Ref.int.<(2).query.get.map(_ ==> List())
        _ <- Ns.s.i.<(Ref.int_).Ref.int.<=(2).query.get.map(_ ==> List(("a", 1, 2)))

        _ <- Ns.s.i.<(Ref.int_).Ref.int_(2).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.i.<(Ref.int_).Ref.int_.not(2).query.get.map(_ ==> List())
        _ <- Ns.s.i.<(Ref.int_).Ref.int_.>(2).query.get.map(_ ==> List())
        _ <- Ns.s.i.<(Ref.int_).Ref.int_.>=(2).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.i.<(Ref.int_).Ref.int_.<(2).query.get.map(_ ==> List())
        _ <- Ns.s.i.<(Ref.int_).Ref.int_.<=(2).query.get.map(_ ==> List(("a", 1)))

        _ <- Ns.s.i_.<(Ref.int_).Ref.int(2).query.get.map(_ ==> List(("a", 2)))
        _ <- Ns.s.i_.<(Ref.int_).Ref.int.not(2).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<(Ref.int_).Ref.int.>(2).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<(Ref.int_).Ref.int.>=(2).query.get.map(_ ==> List(("a", 2)))
        _ <- Ns.s.i_.<(Ref.int_).Ref.int.<(2).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<(Ref.int_).Ref.int.<=(2).query.get.map(_ ==> List(("a", 2)))

        _ <- Ns.s.i_.<(Ref.int_).Ref.int_(2).query.get.map(_ ==> List("a"))
        _ <- Ns.s.i_.<(Ref.int_).Ref.int_.not(2).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<(Ref.int_).Ref.int_.>(2).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<(Ref.int_).Ref.int_.>=(2).query.get.map(_ ==> List("a"))
        _ <- Ns.s.i_.<(Ref.int_).Ref.int_.<(2).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<(Ref.int_).Ref.int_.<=(2).query.get.map(_ ==> List("a"))


        // Pointing backwards

        _ <- Ns.s.i.Ref.int.<(Ns.i_).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- Ns.s.i.Ref.int_.<(Ns.i_).query.get.map(_ ==> List(("c", 5)))
        _ <- Ns.s.i_.Ref.int.<(Ns.i_).query.get.map(_ ==> List(("c", 4)))
        _ <- Ns.s.i_.Ref.int_.<(Ns.i_).query.get.map(_ ==> List("c"))

        // Filter compare attribute itself
        _ <- Ns.s.i(5).Ref.int.<(Ns.i_).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- Ns.s.i.not(5).Ref.int.<(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.>(5).Ref.int.<(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.>=(5).Ref.int.<(Ns.i_).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- Ns.s.i.<(5).Ref.int.<(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.<=(5).Ref.int.<(Ns.i_).query.get.map(_ ==> List(("c", 5, 4)))

        _ <- Ns.s.i(5).Ref.int_.<(Ns.i_).query.get.map(_ ==> List(("c", 5)))
        _ <- Ns.s.i.not(5).Ref.int_.<(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.>(5).Ref.int_.<(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.>=(5).Ref.int_.<(Ns.i_).query.get.map(_ ==> List(("c", 5)))
        _ <- Ns.s.i.<(5).Ref.int_.<(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.<=(5).Ref.int_.<(Ns.i_).query.get.map(_ ==> List(("c", 5)))

        _ <- Ns.s.i_(5).Ref.int.<(Ns.i_).query.get.map(_ ==> List(("c", 4)))
        _ <- Ns.s.i_.not(5).Ref.int.<(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>(5).Ref.int.<(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>=(5).Ref.int.<(Ns.i_).query.get.map(_ ==> List(("c", 4)))
        _ <- Ns.s.i_.<(5).Ref.int.<(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<=(5).Ref.int.<(Ns.i_).query.get.map(_ ==> List(("c", 4)))

        _ <- Ns.s.i_(5).Ref.int_.<(Ns.i_).query.get.map(_ ==> List("c"))
        _ <- Ns.s.i_.not(5).Ref.int_.<(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>(5).Ref.int_.<(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>=(5).Ref.int_.<(Ns.i_).query.get.map(_ ==> List("c"))
        _ <- Ns.s.i_.<(5).Ref.int_.<(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<=(5).Ref.int_.<(Ns.i_).query.get.map(_ ==> List("c"))
      } yield ()
    }


    "<=" - types { implicit conn =>
      for {
        _ <- Ns.s.i.Ref.int.insert(
          ("a", 1, 2),
          ("b", 3, 3),
          ("c", 5, 4),
        ).transact

        _ <- Ns.s.a1.i.<=(Ref.int_).Ref.int.query.get.map(_ ==> List(("a", 1, 2), ("b", 3, 3)))
        _ <- Ns.s.a1.i.<=(Ref.int_).Ref.int_.query.get.map(_ ==> List(("a", 1), ("b", 3)))
        _ <- Ns.s.a1.i_.<=(Ref.int_).Ref.int.query.get.map(_ ==> List(("a", 2), ("b", 3)))
        _ <- Ns.s.a1.i_.<=(Ref.int_).Ref.int_.query.get.map(_ ==> List("a", "b"))

        // Filter compare attribute itself
        _ <- Ns.s.i.<=(Ref.int_).Ref.int(2).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- Ns.s.i.<=(Ref.int_).Ref.int.not(2).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- Ns.s.i.<=(Ref.int_).Ref.int.>(2).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- Ns.s.i.<=(Ref.int_).Ref.int.>=(2).query.get.map(_ ==> List(("a", 1, 2), ("b", 3, 3)))
        _ <- Ns.s.i.<=(Ref.int_).Ref.int.<(2).query.get.map(_ ==> List())
        _ <- Ns.s.i.<=(Ref.int_).Ref.int.<=(2).query.get.map(_ ==> List(("a", 1, 2)))

        _ <- Ns.s.i.<=(Ref.int_).Ref.int_(2).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.i.<=(Ref.int_).Ref.int_.not(2).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i.<=(Ref.int_).Ref.int_.>(2).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i.<=(Ref.int_).Ref.int_.>=(2).query.get.map(_ ==> List(("a", 1), ("b", 3)))
        _ <- Ns.s.i.<=(Ref.int_).Ref.int_.<(2).query.get.map(_ ==> List())
        _ <- Ns.s.i.<=(Ref.int_).Ref.int_.<=(2).query.get.map(_ ==> List(("a", 1)))

        _ <- Ns.s.i_.<=(Ref.int_).Ref.int(2).query.get.map(_ ==> List(("a", 2)))
        _ <- Ns.s.i_.<=(Ref.int_).Ref.int.not(2).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i_.<=(Ref.int_).Ref.int.>(2).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i_.<=(Ref.int_).Ref.int.>=(2).query.get.map(_ ==> List(("a", 2), ("b", 3)))
        _ <- Ns.s.i_.<=(Ref.int_).Ref.int.<(2).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<=(Ref.int_).Ref.int.<=(2).query.get.map(_ ==> List(("a", 2)))

        _ <- Ns.s.i_.<=(Ref.int_).Ref.int_(2).query.get.map(_ ==> List("a"))
        _ <- Ns.s.i_.<=(Ref.int_).Ref.int_.not(2).query.get.map(_ ==> List("b"))
        _ <- Ns.s.i_.<=(Ref.int_).Ref.int_.>(2).query.get.map(_ ==> List("b"))
        _ <- Ns.s.i_.<=(Ref.int_).Ref.int_.>=(2).query.get.map(_ ==> List("a", "b"))
        _ <- Ns.s.i_.<=(Ref.int_).Ref.int_.<(2).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<=(Ref.int_).Ref.int_.<=(2).query.get.map(_ ==> List("a"))


        // Pointing backwards

        _ <- Ns.s.a1.i.Ref.int.<=(Ns.i_).query.get.map(_ ==> List(("b", 3, 3), ("c", 5, 4)))
        _ <- Ns.s.a1.i.Ref.int_.<=(Ns.i_).query.get.map(_ ==> List(("b", 3), ("c", 5)))
        _ <- Ns.s.a1.i_.Ref.int.<=(Ns.i_).query.get.map(_ ==> List(("b", 3), ("c", 4)))
        _ <- Ns.s.a1.i_.Ref.int_.<=(Ns.i_).query.get.map(_ ==> List("b", "c"))

        // Filter compare attribute itself
        _ <- Ns.s.i(5).Ref.int.<=(Ns.i_).query.get.map(_    ==> List(("c", 5, 4)))
        _ <- Ns.s.i.not(5).Ref.int.<=(Ns.i_).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- Ns.s.i.>(5).Ref.int.<=(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.>=(5).Ref.int.<=(Ns.i_).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- Ns.s.i.<(5).Ref.int.<=(Ns.i_).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- Ns.s.i.<=(5).Ref.int.<=(Ns.i_).query.get.map(_ ==> List(("b", 3, 3), ("c", 5, 4)))

        _ <- Ns.s.i(5).Ref.int_.<=(Ns.i_).query.get.map(_    ==> List(("c", 5)))
        _ <- Ns.s.i.not(5).Ref.int_.<=(Ns.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i.>(5).Ref.int_.<=(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.>=(5).Ref.int_.<=(Ns.i_).query.get.map(_ ==> List(("c", 5)))
        _ <- Ns.s.i.<(5).Ref.int_.<=(Ns.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i.<=(5).Ref.int_.<=(Ns.i_).query.get.map(_ ==> List(("b", 3), ("c", 5)))

        _ <- Ns.s.i_(5).Ref.int.<=(Ns.i_).query.get.map(_    ==> List(("c", 4)))
        _ <- Ns.s.i_.not(5).Ref.int.<=(Ns.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i_.>(5).Ref.int.<=(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>=(5).Ref.int.<=(Ns.i_).query.get.map(_ ==> List(("c", 4)))
        _ <- Ns.s.i_.<(5).Ref.int.<=(Ns.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i_.<=(5).Ref.int.<=(Ns.i_).query.get.map(_ ==> List(("b", 3), ("c", 4)))

        _ <- Ns.s.i_(5).Ref.int_.<=(Ns.i_).query.get.map(_    ==> List("c"))
        _ <- Ns.s.i_.not(5).Ref.int_.<=(Ns.i_).query.get.map(_ ==> List("b"))
        _ <- Ns.s.i_.>(5).Ref.int_.<=(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>=(5).Ref.int_.<=(Ns.i_).query.get.map(_ ==> List("c"))
        _ <- Ns.s.i_.<(5).Ref.int_.<=(Ns.i_).query.get.map(_ ==> List("b"))
        _ <- Ns.s.i_.<=(5).Ref.int_.<=(Ns.i_).query.get.map(_ ==> List("b", "c"))
      } yield ()
    }


    ">" - types { implicit conn =>
      for {
        _ <- Ns.s.i.Ref.int.insert(
          ("a", 1, 2),
          ("b", 3, 3),
          ("c", 5, 4),
        ).transact

        _ <- Ns.s.i.>(Ref.int_).Ref.int.query.get.map(_ ==> List(("c", 5, 4)))
        _ <- Ns.s.i.>(Ref.int_).Ref.int_.query.get.map(_ ==> List(("c", 5)))
        _ <- Ns.s.i_.>(Ref.int_).Ref.int.query.get.map(_ ==> List(("c", 4)))
        _ <- Ns.s.i_.>(Ref.int_).Ref.int_.query.get.map(_ ==> List("c"))

        // Filter compare attribute itself
        _ <- Ns.s.i.>(Ref.int_).Ref.int(4).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- Ns.s.i.>(Ref.int_).Ref.int.not(4).query.get.map(_ ==> List())
        _ <- Ns.s.i.>(Ref.int_).Ref.int.>(4).query.get.map(_ ==> List())
        _ <- Ns.s.i.>(Ref.int_).Ref.int.>=(4).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- Ns.s.i.>(Ref.int_).Ref.int.<(4).query.get.map(_ ==> List())
        _ <- Ns.s.i.>(Ref.int_).Ref.int.<=(4).query.get.map(_ ==> List(("c", 5, 4)))

        _ <- Ns.s.i.>(Ref.int_).Ref.int_(4).query.get.map(_ ==> List(("c", 5)))
        _ <- Ns.s.i.>(Ref.int_).Ref.int_.not(4).query.get.map(_ ==> List())
        _ <- Ns.s.i.>(Ref.int_).Ref.int_.>(4).query.get.map(_ ==> List())
        _ <- Ns.s.i.>(Ref.int_).Ref.int_.>=(4).query.get.map(_ ==> List(("c", 5)))
        _ <- Ns.s.i.>(Ref.int_).Ref.int_.<(4).query.get.map(_ ==> List())
        _ <- Ns.s.i.>(Ref.int_).Ref.int_.<=(4).query.get.map(_ ==> List(("c", 5)))

        _ <- Ns.s.i_.>(Ref.int_).Ref.int(4).query.get.map(_ ==> List(("c", 4)))
        _ <- Ns.s.i_.>(Ref.int_).Ref.int.not(4).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>(Ref.int_).Ref.int.>(4).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>(Ref.int_).Ref.int.>=(4).query.get.map(_ ==> List(("c", 4)))
        _ <- Ns.s.i_.>(Ref.int_).Ref.int.<(4).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>(Ref.int_).Ref.int.<=(4).query.get.map(_ ==> List(("c", 4)))

        _ <- Ns.s.i_.>(Ref.int_).Ref.int_(4).query.get.map(_ ==> List("c"))
        _ <- Ns.s.i_.>(Ref.int_).Ref.int_.not(4).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>(Ref.int_).Ref.int_.>(4).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>(Ref.int_).Ref.int_.>=(4).query.get.map(_ ==> List("c"))
        _ <- Ns.s.i_.>(Ref.int_).Ref.int_.<(4).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>(Ref.int_).Ref.int_.<=(4).query.get.map(_ ==> List("c"))


        // Pointing backwards

        _ <- Ns.s.i.Ref.int.>(Ns.i_).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- Ns.s.i.Ref.int_.>(Ns.i_).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.i_.Ref.int.>(Ns.i_).query.get.map(_ ==> List(("a", 2)))
        _ <- Ns.s.i_.Ref.int_.>(Ns.i_).query.get.map(_ ==> List("a"))

        // Filter compare attribute itself
        _ <- Ns.s.i(1).Ref.int.>(Ns.i_).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- Ns.s.i.not(1).Ref.int.>(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.>(1).Ref.int.>(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.>=(1).Ref.int.>(Ns.i_).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- Ns.s.i.<(1).Ref.int.>(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.<=(1).Ref.int.>(Ns.i_).query.get.map(_ ==> List(("a", 1, 2)))

        _ <- Ns.s.i(1).Ref.int_.>(Ns.i_).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.i.not(1).Ref.int_.>(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.>(1).Ref.int_.>(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.>=(1).Ref.int_.>(Ns.i_).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.i.<(1).Ref.int_.>(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i.<=(1).Ref.int_.>(Ns.i_).query.get.map(_ ==> List(("a", 1)))

        _ <- Ns.s.i_(1).Ref.int.>(Ns.i_).query.get.map(_ ==> List(("a", 2)))
        _ <- Ns.s.i_.not(1).Ref.int.>(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>(1).Ref.int.>(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>=(1).Ref.int.>(Ns.i_).query.get.map(_ ==> List(("a", 2)))
        _ <- Ns.s.i_.<(1).Ref.int.>(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<=(1).Ref.int.>(Ns.i_).query.get.map(_ ==> List(("a", 2)))

        _ <- Ns.s.i_(1).Ref.int_.>(Ns.i_).query.get.map(_ ==> List("a"))
        _ <- Ns.s.i_.not(1).Ref.int_.>(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>(1).Ref.int_.>(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>=(1).Ref.int_.>(Ns.i_).query.get.map(_ ==> List("a"))
        _ <- Ns.s.i_.<(1).Ref.int_.>(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<=(1).Ref.int_.>(Ns.i_).query.get.map(_ ==> List("a"))
      } yield ()
    }


    ">=" - types { implicit conn =>
      for {
        _ <- Ns.s.i.Ref.int.insert(
          ("a", 1, 2),
          ("b", 3, 3),
          ("c", 5, 4),
        ).transact

        _ <- Ns.s.a1.i.>=(Ref.int_).Ref.int.query.get.map(_ ==> List(("b", 3, 3), ("c", 5, 4)))
        _ <- Ns.s.a1.i.>=(Ref.int_).Ref.int_.query.get.map(_ ==> List(("b", 3), ("c", 5)))
        _ <- Ns.s.a1.i_.>=(Ref.int_).Ref.int.query.get.map(_ ==> List(("b", 3), ("c", 4)))
        _ <- Ns.s.a1.i_.>=(Ref.int_).Ref.int_.query.get.map(_ ==> List("b", "c"))

        // Filter compare attribute itself
        _ <- Ns.s.a1.i.>=(Ref.int_).Ref.int(3).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- Ns.s.a1.i.>=(Ref.int_).Ref.int.not(3).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- Ns.s.a1.i.>=(Ref.int_).Ref.int.>(3).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- Ns.s.a1.i.>=(Ref.int_).Ref.int.>=(3).query.get.map(_ ==> List(("b", 3, 3), ("c", 5, 4)))
        _ <- Ns.s.a1.i.>=(Ref.int_).Ref.int.<(3).query.get.map(_ ==> List())
        _ <- Ns.s.a1.i.>=(Ref.int_).Ref.int.<=(3).query.get.map(_ ==> List(("b", 3, 3)))

        _ <- Ns.s.a1.i.>=(Ref.int_).Ref.int_(3).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.a1.i.>=(Ref.int_).Ref.int_.not(3).query.get.map(_ ==> List(("c", 5)))
        _ <- Ns.s.a1.i.>=(Ref.int_).Ref.int_.>(3).query.get.map(_ ==> List(("c", 5)))
        _ <- Ns.s.a1.i.>=(Ref.int_).Ref.int_.>=(3).query.get.map(_ ==> List(("b", 3), ("c", 5)))
        _ <- Ns.s.a1.i.>=(Ref.int_).Ref.int_.<(3).query.get.map(_ ==> List())
        _ <- Ns.s.a1.i.>=(Ref.int_).Ref.int_.<=(3).query.get.map(_ ==> List(("b", 3)))

        _ <- Ns.s.a1.i_.>=(Ref.int_).Ref.int(3).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.a1.i_.>=(Ref.int_).Ref.int.not(3).query.get.map(_ ==> List(("c", 4)))
        _ <- Ns.s.a1.i_.>=(Ref.int_).Ref.int.>(3).query.get.map(_ ==> List(("c", 4)))
        _ <- Ns.s.a1.i_.>=(Ref.int_).Ref.int.>=(3).query.get.map(_ ==> List(("b", 3), ("c", 4)))
        _ <- Ns.s.a1.i_.>=(Ref.int_).Ref.int.<(3).query.get.map(_ ==> List())
        _ <- Ns.s.a1.i_.>=(Ref.int_).Ref.int.<=(3).query.get.map(_ ==> List(("b", 3)))

        _ <- Ns.s.a1.i_.>=(Ref.int_).Ref.int_(3).query.get.map(_ ==> List("b"))
        _ <- Ns.s.a1.i_.>=(Ref.int_).Ref.int_.not(3).query.get.map(_ ==> List("c"))
        _ <- Ns.s.a1.i_.>=(Ref.int_).Ref.int_.>(3).query.get.map(_ ==> List("c"))
        _ <- Ns.s.a1.i_.>=(Ref.int_).Ref.int_.>=(3).query.get.map(_ ==> List("b", "c"))
        _ <- Ns.s.a1.i_.>=(Ref.int_).Ref.int_.<(3).query.get.map(_ ==> List())
        _ <- Ns.s.a1.i_.>=(Ref.int_).Ref.int_.<=(3).query.get.map(_ ==> List("b"))


        // Pointing backwards

        _ <- Ns.s.a1.i.Ref.int.>=(Ns.i_).query.get.map(_ ==> List(("a", 1, 2), ("b", 3, 3)))
        _ <- Ns.s.a1.i.Ref.int_.>=(Ns.i_).query.get.map(_ ==> List(("a", 1), ("b", 3)))
        _ <- Ns.s.a1.i_.Ref.int.>=(Ns.i_).query.get.map(_ ==> List(("a", 2), ("b", 3)))
        _ <- Ns.s.a1.i_.Ref.int_.>=(Ns.i_).query.get.map(_ ==> List("a", "b"))

        // Filter compare attribute itself
        _ <- Ns.s.a1.i(1).Ref.int.>=(Ns.i_).query.get.map(_    ==> List(("a", 1, 2)))
        _ <- Ns.s.a1.i.not(1).Ref.int.>=(Ns.i_).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- Ns.s.a1.i.>(1).Ref.int.>=(Ns.i_).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- Ns.s.a1.i.>=(1).Ref.int.>=(Ns.i_).query.get.map(_ ==> List(("a", 1, 2), ("b", 3, 3)))
        _ <- Ns.s.a1.i.<(1).Ref.int.>=(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.a1.i.<=(1).Ref.int.>=(Ns.i_).query.get.map(_ ==> List(("a", 1, 2)))

        _ <- Ns.s.a1.i(1).Ref.int_.>=(Ns.i_).query.get.map(_    ==> List(("a", 1)))
        _ <- Ns.s.a1.i.not(1).Ref.int_.>=(Ns.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.a1.i.>(1).Ref.int_.>=(Ns.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.a1.i.>=(1).Ref.int_.>=(Ns.i_).query.get.map(_ ==> List(("a", 1), ("b", 3)))
        _ <- Ns.s.a1.i.<(1).Ref.int_.>=(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.a1.i.<=(1).Ref.int_.>=(Ns.i_).query.get.map(_ ==> List(("a", 1)))

        _ <- Ns.s.a1.i_(1).Ref.int.>=(Ns.i_).query.get.map(_    ==> List(("a", 2)))
        _ <- Ns.s.a1.i_.not(1).Ref.int.>=(Ns.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.a1.i_.>(1).Ref.int.>=(Ns.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.a1.i_.>=(1).Ref.int.>=(Ns.i_).query.get.map(_ ==> List(("a", 2), ("b", 3)))
        _ <- Ns.s.a1.i_.<(1).Ref.int.>=(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.a1.i_.<=(1).Ref.int.>=(Ns.i_).query.get.map(_ ==> List(("a", 2)))

        _ <- Ns.s.a1.i_(1).Ref.int_.>=(Ns.i_).query.get.map(_    ==> List("a"))
        _ <- Ns.s.a1.i_.not(1).Ref.int_.>=(Ns.i_).query.get.map(_ ==> List("b"))
        _ <- Ns.s.a1.i_.>(1).Ref.int_.>=(Ns.i_).query.get.map(_ ==> List("b"))
        _ <- Ns.s.a1.i_.>=(1).Ref.int_.>=(Ns.i_).query.get.map(_ ==> List("a", "b"))
        _ <- Ns.s.a1.i_.<(1).Ref.int_.>=(Ns.i_).query.get.map(_ ==> List())
        _ <- Ns.s.a1.i_.<=(1).Ref.int_.>=(Ns.i_).query.get.map(_ ==> List("a"))
      } yield ()
    }
  }
}