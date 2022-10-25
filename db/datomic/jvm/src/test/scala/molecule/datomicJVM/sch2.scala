/*
* AUTO-GENERATED schema boilerplate code
*
* To change:
* 1. edit data model file in `molecule.base.parseFiles.dataModel/`
* 2. `sbt clean compile -Dmolecule=true`
*/
package molecule.base.parseFiles.schema
import molecule.base.api.SchemaTransaction
import molecule.base.ast.SchemaAST._


object ASchema extends SchemaTransaction {


  override lazy val metaSchema: MetaSchema = MetaSchema("molecule.base.parseFiles", "A", 3, Seq(
    MetaPart("aa", Seq(
      MetaNs("aa_Ns", Seq(
        MetaAttr("str", one, "String", None, Nil, Some("foo"), Some("xxx"), None),
        MetaAttr("int", one, "Int", None, Seq("unique"), Some("bar"), None, Some("_ > 7")),
        MetaAttr("hejsa", one, "Long", Some("bb_Ref1"), Nil, None, None, None))))),

    MetaPart("bb", Seq(
      MetaNs("bb_Ref1", Seq(
        MetaAttr("str1", one, "String", None, Nil, Some("foo"), None, None),
        MetaAttr("int1", one, "Int", None, Seq("unique"), Some("bar"), Some("hej"), None)))))
  ))


  override lazy val nsMap: Map[String, MetaNs] = Map(
    "aa_Ns" -> MetaNs("aa_Ns", Seq(
      MetaAttr("str", one, "String", None, Nil, Some("foo"), Some("xxx"), None),
      MetaAttr("int", one, "Int", None, Seq("unique"), Some("bar"), None, Some("_ > 7")),
      MetaAttr("hejsa", one, "Long", Some("bb_Ref1"), Nil, None, None, None))),

    "bb_Ref1" -> MetaNs("bb_Ref1", Seq(
      MetaAttr("str1", one, "String", None, Nil, Some("foo"), None, None),
      MetaAttr("int1", one, "Int", None, Seq("unique"), Some("bar"), Some("hej"), None)))
  )


  override lazy val attrMap: Map[String, (Cardinality, String)] = Map(
    ":aa_Ns/str"    -> (one, "String"),
    ":aa_Ns/int"    -> (one, "Int"),
    ":aa_Ns/hejsa"  -> (one, "Long"),
    ":bb_Ref1/str1" -> (one, "String"),
    ":bb_Ref1/int1" -> (one, "Int")
  )


  override lazy val datomicPartitions: String =
    """
      [
        {:db/id      "aa"
         :db/ident   :aa}
        [:db/add :db.part/db :db.install/partition "aa"]

        {:db/id      "bb"
         :db/ident   :bb}
        [:db/add :db.part/db :db.install/partition "bb"]
      ]
    """

  override lazy val datomicSchema: String =
    """
      [
        ;; aa_Ns ---------------------------------------------

        {:db/ident         :aa_Ns/str
         :db/valueType     :db.type/string
         :db/cardinality   :db.cardinality/one
         :db/index         true
         :db/doc           "foo"}

        {:db/ident         :aa_Ns/int
         :db/valueType     :db.type/long
         :db/cardinality   :db.cardinality/one
         :db/index         true
         :db/unique        :db.unique/value
         :db/doc           "bar"}

        {:db/ident         :aa_Ns/hejsa
         :db/valueType     :db.type/ref
         :db/cardinality   :db.cardinality/one
         :db/index         true}


        ;; bb_Ref1 -------------------------------------------

        {:db/ident         :bb_Ref1/str1
         :db/valueType     :db.type/string
         :db/cardinality   :db.cardinality/one
         :db/index         true
         :db/doc           "foo"}

        {:db/ident         :bb_Ref1/int1
         :db/valueType     :db.type/long
         :db/cardinality   :db.cardinality/one
         :db/index         true
         :db/unique        :db.unique/value
         :db/doc           "bar"}
      ]
    """

  override lazy val datomicAliases: String =
    """
      [
        {:db/id      "xxx"
         :db/ident   :str}

        {:db/id      "hej"
         :db/ident   :int1}
      ]
    """
}