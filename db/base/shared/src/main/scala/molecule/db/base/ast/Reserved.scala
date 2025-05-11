package molecule.db.base.ast

case class Reserved(
  reservedEnts: Array[Boolean],
  reservedAttrs: Array[Boolean]
) {
  override def toString =
    s"""Reserved(
       |  // Entity names colliding with db keyword (by index attached to attribute definition)
       |  Array(${reservedEnts.toList.mkString(", ")}),
       |
       |  // Attribute names colliding with db keyword (by index attached to attribute definition)
       |  Array(${reservedAttrs.toList.mkString(", ")})
       |)""".stripMargin
}
