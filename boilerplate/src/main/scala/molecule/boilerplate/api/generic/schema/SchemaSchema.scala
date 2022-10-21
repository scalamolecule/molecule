/*
* AUTO-GENERATED Datomic Schema generation boilerplate code
*
* To change:
* 1. edit data model file in `molecule.boilerplate.api.generic.dataModel/`
* 2. `sbt clean compile -Dmolecule=true`
*/
package molecule.boilerplate.api.generic.schema
import molecule.base.api.SchemaTransaction
import molecule.base.ast.schemaAST._

object SchemaSchema extends SchemaTransaction {

  lazy val datomicPeer = Seq(
    // Attributes
    """
     [
       ;; Schema --------------------------------------------

       {:db/ident         :Schema/t
        :db/valueType     :db.type/long
        :db/cardinality   :db.cardinality/one
        :db/doc           "Schema transaction time t of when Attribute was created"
        :db/index         true}

       {:db/ident         :Schema/tx
        :db/valueType     :db.type/long
        :db/cardinality   :db.cardinality/one
        :db/doc           "Schema transaction entity id of when Attribute was created"
        :db/index         true}

       {:db/ident         :Schema/txInstant
        :db/valueType     :db.type/instant
        :db/cardinality   :db.cardinality/one
        :db/doc           "Schema transaction Date of when Attribute was created"
        :db/index         true}

       {:db/ident         :Schema/attrId
        :db/valueType     :db.type/long
        :db/cardinality   :db.cardinality/one
        :db/doc           "Attribute entity id"
        :db/index         true}

       {:db/ident         :Schema/a
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Full attribute name ':Ns/attr' or ':part_Ns/attr'"
        :db/index         true}

       {:db/ident         :Schema/part
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Partition name 'part'"
        :db/index         true}

       {:db/ident         :Schema/nsFull
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Namespace name with partition prefix 'part_Ns' or 'Ns' if no partitions defined"
        :db/index         true}

       {:db/ident         :Schema/ns
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Namespace name 'Ns'"
        :db/index         true}

       {:db/ident         :Schema/attr
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Attribute name 'attr' without namespace"
        :db/index         true}

       {:db/ident         :Schema/ident
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Datomic identifier"
        :db/index         true}

       {:db/ident         :Schema/valueType
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Datomic value type: string, long, double, boolean, bigint, bigdec, instant, uuid, uri, bytes, ref"
        :db/index         true}

       {:db/ident         :Schema/cardinality
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Cardinality: one, many"
        :db/index         true}

       {:db/ident         :Schema/doc
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/fulltext      true
        :db/doc           "Attribute description"
        :db/index         true}

       {:db/ident         :Schema/unique
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Attribute value unique constraints: identity, value"
        :db/index         true}

       {:db/ident         :Schema/isComponent
        :db/valueType     :db.type/boolean
        :db/cardinality   :db.cardinality/one
        :db/doc           "Is attribute a component?"
        :db/index         true}

       {:db/ident         :Schema/noHistory
        :db/valueType     :db.type/boolean
        :db/cardinality   :db.cardinality/one
        :db/doc           "Is attribute prohibiting history?"
        :db/index         true}

       {:db/ident         :Schema/index
        :db/valueType     :db.type/boolean
        :db/cardinality   :db.cardinality/one
        :db/doc           "Are attribute values indexed?"
        :db/index         true}

       {:db/ident         :Schema/fulltext
        :db/valueType     :db.type/boolean
        :db/cardinality   :db.cardinality/one
        :db/doc           "Does attribute allow fulltext search?"
        :db/index         true}
     ]
    """
  )


  lazy val datomicClient = Seq(
    // Attributes
    """
     [
       ;; Schema --------------------------------------------

       {:db/ident         :Schema/t
        :db/valueType     :db.type/long
        :db/cardinality   :db.cardinality/one
        :db/doc           "Schema transaction time t of when Attribute was created"}

       {:db/ident         :Schema/tx
        :db/valueType     :db.type/long
        :db/cardinality   :db.cardinality/one
        :db/doc           "Schema transaction entity id of when Attribute was created"}

       {:db/ident         :Schema/txInstant
        :db/valueType     :db.type/instant
        :db/cardinality   :db.cardinality/one
        :db/doc           "Schema transaction Date of when Attribute was created"}

       {:db/ident         :Schema/attrId
        :db/valueType     :db.type/long
        :db/cardinality   :db.cardinality/one
        :db/doc           "Attribute entity id"}

       {:db/ident         :Schema/a
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Full attribute name ':Ns/attr' or ':part_Ns/attr'"}

       {:db/ident         :Schema/part
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Partition name 'part'"}

       {:db/ident         :Schema/nsFull
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Namespace name with partition prefix 'part_Ns' or 'Ns' if no partitions defined"}

       {:db/ident         :Schema/ns
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Namespace name 'Ns'"}

       {:db/ident         :Schema/attr
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Attribute name 'attr' without namespace"}

       {:db/ident         :Schema/ident
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Datomic identifier"}

       {:db/ident         :Schema/valueType
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Datomic value type: string, long, double, boolean, bigint, bigdec, instant, uuid, uri, bytes, ref"}

       {:db/ident         :Schema/cardinality
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Cardinality: one, many"}

       {:db/ident         :Schema/doc
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Attribute description"}

       {:db/ident         :Schema/unique
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Attribute value unique constraints: identity, value"}

       {:db/ident         :Schema/isComponent
        :db/valueType     :db.type/boolean
        :db/cardinality   :db.cardinality/one
        :db/doc           "Is attribute a component?"}

       {:db/ident         :Schema/noHistory
        :db/valueType     :db.type/boolean
        :db/cardinality   :db.cardinality/one
        :db/doc           "Is attribute prohibiting history?"}

       {:db/ident         :Schema/index
        :db/valueType     :db.type/boolean
        :db/cardinality   :db.cardinality/one
        :db/doc           "Are attribute values indexed?"}

       {:db/ident         :Schema/fulltext
        :db/valueType     :db.type/boolean
        :db/cardinality   :db.cardinality/one
        :db/doc           "Does attribute allow fulltext search?"}
     ]
    """
  )


  lazy val metaSchema = MetaSchema(Seq(
    MetaPart(0, "db.part/user", None, None, Seq(
      MetaNs(0, "Schema", "Schema", None, None, Seq(
        MetaAttr(0 , "t"          , 1, "Long"   , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(1 , "tx"         , 1, "Long"   , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(2 , "txInstant"  , 1, "Date"   , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(3 , "attrId"     , 1, "Long"   , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(4 , "a"          , 1, "String" , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(5 , "part"       , 1, "String" , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(6 , "nsFull"     , 1, "String" , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(7 , "ns"         , 1, "String" , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(8 , "attr"       , 1, "String" , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(9 , "ident"      , 1, "String" , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(10, "valueType"  , 1, "String" , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(11, "cardinality", 1, "String" , Nil, None, Nil, None, Some(""), None, None, None, Nil),
        MetaAttr(12, "doc"        , 1, "String" , Nil, None, Seq("fulltext"), None, None, None, None, None, Nil),
        MetaAttr(13, "unique"     , 1, "String" , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(14, "isComponent", 1, "Boolean", Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(15, "noHistory"  , 1, "Boolean", Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(16, "index"      , 1, "Boolean", Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(17, "fulltext"   , 1, "Boolean", Nil, None, Nil, None, None, None, None, None, Nil)))))))


  lazy val nsMap = Map(
      "Schema" -> MetaNs(0, "Schema", "Schema", None, None, Seq(
        MetaAttr(0 , "t"          , 1, "Long"   , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(1 , "tx"         , 1, "Long"   , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(2 , "txInstant"  , 1, "Date"   , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(3 , "attrId"     , 1, "Long"   , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(4 , "a"          , 1, "String" , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(5 , "part"       , 1, "String" , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(6 , "nsFull"     , 1, "String" , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(7 , "ns"         , 1, "String" , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(8 , "attr"       , 1, "String" , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(9 , "ident"      , 1, "String" , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(10, "valueType"  , 1, "String" , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(11, "cardinality", 1, "String" , Nil, None, Nil, None, Some(""), None, None, None, Nil),
        MetaAttr(12, "doc"        , 1, "String" , Nil, None, Seq("fulltext"), None, None, None, None, None, Nil),
        MetaAttr(13, "unique"     , 1, "String" , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(14, "isComponent", 1, "Boolean", Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(15, "noHistory"  , 1, "Boolean", Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(16, "index"      , 1, "Boolean", Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(17, "fulltext"   , 1, "Boolean", Nil, None, Nil, None, None, None, None, None, Nil))))


  // Map of Attribute -> (card, type)
  lazy val attrMap: Map[String, (Int, String)] = Map(
      ":Schema/t"           -> (1, "Long"),
      ":Schema/tx"          -> (1, "Long"),
      ":Schema/txInstant"   -> (1, "Date"),
      ":Schema/attrId"      -> (1, "Long"),
      ":Schema/a"           -> (1, "String"),
      ":Schema/part"        -> (1, "String"),
      ":Schema/nsFull"      -> (1, "String"),
      ":Schema/ns"          -> (1, "String"),
      ":Schema/attr"        -> (1, "String"),
      ":Schema/ident"       -> (1, "String"),
      ":Schema/valueType"   -> (1, "String"),
      ":Schema/cardinality" -> (1, "String"),
      ":Schema/doc"         -> (1, "String"),
      ":Schema/unique"      -> (1, "String"),
      ":Schema/isComponent" -> (1, "Boolean"),
      ":Schema/noHistory"   -> (1, "Boolean"),
      ":Schema/index"       -> (1, "Boolean"),
      ":Schema/fulltext"    -> (1, "Boolean"))
}