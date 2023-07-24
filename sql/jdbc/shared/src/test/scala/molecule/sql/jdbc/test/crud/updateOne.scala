package molecule.sql.jdbc.test.crud

import molecule.coreTests.test.crud.update.one._
import molecule.sql.jdbc.setup.CoreTestAsync

object UpdateOne_id$ extends UpdateOne_id with CoreTestAsync
object UpdateOne_filter extends UpdateOne_filter with CoreTestAsync
object UpdateOne_uniqueAttr extends UpdateOne_uniqueAttr with CoreTestAsync
