package molecule.db.sql.h2

import boopickle.Default.*
import molecule.coreTests.domains.dsl.Types.Entity
import molecule.coreTests.setup.{TestUtils, Test_io}
import molecule.db.sql
import molecule.db.sql.h2.io.*
import molecule.db.sql.h2.setup.DbProviders_h2
//import scala.language.implicitConversions


class Adhoc_jvm_h2_io extends Test_io
  with DbProviders_h2 with TestUtils {


  "commit" - types { implicit conn =>

    for {
      _ <- Entity.i.insert(1, 2, 3).transact

      _ <- Entity.i.query.stream
        .compile
        .toList
        .map(_ ==> List(1, 2, 3))
    } yield ()
  }
}
