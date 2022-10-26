package codegen

import codegen.db.datomic.api._DatomicMoleculeApi

object DatomicGenerator extends App {

//  db.datomic._DatomicMoleculeImplicits.generate


  // Use tandems of interface/implementations to coordinate overrides

  core.api._MoleculeApi.generate
  _DatomicMoleculeApi.generate

//  core.api._Insert.generate
//  db.datomic.api._DatomicInsert.generate


//  core.api._MoleculeApi.generate
//  db.datomic.api._DatomicMoleculeApi.generate
}
