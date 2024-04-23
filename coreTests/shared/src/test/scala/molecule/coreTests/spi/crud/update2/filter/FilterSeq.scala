package molecule.coreTests.spi.crud.update2.filter

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    // OBS: Updates by filters will update _all_ matching entities!

    // Use tacit attributes as filters to match entities to be updated.
    // Apply new values to mandatory attributes.

    "Seq asserted" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.iSeq_?.int.insert(
          (None, 0),
          (Some(List(1, 2)), 1),
          (Some(List(2, 3)), 2),
        ).transact.map(_.ids)

        // Update all entities where `iSeq` is asserted
        _ <- Ns
          .iSeq_ // filter by tacit attribute
          .int(3) // apply new value to mandatory attribute
          .update.transact

        // 2 entities updated
        _ <- Ns.id.a1.iSeq_?.int.query.get.map(_ ==> List(
          (a, None, 0),
          (b, Some(List(1, 2)), 3), // updated
          (c, Some(List(2, 3)), 3), // updated
        ))

        // Order of attributes in update molecule makes no difference
        _ <- Ns.int(4).iSeq_.update.transact
        _ <- Ns.id.a1.iSeq_?.int.query.get.map(_ ==> List(
          (a, None, 0),
          (b, Some(List(1, 2)), 4), // updated
          (c, Some(List(2, 3)), 4), // updated
        ))
      } yield ()
    }


    "Seq not asserted" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.iSeq_?.int.insert(
          (None, 0),
          (Some(List(1, 2)), 1),
          (Some(List(2, 3)), 2),
        ).transact.map(_.ids)

        // Update all entities where `iSeq` is not asserted (null)
        _ <- Ns.iSeq_().int(3).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Please add at least one tacit filter attribute (applying empty value not counting)."
          }

        // Add at leas one tacit filter attribute apart from filter applying nothing
        _ <- Ns.iSeq_().int_.int(3).update.transact

        // 1 entity updated
        _ <- Ns.id.a1.iSeq_?.int.query.get.map(_ ==> List(
          (a, None, 3), // updated
          (b, Some(List(1, 2)), 1),
          (c, Some(List(2, 3)), 2),
        ))
      } yield ()
    }


    "Seq equality" - types { implicit conn =>
      // Filtering updates by equality of collections is not supported by molecule.
      // Instead use has/hasNo.
      for {
        _ <- Ns("42").iSeq_(List(1)).int(3).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filtering by Seq match (Ns.iSeq) not supported in updates."
          }
      } yield ()
    }


    "Has value" - types { implicit conn =>
      for {
        List(a, b) <- Ns.iSeq.int.insert(
          (List(0, 1, 2), 1),
          (List(2, 3, 4), 2),
        ).transact.map(_.ids)

        // Update all entities where `iSeq` has a value 1
        _ <- Ns.iSeq_.has(1).int(3).update.transact

        // 1 entity updated
        _ <- Ns.id.a1.iSeq.int.query.get.map(_ ==> List(
          (a, List(0, 1, 2), 3), // updated
          (b, List(2, 3, 4), 2),
        ))

        // Update all entities where iSeq has any values of Iterable
        _ <- Ns.iSeq_.has(Set(3, 7)).int(4).update.transact
        _ <- Ns.id.a1.iSeq.int.query.get.map(_ ==> List(
          (a, List(0, 1, 2), 3),
          (b, List(2, 3, 4), 4), // updated
        ))

        // Nothing updated since no iSeq has any values of Iterable
        _ <- Ns.iSeq_.has(List(5, 6)).int(5).update.transact
        _ <- Ns.id.a1.iSeq.int.query.get.map(_ ==> List(
          (a, List(0, 1, 2), 3),
          (b, List(2, 3, 4), 4),
        ))
      } yield ()
    }


    "Doesn't have value" - types { implicit conn =>
      for {
        List(a, b) <- Ns.iSeq.int.insert(
          (List(1, 2), 1),
          (List(2, 3), 2),
        ).transact.map(_.ids)

        // Update all entities where `iSeq` has no value 1
        _ <- Ns.iSeq_.hasNo(1).int(3).update.transact

        // 1 entity updated
        _ <- Ns.id.a1.iSeq.int.query.get.map(_ ==> List(
          (a, List(1, 2), 1),
          (b, List(2, 3), 3), // updated
        ))

        // Update all entities where iSeq has neither 3 or 4
        _ <- Ns.iSeq_.hasNo(3, 4).int(4).update.transact
        _ <- Ns.id.a1.iSeq.int.query.get.map(_ ==> List(
          (a, List(1, 2), 4), // updated
          (b, List(2, 3), 3),
        ))

        // Nothing updated since all iSeq has neither 1, 2 or 3
        _ <- Ns.iSeq_.hasNo(1, 2, 3).int(5).update.transact
        _ <- Ns.id.a1.iSeq.int.query.get.map(_ ==> List(
          (a, List(1, 2), 4),
          (b, List(2, 3), 3),
        ))
      } yield ()
    }


    "Types" - types { implicit conn =>
      for {
        // Initial values
        _ <- Ns.i(1).stringSeq(List(string1)).save.transact
        _ <- Ns.i(1).intSeq(List(int1)).save.transact
        _ <- Ns.i(1).longSeq(List(long1)).save.transact
        _ <- Ns.i(1).floatSeq(List(float1)).save.transact
        _ <- Ns.i(1).doubleSeq(List(double1)).save.transact
        _ <- Ns.i(1).booleanSeq(List(boolean1)).save.transact
        _ <- Ns.i(1).bigIntSeq(List(bigInt1)).save.transact
        _ <- Ns.i(1).bigDecimalSeq(List(bigDecimal1)).save.transact
        _ <- Ns.i(1).dateSeq(List(date1)).save.transact
        _ <- Ns.i(1).durationSeq(List(duration1)).save.transact
        _ <- Ns.i(1).instantSeq(List(instant1)).save.transact
        _ <- Ns.i(1).localDateSeq(List(localDate1)).save.transact
        _ <- Ns.i(1).localTimeSeq(List(localTime1)).save.transact
        _ <- Ns.i(1).localDateTimeSeq(List(localDateTime1)).save.transact
        _ <- Ns.i(1).offsetTimeSeq(List(offsetTime1)).save.transact
        _ <- Ns.i(1).offsetDateTimeSeq(List(offsetDateTime1)).save.transact
        _ <- Ns.i(1).zonedDateTimeSeq(List(zonedDateTime1)).save.transact
        _ <- Ns.i(1).uuidSeq(List(uuid1)).save.transact
        _ <- Ns.i(1).uriSeq(List(uri1)).save.transact
        _ <- Ns.i(1).byteArray(Array(byte1)).save.transact
        _ <- Ns.i(1).shortSeq(List(short1)).save.transact
        _ <- Ns.i(1).charSeq(List(char1)).save.transact

        // Update i using asserted attribute filter
        _ <- Ns.i(2).stringSeq_.update.transact
        _ <- Ns.i(2).intSeq_.update.transact
        _ <- Ns.i(2).longSeq_.update.transact
        _ <- Ns.i(2).floatSeq_.update.transact
        _ <- Ns.i(2).doubleSeq_.update.transact
        _ <- Ns.i(2).booleanSeq_.update.transact
        _ <- Ns.i(2).bigIntSeq_.update.transact
        _ <- Ns.i(2).bigDecimalSeq_.update.transact
        _ <- Ns.i(2).dateSeq_.update.transact
        _ <- Ns.i(2).durationSeq_.update.transact
        _ <- Ns.i(2).instantSeq_.update.transact
        _ <- Ns.i(2).localDateSeq_.update.transact
        _ <- Ns.i(2).localTimeSeq_.update.transact
        _ <- Ns.i(2).localDateTimeSeq_.update.transact
        _ <- Ns.i(2).offsetTimeSeq_.update.transact
        _ <- Ns.i(2).offsetDateTimeSeq_.update.transact
        _ <- Ns.i(2).zonedDateTimeSeq_.update.transact
        _ <- Ns.i(2).uuidSeq_.update.transact
        _ <- Ns.i(2).uriSeq_.update.transact
        _ <- Ns.i(2).byteArray_.update.transact
        _ <- Ns.i(2).shortSeq_.update.transact
        _ <- Ns.i(2).charSeq_.update.transact

        // i has been updated
        _ <- Ns.i.stringSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.intSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.longSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.floatSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.doubleSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.booleanSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.bigIntSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.bigDecimalSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.dateSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.durationSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.instantSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.localDateSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.localTimeSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.localDateTimeSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.offsetTimeSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.offsetDateTimeSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.zonedDateTimeSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.uuidSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.uriSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.byteArray_.query.get.map(_.head ==> 2)
        _ <- Ns.i.shortSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.charSeq_.query.get.map(_.head ==> 2)
      } yield ()
    }
  }
}
