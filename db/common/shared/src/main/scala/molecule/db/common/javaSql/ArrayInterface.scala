package molecule.db.common.javaSql

trait ArrayInterface {
  def getBaseTypeName: String
  def getBaseType: Int
  def getResultSet: ResultSetInterface
  def getArray: AnyRef
  def getUnderlyingArray: AnyRef
}
