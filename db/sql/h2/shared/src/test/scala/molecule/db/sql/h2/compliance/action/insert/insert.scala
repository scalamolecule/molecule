package molecule.db.sql.h2.compliance.action.insert

import molecule.db.compliance.setup.{MUnitSuiteWithArrays, Test}
import molecule.db.compliance.test.action.insert.{InsertCardMap, InsertCardOne, InsertCardSeq, InsertCardSet, InsertRefs, InsertSemantics}
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_async

class InsertCardOneTest extends Test {
  InsertCardOne(this, Api_h2_async)
}
class InsertCardSetTest extends Test {
  InsertCardSet(this, Api_h2_async)
}
class InsertCardMapTest extends Test {
  InsertCardMap(this, Api_h2_async)
}
class InsertCardSeqTest extends MUnitSuiteWithArrays {
  InsertCardSeq(this, Api_h2_async)
}
class InsertRefsTest extends Test {
  InsertRefs(this, Api_h2_async)
}
class InsertSemanticsTest extends Test {
  InsertSemantics(this, Api_h2_async)
}
