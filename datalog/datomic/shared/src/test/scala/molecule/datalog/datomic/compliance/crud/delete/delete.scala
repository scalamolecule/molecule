package molecule.datalog.datomic.compliance.crud.delete

import molecule.coreTests.spi.crud.delete._
import molecule.datalog.datomic.setup.Test_datomic_async

object Test_Delete_id extends Delete_id with Test_datomic_async
object Test_Delete_filter extends Delete_filter with Test_datomic_async
