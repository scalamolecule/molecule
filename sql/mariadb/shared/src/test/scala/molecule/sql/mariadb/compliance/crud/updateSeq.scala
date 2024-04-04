package molecule.sql.mariadb.compliance.crud

import molecule.coreTests.spi.crud.update.seq._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object UpdateSeq_id extends UpdateSeq_id with TestAsync_mariadb
object UpdateSeq_filter extends UpdateSeq_filter with TestAsync_mariadb
object UpdateSeq_uniqueAttr extends UpdateSeq_uniqueAttr with TestAsync_mariadb
