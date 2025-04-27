package molecule.db.sql.mysql.compliance.action.insert

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.insert.*
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async

class InsertCardOneTest extends Test {
  InsertCardOne(this, Api_mysql_async)
}
class InsertCardSeqTest extends MUnitSuiteWithArrays {
  InsertCardSeq(this, Api_mysql_async)
}
class InsertCardSetTest extends Test {
  InsertCardSet(this, Api_mysql_async)
}
class InsertCardMapTest extends Test {
  InsertCardMap(this, Api_mysql_async)
}
class InsertRefsTest extends Test {
  InsertRefs(this, Api_mysql_async)
}
class InsertSemanticsTest extends Test {
  InsertSemantics(this, Api_mysql_async)
}
