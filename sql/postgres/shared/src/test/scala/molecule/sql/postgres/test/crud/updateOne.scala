package molecule.sql.postgres.test.crud

import molecule.coreTests.test.crud.update.one._
import molecule.sql.postgres.setup.TestAsync_postgres

object UpdateOne_id extends UpdateOne_id with TestAsync_postgres
object UpdateOne_filter extends UpdateOne_filter with TestAsync_postgres
object UpdateOne_uniqueAttr extends UpdateOne_uniqueAttr with TestAsync_postgres
