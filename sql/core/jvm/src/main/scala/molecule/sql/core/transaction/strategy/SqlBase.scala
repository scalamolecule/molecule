package molecule.sql.core.transaction.strategy

import java.sql.PreparedStatement
import molecule.base.util.BaseHelpers

trait SqlBase  {
  type PS = PreparedStatement
  type RowIndex = Int
  type ParamIndex = Int
  type Cast = (PS, ParamIndex) => Any
  type Setter = PS => Unit
}