import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object demo{
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("KafkaSparkWordCountDemo")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._

    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "test-topic")
      .load()

    val messagesDF = df.selectExpr("CAST(value AS STRING)").as[String]

    // Split the lines into words
    val wordsDF = messagesDF
      .flatMap(_.split(" "))

    // Generate running word count
    val wordCountsDF = wordsDF
      .groupBy("value")
      .count()
      .withColumnRenamed("value", "word")
      .orderBy(desc("count"))

    // Start running the query that prints the running counts to the console
    val query = wordCountsDF.writeStream
      .outputMode("complete")
      .format("console")
      .option("truncate", "false")
      .start()

    query.awaitTermination()
  }
}
