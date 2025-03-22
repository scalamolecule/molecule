package molecule.sql.h2

import geny.Generator
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup.{Test, TestUtils}
import molecule.sql.h2.setup.DbProviders_h2
import molecule.sql.h2.sync._
import scala.language.implicitConversions


class AdhocJVM_h2_sync extends Test with DbProviders_h2 with TestUtils {


  "commit1" - refs { implicit conn =>
    import molecule.coreTests.domains.dsl.Refs._


    A.i.insert(1, 2, 3).transact

//    A.i.query.get ==> List(1, 2, 3)
//    A.i.query.limit(2).get ==> List(1, 2, 3)

    val a: Generator[Int] = A.i.query.stream



//    val b = a.take(2)
    a.take(2).toList ==> List(1, 2)
    a.toList ==> List(1, 2, 3)








    //        A.?(A.i.s).B.s.insert(
    //          (Some((1, "x")), "a"),
    //    //      (Some((2, "y")), "b"),
    //          (None, "c"),
    //        ).transact
    //
    //    //    rawQuery(
    //    //      """SELECT DISTINCT
    //    //        |  A.i,
    //    //        |  A.s,
    //    //        |  B.s
    //    //        |FROM A
    //    //        |  RIGHT JOIN B ON
    //    //        |    A.b = B.id AND
    //    //        |    A.i IS NOT NULL AND
    //    //        |    A.s IS NOT NULL AND
    //    //        |    B.s IS NOT NULL
    //    //        |ORDER BY B.s;
    //    //        |""".stripMargin, true
    //    //    )
    //
    //        A.?(A.i.s).B.s.a1.query.i.get ==> List(
    //          (Some((1, "x")), "a"),
    //    //      (Some((2, "y")), "b"),
    //          (None, "c"),
    //        )
  }

  //  "commit" - types { implicit conn =>
  //    //    Entity.int.insert(1 to 7).transact
  //    //    Entity.int(count).query.get.head ==> 7
  //    //
  //    //    Entity.int_.delete.transact
  //    //    Entity.int(count).query.get.head ==> 0
  //    //
  //    //
  //
  //
  //    //    Entity.i.Ref.s.insert(
  //    //      (1, "a"),
  //    //      (2, "b"),
  //    //    ).i.transact
  //    //
  //    //    Entity.i.Ref.?(Ref.s).insert(
  //    //      (1, Some("a")),
  //    //      (2, None),
  //    //    ).i.transact
  //
  //    /*
  //Insert(
  //  Entity(
  //    RefOpt(
  //      INSERT INTO Ref (
  //        s
  //      ) VALUES (?)
  //    )
  //    ---------------------------
  //    INSERT INTO Entity (
  //      i,
  //      ref
  //    ) VALUES (?, ?)
  //  )
  //)
  //     */
  //
  //    //    Entity.i.Ref.?(Ref.s).query.get ==> List(
  //    //      (1, Some("a")),
  //    //      (2, None),
  //    //    )
  //    //
  //    //    """SELECT city0.name AS res_0, country1.name AS res_1
  //    //      |FROM city city0
  //    //      |RIGHT JOIN country country1 ON (city0.countrycode = country1.code)
  //    //      |WHERE (city0.id IS NULL)
  //    //      |""".stripMargin
  //    //
  //    //    Ref.?(Entity.i).s.insert(
  //    //      (Some(1), "a"),
  //    //      (None, "b"),
  //    //    ).i.transact
  //
  //
  //    Entity.?(Entity.i).Ref.s.insert(
  //      (Some(1), "a"),
  //      (None, "b"),
  //    ).transact
  //
  //    //    rawQuery(
  //    //      """SELECT Entity.i, Ref.s
  //    //        |FROM Entity
  //    //        |RIGHT JOIN Ref ON Entity.ref = Ref.id
  //    //        |""".stripMargin, true
  //    //    )
  //    //
  //    //    rawQuery(
  //    //      """SELECT DISTINCT
  //    //        |  Entity.i,
  //    //        |  Ref.s
  //    //        |FROM Entity
  //    //        |  RIGHT JOIN Ref ON
  //    //        |    Entity.ref = Ref.id AND
  //    //        |    Ref.s IS NOT NULL;
  //    //        |""".stripMargin, true
  //    //    )
  //
  //
  //    Entity.?(Entity.i).Ref.s.a1.query.i.get ==> List(
  //      (Some(1), "a"),
  //      (None, "b"),
  //    )
  //
  //    //    Entity.i(1).Ref.s("a").save
  //    //    Ref.s("b").save
  //    //
  //    //    Entity.?(Entity.i).Ref.s.a1.query.get ==> List(
  //    //      (Some(1), "a"),
  //    //      (None, "b")
  //    //    )
  //
  //  }
}
