package molecule.boilerplate.api.generic.dataModel

import molecule.DataModel

object Schema extends DataModel(22) {

  trait Schema {
    val t         = oneLong.doc("Schema transaction time t of when Attribute was created")
    val tx        = oneLong.doc("Schema transaction entity id of when Attribute was created")
    val txInstant = oneDate.doc("Schema transaction Date of when Attribute was created")
    val attrId    = oneLong.doc("Attribute entity id")
    val a         = oneString.doc("Full attribute name ':Ns/attr' or ':part_Ns/attr'")
    val part      = oneString.doc("Partition name 'part'")
    val nsFull    = oneString.doc("Namespace name with partition prefix 'part_Ns' or 'Ns' if no partitions defined")
    val ns        = oneString.doc("Namespace name 'Ns'")
    val attr      = oneString.doc("Attribute name 'attr' without namespace")
//    val enumm     = oneString.doc("Enum value(s) of attribute")
    val ident     = oneString.doc("Datomic identifier")
    val valueType = oneString.doc("Datomic value type: string, long, double, boolean, bigint, bigdec, instant, uuid, uri, bytes, ref")

    val cardinality = oneString.doc("Cardinality: one, many")
    val doc         = oneString.fulltext.doc("Attribute description")
    val unique      = oneString.doc("Attribute value unique constraints: identity, value")
    val isComponent = oneBoolean.doc("Is attribute a component?")
    val noHistory   = oneBoolean.doc("Is attribute prohibiting history?")
    val index       = oneBoolean.doc("Are attribute values indexed?")
    val fulltext    = oneBoolean.doc("Does attribute allow fulltext search?")

  }

}
