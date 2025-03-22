//package molecule.coreTests
//
//import molecule.core.marshalling.Boopicklers._
//import molecule.core.marshalling.JdbcProxy_h2
//import molecule.coreTests.domains.schema.TypesSchema_h2
//import molecule.coreTests.setup.Test
//
//
//class Testing extends Test {
//
//  import boopickle.Default._
////  import SchemaPicklers._
//
//
//  "abc" - {
//
////    pickleSchema_h2.addConcreteType[TypesSchema_h2.type]
////
////    //    val data = Seq("a", "b")
////    //    val data = OneString("hej")
////    //    val data = StartsWith
////    //    val data = TypesSchema_h2
////    val data = JdbcProxy_h2("a", TypesSchema_h2)
////
////    val buf = Pickle.intoBytes(data)
////
////    //    val helloWorld = Unpickle[Seq[String]].fromBytes(buf)
////    //    val helloWorld = Unpickle[OneString].fromBytes(buf)
////    //    val helloWorld = Unpickle[StartsWith.type].fromBytes(buf)
////    //    val helloWorld = Unpickle[TypesSchema_h2.type].fromBytes(buf)
////    val helloWorld = Unpickle[JdbcProxy_h2].fromBytes(buf)
////
////    data ==> helloWorld
//
//    1 ==> 1
//  }
//}