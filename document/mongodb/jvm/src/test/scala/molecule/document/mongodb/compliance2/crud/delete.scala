package molecule.document.mongodb.compliance2.crud

import molecule.coreTests.spi.crud.delete._
import molecule.document.mongodb.setup.TestAsync_mongodb2

object Delete_id extends Delete_id with TestAsync_mongodb2

// Molecule only implements the embedded data model of MongoDB so
// no external references/relationships are used. Please use separate
// queries for that or use $lookup in raw MongoDB aggregation.
// object Delete_id_ref extends Delete_id_ref with TestAsync_mongodb2

object Delete_filter extends Delete_filter with TestAsync_mongodb2
object Delete_uniqueAttr extends Delete_uniqueAttr with TestAsync_mongodb2
