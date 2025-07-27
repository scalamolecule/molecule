package molecule.db.h2

import molecule.core.setup.{MUnit_io, TestUtils}
import molecule.db.compliance.domains.dsl.Types.Entity
import io.*
import molecule.db.h2.setup.DbProviders_h2


class Adhoc_h2_jvm_io extends MUnit_io
  with DbProviders_h2 with TestUtils {


  "commit" - types {

    for {
      _ <- Entity.i.insert(1, 2, 3).transact

      _ <- Entity.i.query.stream
        .compile
        .toList
        .map(_ ==> List(1, 2, 3))
    } yield ()
  }
}
