package molecule.datalog.datomic.compliance.relation

import molecule.coreTests.spi.relation.nested._
import molecule.datalog.datomic.setup.{TestAsync_datomic, TestSuiteArray_datomic}
import molecule.datalog.datomic.spi.SpiAsync_datomic

object NestedBasic extends NestedBasic with TestAsync_datomic
object NestedExpr extends NestedExpr with TestAsync_datomic
object NestedLevels extends NestedLevels with TestAsync_datomic
object NestedOptional extends NestedOptional with TestAsync_datomic
object NestedRef extends NestedRef with TestAsync_datomic
object NestedSemantics extends NestedSemantics with TestAsync_datomic
object NestedTypes extends NestedTypes with TestSuiteArray_datomic with SpiAsync_datomic
