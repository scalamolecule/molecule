package codegen

import codegen.db.datomic.query.casting._


object DatomicGenerator extends App {

  // Coordinate overrides in multiple files --------------

  //  core.api._Insert.generate
  //    db.datomic.api._DatomicInsert.generate


  //  core.api._MoleculeApi.generate
  //    db.datomic.api._DatomicMoleculeApi.generate


  // jvm specific only -----------------------------------

  //    db.datomic._DatomicMoleculeImplicits.generate

  //    db.datomic.query._CastFlat.generate
  //    db.datomic.query._CastNestedBranch.generate
  //    db.datomic.query._CastNestedLeaf.generate
  //    _CastNestedOptBranch.generate
      _CastNestedOptBranchFlatten.generate
  //    _CastNestedOptLeaf.generate
//  _CastNestedOptLeafFlatten.generate
  //    _NestOpt.generate
//  _NestOptFlatten.generate

  //    db.datomic.query._SortOne.generate
  //    db.datomic.query._SortOneOpt.generate

}
