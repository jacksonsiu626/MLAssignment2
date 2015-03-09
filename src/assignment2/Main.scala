package assignment2

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics

object Main {

  def main(args: Array[String]): Unit = {
    // We need to use spark-submit command to run this program
    val conf = new SparkConf().setAppName("COMP4434 Assignment 2");
    val sc = new SparkContext(conf);

    
    // File names
    val trainingFile  = "sample.txt"
    val testFile  = "test.txt"
      
    // Load raw data
    val rawData = parseFile(sc, trainingFile)
    
    // Call AssignmentHelper to do all dirty work
    val winner = AssignmentHelper.createAndFindBestModel(rawData)
 
    // Load test data
    val testData = parseFile(sc, testFile)
    
    // Calculate auROC on test data
    val scoreAndLabels = testData.map { point =>
      val score = winner.extendAndPredict(point.features)
      (score, point.label)
    }
	val metrics = new BinaryClassificationMetrics(scoreAndLabels)
	val auROC = metrics.areaUnderROC()
	
	// Print the result
	println("-"*50)
	println("The best hypothesis is: " + winner.tag)
	println("Area under ROC (test set) = " + auROC)
	println("-"*50)

  }
  
  def parseFile (sc: SparkContext, file: String): RDD[LabeledPoint] = {
    val data = sc.textFile(file)
	val parsedData = data.map { line =>
	  val parts = line.split(',')
	  val label = parts(0).toDouble
	  val features = parts(1).split(' ').map(_.toDouble)
	  LabeledPoint(label, Vectors.dense(features))
	}.cache()
    parsedData
  } 

}