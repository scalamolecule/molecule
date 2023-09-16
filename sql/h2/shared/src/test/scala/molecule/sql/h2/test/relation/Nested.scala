package molecule.sql.h2.test.relation

import molecule.coreTests.test.relation.nested._
import molecule.sql.h2.setup.TestAsync_h2

object NestedBasic extends NestedBasic with TestAsync_h2
object NestedExpr extends NestedExpr with TestAsync_h2
object NestedLevels extends NestedLevels with TestAsync_h2
object NestedOptional extends NestedOptional with TestAsync_h2
object NestedRef extends NestedRef with TestAsync_h2
object NestedSemantics extends NestedSemantics with TestAsync_h2
object NestedTypes extends NestedTypes with TestAsync_h2

