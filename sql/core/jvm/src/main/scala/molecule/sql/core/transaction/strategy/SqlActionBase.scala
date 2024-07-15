package molecule.sql.core.transaction.strategy

import java.sql.{Connection, PreparedStatement}
import molecule.base.util.BaseHelpers
import scala.collection.mutable.ListBuffer

trait SqlActionBase extends BaseHelpers {
  type PS = PreparedStatement
  type RowIndex = Int
  type ParamIndex = Int
  type Cast = (PS, ParamIndex) => Any
  type Setter = PS => Unit

  def joinIdNames(ns: String, refNs: String): (String, String) = {
    if (ns == refNs)
      (ss(ns, "1_id"), ss(refNs, "2_id"))
    else
      (ss(ns, "id"), ss(refNs, "id"))
  }
}