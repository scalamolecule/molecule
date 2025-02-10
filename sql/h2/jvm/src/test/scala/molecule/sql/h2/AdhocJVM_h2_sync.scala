package molecule.sql.h2

import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup.{Test, TestUtils}
import molecule.sql.h2.setup.DbProviders_h2
import molecule.sql.h2.sync._
import scala.language.implicitConversions


class AdhocJVM_h2_sync extends Test with DbProviders_h2 with TestUtils {


  "commit" - types { implicit conn =>
    //    Entity.int.insert(1 to 7).transact
    //    Entity.int(count).query.get.head ==> 7
    //
    //    Entity.int_.delete.transact
    //    Entity.int(count).query.get.head ==> 0
    //
    //


    //    Entity.i.Ref.s.insert(
    //      (1, "a"),
    //      (2, "b"),
    //    ).i.transact
    //
    //    Entity.i.Ref.?(Ref.s).insert(
    //      (1, Some("a")),
    //      (2, None),
    //    ).i.transact

    /*
Insert(
  Entity(
    RefOpt(
      INSERT INTO Ref (
        s
      ) VALUES (?)
    )
    ---------------------------
    INSERT INTO Entity (
      i,
      ref
    ) VALUES (?, ?)
  )
)
     */

    //    Entity.i.Ref.?(Ref.s).query.get ==> List(
    //      (1, Some("a")),
    //      (2, None),
    //    )
    //
    //    """SELECT city0.name AS res_0, country1.name AS res_1
    //      |FROM city city0
    //      |RIGHT JOIN country country1 ON (city0.countrycode = country1.code)
    //      |WHERE (city0.id IS NULL)
    //      |""".stripMargin
    //
    //    Ref.?(Entity.i).s.insert(
    //      (Some(1), "a"),
    //      (None, "b"),
    //    ).i.transact


    Entity.?(Entity.i).Ref.s.insert(
      (Some(1), "a"),
      (None, "b"),
    ).transact

//    rawQuery(
//      """SELECT Entity.i, Ref.s
//        |FROM Entity
//        |RIGHT JOIN Ref ON Entity.ref = Ref.id
//        |""".stripMargin, true
//    )
//
//    rawQuery(
//      """SELECT DISTINCT
//        |  Entity.i,
//        |  Ref.s
//        |FROM Entity
//        |  RIGHT JOIN Ref ON
//        |    Entity.ref = Ref.id AND
//        |    Ref.s IS NOT NULL;
//        |""".stripMargin, true
//    )


    Entity.?(Entity.i).Ref.s.a1.query.i.get ==> List(
      (Some(1), "a"),
      (None, "b"),
    )

    //    Entity.i(1).Ref.s("a").save
    //    Ref.s("b").save
    //
    //    Entity.?(Entity.i).Ref.s.a1.query.get ==> List(
    //      (Some(1), "a"),
    //      (None, "b")
    //    )

  }
}
