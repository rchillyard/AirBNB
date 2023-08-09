import com.phasmidsoftware.parse.{RawTableParser, TableParser}
import com.phasmidsoftware.table.{HeadedTable, RawTable, Table}
import com.phasmidsoftware.util.FP
import com.phasmidsoftware.util.FP.resource
import org.apache.spark.rdd.RDD

import scala.util._
import org.apache.spark.sql.{Dataset, SparkSession}

import scala.Console.println
import scala.io.Source

object AirBNB extends App {

//    val airBNBFile = "/AIRBNB.Listing.csv"
  val airBNBFile = "/airbnb2.csv"
  // Set up the source
  val sy: Try[Source] = for (u <- FP.resourceForClass(airBNBFile)) yield Source.fromURL(u)
  private val parser1: RawTableParser = new RawTableParser()
  val parser = parser1.setMultiline(true)

  val wsty: Try[RawTable] = parser parse sy
//    Table.parseResourceRaw(airBNBFile)
    wsty match {
      case Success(HeadedTable(r, h)) =>
        println(s"AirBNB: successfully read ${r.size} rows")
        println(s"AirBNB: successfully read ${h.size} columns")

        val session = SparkSession.builder()
          .master("local")
          .appName("AirBNB")
          .getOrCreate()

        val rdd: RDD[Seq[String]] = session.sparkContext.makeRDD(r.toStream)
        rdd.map(ws => ws.mkString(",")).foreach(println)

      case _ =>
    }
}
