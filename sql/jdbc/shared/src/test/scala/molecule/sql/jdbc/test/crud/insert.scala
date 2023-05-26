package molecule.sql.jdbc.test.crud

import molecule.coreTests.test.crud.insert.{InsertFlat, InsertSemantics}
import molecule.sql.jdbc.setup.CoreTestAsync

object InsertFlat extends InsertFlat with CoreTestAsync
object InsertSemantics extends InsertSemantics with CoreTestAsync
