package molecule.datalog.datomic.compliance.pagination.cursor

import molecule.coreTests.spi.pagination.cursor.SharedSemantics
import molecule.datalog.datomic.setup.TestAsync_datomic

object SharedSemantics extends SharedSemantics with TestAsync_datomic
