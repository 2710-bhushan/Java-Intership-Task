import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RecommendationSystem {

    public static void main(String[] args) {
        try {
            // Load data from a CSV file
            DataModel model = new FileDataModel(new File("data.csv"));

            // Define a user similarity metric (Pearson Correlation)
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

            // Define the neighborhood (Nearest 2 Users)
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

            // Create the recommender
            Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

            // Recommend items for a user (e.g., user 1)
            List<RecommendedItem> recommendations = recommender.recommend(1, 3);

            // Print recommendations
            System.out.println("Recommendations for user 1:");
            for (RecommendedItem recommendation : recommendations) {
                System.out.println("Item ID: " + recommendation.getItemID() + " | Value: " + recommendation.getValue());
            }

            // Evaluate the recommender
            RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
            double score = evaluator.evaluate(recommender, null, model, 0.7, 1.0);
            System.out.println("Recommender evaluation score: " + score);

        } catch (IOException | TasteException e) {
            e.printStackTrace();
        }
    }
}
