package molecule.boilerplate.markers


object schemaOptionMarkers {

  case class Doc(msg: String)
  trait UniqueValue
  trait UniqueIdentity
  trait Index
  trait Fulltext
  trait IsComponent
  trait NoHistory

  object EnumValue
}
