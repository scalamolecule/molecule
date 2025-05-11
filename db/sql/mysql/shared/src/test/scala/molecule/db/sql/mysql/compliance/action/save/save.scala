package molecule.db.sql.mysql.compliance.action.save

import molecule.db.compliance.setup.{MUnitSuiteWithArrays, Test}
import molecule.db.compliance.test.action.save.{SaveCardMap, SaveCardOne, SaveCardSeq, SaveCardSet, SaveRefs, SaveSemantics}
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async

class SaveCardOneTest extends Test {
  SaveCardOne(this, Api_mysql_async)
}
class SaveCardSeqTest extends MUnitSuiteWithArrays {
  SaveCardSeq(this, Api_mysql_async)
}
class SaveCardSetTest extends Test {
  SaveCardSet(this, Api_mysql_async)
}
class SaveCardMapTest extends Test {
  SaveCardMap(this, Api_mysql_async)
}
class SaveRefsTest extends Test {
  SaveRefs(this, Api_mysql_async)
}
class SaveSemanticsTest extends Test {
  SaveSemantics(this, Api_mysql_async)
}
