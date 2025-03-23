package molecule.frontendTests

import boopickle.Default._
import molecule.core.marshalling.Boopicklers._
import molecule.core.util.Executor._
import molecule.frontendTests.domains.dsl.Types._
import molecule.frontendTests.setup.{MUnitSuite, Test_mysql}
import molecule.sql.mysql.async._


//class AdhocJS extends MUnitSuiteWithArrays with Test_mysql {
class AdhocJS_mysql extends MUnitSuite with Test_mysql {

  "types" - types { implicit conn =>
    for {
      List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
      _ <- Entity.int(3).save.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- Entity(a).int(10).update.transact
      _ <- Entity(b).delete.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))

    } yield ()
  }
}
