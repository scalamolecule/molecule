package molecule.coreTests.spi.crud.delete

import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future

trait Delete_filter extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Filter by 1 non-ns value" - refs { implicit conn =>
      for {
        _ <- A.i.insert(1, 2).transact
        _ <- A.i_(1).delete.transact
        _ <- A.i.query.get.map(_ ==> List(2))
      } yield ()
    }


    "Filter by multiple non-ns value" - refs { implicit conn =>
      for {
        List(e1, e2, e3) <- A.i.insert(1, 2, 2).transact.map(_.ids)
        _ <- A.id.a1.i.query.get.map(_ ==> List(
          (e1, 1),
          (e2, 2),
          (e3, 2),
        ))

        // Delete two entities having `i` with value 2
        _ <- A.i_(2).delete.transact

        // One entity left
        _ <- A.id.i.query.get.map(_ ==> List(
          (e1, 1),
        ))
      } yield ()
    }


    "Expression" -  {
      import molecule.coreTests.dataModels.core.dsl.Types._

      "equal 0" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(int1, int2, int3).transact
          _ <- Ns.int_(int0).delete.transact
          _ <- Ns.int.a1.query.get.map(_ ==> List(int1, int2, int3))
        } yield ()
      }
      "equal 2" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(int1, int2, int3).transact
          _ <- Ns.int_(int2).delete.transact
          _ <- Ns.int.a1.query.get.map(_ ==> List(int1, int3))
        } yield ()
      }
      "equal 1 2" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(int1, int2, int3).transact
          _ <- Ns.int_(int1, int2).delete.transact
          _ <- Ns.int.query.get.map(_ ==> List(int3))
        } yield ()
      }


      "not equal 0" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(int1, int2, int3).transact
          _ <- Ns.int_.not(int0).delete.transact
          _ <- Ns.int.query.get.map(_ ==> List())
        } yield ()
      }
      "not equal 2" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(int1, int2, int3).transact
          _ <- Ns.int_.not(int2).delete.transact
          _ <- Ns.int.query.get.map(_ ==> List(int2))
        } yield ()
      }
      "not equal 1 2" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(int1, int2, int3).transact
          _ <- Ns.int_.not(int1, int2).delete.transact
          _ <- Ns.int.a1.query.get.map(_ ==> List(int1, int2))
        } yield ()
      }


      "<" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(int1, int2, int3).transact
          _ <- Ns.int_.<(int2).delete.transact
          _ <- Ns.int.a1.query.get.map(_ ==> List(int2, int3))
        } yield ()
      }


      "<=" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(int1, int2, int3).transact
          _ <- Ns.int_.<=(int2).delete.transact
          _ <- Ns.int.query.get.map(_ ==> List(int3))
        } yield ()
      }


      ">" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(int1, int2, int3).transact
          _ <- Ns.int_.>(int2).delete.transact
          _ <- Ns.int.a1.query.get.map(_ ==> List(int1, int2))
        } yield ()
      }


      ">=" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(int1, int2, int3).transact
          _ <- Ns.int_.>=(int2).delete.transact
          _ <- Ns.int.query.get.map(_ ==> List(int1))
        } yield ()
      }
    }

    "Multiple expressions" - refs { implicit conn =>
      for {
        _ <- A.i.s.insert(
          (1, "a"),
          (2, "b"),
          (3, "c"),
        ).transact

        _ <- A.i.a1.s.query.get.map(_ ==> List(
          (1, "a"),
          (2, "b"),
          (3, "c"),
        ))

        // Update all entities where non-ns attribute i > 1 and s < "c"
        _ <- A.i_.>(1).s_.<("c").delete.transact

        _ <- A.i.a1.s.query.get.map(_ ==> List(
          (1, "a"),
          (3, "c"),
        ))
      } yield ()
    }


    "Ref" - refs { implicit conn =>
      for {
        _ <- A.i.insert(1).transact
        _ <- A.i.B.i.insert((2, 20), (3, 30)).transact

        _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))
        _ <- A.i.a1.B.i.query.get.map(_ ==> List((2, 20), (3, 30)))

        // Nothing deleted since entity 1 doesn't have a ref
        _ <- A.i_(1).B.i_.delete.transact
        _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))

        // Second entity has a ref and will be deleted
        _ <- A.i_(2).B.i_.delete.transact
        _ <- A.i.a1.query.get.map(_ ==> List(1, 3))
        _ <- A.i.a1.B.i.query.get.map(_ ==> List((3, 30)))

        // Note that B.int entity is a separate entity and is not deleted.
        // Only the entity of the initial namespace is deleted
        _ <- B.i.a1.query.get.map(_ ==> List(20, 30))

        // A.i entity has no ref to B.i_(42) so nothing is deleted
        _ <- A.i_.B.i_(42).delete.transact
        _ <- A.i.a1.query.get.map(_ ==> List(1, 3))
        _ <- A.i.B.i.query.get.map(_ ==> List((3, 30)))

        // A.i entity has a ref to B.i_(30) so it will be deleted
        _ <- A.i_.B.i_(30).delete.transact
        _ <- A.i.query.get.map(_ ==> List(1))
        _ <- A.i.B.i.query.get.map(_ ==> List())
      } yield ()
    }

    "Ref owned" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i.insert(1).transact
        _ <- A.i.OwnB.i.insert((2, 20), (3, 30)).transact

        _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))
        _ <- A.i.a1.OwnB.i.query.get.map(_ ==> List((2, 20), (3, 30)))

        // Nothing deleted since entity 1 doesn't have a ref
        _ <- A.i_(1).OwnB.i_.delete.transact
        _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))

        // Second entity has a ref and will be deleted
        _ <- A.i_(2).OwnB.i_.delete.transact
        _ <- A.i.a1.query.get.map(_ ==> List(1, 3))
        _ <- A.i.OwnB.i.query.get.map(_ ==> List((3, 30)))

        _ <- if (database != "MongoDB") {
          // Owned B entity is deleted too
          B.i.a1.query.get.map(_ ==> List(30))
        } else {
          // Owned entity in Mongo is embedded in the A document.
          // So we can't query it in isolation without its parent A document.
          Future.unit
        }

        // A.i entity has no ref to OwnB.i_(42) so nothing is deleted
        _ <- A.i_.OwnB.i_(42).delete.transact
        _ <- A.i.a1.query.get.map(_ ==> List(1, 3))
        _ <- A.i.OwnB.i.query.get.map(_ ==> List((3, 30)))

        // A.i entity has a ref to OwnB.i_(30) so it will be deleted
        _ <- A.i_.OwnB.i_(30).delete.transact
        _ <- A.i.query.get.map(_ ==> List(1))
        _ <- A.i.OwnB.i.query.get.map(_ ==> List())
      } yield ()
    }


    "Ref + expr" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.insert((1, 10), (2, 20)).transact
        _ <- A.i.a1.query.get.map(_ ==> List(1, 2))
        _ <- B.i.a1.query.get.map(_ ==> List(10, 20))
        _ <- A.i.a1.B.i.query.get.map(_ ==> List((1, 10), (2, 20)))

        _ <- A.i_.B.i_.>(15).delete.transact
        _ <- A.i.query.get.map(_ ==> List(1))
        // Note that the B.int entity is a separate entity and is not deleted
        // Only the entity of the initial namespace is deleted
        _ <- B.i.a1.query.get.map(_ ==> List(10, 20))
        _ <- A.i.B.i.query.get.map(_ ==> List((1, 10)))
      } yield ()
    }

    "Ref owned + expr" - refs { implicit conn =>
      if (database == "MongoDB") {
        // Since owned B is an embedded document in Mongo we can't query it as an independent entity
        for {
          _ <- A.i.OwnB.i.insert((1, 10), (2, 20)).transact
          _ <- A.i.a1.query.get.map(_ ==> List(1, 2))
          _ <- A.i.a1.OwnB.i.query.get.map(_ ==> List((1, 10), (2, 20)))

          _ <- A.i_.OwnB.i_.>(15).delete.transact
          _ <- A.i.query.get.map(_ ==> List(1))
          _ <- A.i.OwnB.i.query.get.map(_ ==> List((1, 10)))
        } yield ()

      } else {
        // In other dbs, owned B is a separate entity. So we can query it independently
        for {
          _ <- A.i.OwnB.i.insert((1, 10), (2, 20)).transact
          _ <- A.i.a1.query.get.map(_ ==> List(1, 2))
          _ <- B.i.a1.query.get.map(_ ==> List(10, 20))
          _ <- A.i.a1.OwnB.i.query.get.map(_ ==> List((1, 10), (2, 20)))

          _ <- A.i_.OwnB.i_.>(15).delete.transact
          _ <- A.i.query.get.map(_ ==> List(1))

          // Owned B entity with i == 20 is deleted too
          _ <- B.i.a1.query.get.map(_ ==> List(10))
          _ <- A.i.OwnB.i.query.get.map(_ ==> List((1, 10)))
        } yield ()
      }
    }


    "Semantics" - {

      "Only tacit attributes" - refs { implicit conn =>
        for {
          _ <- A.i.<=(int1).delete.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can only filter delete by values applied to tacit card-one attributes. Found:\n" +
                s"""AttrOneManInt("A", "i", Le, Seq($int1), None, None, Nil, Nil, None, None, Seq(0, 1))"""
            }

          _ <- A.iSet(Set(int1)).delete.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can only filter delete by values applied to tacit card-one attributes. Found:\n" +
                s"""AttrSetManInt("A", "iSet", Eq, Seq(Set($int1)), None, None, Nil, Nil, None, None, Seq(0, 2))"""
            }

          _ <- A.iSeq(List(int1)).delete.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can only filter delete by values applied to tacit card-one attributes. Found:\n" +
                s"""AttrSeqManInt("A", "iSeq", Eq, Seq(Seq($int1)), None, None, Nil, Nil, None, None, Seq(0, 3))"""
            }

          _ <- A.iMap(Map(pint1)).delete.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can only filter delete by values applied to tacit card-one attributes. Found:\n" +
                s"""AttrMapManInt("A", "iMap", Eq, Map($pint1), None, None, Nil, Nil, None, None, Seq(0, 4))"""
            }
        } yield ()
      }
    }
  }
}
