package molecule.base.ast

case class Reserved(
  reservedNss: Array[Boolean],
  reservedAttrs: Array[Boolean]
) {
  override def toString =
    s"""Reserved(
       |  // Namespace names colliding with db keyword (by index attached to attribute definition)
       |  Array(${reservedNss.toList.mkString(", ")}),
       |
       |  // Attribute names colliding with db keyword (by index attached to attribute definition)
       |  Array(${reservedAttrs.toList.mkString(", ")})
       |)""".stripMargin
}
