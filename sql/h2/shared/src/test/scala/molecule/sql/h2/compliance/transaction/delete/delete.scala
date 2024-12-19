package molecule.sql.h2.compliance.transaction.delete

import molecule.coreTests.spi.transaction.delete._
import molecule.sql.h2.setup.Test_h2_async

object Test_Delete_id extends Delete_id with Test_h2_async
object Test_Delete_filter extends Delete_filter with Test_h2_async
