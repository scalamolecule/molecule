package molecule.sql.h2

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.sql.h2.async._
import molecule.sql.h2.setup.TestSuite_h2
import utest._
import scala.collection.immutable.List
import scala.language.implicitConversions

object AdhocJVM_h2 extends TestSuite_h2 {

  def getTriples: List[(String, Int, Int)] = (1 to 5).toList.map { int =>
    val s = ('a' + scala.util.Random.nextInt(3)).toChar.toString // "a" or "b"
    val i = scala.util.Random.nextInt(3) + 1 // 1 or 2
    (s, i, int)
  }

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        //        _ <- Ns.int.insert(1).i.transact
        //        _ <- Ns.int.query.i.get.map(_ ==> List(1))

        _ <- Ns.s.i.Refs.*(Ref.int).insert(
          ("a", 1, List(2, 3)),
          ("b", 4, List(4, 5)),
          ("c", 7, List(5, 6)),
          ("d", 9, Nil),
        ).transact

        _ <- Ns.s.i(Ref.int_).Refs.*(Ref.int).query.i.get.map(_ ==> List(("b", 4, List(4))))

      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

        // Card one ref attr
        List(_, e1) <- A.i.B.i.insert(1, 2).i.transact.map(_.ids)
        _ <- A.i.b.query.i.get.map(_ ==> List((1, e1)))

      } yield ()
    }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      val triples             = getTriples.map(t => (t._3, t._1, t._2))
    //      val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
    //      val query               = (c: String, l: Int) => Uniques.int.a3.s.a1.i.a2.query.from(c).limit(l)
    //      for {
    //        _ <- Uniques.int.s.i.insert(triples).transact
    //
    //      } yield ()
    //    }

    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //
    //      } yield ()
    //    }
  }
}
