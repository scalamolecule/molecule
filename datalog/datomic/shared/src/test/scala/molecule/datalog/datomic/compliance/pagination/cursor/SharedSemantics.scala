package molecule.datalog.datomic.compliance.pagination.cursor

import molecule.coreTests.spi.pagination.cursor.SharedSemantics
import molecule.datalog.datomic.setup.Test_datomic_async

object Test_SharedSemantics extends SharedSemantics with Test_datomic_async
