package molecule.document.mongodb.compliance2.relation

import molecule.coreTests.spi.relation.nested._
import molecule.document.mongodb.setup.TestAsync_mongodb2

object NestedBasic extends NestedBasic with TestAsync_mongodb2
object NestedExpr extends NestedExpr with TestAsync_mongodb2
object NestedLevels extends NestedLevels with TestAsync_mongodb2
object NestedOptional extends NestedOptional with TestAsync_mongodb2
object NestedRef extends NestedRef with TestAsync_mongodb2
object NestedSemantics extends NestedSemantics with TestAsync_mongodb2
object NestedTypes extends NestedTypes with TestAsync_mongodb2

