package molecule.sql.h2.compliance.crud

import molecule.coreTests.spi.crud.update.seq._
import molecule.sql.h2.setup.TestAsync_h2

object UpdateSeq_id extends UpdateSeq_id with TestAsync_h2
object UpdateSeq_filter extends UpdateSeq_filter with TestAsync_h2
object UpdateSeq_uniqueAttr extends UpdateSeq_uniqueAttr with TestAsync_h2
