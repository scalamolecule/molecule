package molecule.sql.mariadb.compliance.transaction.insert

import molecule.coreTests.setup.{Test, MUnitSuiteWithArrays}
import molecule.coreTests.spi.transaction.insert._
import molecule.sql.mariadb.setup.Api_mariadb_async

class InsertCardOne extends Test {
  InsertCardOne(this, Api_mariadb_async)
}
class InsertCardSeq extends MUnitSuiteWithArrays {
  InsertCardSeq(this, Api_mariadb_async)
}
class InsertCardSet extends Test {
  InsertCardSet(this, Api_mariadb_async)
}
class InsertCardMap extends Test {
  InsertCardMap(this, Api_mariadb_async)
}
class InsertRefs extends Test {
  InsertRefs(this, Api_mariadb_async)
}
class InsertSemantics extends Test {
  InsertSemantics(this, Api_mariadb_async)
}
