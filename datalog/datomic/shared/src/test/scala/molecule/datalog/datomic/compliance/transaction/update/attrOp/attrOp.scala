package molecule.datalog.datomic.compliance.transaction.update.attrOp

import molecule.coreTests.spi.transaction.update.attrOp._
import molecule.datalog.datomic.setup.Test_datomic_async


object Test_AttrOp_Boolean extends AttrOp_Boolean with Test_datomic_async
object Test_AttrOp_String extends AttrOp_String with Test_datomic_async

