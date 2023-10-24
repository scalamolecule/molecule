package molecule.document.mongodb.compliance.validation

import molecule.coreTests.spi.validation.StringValidationFns
import molecule.document.mongodb.setup.TestAsync_mongodb

object StringValidationFns extends StringValidationFns with TestAsync_mongodb
