package molecule.coreTests.spi.crud.update.one

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Uniques._
import molecule.coreTests.setup.CoreTestSuite
import utest._


trait UpdateOne_uniqueAttr extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Update by unique attribute value, check types" - unique { implicit conn =>
      for {
        // Initial values
        _ <- Uniques.i(1).string(string1).save.transact
        _ <- Uniques.i(1).int(int1).save.transact
        _ <- Uniques.i(1).long(long1).save.transact
        _ <- Uniques.i(1).float(float1).save.transact
        _ <- Uniques.i(1).double(double1).save.transact
        _ <- Uniques.i(1).boolean(boolean1).save.transact
        _ <- Uniques.i(1).bigInt(bigInt1).save.transact
        _ <- Uniques.i(1).bigDecimal(bigDecimal1).save.transact
        _ <- Uniques.i(1).date(date1).save.transact
        _ <- Uniques.i(1).uuid(uuid1).save.transact
        _ <- Uniques.i(1).uri(uri1).save.transact
        _ <- Uniques.i(1).byte(byte1).save.transact
        _ <- Uniques.i(1).short(short1).save.transact
        _ <- Uniques.i(1).char(char1).save.transact

        // Update with looked up unique value
        _ <- Uniques.i(2).string_(string1).update.transact
        _ <- Uniques.i(2).int_(int1).update.transact
        _ <- Uniques.i(2).long_(long1).update.transact
        _ <- Uniques.i(2).float_(float1).update.transact
        _ <- Uniques.i(2).double_(double1).update.transact
        _ <- Uniques.i(2).boolean_(boolean1).update.transact
        _ <- Uniques.i(2).bigInt_(bigInt1).update.transact
        _ <- Uniques.i(2).bigDecimal_(bigDecimal1).update.transact
        _ <- Uniques.i(2).date_(date1).update.transact
        _ <- Uniques.i(2).uuid_(uuid1).update.transact
        _ <- Uniques.i(2).uri_(uri1).update.transact
        _ <- Uniques.i(2).byte_(byte1).update.transact
        _ <- Uniques.i(2).short_(short1).update.transact
        _ <- Uniques.i(2).char_(char1).update.transact

        // i has been updated
        _ <- Uniques.i.string_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.int_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.long_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.float_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.double_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.boolean_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.bigInt_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.bigDecimal_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.date_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.uuid_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.uri_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.byte_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.short_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.char_.query.get.map(_ ==> List(2))
      } yield ()
    }


    "Update unique value itself" - unique { implicit conn =>
      for {
        _ <- Uniques.int.insert(1).transact
        _ <- Uniques.int.query.get.map(_ ==> List(1))

        // Find by current value 1 and update to 2
        _ <- Uniques.int_(1).int(2).update.transact
        _ <- Uniques.int.query.get.map(_ ==> List(2))
      } yield ()
    }


    "Multiple entities updated" - unique { implicit conn =>
      for {
        List(a, b, c) <- Uniques.i.int.insert(
          (1, int1),
          (2, int2),
          (3, int3),
        ).transact.map(_.ids)
        _ <- Uniques.id.a1.i.int.query.get.map(_ ==> List(
          (a, 1, int1),
          (b, 2, int2),
          (c, 3, int3),
        ))

        _ <- Uniques.i(4).int_(int2, int3).update.transact
        _ <- Uniques.id.a1.i.query.get.map(_ ==> List(
          (a, 1),
          (b, 4),
          (c, 4),
        ))
      } yield ()
    }


    "Delete individual attribute value(s) with update" - unique { implicit conn =>
      for {
        _ <- Uniques.int.i.s.insert(0, 1, "a").transact
        _ <- Uniques.i.s.query.get.map(_ ==> List((1, "a")))

        // Apply empty value to delete attribute of entity (entity remains)
        _ <- Uniques.int_(0).s().update.transact
        _ <- Uniques.i.s_?.query.get.map(_ ==> List((1, None)))
      } yield ()
    }


    "Update multiple attributes" - unique { implicit conn =>
      for {
        _ <- Uniques.int.i.s.insert(0, 1, "a").transact
        _ <- Uniques.i.s.query.get.map(_ ==> List((1, "a")))

        // Apply empty value to delete attribute of entity (entity remains)
        _ <- Uniques.int_(0).i(2).s("b").update.transact
        _ <- Uniques.i.s.query.get.map(_ ==> List((2, "b")))
      } yield ()
    }


    "Referenced attributes" - unique { implicit conn =>
      for {
        _ <- Uniques.int(0).i(1).Ref.i(2).save.transact
        _ <- Uniques.i.Ref.i.query.get.map(_ ==> List((1, 2)))

        _ <- Uniques.int_(0).i(3).Ref.i(4).update.transact
        _ <- Uniques.i.Ref.i.query.get.map(_ ==> List((3, 4)))

        _ <- Uniques.int_(0).Ref.i(5).update.transact
        _ <- Uniques.i.Ref.i.query.get.map(_ ==> List((3, 5)))
      } yield ()
    }


    "Semantics" - unique { implicit conn =>
      // Depends on wether the attribute name is a reserved keyword of the tested database
      val attr = database match {
        case "Mysql" => "string_"
        case _       => "string"
      }
      for {
        _ <- Uniques.i(1).i(2).int_(1).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute Uniques.i"
          }

        _ <- Uniques.i_(1).i(2).update.transact

        _ <- Uniques.int_(1).string_("x").s("c").update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can only apply one unique attribute value for update. Found:\n" +
              s"""AttrOneTacString("Uniques", "$attr", Eq, Seq("x"), None, None, Nil, Nil, None, None, Seq(0, 3))"""
          }

        _ <- Uniques.ints_(1).s("b").update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can only lookup entity with card-one attribute value. Found:\n" +
              """AttrSetTacInt("Uniques", "ints", Eq, Seq(Set(1)), None, None, Nil, Nil, None, None, Seq(0, 25))"""
          }
      } yield ()
    }
  }
}
