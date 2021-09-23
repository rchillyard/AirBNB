import com.phasmidsoftware.table.{HeadedTable, RawTable, Table}
import org.apache.spark.rdd.RDD

import scala.util._
import org.apache.spark.sql.{Dataset, SparkSession}

object AirBNB extends App {

//    val airBNBFile = "/AIRBNB.Listing.csv"
  val airBNBFile = "/airbnb2.csv"
    val mty: Try[RawTable] = Table.parseResourceRaw(airBNBFile)
    mty match {
      case Success(HeadedTable(r, h)) =>
        println(s"AirBNB: successfully read ${r.size} rows")
        println(s"AirBNB: successfully read ${h.size} columns")

        val session = SparkSession.builder()
          .master("local")
          .appName("AirBNB")
          .getOrCreate()

        val rdd: RDD[Seq[String]] = session.sparkContext.makeRDD(r.toList)
        rdd.map(ws => ws.mkString(",")).foreach (println(_))
    }
}
