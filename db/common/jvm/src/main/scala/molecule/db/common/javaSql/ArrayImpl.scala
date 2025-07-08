package molecule.db.common.javaSql

import molecule.db.common.javaSql.{ArrayInterface, ResultSetInterface}

class ArrayImpl(val underlying: java.sql.Array) extends ArrayInterface {
  override def getBaseTypeName: String = underlying.getBaseTypeName
  override def getBaseType: Int = underlying.getBaseType
  override def getResultSet: ResultSetInterface = new ResultSetImpl(underlying.getResultSet)
  override def getArray: AnyRef = underlying.getArray
  override def getUnderlyingArray: java.sql.Array = underlying
}
