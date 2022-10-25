package codegen

import codegen.db.datomic._DatomicMoleculeImplicits

object DatomicGenerator extends App {

  db.datomic._DatomicMoleculeImplicits.generate
  db.datomic.api._DatomicInsert.generate
  db.datomic.api._DatomicMoleculeApi.generate
}
