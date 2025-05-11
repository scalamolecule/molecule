package molecule.db.sql.sqlite.compliance.action.insert

import molecule.db.compliance.setup.{MUnitSuiteWithArrays, Test}
import molecule.db.compliance.test.action.insert.{InsertCardMap, InsertCardOne, InsertCardSeq, InsertCardSet, InsertRefs, InsertSemantics}
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class InsertCardOneTest extends Test {
  InsertCardOne(this, Api_sqlite_async)
}
class InsertCardSetTest extends Test {
  InsertCardSet(this, Api_sqlite_async)
}
class InsertCardMapTest extends Test {
  InsertCardMap(this, Api_sqlite_async)
}
class InsertCardSeqTest extends MUnitSuiteWithArrays {
  InsertCardSeq(this, Api_sqlite_async)
}
class InsertRefsTest extends Test {
  InsertRefs(this, Api_sqlite_async)
}
class InsertSemanticsTest extends Test {
  InsertSemantics(this, Api_sqlite_async)
}
