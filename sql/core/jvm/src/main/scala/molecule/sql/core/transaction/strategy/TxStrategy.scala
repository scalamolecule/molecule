package molecule.sql.core.transaction.strategy

import java.sql.{Connection, PreparedStatement, Statement}
import molecule.base.util.BaseHelpers
import molecule.core.util.ModelUtils
import molecule.sql.core.transaction.op._


trait TxBase {
  type PS = PreparedStatement
  type RowIndex = Int
  type ParamIndex = Int
  type Cast = (PS, ParamIndex) => Any
  type Setter = PS => Unit
}


trait TxStrategy extends TxBase with BaseHelpers {
  val sqlConn: Connection

  def paramIndex: Int
  def add(col: String, setter: Setter, placeHolder: String = "?"): Unit

  def refOne(ns: String, refAttr: String, refNs: String): TxStrategy = ???
  def refMany(ns: String, refAttr: String, refNs: String): TxStrategy = ???
  def optRef: TxStrategy = ???
  def optRefNest: TxStrategy = ???
  def nest: TxStrategy = ???

  def execute: List[Long]
}