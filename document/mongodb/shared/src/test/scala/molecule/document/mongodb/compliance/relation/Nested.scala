package molecule.document.mongodb.compliance.relation

import molecule.coreTests.spi.relation.nested._
import molecule.document.mongodb.setup.{TestAsync_mongodb, TestSuiteArray_mongodb}
import molecule.document.mongodb.spi.SpiAsync_mongodb

object NestedBasic extends NestedBasic with TestAsync_mongodb
object NestedExpr extends NestedExpr with TestAsync_mongodb
object NestedLevels extends NestedLevels with TestAsync_mongodb
object NestedOptional extends NestedOptional with TestAsync_mongodb
object NestedRef extends NestedRef with TestAsync_mongodb
object NestedSemantics extends NestedSemantics with TestAsync_mongodb
object NestedTypes extends NestedTypes with TestSuiteArray_mongodb with SpiAsync_mongodb

