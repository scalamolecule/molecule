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

object DatomSchema extends SchemaTransaction {

  lazy val datomicPeer = Seq(
    // Attributes
    """
     [
       ;; Datom ---------------------------------------------

       {:db/ident         :Datom/e
        :db/valueType     :db.type/long
        :db/cardinality   :db.cardinality/one
        :db/doc           "Entity id"
        :db/index         true}

       {:db/ident         :Datom/a
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Attribute name"
        :db/index         true}

       {:db/ident         :Datom/v
        :db/valueType     :db.type/object
        :db/cardinality   :db.cardinality/one
        :db/doc           "Value"
        :db/index         true}

       {:db/ident         :Datom/t
        :db/valueType     :db.type/long
        :db/cardinality   :db.cardinality/one
        :db/doc           "Transaction time t"
        :db/index         true}

       {:db/ident         :Datom/tx
        :db/valueType     :db.type/long
        :db/cardinality   :db.cardinality/one
        :db/doc           "Transaction entity id"
        :db/index         true}

       {:db/ident         :Datom/txInstant
        :db/valueType     :db.type/instant
        :db/cardinality   :db.cardinality/one
        :db/doc           "Transaction time as java.util.Date"
        :db/index         true}

       {:db/ident         :Datom/op
        :db/valueType     :db.type/boolean
        :db/cardinality   :db.cardinality/one
        :db/doc           "Transaction operation (add: True or retract: False"
        :db/index         true}
     ]
    """
  )


  lazy val datomicClient = Seq(
    // Attributes
    """
     [
       ;; Datom ---------------------------------------------

       {:db/ident         :Datom/e
        :db/valueType     :db.type/long
        :db/cardinality   :db.cardinality/one
        :db/doc           "Entity id"}

       {:db/ident         :Datom/a
        :db/valueType     :db.type/string
        :db/cardinality   :db.cardinality/one
        :db/doc           "Attribute name"}

       {:db/ident         :Datom/v
        :db/valueType     :db.type/object
        :db/cardinality   :db.cardinality/one
        :db/doc           "Value"}

       {:db/ident         :Datom/t
        :db/valueType     :db.type/long
        :db/cardinality   :db.cardinality/one
        :db/doc           "Transaction time t"}

       {:db/ident         :Datom/tx
        :db/valueType     :db.type/long
        :db/cardinality   :db.cardinality/one
        :db/doc           "Transaction entity id"}

       {:db/ident         :Datom/txInstant
        :db/valueType     :db.type/instant
        :db/cardinality   :db.cardinality/one
        :db/doc           "Transaction time as java.util.Date"}

       {:db/ident         :Datom/op
        :db/valueType     :db.type/boolean
        :db/cardinality   :db.cardinality/one
        :db/doc           "Transaction operation (add: True or retract: False"}
     ]
    """
  )


  lazy val metaSchema = MetaSchema(Seq(
    MetaPart(0, "db.part/user", None, None, Seq(
      MetaNs(0, "Datom", "Datom", None, None, Seq(
        MetaAttr(0, "e"        , 1, "Long"   , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(1, "a"        , 1, "String" , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(2, "v"        , 1, "Any"    , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(3, "t"        , 1, "Long"   , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(4, "tx"       , 1, "Long"   , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(5, "txInstant", 1, "Date"   , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(6, "op"       , 1, "Boolean", Nil, None, Nil, None, None, None, None, None, Nil)))))))


  lazy val nsMap = Map(
      "Datom" -> MetaNs(0, "Datom", "Datom", None, None, Seq(
        MetaAttr(0, "e"        , 1, "Long"   , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(1, "a"        , 1, "String" , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(2, "v"        , 1, "Any"    , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(3, "t"        , 1, "Long"   , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(4, "tx"       , 1, "Long"   , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(5, "txInstant", 1, "Date"   , Nil, None, Nil, None, None, None, None, None, Nil),
        MetaAttr(6, "op"       , 1, "Boolean", Nil, None, Nil, None, None, None, None, None, Nil))))


  // Map of Attribute -> (card, type)
  lazy val attrMap: Map[String, (Int, String)] = Map(
      ":Datom/e"         -> (1, "Long"),
      ":Datom/a"         -> (1, "String"),
      ":Datom/v"         -> (1, "Any"),
      ":Datom/t"         -> (1, "Long"),
      ":Datom/tx"        -> (1, "Long"),
      ":Datom/txInstant" -> (1, "Date"),
      ":Datom/op"        -> (1, "Boolean"))
}