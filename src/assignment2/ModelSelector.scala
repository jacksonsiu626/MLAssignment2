package assignment2

import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics


object ModelSelector {
  def findBestModel(candidates: Array[LRWithExtendedFeatures], cvData: RDD[LabeledPoint]): LRWithExtendedFeatures = {
    // Find the best model from the candidates
    var bestModel: LRWithExtendedFeatures = candidates(0)
    var bestAuROC: Double = 0.0
    
    // For each candidate model in the array,
    // evaluate it against the cross-validation data
    // and update the currently best one.
    for(model <- candidates){
      // ************************************
      // ** Fill in the body
      
      
      //                                   **
      // ************************************
    }
    bestModel
  }

}