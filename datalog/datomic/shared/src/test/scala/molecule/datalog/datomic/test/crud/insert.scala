package molecule.datalog.datomic.test.crud

import molecule.coreTests.test.crud.insert.{InsertRefs, InsertSemantics}
import molecule.datalog.datomic.setup.CoreTestAsync

object InsertRefs extends InsertRefs with CoreTestAsync
object InsertSemantics extends InsertSemantics with CoreTestAsync
