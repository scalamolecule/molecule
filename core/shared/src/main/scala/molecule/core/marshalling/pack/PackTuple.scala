package molecule.core.marshalling.pack

import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._


abstract class PackTuple(elements: Seq[Element]) { self: Tpls2DTO =>


  protected lazy val packTpl: Product => Unit = {
    //    if()


    (tpl: Product) => {


      ()
    }
  }
}
