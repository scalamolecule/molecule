package molecule.datalog.datomic.compliance.crud.update.attrOp

import molecule.coreTests.spi.crud.update.attrOp._
import molecule.datalog.datomic.setup.Test_datomic_async


object Test_AttrOp_Boolean extends AttrOp_Boolean with Test_datomic_async
object Test_AttrOp_String extends AttrOp_String with Test_datomic_async

