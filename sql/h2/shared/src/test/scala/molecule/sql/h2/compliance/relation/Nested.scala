package molecule.sql.h2.compliance.relation

import molecule.coreTests.spi.relation.nested._
import molecule.sql.h2.setup.{TestAsync_h2, TestSuiteArray_h2}
import molecule.sql.h2.spi.SpiAsync_h2

object Test_NestedBasic extends NestedBasic with TestAsync_h2
object Test_NestedExpr extends NestedExpr with TestAsync_h2
object Test_NestedLevels extends NestedLevels with TestAsync_h2
object Test_NestedOptional extends NestedOptional with TestAsync_h2
object Test_NestedRef extends NestedRef with TestAsync_h2
object Test_NestedSemantics extends NestedSemantics with TestAsync_h2
object Test_NestedTypes extends NestedTypes with TestSuiteArray_h2 with SpiAsync_h2

