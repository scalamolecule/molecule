package molecule.datalog.datomic.compliance.validation

import molecule.coreTests.spi.validation.StringValidationFns
import molecule.datalog.datomic.setup.TestAsync_datomic

object Test_StringValidationFns extends StringValidationFns with TestAsync_datomic
