package molecule.sql.core.transaction.strategy

import java.sql.PreparedStatement
import molecule.sql.core.javaSql.ResultSetInterface


trait TxBase {
  type PS = PreparedStatement
  type RowIndex = Int
  type ParamIndex = Int
  type Cast = (PS, ParamIndex) => Any
}


sealed trait TxStrategy extends TxBase {

}



case class SaveStrategy() extends TxStrategy {

}

case class InsertStrategy() extends TxStrategy {

}

case class UpdateStrategy() extends TxStrategy {

}

case class DeleteStrategy() extends TxStrategy {

}