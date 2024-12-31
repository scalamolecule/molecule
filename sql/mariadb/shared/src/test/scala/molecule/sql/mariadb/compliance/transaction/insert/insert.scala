package molecule.sql.mariadb.compliance.transaction.insert

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.insert._
import molecule.sql.mariadb.setup.Api_mariadb_async

class InsertCardOne extends MUnitSuite {
  InsertCardOne(this, Api_mariadb_async)
}
class InsertCardSeq extends MUnitSuiteWithArrays {
  InsertCardSeq(this, Api_mariadb_async)
}
class InsertCardSet extends MUnitSuite {
  InsertCardSet(this, Api_mariadb_async)
}
class InsertCardMap extends MUnitSuite {
  InsertCardMap(this, Api_mariadb_async)
}
class InsertRefs extends MUnitSuite {
  InsertRefs(this, Api_mariadb_async)
}
class InsertSemantics extends MUnitSuite {
  InsertSemantics(this, Api_mariadb_async)
}
