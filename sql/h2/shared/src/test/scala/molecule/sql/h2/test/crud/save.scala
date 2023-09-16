package molecule.sql.h2.test.crud

import molecule.coreTests.test.crud.save._
import molecule.sql.h2.setup.TestAsync_h2

object SaveCardOne extends SaveCardOne with TestAsync_h2
object SaveCardSet extends SaveCardSet with TestAsync_h2
object SaveRefs extends SaveRefs with TestAsync_h2
object SaveSemantics extends SaveSemantics with TestAsync_h2
