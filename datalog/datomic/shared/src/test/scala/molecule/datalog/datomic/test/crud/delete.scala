package molecule.datalog.datomic.test.crud

import molecule.coreTests.test.crud.delete._
import molecule.datalog.datomic.setup.CoreTestAsync

object Delete_id$ extends Delete_id with CoreTestAsync
object Delete_filter extends Delete_filter with CoreTestAsync
object Delete_uniqueAttr extends Delete_uniqueAttr with CoreTestAsync
