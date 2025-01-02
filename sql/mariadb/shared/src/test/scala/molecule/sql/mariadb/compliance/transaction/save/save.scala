package molecule.sql.mariadb.compliance.transaction.save

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.save._
import molecule.sql.mariadb.setup.Api_mariadb_async

class SaveCardOne extends Test {
  SaveCardOne(this, Api_mariadb_async)
}
class SaveCardSeq extends MUnitSuiteWithArrays {
  SaveCardSeq(this, Api_mariadb_async)
}
class SaveCardSet extends Test {
  SaveCardSet(this, Api_mariadb_async)
}
class SaveCardMap extends Test {
  SaveCardMap(this, Api_mariadb_async)
}
class SaveRefs extends Test {
  SaveRefs(this, Api_mariadb_async)
}
class SaveSemantics extends Test {
  SaveSemantics(this, Api_mariadb_async)
}
