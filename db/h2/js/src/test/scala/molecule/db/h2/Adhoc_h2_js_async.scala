package molecule.db.h2

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.h2.async.*
import molecule.db.h2.setup.DbProviders_h2
import org.scalactic.Equality


class Adhoc_h2_js_async extends MUnit with DbProviders_h2 with TestUtils {


  "types" - types {
    import molecule.db.compliance.domains.dsl.Types.*
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val a = (1, Some(true))
    val b = (2, Some(false))
    val x = (3, Option.empty[Boolean])
    for {
      _ <- Entity.i.boolean_?.insert(List(a, b, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.boolean_?.query.get.map(_ ==> List(a, b, x))

      // Find optional values matching
      _ <- Entity.i.a1.boolean_?(Some(true)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.boolean_?(Some(false)).query.get.map(_ ==> List(b))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.boolean_?(Option.empty[Boolean]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.boolean_().query.get.map(_ ==> List(3))

    } yield ()
  }




  //  "refs1" - refs {
  //    import molecule.db.compliance.domains.dsl.Refs.*
  //    for {
  //      _ <- A.i.Bb.i.query.get
  //      _ <- A.i.B.i.query.get
  //      _ <- B.i.A.i.query.get
  //      _ <- B.i.Aa.i.query.get
  //
  //    } yield ()
  //  }


  //  "refs" - refs {
  //    import molecule.db.compliance.domains.dsl.Refs.*
  //    for {
  //
  //      _ <- A.i(1).B.i(2).save.transact
  //      _ <- A.i.B.i.query.get.map(_ ==> List((1, 2)))
  //
  //    } yield ()
  //  }
  //
  //
  //    "validation" - validation {
  //      import molecule.db.compliance.domains.dsl.Validation._
  //      for {
  //
  //        id <- MandatoryAttr.name("Bob").age(42)
  //          .hobbies(Set("golf")).save.transact.map(_.id)
  //        //          .hobbies(Set("golf", "stamps")).save.transact.map(_.id)
  //
  //        //        // We can remove a value from a Set as long as it's not the last value
  //        //        _ <- MandatoryAttr(id).hobbies.remove("stamps").update.transact
  //
  //        // Can't remove the last value of a mandatory attribute Set of values
  //        _ <- MandatoryAttr(id).hobbies.remove("golf").update.transact
  //          .map(_ ==> "Unexpected success").recover {
  //            case ModelError(error) =>
  //              error ==>
  //                """Can't delete mandatory attributes (or remove last values of card-many attributes):
  //                  |  MandatoryAttr.hobbies
  //                  |""".stripMargin
  //          }
  //
  //      } yield ()
  //    }
}
