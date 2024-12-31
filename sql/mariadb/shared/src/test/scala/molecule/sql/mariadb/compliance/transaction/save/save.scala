package molecule.sql.mariadb.compliance.transaction.save

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.save._
import molecule.sql.mariadb.setup.Api_mariadb_async

class SaveCardOne extends MUnitSuite {
  SaveCardOne(this, Api_mariadb_async)
}
class SaveCardSeq extends MUnitSuiteWithArrays {
  SaveCardSeq(this, Api_mariadb_async)
}
class SaveCardSet extends MUnitSuite {
  SaveCardSet(this, Api_mariadb_async)
}
class SaveCardMap extends MUnitSuite {
  SaveCardMap(this, Api_mariadb_async)
}
class SaveRefs extends MUnitSuite {
  SaveRefs(this, Api_mariadb_async)
}
class SaveSemantics extends MUnitSuite {
  SaveSemantics(this, Api_mariadb_async)
}
