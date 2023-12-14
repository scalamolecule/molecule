package molecule.document.mongodb.compliance2.validation

import molecule.coreTests.spi.validation.StringValidationFns
import molecule.document.mongodb.setup.TestAsync_mongodb

object StringValidationFns extends StringValidationFns with TestAsync_mongodb
