package codegen

import codegen.db._


object DatomicGenerator extends App {


    core.api._MoleculeApi.generate
  //  core.api._Insert.generate
    datomic.api._DatomicMoleculeApi.generate
//    datomic.api._DatomicInsert.generate
  //
  //
//    datomic.query.casting._CastNestedBranch.generate
//    datomic.query.casting._CastNestedOptBranch.generate
//    datomic.query.casting._CastNestedOptBranchFlatten.generate
//    datomic.query.casting._CastNestedOptLeaf.generate
//    datomic.query.casting._CastNestedOptLeafFlatten.generate
//    datomic.query.casting._CastTpl.generate
//    datomic.query.casting._NestOpt.generate
//    datomic.query.casting._NestOptFlatten.generate

//    datomic.query._SortOne.generate
//    datomic.query._SortOneOpt.generate
//
    codegen.core._MoleculeImplicits.generate
    datomic._DatomicMoleculeImplicits.generate
}
