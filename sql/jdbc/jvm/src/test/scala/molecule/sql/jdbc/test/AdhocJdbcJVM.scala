package molecule.sql.jdbc.test

import molecule.base.ast.SchemaAST.CardOne
import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.sql.jdbc.async._
import molecule.sql.jdbc.facade.JdbcConn_jvm
import molecule.sql.jdbc.setup.JdbcTestSuite
import utest._
import scala.collection.immutable.List
import scala.concurrent.Future
import scala.language.implicitConversions

object AdhocJdbcJVM extends JdbcTestSuite {

  //  val name   = Map("en" -> "hello", "de" -> "hello", "da" -> "hej")
  //  val name_k = Set("en", "de", "da")
  //  val name_v = Set("hello", "hej")
  //
  //  val name_   = 7
  //  val name_k_ = 7
  //  val name_v_ = 7
  //
  //  val name_?   = 7
  //  val name_k_? = 7
  //  val name_v_? = 7
  //
  //  val ranked   = List("Peter", "Bob", "Mary")
  //  val ranked_i = List(0, 1, 2)
  //
  //  val rankedA   = Array("Peter", "Bob", "Mary")
  //  val rankedA_i = Array(0, 1, 2)

  //  val x: Instant = ???
  //  val y: Long    = x.toEpochMilli


  override lazy val tests = Tests {
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

    "types" - types { implicit conn =>

      for {
        //        _ <- Ns.i(1).save.transact
        //        _ <- Ns.i.query.get.map(_ ==> List(1))

        id <- Ns.ints(Set(int1)).save.transact.map(_.id)

        _ <- rawQuery(
          """SELECT *
            |FROM Ns
            |""".stripMargin)

        _ <- rawTransact(
          """Insert into Ns(s) values ('Hi')""".stripMargin
        )

        _ <- rawQuery(
          """SELECT *
            |FROM Ns
            |""".stripMargin)

        // Add value
        _ <- Ns(id).ints.add(int2).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2))

        // Add existing value (no effect)
        _ <- Ns(id).ints.add(int2).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2))

        // Add multiple values (vararg)
        _ <- Ns(id).ints.add(int3, int4).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).ints.add(Seq(int4, int5)).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5))
        // Set
        _ <- Ns(id).ints.add(Set(int6)).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))
        // Iterable
        _ <- Ns(id).ints.add(Iterable(int7)).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6, int7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).ints.add(Seq.empty[Int]).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6, int7))


      } yield ()
    }



    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //        _ <- Require.int1.errorMsg.insert(
    //          (1, 2),
    //          (2, 2),
    //          (3, 2),
    //        ).transact
    //
    //        _ <- Variables.int1.errorMsg.query.inspect
    //        _ <- Variables.int1.<(Variables.errorMsg).query.inspect
    //        _ <- Variables.int1.<(Variables.errorMsg).query.get.map(_ ==> List())
    //
    //
    //      } yield ()
    //    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._

      for {
        //        id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
        //        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))


        _ <- rawQuery(
          s"""SELECT A_ownBb_B.B_id
             |FROM A_ownBb_B
             |INNER JOIN A on A.id = A_ownBb_B.A_id
             |WHERE A.id in (1)
             |""".stripMargin
        )

      } yield ()
    }

  }
}

/*

Can't use cascading deletes since the semantic model of a
cardinality-one relationship in molecule (and Datomic) is reversed
compared to sql. In Molecule, an entity is a parent if it holds
a reference to another entity. Let's see what problems we get in sql:


drop table A if exists;
drop table B if exists;
drop table C if exists;
create table A(
  id int primary key auto_increment,
  a int,
  b_id int
);
create table B(
  id int primary key auto_increment,
  b int,
  c_id int
);
create table C(
  id int primary key auto_increment,
  c int,
  c_id int
);

ALTER TABLE A
  ADD CONSTRAINT Ax
  FOREIGN KEY (B_id)
  REFERENCES B(id)
  ON DELETE CASCADE;

ALTER TABLE B
  ADD CONSTRAINT Bx
  FOREIGN KEY (C_id)
  REFERENCES C(id)
  ON DELETE CASCADE;

Insert into C(c) values (100), (200);
Insert into B(b, c_id) values (10, 1), (20, 2);
Insert into A(a, B_id) values (1, 1), (2, 2);

Select * from A;
Select * from B;
Select * from C;

/*
  In sql we would need to go "backwards" from the deepest owned entity.
  This breaks down if A has multiple owned refs that need to be deleted -
  especially if those in turn have other owned entities etc!
 */
DELETE FROM C WHERE ID in (
  select C_id from B
  inner join A
  where A.b_id = b.id And
  A.id = 1
);

Select * from A;
Select * from B;
Select * from C;

*/


