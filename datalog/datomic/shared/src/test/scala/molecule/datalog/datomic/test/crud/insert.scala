package molecule.datalog.datomic.test.crud

import molecule.coreTests.test.crud.insert.{InsertFlat, InsertSemantics}
import molecule.datalog.datomic.setup.CoreTestAsync

object InsertFlat extends InsertFlat with CoreTestAsync
object InsertSemantics extends InsertSemantics with CoreTestAsync
