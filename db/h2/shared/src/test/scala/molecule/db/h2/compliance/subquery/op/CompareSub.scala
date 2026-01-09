package molecule.db.h2.compliance.subquery.op

import scala.concurrent.Future
import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.spi.Conn
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.h2.async.*
import molecule.db.h2.setup.DbProviders_h2

//import molecule.core.dataModel.*

class CompareSub extends MUnit with DbProviders_h2 with TestUtils {

  "where" - types {
    for {
      _ <- Entity.s.i.Ref.i.s.insert(
        ("a", 1, 2, "x"),
        ("b", 4, 3, "y"),
      ).transact


      // Ref.i _doesn't_ have a "match" outside, then we can interpret it as a subquery...
      _ <- Entity.s.i.>(Ref.i(max)).query.i.get.map(_ ==> List(("b", 4, 3))) // Entity.i 4 > Ref.i(max) 3

    } yield ()
  }
}
