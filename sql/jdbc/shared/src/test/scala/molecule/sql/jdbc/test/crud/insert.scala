package molecule.sql.jdbc.test.crud

import molecule.coreTests.test.crud.insert.{InsertRefs, InsertSemantics}
import molecule.sql.jdbc.setup.CoreTestAsync

object InsertRefs extends InsertRefs with CoreTestAsync
object InsertSemantics extends InsertSemantics with CoreTestAsync
