package molecule.sql.mysql.compliance.crud.update.attrOp

import molecule.coreTests.spi.crud.update.attrOp._
import molecule.sql.mysql.setup.Test_mysql_async


object Test_AttrOp_Boolean extends AttrOp_Boolean with Test_mysql_async
object Test_AttrOp_String extends AttrOp_String with Test_mysql_async

