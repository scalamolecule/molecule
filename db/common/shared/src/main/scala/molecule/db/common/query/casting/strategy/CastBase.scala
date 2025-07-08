package molecule.db.common.query.casting.strategy

import molecule.db.common.javaSql.ResultSetInterface

trait CastBase {
  type RS = ResultSetInterface
  type ParamIndex = Int
  type Cast = (RS, ParamIndex) => Any
}