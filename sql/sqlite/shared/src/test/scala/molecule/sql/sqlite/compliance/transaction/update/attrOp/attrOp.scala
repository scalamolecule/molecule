package molecule.sql.sqlite.compliance.transaction.update.attrOp

import molecule.coreTests.spi.transaction.update.attrOp._
import molecule.sql.sqlite.setup.Test_sqlite_async


object Test_AttrOp_Boolean extends AttrOp_Boolean with Test_sqlite_async
object Test_AttrOp_String extends AttrOp_String with Test_sqlite_async

