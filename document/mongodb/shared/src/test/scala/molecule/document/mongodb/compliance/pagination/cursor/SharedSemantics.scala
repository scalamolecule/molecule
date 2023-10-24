package molecule.document.mongodb.compliance.pagination.cursor

import molecule.coreTests.spi.pagination.cursor.SharedSemantics
import molecule.document.mongodb.setup.TestAsync_mongodb

object SharedSemantics extends SharedSemantics with TestAsync_mongodb
