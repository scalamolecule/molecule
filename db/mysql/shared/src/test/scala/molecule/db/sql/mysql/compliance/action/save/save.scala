package molecule.db.sql.mysql.compliance.action.save

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db.compliance.test.action.save.*
import molecule.db.sql.mysql.setup.Api_mysql_async

class SaveCardOneTest extends MUnit {
  SaveCardOne(this, Api_mysql_async)
}
class SaveCardSeqTest extends MUnit_arrays {
  SaveCardSeq(this, Api_mysql_async)
}
class SaveCardSetTest extends MUnit {
  SaveCardSet(this, Api_mysql_async)
}
class SaveCardMapTest extends MUnit {
  SaveCardMap(this, Api_mysql_async)
}
class SaveRefsTest extends MUnit {
  SaveRefs(this, Api_mysql_async)
}
class SaveSemanticsTest extends MUnit {
  SaveSemantics(this, Api_mysql_async)
}
