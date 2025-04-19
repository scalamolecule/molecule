/*
* AUTO-GENERATED schema boilerplate code
*
* To change:
* 1. edit data model file in `molecule.graphql.client.dataModel/`
* 2. `sbt compile -Dmolecule=true`
*/
package molecule.graphql.client.schema

import molecule.base.api.Schema
import molecule.base.ast.*


trait StarwarsSchema_Datomic extends Schema {

  val datomicPartitions: String = ""


  val datomicSchema: String = 
    """
      [
        ;; Character -----------------------------------------

        {:db/ident         :Character/name
         :db/valueType     :db.type/string
         :db/cardinality   :db.cardinality/one
         :db/index         true}

        {:db/ident         :Character/friends
         :db/valueType     :db.type/ref
         :db/cardinality   :db.cardinality/many
         :db/index         true}

        {:db/ident         :Character/appearsIn
         :db/valueType     :db.type/string
         :db/cardinality   :db.cardinality/many
         :db/index         true}


        ;; Droid ---------------------------------------------

        {:db/ident         :Droid/name
         :db/valueType     :db.type/string
         :db/cardinality   :db.cardinality/one
         :db/index         true}

        {:db/ident         :Droid/friends
         :db/valueType     :db.type/ref
         :db/cardinality   :db.cardinality/many
         :db/index         true}

        {:db/ident         :Droid/appearsIn
         :db/valueType     :db.type/string
         :db/cardinality   :db.cardinality/many
         :db/index         true}

        {:db/ident         :Droid/primaryFunction
         :db/valueType     :db.type/string
         :db/cardinality   :db.cardinality/one
         :db/index         true}


        ;; Human ---------------------------------------------

        {:db/ident         :Human/name
         :db/valueType     :db.type/string
         :db/cardinality   :db.cardinality/one
         :db/index         true}

        {:db/ident         :Human/friends
         :db/valueType     :db.type/ref
         :db/cardinality   :db.cardinality/many
         :db/index         true}

        {:db/ident         :Human/appearsIn
         :db/valueType     :db.type/string
         :db/cardinality   :db.cardinality/many
         :db/index         true}

        {:db/ident         :Human/homePlanet
         :db/valueType     :db.type/string
         :db/cardinality   :db.cardinality/one
         :db/index         true}
      ]
    """


  val datomicAliases: String = ""
}