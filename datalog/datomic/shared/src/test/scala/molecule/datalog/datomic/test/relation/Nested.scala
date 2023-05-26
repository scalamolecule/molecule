package molecule.datalog.datomic.test.relation

import molecule.coreTests.test.relation.nested._
import molecule.datalog.datomic.setup.CoreTestAsync

object NestedBasic extends NestedBasic with CoreTestAsync
object NestedComposite extends NestedComposite with CoreTestAsync
object NestedExpr extends NestedExpr with CoreTestAsync
object NestedLevels extends NestedLevels with CoreTestAsync
object NestedRef extends NestedRef with CoreTestAsync
object NestedSemantics extends NestedSemantics with CoreTestAsync

//object NestedTypes extends NestedTypes with TestAsync
