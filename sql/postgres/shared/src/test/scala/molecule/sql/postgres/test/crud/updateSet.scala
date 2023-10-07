package molecule.sql.postgres.test.crud

import molecule.coreTests.test.crud.update.set._
import molecule.sql.postgres.setup.TestAsync_postgres

object UpdateSet_id extends UpdateSet_id with TestAsync_postgres
object UpdateSet_filter extends UpdateSet_filter with TestAsync_postgres
object UpdateSet_uniqueAttr extends UpdateSet_uniqueAttr with TestAsync_postgres