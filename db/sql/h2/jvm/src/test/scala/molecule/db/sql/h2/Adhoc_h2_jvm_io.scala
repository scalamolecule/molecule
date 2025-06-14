package molecule.db.sql.h2

import boopickle.Default.*
import molecule.core.setup.{MUnit_io, TestUtils}
import molecule.db.compliance.domains.dsl.Types.Entity
import molecule.db.sql.h2.io.*
import molecule.db.sql.h2.setup.DbProviders_h2


class Adhoc_h2_jvm_io extends MUnit_io
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
