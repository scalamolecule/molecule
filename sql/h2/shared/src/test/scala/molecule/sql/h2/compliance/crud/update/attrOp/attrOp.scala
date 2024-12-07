package molecule.sql.h2.compliance.crud.update.attrOp

import molecule.coreTests.spi.crud.update.attrOp._
import molecule.sql.h2.setup.Test_h2_async


object Test_AttrOp_Boolean extends AttrOp_Boolean with Test_h2_async
object Test_AttrOp_String extends AttrOp_String with Test_h2_async

