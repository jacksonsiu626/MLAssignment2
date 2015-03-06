package assignment2

import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import scala.math.pow

object AssignmentHelper {
  def createAndFindBestModel(rawData: RDD[LabeledPoint]): LRWithExtendedFeatures = {
    // Split into training set and test set
    val splits = rawData.randomSplit(Array(0.7, 0.3), seed = System.currentTimeMillis)
    val trainingData = splits(0).cache()
    val cvData = splits(1).cache()
    
    // extend function for H0, which does nothing but pack the values into a dense Vector only
    // given to you as an example to begin with
    def extend0 (inVec: Vector): Vector = {
      val x1 = inVec.toArray(0)
      val x2 = inVec.toArray(1)
      Vectors.dense(x1, x2)
    }
    // ************************************
    // ** Create extend functions for H1~H3
  
    //                                   **
    // ************************************
    
    
    // Create different models
    val model0 = new LRWithExtendedFeatures(extend0, "H0")
    // ************************************
    // ** Create models for H1~H3
  
    //                                   **
    // ************************************
    
    
    // Train the candidates
    model0.extendAndTrain(trainingData)
    // ************************************
    // ** Train models for H1~H3
  
    //                                   **
    // ************************************
    

    // ************************************
    // ** Prepare the candidate array
    // ** Add more candidates as necessary
     val candidates: Array[LRWithExtendedFeatures] = Array(model0)   
    //                                   **
    // ************************************

    // Invoke ModelSelector to find the best candidate using the cross-validation set
    ModelSelector.findBestModel(candidates, cvData)
  }

}