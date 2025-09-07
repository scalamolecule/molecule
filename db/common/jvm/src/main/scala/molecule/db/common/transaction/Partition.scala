package molecule.db.common.transaction

sealed trait DataKind
case object HasNestedTuples extends DataKind
case object HasNestedValues extends DataKind
case object HasOptTuple extends DataKind
case object HasOptValue extends DataKind
case object Tpl extends DataKind

case class Partition(
  tableInserts: List[TableInsert],
  tupleIndex: Int,
  dataKind: DataKind,
) {
  override def toString = {
    s"""====================================================
       |PARTITION  $tupleIndex  $dataKind
       |${tableInserts.map(_.toString).mkString("\n")}
       |""".stripMargin
  }
}
