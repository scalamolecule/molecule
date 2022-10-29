///*
//* AUTO-GENERATED Datomic Schema generation boilerplate code
//*
//* To change:
//* 1. edit data model file in `moleculeTests.dataModels.core.base.dataModel/`
//* 2. `sbt clean compile -Dmolecule=true`
//*/
//package molecule.datomicJVM
//
//import molecule.base.api.SchemaTransaction
//import molecule.base.ast.SchemaAST._
//
//object CoreTestSchema extends SchemaTransaction {
//
//
//  override lazy val datomicAliases =
//    """
//   [
//     {:db/id     :Community/type
//      :db/ident  :Community/tpe}
//   ]
//  """
//
//  override lazy val datomicPartitions =
//    """
//     [
//       {:db/id      "gen"
//        :db/ident   :gen}
//       [:db/add :db.part/db :db.install/partition "gen"]
//
//       {:db/id      "lit"
//        :db/ident   :lit}
//       [:db/add :db.part/db :db.install/partition "lit"]
//     ]
//    """
//
//  override lazy val datomicSchema =
//    """
//      [
//
//            {:db/ident         :Community/type
//             :db/valueType     :db.type/ref
//             :db/cardinality   :db.cardinality/one
//             :db/doc           "Community type"
//             :db/index         true}
//
//        ;; Ns ------------------------------------------------
//
//        {:db/ident         :Ns/n
//         :db/valueType     :db.type/long
//         :db/cardinality   :db.cardinality/one
//         :db/doc           "Independent index for tests"
//         :db/index         true}
//
//        {:db/ident         :Ns/str
//         :db/valueType     :db.type/string
//         :db/cardinality   :db.cardinality/one
//         :db/fulltext      true
//         :db/doc           "Card one String attribute"
//         :db/index         true}
//
//       {:db/ident         :Ns/int
//        :db/valueType     :db.type/long
//        :db/cardinality   :db.cardinality/one
//        :db/doc           "Card one Int attribute"
//        :db/index         true}
//
//       {:db/ident         :Ns/long
//        :db/valueType     :db.type/long
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//
//       {:db/ident         :Ns/double
//        :db/valueType     :db.type/double
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//
//       {:db/ident         :Ns/bool
//        :db/valueType     :db.type/boolean
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//
//       {:db/ident         :Ns/date
//        :db/valueType     :db.type/instant
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//
//       {:db/ident         :Ns/uuid
//        :db/valueType     :db.type/uuid
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//
//       {:db/ident         :Ns/uri
//        :db/valueType     :db.type/uri
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//
//       {:db/ident         :Ns/bigInt
//        :db/valueType     :db.type/bigint
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//
//       {:db/ident         :Ns/bigDec
//        :db/valueType     :db.type/bigdec
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//
//       {:db/ident         :Ns/enumm
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//
//       {:db/ident         :Ns/parent
//        :db/valueType     :db.type/ref
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//
//       {:db/ident         :Ns/ref1
//        :db/valueType     :db.type/ref
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//
//       {:db/ident         :Ns/refSub1
//        :db/valueType     :db.type/ref
//        :db/cardinality   :db.cardinality/one
//        :db/isComponent   true
//        :db/index         true}
//
//       {:db/ident         :Ns/strs
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/many
//        :db/fulltext      true
//        :db/index         true}
//
//       {:db/ident         :Ns/ints
//        :db/valueType     :db.type/long
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ns/longs
//        :db/valueType     :db.type/long
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ns/doubles
//        :db/valueType     :db.type/double
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ns/bools
//        :db/valueType     :db.type/boolean
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ns/dates
//        :db/valueType     :db.type/instant
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ns/uuids
//        :db/valueType     :db.type/uuid
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ns/uris
//        :db/valueType     :db.type/uri
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ns/bigInts
//        :db/valueType     :db.type/bigint
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ns/bigDecs
//        :db/valueType     :db.type/bigdec
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ns/enums
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ns/parents
//        :db/valueType     :db.type/ref
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ns/refs1
//        :db/valueType     :db.type/ref
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ns/refsSub1
//        :db/valueType     :db.type/ref
//        :db/cardinality   :db.cardinality/many
//        :db/isComponent   true
//        :db/index         true}
//
//       {:db/ident         :Ns/strMap
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/many
//        :db/fulltext      true
//        :db/index         true}
//
//       {:db/ident         :Ns/intMap
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ns/longMap
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ns/doubleMap
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ns/boolMap
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ns/dateMap
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ns/uuidMap
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ns/uriMap
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ns/bigIntMap
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ns/bigDecMap
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//
//       ;; Ref1 ----------------------------------------------
//
//       {:db/ident         :Ref1/n1
//        :db/valueType     :db.type/long
//        :db/cardinality   :db.cardinality/one
//        :db/doc           "Independent index for tests"
//        :db/index         true}
//
//       {:db/ident         :Ref1/str1
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/one
//        :db/fulltext      true
//        :db/index         true}
//
//       {:db/ident         :Ref1/int1
//        :db/valueType     :db.type/long
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//
//       {:db/ident         :Ref1/enum1
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//
//       {:db/ident         :Ref1/ref2
//        :db/valueType     :db.type/ref
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//
//       {:db/ident         :Ref1/refSub2
//        :db/valueType     :db.type/ref
//        :db/cardinality   :db.cardinality/one
//        :db/isComponent   true
//        :db/index         true}
//
//       {:db/ident         :Ref1/strs1
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ref1/ints1
//        :db/valueType     :db.type/long
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ref1/enums1
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ref1/refs2
//        :db/valueType     :db.type/ref
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ref1/refsSub2
//        :db/valueType     :db.type/ref
//        :db/cardinality   :db.cardinality/many
//        :db/isComponent   true
//        :db/index         true}
//
//       {:db/ident         :Ref1/nss
//        :db/valueType     :db.type/ref
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ref1/intMap1
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//
//       ;; Ref2 ----------------------------------------------
//
//       {:db/ident         :Ref2/n2
//        :db/valueType     :db.type/long
//        :db/cardinality   :db.cardinality/one
//        :db/doc           "Independent index for tests"
//        :db/index         true}
//
//       {:db/ident         :Ref2/str2
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/one
//        :db/unique        :db.unique/identity
//        :db/fulltext      true
//        :db/index         true}
//
//       {:db/ident         :Ref2/int2
//        :db/valueType     :db.type/long
//        :db/cardinality   :db.cardinality/one
//        :db/unique        :db.unique/value
//        :db/index         true}
//
//       {:db/ident         :Ref2/enum2
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//
//       {:db/ident         :Ref2/strs2
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//       {:db/ident         :Ref2/ints2
//        :db/valueType     :db.type/long
//        :db/cardinality   :db.cardinality/many
//        :db/noHistory     true
//        :db/index         true}
//
//       {:db/ident         :Ref2/ref3
//        :db/valueType     :db.type/ref
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//
//       {:db/ident         :Ref2/refs3
//        :db/valueType     :db.type/ref
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//
//       ;; Ref3 ----------------------------------------------
//
//       {:db/ident         :Ref3/n3
//        :db/valueType     :db.type/long
//        :db/cardinality   :db.cardinality/one
//        :db/doc           "Independent index for tests"
//        :db/index         true}
//
//       {:db/ident         :Ref3/str3
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//
//       {:db/ident         :Ref3/int3
//        :db/valueType     :db.type/long
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//
//       {:db/ident         :Ref3/ref4
//        :db/valueType     :db.type/ref
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//
//       {:db/ident         :Ref3/refs4
//        :db/valueType     :db.type/ref
//        :db/cardinality   :db.cardinality/many
//        :db/index         true}
//
//
//       ;; Ref4 ----------------------------------------------
//
//       {:db/ident         :Ref4/n4
//        :db/valueType     :db.type/long
//        :db/cardinality   :db.cardinality/one
//        :db/doc           "Independent index for tests"
//        :db/index         true}
//
//       {:db/ident         :Ref4/str4
//        :db/valueType     :db.type/string
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//
//       {:db/ident         :Ref4/int4
//        :db/valueType     :db.type/long
//        :db/cardinality   :db.cardinality/one
//        :db/index         true}
//     ]
//    """
//
//
//
//  lazy val metaSchema = MetaSchema("", "", 3, Nil)
////  lazy val metaSchema = MetaSchema(Seq(
////    MetaPart(0, "db.part/user", None, None, Seq(
////      MetaNs(0, "Ns", "Ns", None, None, Seq(
////        MetaAttr(0 , "n"        , 1, "Int"       , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(1 , "str"      , 1, "String"    , Nil, None, Seq("fulltext"), None, None, None, None, None, Nil),
////        MetaAttr(2 , "int"      , 1, "Int"       , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(3 , "long"     , 1, "Long"      , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(4 , "double"   , 1, "Double"    , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(5 , "bool"     , 1, "Boolean"   , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(6 , "date"     , 1, "Date"      , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(7 , "uuid"     , 1, "UUID"      , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(8 , "uri"      , 1, "URI"       , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(9 , "bigInt"   , 1, "BigInt"    , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(10, "bigDec"   , 1, "BigDecimal", Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(11, "enumm"    , 1, "String"    , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(12, "parent"   , 1, "ref"       , Nil, Some("Ns"), Nil, None, None, None, None, None, Nil),
////        MetaAttr(13, "ref1"     , 1, "ref"       , Nil, Some("Ref1"), Nil, None, None, None, None, None, Nil),
////        MetaAttr(14, "refSub1"  , 1, "ref"       , Nil, Some("Ref1"), Seq("isComponent"), None, None, None, None, None, Nil),
////        MetaAttr(15, "strs"     , 2, "String"    , Nil, None, Seq("fulltext"), None, Some(""), None, None, None, Nil),
////        MetaAttr(16, "ints"     , 2, "Int"       , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(17, "longs"    , 2, "Long"      , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(18, "doubles"  , 2, "Double"    , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(19, "bools"    , 2, "Boolean"   , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(20, "dates"    , 2, "Date"      , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(21, "uuids"    , 2, "UUID"      , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(22, "uris"     , 2, "URI"       , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(23, "bigInts"  , 2, "BigInt"    , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(24, "bigDecs"  , 2, "BigDecimal", Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(25, "enums"    , 2, "String"    , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(26, "parents"  , 2, "ref"       , Nil, Some("Ns"), Nil, None, None, None, None, None, Nil),
////        MetaAttr(27, "refs1"    , 2, "ref"       , Nil, Some("Ref1"), Nil, None, None, None, None, None, Nil),
////        MetaAttr(28, "refsSub1" , 2, "ref"       , Nil, Some("Ref1"), Seq("isComponent"), None, None, None, None, None, Nil),
////        MetaAttr(29, "strMap"   , 3, "String"    , Nil, None, Seq("fulltext"), None, Some(""), None, None, None, Nil),
////        MetaAttr(30, "intMap"   , 3, "Int"       , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(31, "longMap"  , 3, "Long"      , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(32, "doubleMap", 3, "Double"    , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(33, "boolMap"  , 3, "Boolean"   , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(34, "dateMap"  , 3, "Date"      , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(35, "uuidMap"  , 3, "UUID"      , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(36, "uriMap"   , 3, "URI"       , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(37, "bigIntMap", 3, "BigInt"    , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(38, "bigDecMap", 3, "BigDecimal", Nil, None, Nil, None, None, None, None, None, Nil))),
////
////      MetaNs(1, "Ref1", "Ref1", None, None, Seq(
////        MetaAttr(0 , "n1"      , 1, "Int"   , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(1 , "str1"    , 1, "String", Nil, None, Seq("fulltext"), None, None, None, None, None, Nil),
////        MetaAttr(2 , "int1"    , 1, "Int"   , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(3 , "enum1"   , 1, "String", Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(4 , "ref2"    , 1, "ref"   , Nil, Some("Ref2"), Nil, None, None, None, None, None, Nil),
////        MetaAttr(5 , "refSub2" , 1, "ref"   , Nil, Some("Ref2"), Seq("isComponent"), None, None, None, None, None, Nil),
////        MetaAttr(6 , "strs1"   , 2, "String", Nil, None, Nil, None, Some(""), None, None, None, Nil),
////        MetaAttr(7 , "ints1"   , 2, "Int"   , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(8 , "enums1"  , 2, "String", Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(9 , "refs2"   , 2, "ref"   , Nil, Some("Ref2"), Nil, None, None, None, None, None, Nil),
////        MetaAttr(10, "refsSub2", 2, "ref"   , Nil, Some("Ref2"), Seq("isComponent"), None, None, None, None, None, Nil),
////        MetaAttr(11, "nss"     , 2, "ref"   , Nil, Some("Ns"), Nil, None, Some(""), None, None, None, Nil),
////        MetaAttr(12, "intMap1" , 3, "Int"   , Nil, None, Nil, None, None, None, None, None, Nil))),
////
////      MetaNs(2, "Ref2", "Ref2", None, None, Seq(
////        MetaAttr(0, "n2"   , 1, "Int"   , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(1, "str2" , 1, "String", Nil, None, Seq("uniqueIdentity", "fulltext"), None, None, None, None, None, Nil),
////        MetaAttr(2, "int2" , 1, "Int"   , Nil, None, Seq("uniqueValue"), None, None, None, None, None, Nil),
////        MetaAttr(3, "enum2", 1, "String", Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(4, "strs2", 2, "String", Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(5, "ints2", 2, "Int"   , Nil, None, Seq("noHistory"), None, None, None, None, None, Nil),
////        MetaAttr(6, "ref3" , 1, "ref"   , Nil, Some("Ref3"), Nil, None, None, None, None, None, Nil),
////        MetaAttr(7, "refs3", 2, "ref"   , Nil, Some("Ref3"), Nil, None, None, None, None, None, Nil))),
////
////      MetaNs(3, "Ref3", "Ref3", None, None, Seq(
////        MetaAttr(0, "n3"   , 1, "Int"   , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(1, "str3" , 1, "String", Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(2, "int3" , 1, "Int"   , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(3, "ref4" , 1, "ref"   , Nil, Some("Ref4"), Nil, None, None, None, None, None, Nil),
////        MetaAttr(4, "refs4", 2, "ref"   , Nil, Some("Ref4"), Nil, None, None, None, None, None, Nil))),
////
////      MetaNs(4, "Ref4", "Ref4", None, None, Seq(
////        MetaAttr(0, "n4"  , 1, "Int"   , Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(1, "str4", 1, "String", Nil, None, Nil, None, None, None, None, None, Nil),
////        MetaAttr(2, "int4", 1, "Int"   , Nil, None, Nil, None, None, None, None, None, Nil)))))))
////
////
////  lazy val nsMap = Map(
////    "Ns" -> MetaNs(0, "Ns", "Ns", None, None, Seq(
////      MetaAttr(0 , "n"        , 1, "Int"       , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(1 , "str"      , 1, "String"    , Nil, None, Seq("fulltext"), None, None, None, None, None, Nil),
////      MetaAttr(2 , "int"      , 1, "Int"       , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(3 , "long"     , 1, "Long"      , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(4 , "double"   , 1, "Double"    , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(5 , "bool"     , 1, "Boolean"   , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(6 , "date"     , 1, "Date"      , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(7 , "uuid"     , 1, "UUID"      , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(8 , "uri"      , 1, "URI"       , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(9 , "bigInt"   , 1, "BigInt"    , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(10, "bigDec"   , 1, "BigDecimal", Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(11, "enumm"    , 1, "String"    , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(12, "parent"   , 1, "ref"       , Nil, Some("Ns"), Nil, None, None, None, None, None, Nil),
////      MetaAttr(13, "ref1"     , 1, "ref"       , Nil, Some("Ref1"), Nil, None, None, None, None, None, Nil),
////      MetaAttr(14, "refSub1"  , 1, "ref"       , Nil, Some("Ref1"), Seq("isComponent"), None, None, None, None, None, Nil),
////      MetaAttr(15, "strs"     , 2, "String"    , Nil, None, Seq("fulltext"), None, Some(""), None, None, None, Nil),
////      MetaAttr(16, "ints"     , 2, "Int"       , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(17, "longs"    , 2, "Long"      , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(18, "doubles"  , 2, "Double"    , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(19, "bools"    , 2, "Boolean"   , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(20, "dates"    , 2, "Date"      , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(21, "uuids"    , 2, "UUID"      , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(22, "uris"     , 2, "URI"       , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(23, "bigInts"  , 2, "BigInt"    , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(24, "bigDecs"  , 2, "BigDecimal", Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(25, "enums"    , 2, "String"    , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(26, "parents"  , 2, "ref"       , Nil, Some("Ns"), Nil, None, None, None, None, None, Nil),
////      MetaAttr(27, "refs1"    , 2, "ref"       , Nil, Some("Ref1"), Nil, None, None, None, None, None, Nil),
////      MetaAttr(28, "refsSub1" , 2, "ref"       , Nil, Some("Ref1"), Seq("isComponent"), None, None, None, None, None, Nil),
////      MetaAttr(29, "strMap"   , 3, "String"    , Nil, None, Seq("fulltext"), None, Some(""), None, None, None, Nil),
////      MetaAttr(30, "intMap"   , 3, "Int"       , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(31, "longMap"  , 3, "Long"      , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(32, "doubleMap", 3, "Double"    , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(33, "boolMap"  , 3, "Boolean"   , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(34, "dateMap"  , 3, "Date"      , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(35, "uuidMap"  , 3, "UUID"      , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(36, "uriMap"   , 3, "URI"       , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(37, "bigIntMap", 3, "BigInt"    , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(38, "bigDecMap", 3, "BigDecimal", Nil, None, Nil, None, None, None, None, None, Nil))),
////
////    "Ref1" -> MetaNs(1, "Ref1", "Ref1", None, None, Seq(
////      MetaAttr(0 , "n1"      , 1, "Int"   , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(1 , "str1"    , 1, "String", Nil, None, Seq("fulltext"), None, None, None, None, None, Nil),
////      MetaAttr(2 , "int1"    , 1, "Int"   , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(3 , "enum1"   , 1, "String", Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(4 , "ref2"    , 1, "ref"   , Nil, Some("Ref2"), Nil, None, None, None, None, None, Nil),
////      MetaAttr(5 , "refSub2" , 1, "ref"   , Nil, Some("Ref2"), Seq("isComponent"), None, None, None, None, None, Nil),
////      MetaAttr(6 , "strs1"   , 2, "String", Nil, None, Nil, None, Some(""), None, None, None, Nil),
////      MetaAttr(7 , "ints1"   , 2, "Int"   , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(8 , "enums1"  , 2, "String", Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(9 , "refs2"   , 2, "ref"   , Nil, Some("Ref2"), Nil, None, None, None, None, None, Nil),
////      MetaAttr(10, "refsSub2", 2, "ref"   , Nil, Some("Ref2"), Seq("isComponent"), None, None, None, None, None, Nil),
////      MetaAttr(11, "nss"     , 2, "ref"   , Nil, Some("Ns"), Nil, None, Some(""), None, None, None, Nil),
////      MetaAttr(12, "intMap1" , 3, "Int"   , Nil, None, Nil, None, None, None, None, None, Nil))),
////
////    "Ref2" -> MetaNs(2, "Ref2", "Ref2", None, None, Seq(
////      MetaAttr(0, "n2"   , 1, "Int"   , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(1, "str2" , 1, "String", Nil, None, Seq("uniqueIdentity", "fulltext"), None, None, None, None, None, Nil),
////      MetaAttr(2, "int2" , 1, "Int"   , Nil, None, Seq("uniqueValue"), None, None, None, None, None, Nil),
////      MetaAttr(3, "enum2", 1, "String", Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(4, "strs2", 2, "String", Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(5, "ints2", 2, "Int"   , Nil, None, Seq("noHistory"), None, None, None, None, None, Nil),
////      MetaAttr(6, "ref3" , 1, "ref"   , Nil, Some("Ref3"), Nil, None, None, None, None, None, Nil),
////      MetaAttr(7, "refs3", 2, "ref"   , Nil, Some("Ref3"), Nil, None, None, None, None, None, Nil))),
////
////    "Ref3" -> MetaNs(3, "Ref3", "Ref3", None, None, Seq(
////      MetaAttr(0, "n3"   , 1, "Int"   , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(1, "str3" , 1, "String", Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(2, "int3" , 1, "Int"   , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(3, "ref4" , 1, "ref"   , Nil, Some("Ref4"), Nil, None, None, None, None, None, Nil),
////      MetaAttr(4, "refs4", 2, "ref"   , Nil, Some("Ref4"), Nil, None, None, None, None, None, Nil))),
////
////    "Ref4" -> MetaNs(4, "Ref4", "Ref4", None, None, Seq(
////      MetaAttr(0, "n4"  , 1, "Int"   , Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(1, "str4", 1, "String", Nil, None, Nil, None, None, None, None, None, Nil),
////      MetaAttr(2, "int4", 1, "Int"   , Nil, None, Nil, None, None, None, None, None, Nil))))
//
//
//  // Map of Attribute -> (card, type)
//  lazy val attrMap2: Map[String, (Int, String)] = Map(
//    ":Ns/n"          -> (1, "Int"),
//    ":Ns/str"        -> (1, "String"),
//    ":Ns/int"        -> (1, "Int"),
//    ":Ns/long"       -> (1, "Long"),
//    ":Ns/double"     -> (1, "Double"),
//    ":Ns/bool"       -> (1, "Boolean"),
//    ":Ns/date"       -> (1, "Date"),
//    ":Ns/uuid"       -> (1, "UUID"),
//    ":Ns/uri"        -> (1, "URI"),
//    ":Ns/bigInt"     -> (1, "BigInt"),
//    ":Ns/bigDec"     -> (1, "BigDecimal"),
//    ":Ns/enumm"      -> (1, "String"),
//    ":Ns/parent"     -> (1, "ref"),
//    ":Ns/ref1"       -> (1, "ref"),
//    ":Ns/refSub1"    -> (1, "ref"),
//    ":Ns/strs"       -> (2, "String"),
//    ":Ns/ints"       -> (2, "Int"),
//    ":Ns/longs"      -> (2, "Long"),
//    ":Ns/doubles"    -> (2, "Double"),
//    ":Ns/bools"      -> (2, "Boolean"),
//    ":Ns/dates"      -> (2, "Date"),
//    ":Ns/uuids"      -> (2, "UUID"),
//    ":Ns/uris"       -> (2, "URI"),
//    ":Ns/bigInts"    -> (2, "BigInt"),
//    ":Ns/bigDecs"    -> (2, "BigDecimal"),
//    ":Ns/enums"      -> (2, "String"),
//    ":Ns/parents"    -> (2, "ref"),
//    ":Ns/refs1"      -> (2, "ref"),
//    ":Ns/refsSub1"   -> (2, "ref"),
//    ":Ns/strMap"     -> (3, "String"),
//    ":Ns/intMap"     -> (3, "Int"),
//    ":Ns/longMap"    -> (3, "Long"),
//    ":Ns/doubleMap"  -> (3, "Double"),
//    ":Ns/boolMap"    -> (3, "Boolean"),
//    ":Ns/dateMap"    -> (3, "Date"),
//    ":Ns/uuidMap"    -> (3, "UUID"),
//    ":Ns/uriMap"     -> (3, "URI"),
//    ":Ns/bigIntMap"  -> (3, "BigInt"),
//    ":Ns/bigDecMap"  -> (3, "BigDecimal"),
//    ":Ref1/n1"       -> (1, "Int"),
//    ":Ref1/str1"     -> (1, "String"),
//    ":Ref1/int1"     -> (1, "Int"),
//    ":Ref1/enum1"    -> (1, "String"),
//    ":Ref1/ref2"     -> (1, "ref"),
//    ":Ref1/refSub2"  -> (1, "ref"),
//    ":Ref1/strs1"    -> (2, "String"),
//    ":Ref1/ints1"    -> (2, "Int"),
//    ":Ref1/enums1"   -> (2, "String"),
//    ":Ref1/refs2"    -> (2, "ref"),
//    ":Ref1/refsSub2" -> (2, "ref"),
//    ":Ref1/nss"      -> (2, "ref"),
//    ":Ref1/intMap1"  -> (3, "Int"),
//    ":Ref2/n2"       -> (1, "Int"),
//    ":Ref2/str2"     -> (1, "String"),
//    ":Ref2/int2"     -> (1, "Int"),
//    ":Ref2/enum2"    -> (1, "String"),
//    ":Ref2/strs2"    -> (2, "String"),
//    ":Ref2/ints2"    -> (2, "Int"),
//    ":Ref2/ref3"     -> (1, "ref"),
//    ":Ref2/refs3"    -> (2, "ref"),
//    ":Ref3/n3"       -> (1, "Int"),
//    ":Ref3/str3"     -> (1, "String"),
//    ":Ref3/int3"     -> (1, "Int"),
//    ":Ref3/ref4"     -> (1, "ref"),
//    ":Ref3/refs4"    -> (2, "ref"),
//    ":Ref4/n4"       -> (1, "Int"),
//    ":Ref4/str4"     -> (1, "String"),
//    ":Ref4/int4"     -> (1, "Int"))
//
//  override val nsMap: Map[String, MetaNs] = ???
//  override val attrMap: Map[String, (Cardinality, String)] = ???
//}