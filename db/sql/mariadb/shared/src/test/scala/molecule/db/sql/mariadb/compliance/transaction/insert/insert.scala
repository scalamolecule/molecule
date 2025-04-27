package molecule.db.sql.mariadb.compliance.transaction.insert

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.insert.*
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class InsertCardOneTest extends Test {
  InsertCardOne(this, Api_mariadb_async)
}
class InsertCardSeqTest extends MUnitSuiteWithArrays {
  InsertCardSeq(this, Api_mariadb_async)
}
class InsertCardSetTest extends Test {
  InsertCardSet(this, Api_mariadb_async)
}
class InsertCardMapTest extends Test {
  InsertCardMap(this, Api_mariadb_async)
}
class InsertRefsTest extends Test {
  InsertRefs(this, Api_mariadb_async)
}
class InsertSemanticsTest extends Test {
  InsertSemantics(this, Api_mariadb_async)
}
