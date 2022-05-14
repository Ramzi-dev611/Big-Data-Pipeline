package tn.model.training;
import org.apache.spark.ml.classification.DecisionTreeClassificationModel;
import org.apache.spark.ml.classification.DecisionTreeClassifier;
import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.classification.LogisticRegressionModel;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.io.IOException;
import java.util.List;


public class Job {
    public static void main(String[] args) {
        try{ run(); } catch (IOException e) { }
    }
    public static void run() throws IOException {
        // opening spark session
        SparkSession session = SparkSession.builder()
                .appName("model training")
                .getOrCreate();

        // defining the dataset's schema
        StructField[] schemaDefinition = new StructField[] {
                new StructField("label", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("Account Balance", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("Duration of Credit (month)", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("Payment Status of Previous Credit", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("Purpose", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("Credit Amount", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("Value Savings/Stocks", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("Length of current employment", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("Instalment per cent", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("Sex & Marital Status", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("Guarantors", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("Duration in Current address", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("Most valuable available asset", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("Age (years)", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("Concurrent Credits", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("Type of apartment", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("No of Credits at this Bank", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("Occupation", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("No of dependents", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("Telephone", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("Foreign Worker", DataTypes.IntegerType, false, Metadata.empty()),
        };

        // reading the dataset
        StructType schema = new StructType(schemaDefinition);
        Dataset<Row> dataframe = session.read()
                .format("csv")
                .option("header", "true")
                .schema(schema)
                .csv("input/index.csv");

        // create features column
        String[] featuresNames = {"Account Balance", "Duration of Credit (month)", "Payment Status of Previous Credit", "Purpose", "Credit Amount", "Value Savings/Stocks", "Length of current employment", "Instalment per cent", "Sex & Marital Status", "Guarantors", "Duration in Current address", "Most valuable available asset", "Age (years)", "Concurrent Credits", "Type of apartment", "No of Credits at this Bank", "Occupation", "No of dependents", "Telephone", "Foreign Worker"};
        VectorAssembler vectorAssembler = new VectorAssembler();
        vectorAssembler.setInputCols(featuresNames);
        vectorAssembler.setOutputCol("features");
        Dataset<Row> transformedDataset = vectorAssembler.transform(dataframe);

        // Splitting the dataset into train in test datasets
        double[] train_split_sizes = {800, 200};
        List<Dataset<Row>> datasetSplit = transformedDataset.randomSplitAsList(train_split_sizes, 1);


        // Logistic regression model
        LogisticRegression lr = new LogisticRegression();
        lr.setMaxIter(10).setRegParam(0.01).setThreshold(0.55);
        LogisticRegressionModel lrm = lr.fit(datasetSplit.get(0));

        // decision tree model
        DecisionTreeClassifier dt = new DecisionTreeClassifier();
        dt.setMaxDepth(20).setMaxBins(10);
        DecisionTreeClassificationModel dtm = dt.fit(datasetSplit.get(0));

        // test and calculate precision for the logistic regression model
        double lrPrecision = 0.0;
        int tp =0;
        int fp =0;
        Dataset<Row> testLrm = lrm.transform(datasetSplit.get(1));
        for(Row row: testLrm.select("label", "prediction").collectAsList()){
            if (row.get(0).toString().equals("1")){
                if(row.get(1).toString().equals("0.0")){
                    fp++;
                }else{
                    tp++;
                }
            }
        }
        lrPrecision = (double)tp/(double)(tp+fp);
        System.out.println(lrPrecision);


        // test and calculate the precision for the decision tree model
        double dtPrecision = 0.0;
        tp =0;
        fp =0;
        Dataset<Row> testDtm = dtm.transform(datasetSplit.get(1));
        for(Row row: testDtm.select("label", "prediction").collectAsList()){
            if (row.get(0).toString().equals("1")){
                if(row.get(1).toString().equals("0.0")){
                    fp++;
                }else{
                    tp++;
                }
            }

        }
        dtPrecision =  (double)tp/(double)(tp+fp);
        System.out.println("precision = "+dtPrecision);


        //  saving the model with the highest precision
        if (lrPrecision >= dtPrecision){
            System.out.println("Logistic regression model was successfully generated");
            lrm.save("decision.model");
        } else {
            System.out.println("decision tree model was successfully generated");
            dtm.save("decision.model");
        }


    }
}
