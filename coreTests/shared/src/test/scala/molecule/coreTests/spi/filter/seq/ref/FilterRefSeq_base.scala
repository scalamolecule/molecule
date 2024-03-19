package molecule.coreTests.spi.filter.seq.ref

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterRefSeq_base extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "mandatory" - refs { implicit conn =>
      for {
        _ <- A.i.iSeq.insert(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
          (4, List())
        ).transact

        // All
        _ <- A.i.a1.iSeq.query.get.map(_ ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
        ))


        // equal/apply

        _ <- A.i.a1.iSeq(List(1)).query.get.map(_ ==> Nil)
        _ <- A.i.a1.iSeq(List(1, 2)).query.get.map(_ ==> Nil)
        _ <- A.i.a1.iSeq(List(1, 2, 2)).query.get.map(_ ==> List(
          (1, List(1, 2, 2)),
        ))
        _ <- A.i.a1.iSeq(List.empty[Int]).query.get.map(_ ==> Nil)


        // not

        _ <- A.i.iSeq.not(List(1)).query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
        ))
        _ <- A.i.iSeq.not(List(2)).query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(1, 2, 2)),
          (2, List(7)),
          (3, List(3)),
        ))
        _ <- A.i.iSeq.not(List(1, 2)).query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
        ))
        _ <- A.i.iSeq.not(List(1, 2, 2)).query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
        ))
        _ <- A.i.iSeq.not(List.empty[Int]).query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
        ))


        // has

        _ <- A.i.iSeq.has(1).query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(1, 2, 2)),
        ))
        _ <- A.i.iSeq.has(2).query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
        ))

        _ <- A.i.iSeq.has(2, 1).query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
        ))
        _ <- A.i.iSeq.has(2, 7).query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
        ))
        _ <- A.i.iSeq.has(2, 3).query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (3, List(3)),
        ))


        // hasNo

        _ <- A.i.iSeq.hasNo(1).query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
        ))
        _ <- A.i.iSeq.hasNo(2).query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (2, List(7)),
          (3, List(3)),
        ))
        _ <- A.i.iSeq.hasNo(3).query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
        ))
      } yield ()
    }


    "tacit" - refs { implicit conn =>
      for {
        _ <- A.i.iSeq.insert(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
          (4, List())
        ).transact

        // all
        _ <- A.i.a1.iSeq_.query.get.map(_ ==> List(1, 2, 2, 3))

        // equal/apply
        _ <- A.i.a1.iSeq_(List(1)).query.get.map(_ ==> Nil)
        _ <- A.i.a1.iSeq_(List(1, 2)).query.get.map(_ ==> Nil)
        _ <- A.i.a1.iSeq_(List(1, 2, 2)).query.get.map(_ ==> List(1))
        _ <- A.i.a1.iSeq_(List.empty[Int]).query.get.map(_ ==> Nil)

        // not
        _ <- A.i.a1.iSeq_.not(List(1)).query.get.map(_ ==> List(1, 2, 2, 3))
        _ <- A.i.a1.iSeq_.not(List(1, 2)).query.get.map(_ ==> List(1, 2, 2, 3))
        _ <- A.i.a1.iSeq_.not(List(1, 2, 2)).query.get.map(_ ==> List(2, 2, 3))
        _ <- A.i.a1.iSeq_.not(List.empty[Int]).query.get.map(_ ==> List(1, 2, 2, 3))

        // has
        _ <- A.i.a1.iSeq_.has(1).query.get.map(_ ==> List(1))
        _ <- A.i.a1.iSeq_.has(2).query.get.map(_ ==> List(1, 2))
        _ <- A.i.a1.iSeq_.has(2, 7).query.get.map(_ ==> List(1, 2, 2))
        _ <- A.i.a1.iSeq_.has(2, 3).query.get.map(_ ==> List(1, 2, 3))

        // hasNo
        _ <- A.i.a1.iSeq_.hasNo(1).query.get.map(_ ==> List(2, 2, 3))
        _ <- A.i.a1.iSeq_.hasNo(2).query.get.map(_ ==> List(2, 3))
        _ <- A.i.a1.iSeq_.hasNo(3).query.get.map(_ ==> List(1, 2, 2))

        // no value - match non-asserted attribute (null)
        _ <- A.i.a1.iSeq_().query.get.map(_ ==> List(4))
      } yield ()
    }


    "optional" - refs { implicit conn =>
      val allAssertedOptional = List(
        (1, Some(List(1, 2, 2))),
        (2, Some(List(2))),
        (2, Some(List(7))),
        (3, Some(List(3))),
      )
      for {
        _ <- A.i.iSeq.insert(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
          (4, List())
        ).transact

        // All
        _ <- A.i.a1.iSeq_?.query.get.map(_ ==> List(
          (1, Some(List(1, 2, 2))),
          (2, Some(List(2))),
          (2, Some(List(7))),
          (3, Some(List(3))),
          (4, None)
        ))


        // equal/apply

        _ <- A.i.a1.iSeq_?(Some(List(1))).query.get.map(_ ==> Nil)
        _ <- A.i.a1.iSeq_?(Some(List(1, 2))).query.get.map(_ ==> Nil)
        _ <- A.i.a1.iSeq_?(Some(List(1, 2, 2))).query.get.map(_ ==> List(
          (1, Some(List(1, 2, 2))),
        ))

        // None matches non-asserted values
        _ <- A.i.a1.iSeq_?(Option.empty[List[Int]]).query.get.map(_ ==> List((4, None)))
        _ <- A.i.a1.iSeq_?(Option.empty[List[List[Int]]]).query.get.map(_ ==> List((4, None)))

        // Empty Lists are ignored (use None to match non-asserted card-set attributes)
        _ <- A.i.a1.iSeq_?(Some(List.empty[Int])).query.get.map(_ ==> Nil)
        _ <- A.i.a1.iSeq_?(Some(List.empty[List[Int]])).query.get.map(_ ==> Nil)
        _ <- A.i.a1.iSeq_?(Some(List(List.empty[Int]))).query.get.map(_ ==> Nil)


        // not

        _ <- A.i.a1.iSeq_?.not(Some(List(1))).query.get.map(_ ==> List(
          (1, Some(List(1, 2, 2))),
          (2, Some(List(2))),
          (2, Some(List(7))),
          (3, Some(List(3))),
        ))
        _ <- A.i.a1.iSeq_?.not(Some(List(2))).query.get.map(_ ==> List(
          (1, Some(List(1, 2, 2))),
          (2, Some(List(7))),
          (3, Some(List(3))),
        ))
        _ <- A.i.a1.iSeq_?.not(Some(List(1, 2, 2))).query.get.map(_ ==> List(
          (2, Some(List(2))),
          (2, Some(List(7))),
          (3, Some(List(3))),
        ))

        // Negating None matches all asserted values
        _ <- A.i.a1.iSeq_?.not(Option.empty[List[Int]]).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.iSeq_?.not(Option.empty[List[List[Int]]]).query.get.map(_ ==> allAssertedOptional)

        // Negating empty Lists match nothing
        _ <- A.i.a1.iSeq_?.not(Some(List.empty[Int])).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.iSeq_?.not(Some(List.empty[List[Int]])).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.iSeq_?.not(Some(List(List.empty[Int]))).query.get.map(_ ==> allAssertedOptional)


        // has

        _ <- A.i.a1.iSeq_?.has(Some(1)).query.get.map(_ ==> List(
          (1, Some(List(1, 2, 2))),
        ))
        _ <- A.i.a1.iSeq_?.has(Some(2)).query.get.map(_ ==> List(
          (1, Some(List(1, 2, 2))),
          (2, Some(List(2))),
        ))
        // same as
        _ <- A.i.a1.iSeq_?.has(Some(List(2))).query.get.map(_ ==> List(
          (1, Some(List(1, 2, 2))),
          (2, Some(List(2))),
        ))
        // has 2 or 3
        _ <- A.i.a1.iSeq_?.has(Some(List(2, 3))).query.get.map(_ ==> List(
          (1, Some(List(1, 2, 2))),
          (2, Some(List(2))),
          (3, Some(List(3))),
        ))

        // None matches non-asserted values
        _ <- A.i.a1.iSeq_?.has(Option.empty[List[Int]]).query.get.map(_ ==> List((4, None)))

        // Empty Lists match nothing
        _ <- A.i.a1.iSeq_?.has(Some(List.empty[Int])).query.get.map(_ ==> Nil)


        // hasNo

        _ <- A.i.a1.iSeq_?.hasNo(Some(1)).query.get.map(_ ==> List(
          (2, Some(List(2))),
          (2, Some(List(7))),
          (3, Some(List(3))),
        ))
        _ <- A.i.a1.iSeq_?.hasNo(Some(2)).query.get.map(_ ==> List(
          (2, Some(List(7))),
          (3, Some(List(3))),
        ))
        _ <- A.i.a1.iSeq_?.hasNo(Some(3)).query.get.map(_ ==> List(
          (1, Some(List(1, 2, 2))),
          (2, Some(List(2))),
          (2, Some(List(7))),
        ))

        // Negating None matches all asserted values
        _ <- A.i.a1.iSeq_?.hasNo(Option.empty[List[Int]]).query.get.map(_ ==> allAssertedOptional)

        // Negating empty Lists match nothing
        _ <- A.i.a1.iSeq_?.hasNo(Some(List.empty[Int])).query.get.map(_ ==> allAssertedOptional)
      } yield ()
    }
  }
}
