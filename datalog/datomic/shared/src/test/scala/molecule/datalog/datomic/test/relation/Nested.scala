package molecule.datalog.datomic.test.relation

import molecule.coreTests.test.relation.nested._
import molecule.datalog.datomic.setup.TestAsync_datomic

object NestedBasic extends NestedBasic with TestAsync_datomic
object NestedExpr extends NestedExpr with TestAsync_datomic
object NestedLevels extends NestedLevels with TestAsync_datomic
object NestedOptional extends NestedOptional with TestAsync_datomic
object NestedRef extends NestedRef with TestAsync_datomic
object NestedSemantics extends NestedSemantics with TestAsync_datomic
