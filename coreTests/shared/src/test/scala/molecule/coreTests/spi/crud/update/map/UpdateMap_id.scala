package molecule.coreTests.spi.crud.update.map

import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMap_id extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Multiple entities updated" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.intMap.insert(Map(pint1), Map(pint2), Map(pint3)).transact.map(_.ids)
        _ <- Ns.id.a1.intMap.query.get.map(_ ==> List(
          (a, Map(pint1)),
          (b, Map(pint2)),
          (c, Map(pint3)),
        ))

        _ <- Ns(b, c).intMap(Map(pint4)).update.transact
        _ <- Ns.id.a1.intMap.query.get.map(_ ==> List(
          (a, Map(pint1)),
          (b, Map(pint4)),
          (c, Map(pint4)),
        ))
      } yield ()
    }


    "Delete individual map attribute value(s) with update" - types { implicit conn =>
      for {
        id <- Ns.intMap.stringMap.insert(Map(pint1), Map(pstring1)).transact.map(_.id)
        _ <- Ns.intMap.stringMap.query.get.map(_ ==> List((Map(pint1), Map(pstring1))))

        // Apply empty value to delete map attribute of entity (entity remains)
        _ <- Ns(id).stringMap().update.transact
        _ <- Ns.intMap.stringMap_?.query.get.map(_ ==> List((Map(pint1), None)))
      } yield ()
    }


    "Update multiple map attributes" - types { implicit conn =>
      for {
        id <- Ns.intMap.stringMap.insert(Map(pint1), Map(pstring1)).transact.map(_.id)
        _ <- Ns.intMap.stringMap.query.get.map(_ ==> List((Map(pint1), Map(pstring1))))

        // Apply empty value to delete map attribute of entity (entity remains)
        _ <- Ns(id).intMap(Map(pint2)).stringMap(Map(pstring2, pstring3)).update.transact
        _ <- Ns.intMap.stringMap.query.get.map(_ ==> List((Map(pint2), Map(pstring2, pstring3))))
      } yield ()
    }


    "Ref" - refs { implicit conn =>
      for {
        id <- A.iMap(Map(pint1)).B.iMap(Map(pint2)).C.iMap(Map(pint3)).save.transact.map(_.id)
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint2), Map(pint3))))

        // A
        _ <- A(id).iMap(Map(pint1)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint2), Map(pint3))))

        // A + B
        _ <- A(id).iMap(Map(pint2)).B.iMap(Map(pint1)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint2), Map(pint1), Map(pint3))))

        // B
        _ <- A(id).B.iMap(Map(pint2)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint2), Map(pint2), Map(pint3))))

        // A + B + C
        _ <- A(id).iMap(Map(pint3)).B.iMap(Map(pint3)).C.iMap(Map(pint1)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint3), Map(pint3), Map(pint1))))

        // A + C
        _ <- A(id).iMap(Map(pint4)).B.C.iMap(Map(pint2)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint3), Map(pint2))))

        // B + C
        _ <- A(id).B.iMap(Map(pint4)).C.iMap(Map(pint3)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint4), Map(pint3))))

        // C
        _ <- A(id).B.C.iMap(Map(pint4)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint4), Map(pint4))))
      } yield ()
    }

    "OwnB" - refs { implicit conn =>
      for {
        id <- A.iMap(Map(pint1)).B.iMap(Map(pint2)).C.iMap(Map(pint3)).save.transact.map(_.id)
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint2), Map(pint3))))

        // A
        _ <- A(id).iMap(Map(pint1)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint2), Map(pint3))))

        // A + B
        _ <- A(id).iMap(Map(pint2)).B.iMap(Map(pint1)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint2), Map(pint1), Map(pint3))))

        // B
        _ <- A(id).B.iMap(Map(pint2)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint2), Map(pint2), Map(pint3))))

        // A + B + C
        _ <- A(id).iMap(Map(pint3)).B.iMap(Map(pint3)).C.iMap(Map(pint1)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint3), Map(pint3), Map(pint1))))

        // A + C
        _ <- A(id).iMap(Map(pint4)).B.C.iMap(Map(pint2)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint3), Map(pint2))))

        // B + C
        _ <- A(id).B.iMap(Map(pint4)).C.iMap(Map(pint3)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint4), Map(pint3))))

        // C
        _ <- A(id).B.C.iMap(Map(pint4)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint4), Map(pint4))))
      } yield ()
    }

    "OwnC" - refs { implicit conn =>
      for {
        id <- A.iMap(Map(pint1)).B.iMap(Map(pint2)).C.iMap(Map(pint3)).save.transact.map(_.id)
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint2), Map(pint3))))

        // A
        _ <- A(id).iMap(Map(pint1)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint2), Map(pint3))))

        // A + B
        _ <- A(id).iMap(Map(pint2)).B.iMap(Map(pint1)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint2), Map(pint1), Map(pint3))))

        // B
        _ <- A(id).B.iMap(Map(pint2)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint2), Map(pint2), Map(pint3))))

        // A + B + C
        _ <- A(id).iMap(Map(pint3)).B.iMap(Map(pint3)).C.iMap(Map(pint1)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint3), Map(pint3), Map(pint1))))

        // A + C
        _ <- A(id).iMap(Map(pint4)).B.C.iMap(Map(pint2)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint3), Map(pint2))))

        // B + C
        _ <- A(id).B.iMap(Map(pint4)).C.iMap(Map(pint3)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint4), Map(pint3))))

        // C
        _ <- A(id).B.C.iMap(Map(pint4)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint4), Map(pint4))))
      } yield ()
    }

    "OwnB OwnC" - refs { implicit conn =>
      for {
        id <- A.iMap(Map(pint1)).B.iMap(Map(pint2)).C.iMap(Map(pint3)).save.transact.map(_.id)
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint2), Map(pint3))))

        // A
        _ <- A(id).iMap(Map(pint1)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint2), Map(pint3))))

        // A + B
        _ <- A(id).iMap(Map(pint2)).B.iMap(Map(pint1)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint2), Map(pint1), Map(pint3))))

        // B
        _ <- A(id).B.iMap(Map(pint2)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint2), Map(pint2), Map(pint3))))

        // A + B + C
        _ <- A(id).iMap(Map(pint3)).B.iMap(Map(pint3)).C.iMap(Map(pint1)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint3), Map(pint3), Map(pint1))))

        // A + C
        _ <- A(id).iMap(Map(pint4)).B.C.iMap(Map(pint2)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint3), Map(pint2))))

        // B + C
        _ <- A(id).B.iMap(Map(pint4)).C.iMap(Map(pint3)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint4), Map(pint3))))

        // C
        _ <- A(id).B.C.iMap(Map(pint4)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint4), Map(pint4))))
      } yield ()
    }


    "Referenced map attributes with backref" - refs { implicit conn =>
      for {
        id <- A.iMap(Map(pint1)).B.iMap(Map(pint2))._A.C.iMap(Map(pint3)).save.transact.map(_.id)
        _ <- A.iMap.B.iMap._A.C.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint2), Map(pint3))))

        // Updating A.B.iMap and A.C.iMap
        _ <- A(id).iMap(Map(pint1)).B.iMap(Map(pint1))._A.C.iMap(Map(pint1)).update.transact
        _ <- A.iMap.B.iMap._A.C.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint1), Map(pint1))))
      } yield ()
    }


    "Semantics" - {

      "Can't update optional values" - types { implicit conn =>
        for {
          _ <- Ns("42").intMap_?(Some(Map(("a", 1)))).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can't update optional values. Found:\n" +
                s"""AttrMapOptInt("Ns", "intMap", Eq, Some(Map(("a", 1))), None, None, Nil, Nil, None, None, Seq(0, 77))"""
            }
        } yield ()
      }

//      "Can't update card-many referenced map attributes" - types { implicit conn =>
//        for {
//          _ <- Ref("42").i(1).Nss.intMap(Map(("a", 1))).update.transact
//            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
//              err ==> "Can't update attributes in card-many referenced namespace `Nss`"
//            }
//        } yield ()
//      }

      "Valid keys" - types { implicit conn =>
        for {
          id <- Ns.intMap(Map("a" -> int0)).save.transact.map(_.id)

          // Allowed characters in a key name
          _ <- Ns(id).intMap(Map("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789" -> 1)).update.transact

          // No spaces
          _ <- Ns(id).intMap(Map("foo bar" -> 1)).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Keys of map attributes can only contain [a-zA-Z_\\-0-9] (no spaces or special characters)."
            }

          // No special characters
          _ <- Ns(id).intMap(Map("foo:" -> 1)).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Keys of map attributes can only contain [a-zA-Z_\\-0-9] (no spaces or special characters)."
            }
          _ <- Ns(id).intMap.add("bar?" -> 2).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Keys of map attributes can only contain [a-zA-Z_\\-0-9] (no spaces or special characters)."
            }
        } yield ()
      }
    }
  }
}
