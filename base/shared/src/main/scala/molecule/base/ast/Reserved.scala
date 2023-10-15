package molecule.base.ast

case class Reserved(
  reservedNss: Array[Boolean],
  reservedAttrs: Array[Boolean]
) {
  override def toString =
    s"""Reserved(
       |  Array(${reservedNss.toList.mkString(", ")}),
       |  Array(${reservedAttrs.toList.mkString(", ")})
       |)""".stripMargin
}
