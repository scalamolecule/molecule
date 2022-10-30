package codegen

import codegen.db.datomic.api._DatomicMoleculeApi

object DatomicGenerator extends App {

  // Coordinate overrides in multiple files --------------

  //  core.api._MoleculeApi.generate
  //  _DatomicMoleculeApi.generate

  //  core.api._Insert.generate
  //  db.datomic.api._DatomicInsert.generate


  //  core.api._MoleculeApi.generate
  //  db.datomic.api._DatomicMoleculeApi.generate


  // jvm specific only -----------------------------------

  //  db.datomic._DatomicMoleculeImplicits.generate

    db.datomic.query._Cast.generate
//    db.datomic.query._Sort.generate

}
