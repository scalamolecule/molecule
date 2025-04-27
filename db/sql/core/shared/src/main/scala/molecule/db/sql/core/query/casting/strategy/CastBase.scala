package molecule.db.sql.core.query.casting.strategy

import molecule.db.sql.core.javaSql.ResultSetInterface

trait CastBase {
  type RS = ResultSetInterface
  type ParamIndex = Int
  type Cast = (RS, ParamIndex) => Any
}