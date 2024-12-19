package molecule.sql.mariadb.compliance.transaction.update.attrOp

import molecule.coreTests.spi.transaction.update.attrOp._
import molecule.sql.mariadb.setup.Test_mariadb_async


object Test_AttrOp_Boolean extends AttrOp_Boolean with Test_mariadb_async
object Test_AttrOp_String extends AttrOp_String with Test_mariadb_async

