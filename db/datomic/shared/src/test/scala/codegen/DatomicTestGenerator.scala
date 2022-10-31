package codegen

import codegen.db.datomic.test.aggr.any._Aggr_Types
import codegen.db.datomic.test.aggr.number._AggrNum_Types

object DatomicTestGenerator extends App {

  //  db.datomic.test.expr._Expr_Types.generate
  _Aggr_Types.generate
  //  _AggrNum_Types.generate
}
