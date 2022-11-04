package molecule.db.datomic.util

import clojure.java.api.Clojure
import clojure.lang.IFn
import datomic.Util.read

trait DatomicApiLoader {

  def fn(ns: String, method: String): IFn = Clojure.`var`(ns, method)

  lazy val requireFn: IFn = fn("clojure.core", "require")

  def require(nss: String): AnyRef = requireFn.invoke(read(nss))


  // Needed to make datomic.api visible to classloader when using Datomic Free
  require("datomic.api")
}
